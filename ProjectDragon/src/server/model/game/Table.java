package server.model.game;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import common.model.card.ICard;
import common.model.game.IDealer;
import common.model.game.Round;
import common.model.game.SidePotHandler;
import common.model.game.TexasHoldemDealer;
import common.model.player.Bet;
import common.model.player.IPlayer;
import common.model.player.hand.FullTHHand;
import common.model.player.hand.HandValue;
import common.model.player.hand.HandValueType;
import common.model.player.hand.IHand;
import common.utilities.CommunityCardsFullException;
import common.utilities.PlayersFullException;


import server.event.Event;
import server.event.EventBus;

/**
 * A class that represent a table at which a poker game takes place. This class
 * has a central role in Project Dragon. It has access to all the other classes
 * in the application and is the class through which the game is controlled.
 * 
 * @author mattiashenriksson
 * @author lisastenberg
 * @author robinandersson
 * 
 */

public class Table {
	
	private Round round;
	private IDealer dealer;
	private List<ICard> communityCards;
	private List<IPlayer> players;
	private boolean showdownDone;
	private int indexOfCurrentPlayer;
	private int indexOfDealerButton;
	private int maxPlayers;
	private int entranceFee;
	private int startingChips;
	private Map<IPlayer, HandValueType> handTypes = new TreeMap<IPlayer, HandValueType>();
	private List<SidePotHandler> sidePots = new LinkedList<SidePotHandler>();

	/**
	 * Creates a new Table.
	 */
	public Table(int maxPlayers, int entranceFee, int startingChips) {
		this(new LinkedList<IPlayer>(), maxPlayers, entranceFee, startingChips);
	}

	public Table(List<IPlayer> players, int maxPlayers, int entranceFee,
														int startingChips) {
		
		this.players = players;		
		this.maxPlayers = maxPlayers;
		this.entranceFee = entranceFee;
		this.startingChips = startingChips;
		
		round = new Round();
		dealer = new TexasHoldemDealer();
		communityCards = new LinkedList<ICard>();

		indexOfCurrentPlayer = 0;
		indexOfDealerButton = 0;
	}

	/**
	 * Adds a player to the table.
	 * 
	 * @param p
	 *            The player that will be added to the list of players.
	 * @throws PlayersFullException if there already is 10 players at the table.
	 *        
	 */
	public void addPlayer(IPlayer player) throws PlayersFullException {
		System.out.println("Start addPlayer");
		System.out.println("");
		for(int i = 0; i < players.size(); i++) {
			System.out.println("For loop");
			IPlayer playerSeat = players.get(i);
			System.out.println(playerSeat);
			if(playerSeat == null) {
				System.out.println("If-sats 1");
				players.set(i, player);
				
				return;
			}
		}
		
		if(players.size() < maxPlayers) {
			System.out.println("If-sats 2");
			players.add(player);
		} else {
			throw new PlayersFullException();
		}
		System.out.println(players);
		System.out.println("");
		System.out.println("End addplayer");
	}

	/**
	 * Adds the players in the array to the table.
	 * 
	 * @param playerArray
	 *            The players that will be added to the list of players
	 */
	public void addPlayers(Collection<IPlayer> playerArray) {
		for (IPlayer player : playerArray) {
			addPlayer(player);
		}
	}

	/**
	 * Adds a card to the community cards.
	 * 
	 * @throws CommunityCardsFullException
	 *             if there are already five cards on the table.
	 */
	public void addCommunityCard() {
		if (communityCards.size() < 5) {
			ICard card = dealer.popCard();
			communityCards.add(card);
			EventBus.publish(new Event(Event.Tag.SERVER_ADD_TABLE_CARD, card));
		} else {
			throw new CommunityCardsFullException();
		}
	}

	/**
	 * Clears all "table cards" from the table.
	 */
	public void clearCommunityCards() {
		communityCards.clear();
	}

	/**
	 * @author Mattias Henriksson
	 * @author lisastenberg
	 * 
	 *         Calculates the amount of chips the winner(s) will get and
	 *         distributes it to him. After the pot is distributed equally among
	 *         the winner(s), the pot is emptied.
	 */
	public void distributePot(List<IPlayer> winners, int potAmount) {
		// This assumes that the pot can be distributed equally.
		// TODO: How to do?
		int winnerAmount = potAmount / winners.size();

		for (IPlayer p : winners) {
			p.getBalance().addToBalance(winnerAmount);
			EventBus.publish(new Event(Event.Tag.SERVER_DISTRIBUTE_POT,
					new Bet(p, winnerAmount)));
		}
	}

	/**
	 * Distributes the two "personal cards" to all remaining players in the
	 * round
	 * 
	 * @author robinandersson
	 */
	public void distributeCards() {

		/*
		 * Prepares the list of players to simplify the distribution of cards.
		 * The list of players is ordered so that the first player in the list
		 * gets the first card (this is the player directly after the dealer
		 * button)
		 */
		for (int i = 0; i <= getDealerButtonIndex(); i++) {
			players.add(players.remove(0));
		}

		/*
		 * Every (active) player gets two cards where the first is distributed
		 * directly, and the second after everyone else has gotten their first
		 * card
		 */
		for (int i = 0; i < 2; i++) {
			for (IPlayer player : getActivePlayers()) {
				player.getHand().addCard(dealer.popCard());
			}
		}

		// Restores the list to the previous state before it was prepared
		for (int i = 0; i <= getDealerButtonIndex(); i++) {
			players.add(0, players.remove(players.size() - 1));
		}

		Map<IPlayer, IHand> playerHands = new TreeMap<IPlayer, IHand>();
		for (IPlayer p : getActivePlayers()) {
			playerHands.put(p, p.getHand());
		}
		EventBus.publish(new Event(Event.Tag.SERVER_DISTRIBUTE_CARDS,
				playerHands));
	}

	/**
	 * @author Oscar Stigter
	 * @author lisastenberg
	 * @author mattiashenriksson
	 * 
	 *         Performs the Showdown.
	 */
	public void doShowdown(List<IPlayer> plrs, int potAmount) {
		// Look at each hand value (calculated in HandEvaluator), sorted from
		// highest to lowest.
		Map<HandValue, List<IPlayer>> rankedPlayers = getRankedPlayers(plrs);

		for (HandValue handValue : rankedPlayers.keySet()) {
			// Get players with winning hand value.
			List<IPlayer> winners = rankedPlayers.get(handValue);
			distributePot(winners, potAmount);

			setShowdownDone(true);

			/* utskrift f�r kontroll */
			System.out.println("\n\n-------------------------------\n"
					+ "SHOWDOWN RESULT:\n");
			for (IPlayer p : winners) {
				System.out.println("\nWinner: " + p.getName());
				HandValueType hvt = getHandTypes().get(p);
				System.out.print(hvt);
				System.out.println(getHandTypes().toString());
			}
			System.out.println("potamount: " + potAmount);
			System.out.println("Players:");
			for (IPlayer p : plrs) {
				System.out.println(p.getName());
			}
			System.out.println("\n-----------------------------------\n");

			// TODO: riktigt ful l�sning. borde g�ras b�ttre. ingen loop beh�vs
			// utan inneh�llet borde bara g�ras f�r f�rsta v�rdet i
			// rankedPlayers
			break;
		}
	}

	/**
	 * This method finds and returns the index of the next active player,
	 * counted after the currentPlayerIndex, which is a parameter provided to
	 * the method by the caller.
	 * 
	 * @param currentPlayerIndex
	 * @return The index of the next player.
	 */
	// TODO: denna kanske kan anv�ndas p� fler st�llen..
	public int findIndexOfNextActivePlayer(int currentPlayerIndex) {
		int returnIndex = -1;
		int count = 1;

		/* if none is active at the table, do nothing */
		if (getActivePlayers().size() == 0) {
			//TODO: ta bort kontrollutskrift
			System.out.println("---------------- none is active-----------------");
			return getIndexOfCurrentPlayer();
		}
		
		do {
			returnIndex = (currentPlayerIndex + count) % getPlayers().size();
			count++;
		} while (!getPlayers().get(returnIndex).isActive());
		return returnIndex;
	}
	
	/**
	 * @author Oscar Stigter
	 * @author lisastenberg
	 * @author mattiashenriksson Returns the active players mapped and sorted by
	 *         their hand value.
	 * 
	 *         The players are sorted in descending order (highest value first).
	 * 
	 * @return The active players mapped by their hand value (sorted).
	 */
	private Map<HandValue, List<IPlayer>> getRankedPlayers(List<IPlayer> plrs) {
		Map<HandValue, List<IPlayer>> winners = new TreeMap<HandValue, List<IPlayer>>();
		for (IPlayer player : plrs) {
			// Create a hand with the community cards and the player's hole
			// cards.
			FullTHHand hand = new FullTHHand(communityCards);
			hand.addCards(player.getHand());

			// Store the player together with other players with the same
			// hand value.
			HandValue handValue = new HandValue(hand);

			// Store the player with its handvaluetype for later purpose.
			handTypes.put(player, handValue.getType());

			List<IPlayer> playerList = winners.get(handValue);
			if (playerList == null) {
				playerList = new LinkedList<IPlayer>();
			}
			playerList.add(player);
			winners.put(handValue, playerList);

			hand.discard();
		}
		return winners;
	}
	
	/**
	 * 
	 * @return The player who's turn it currently is to bet, fold, raise or
	 *         check
	 */
	public IPlayer getCurrentPlayer() {
		return players.get(indexOfCurrentPlayer);
	}

	/**
	 * 
	 * @return The player who's turn it currently is
	 */
	public int getDealerButtonIndex() {
		return indexOfDealerButton;
	}

	/**
	 * @return a list containing the players who has currently gone all-in
	 */
	public List<IPlayer> getAllInPlayers() {
		List<IPlayer> allInPlayers = new LinkedList<IPlayer>();

		for (IPlayer ap : getActivePlayers()) {
			if (ap.isAllIn()) {
				allInPlayers.add(ap);
			}
		}
		return allInPlayers;
	}
	
	/**
	 * Returns the maximum number of allowed players in the table
	 * @return the maximum number of allowed players
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	/**
	 * Returns the entrance fee to join the table
	 * @return the cost of joining the game at the table
	 */
	public int getEntranceFee() {
		return entranceFee;
	}
	
	/**
	 * Returns the amount of chips players get at the start of the game
	 * @return The amount of chips players get at the start of the game
	 */
	public int getStartingChips() {
		return startingChips;
	}

	/**
	 * 
	 * @return The current round
	 */
	public Round getRound() {
		return round;
	}

	/**
	 * 
	 * @return The table's dealer
	 */
	public IDealer getDealer() {
		return dealer;
	}

	/**
	 * This method is used only for testing of the class.
	 * 
	 * @return A list of players at the table.
	 */
	public List<IPlayer> getPlayers() {
		return players;
	}

	/**
	 * 
	 * @return A list of the players at the table who are currently active
	 */
	public List<IPlayer> getActivePlayers() {
		List<IPlayer> activePlayers = new LinkedList<IPlayer>();
		for (IPlayer p : players) {
			if (p.isActive()) {
				activePlayers.add(p);
			}
		}
		return activePlayers;
	}

	/**
	 * 
	 * @return The community cards represented as a list of cards.
	 */
	public List<ICard> getCommunityCards() {
		return communityCards;
	}

	/**
	 * This method is only used when the showDown is done and we want to show
	 * the winners handtype(s).
	 * 
	 * @return A map containing a player with the type of his hand.
	 */
	public Map<IPlayer, HandValueType> getHandTypes() {
		return handTypes;
	}

	/**
	 * 
	 * @return sidePots Possible "side pots" a table might contain.
	 */
	public List<SidePotHandler> getSidePots() {
		return sidePots;
	}
	
	/**
	 * 
	 * @return The players list-index of the current player
	 */
	public int getIndexOfCurrentPlayer() {
		return indexOfCurrentPlayer;
	}
	
	/**
	 * 
	 * @return a list of the players who won the last round
	 */
	public boolean isShowdownDone() {
		return showdownDone;
	}

	/**
	 * This method checks if the players has all done their bets properly and
	 * the betting for the current round is therefore done.
	 * 
	 * @return a boolean telling whether betting for the current round is done.
	 */
	public boolean isBettingDone() {
		List<IPlayer> activePlayers = getActivePlayers();

		for (IPlayer ap : activePlayers) {
			if (!ap.hasDoneFirstTurn()) {
				return false;
			}

			/*
			 * if all players hasn't posted the same bet the betting isn't done,
			 * unless the players who hasn't done this is all-in
			 */
			if (ap.getOwnCurrentBet() != activePlayers.get(0)
					.getOwnCurrentBet() && !ap.isAllIn()) {
				return false;
			}
		}
		return true;
	}

	
	/**
	 * Set the turn to the next player in order, and returns that player.
	 * 
	 * @return the next (active) player
	 * @author lisastenberg
	 */
	public IPlayer nextPlayer() {

		indexOfCurrentPlayer = findIndexOfNextActivePlayer(indexOfCurrentPlayer);
		EventBus.publish(new Event(Event.Tag.SERVER_NEXT_TURN,
				getCurrentPlayer()));
		return players.get(indexOfCurrentPlayer);

		// TODO: gammal kod, ta bort om nya funkar
		/*
		 * indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % players.size();
		 * 
		 * if (getCurrentPlayer().isActive()) { EventBus.publish(new
		 * Event(Event.Tag.SERVER_NEXT_TURN, getCurrentPlayer())); return
		 * getCurrentPlayer(); } return nextPlayer();
		 */
	}

	/**
	 * Increases the dealer button index to the next player still in the game
	 * 
	 * @return the next dealer button index.
	 * @author robinandersson
	 * @author mattiashenriksson
	 */
	// TODO Test nextDealerButtonPlayer()
	// TODO Discuss and implement a possible better solution to dealer button
	public int nextDealerButtonIndex() {
		indexOfDealerButton = findIndexOfNextActivePlayer(indexOfDealerButton);

		// TODO: gammal kod, ta bort om nya funkar
		/*
		 * do { indexOfDealerButton = (indexOfDealerButton + 1) %
		 * players.size(); } while
		 * (!players.get(indexOfDealerButton).isActive());
		 */

		// TODO Determine what happens if a player has lost recently.
		// If the dealer button only should be set to players still in the game
		// or if lost players should be "ghosts"

		// The dealer button is set to a player that is still in the game.
		/*
		 * while(!players.get(indexOfDealerButton).isStillInGame()){
		 * indexOfDealerButton++; return nextDealerButtonIndex()? }
		 */
		return indexOfDealerButton;
	}

	/**
	 * This method recieves a bet and placese it om the table.
	 * 
	 * @param bet
	 *            The bet that should be placed on the table.
	 */
	public void recieveBet(Bet bet) {
		getRound().getPot().addToPot(bet.getValue());

		Bet currentBet = getRound().getBettingRound().getCurrentBet();
		if (bet.getValue() >= currentBet.getValue()) {
			getRound().getBettingRound().setCurrentBet(bet);
		}
	}
	
	/**
	 * 
	 * @param index
	 *            The index indexOfCurrentPlayer should be set to.
	 */
	public void setIndexOfCurrentPlayer(int index) {
		indexOfCurrentPlayer = index;
	}
	
	/**
	 * Sets the list of players who won the last round
	 * 
	 * @param winners
	 */
	public void setShowdownDone(boolean showdownDone) {
		this.showdownDone = showdownDone;
	}

	/**
	 * Tostring method for the Table class
	 * 
	 * @author Mattias Forssen
	 * @author mattiashenriksson
	 * @return Returns a string containing the names of all players, cards, who
	 *         the current player is and what cards are shown.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Players at table:\n");
		for (IPlayer p : this.players) {
			result.append(p.toString() + "\n");
		}
		result.append("\n" + "Current player is "
				+ getCurrentPlayer().getName() + "\n");
		result.append("Player with Dealer button is: "
				+ (players.get(getDealerButtonIndex())).getName() + "\n");
		result.append("Table cards are:" + "\n" + communityCards.toString()
				+ "\n");
		result.append("Pot is: " + round.getPot().getValue() + "\n");
		result.append("Pre-betting pot is: "
				+ round.getPreBettingPot().getValue() + "\n");
		result.append("Current bet is: "
				+ round.getBettingRound().getCurrentBet().getValue() + "\n");

		return result.toString();
	}

	/**
	 * Equals method for the Table class
	 * 
	 * @author forssenm
	 * @param Object
	 *            to compare with
	 * @return returns true if they are the same object otherwise false
	 */
	@Override
	public boolean equals(Object o) {
		return (o == this);
	}

	// Since we at the current state aren't planning on using any hashtables
	// this code was added
	// for the cause of good practice
	public int hashCode() {
		assert false : "hashCode not designed";
		return 42; // any arbitrary constant will do
	}

}
