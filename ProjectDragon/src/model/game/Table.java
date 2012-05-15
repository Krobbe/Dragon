package model.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.card.ICard;
import model.player.Bet;
import model.player.IPlayer;
import model.player.hand.FullTHHand;
import model.player.hand.HandValue;
import model.player.hand.HandValueType;
import model.player.hand.IHand;
import utilities.CommunityCardsFullException;
import event.Event;
import event.EventBus;

/**
 * A class that represent table at which a poker game takes place. This class 
 * has a central role in Dragon. It has access to all the other classes in the 
 * application and is the class through which the game is controlled.
 * 
 * @author Mattias Henriksson
 * @author lisastenberg
 * @author robinandersson
 * 
 */

public class Table {
	private Round round;
	private TexasHoldemDealer dealer;
	private List<ICard> communityCards;
	private List<IPlayer> players;

	private boolean showdownDone;
	private int indexOfCurrentPlayer;
	private int indexOfDealerButton;
	private Map<IPlayer, HandValueType> handTypes = 
			new TreeMap<IPlayer, HandValueType>();
	private List<SidePotHandler> sidePots = new ArrayList<SidePotHandler>();
	
	/**
	 * Creates a new Table.
	 */
	public Table() {
		this(null);
	}
	
	public Table(Collection<IPlayer> players) {
		round = new Round();
		dealer = new TexasHoldemDealer();
		communityCards = new ArrayList<ICard>();
		this.players = new ArrayList<IPlayer>(players);

		indexOfCurrentPlayer = 0;
		indexOfDealerButton = 0;
		
	}
	
	/**
	 * Adds a player to the table.
	 * @param p The player that will be added to the list of players
	 */
	public void addPlayer(IPlayer player) {
			players.add(player);
	}
	
	/**
	 * Adds the players in the array to the table.
	 * @param playerArray The players that will be added to the list of players
	 */
	public void addPlayers(Collection<IPlayer> playerArray) {
		for(IPlayer player : playerArray){
			addPlayer(player);

		}
	}
	
	/**
	 * Set the turn to the next player in order, and returns that player.
	 * 
	 * @return the next (active) player
	 * @author lisastenberg
	 */
	public IPlayer nextPlayer() {
		
		/* if none is active at the table, do nothing */
		if (getActivePlayers().size() == 0) {
			return getCurrentPlayer();
		}
		
		indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % players.size();

		if (getCurrentPlayer().isActive()) {
			EventBus.publish(new Event(Event.Tag.SERVER_NEXT_TURN, getCurrentPlayer()));
			return getCurrentPlayer();
		}
		return nextPlayer();
	}
	
	/**
	 * Increases the dealer button index to the next player still in the game
	 * 
	 * @return the next dealer button index. 
	 * @author robinandersson
	 * @author mattiashenriksson
	 */
	//TODO annat namn på denna?
	//TODO Test nextDealerButtonPlayer()
	//TODO Discuss and implement a possible better solution to dealer button
	public int nextDealerButtonIndex() {
		do {
			indexOfDealerButton++;
			// Reset the index if it is at the end of the player-list
			if (indexOfDealerButton == players.size()) {
				indexOfDealerButton = 0;
			}
		} while (!players.get(indexOfDealerButton).isActive());
		
		// TODO Determine what happens if a player has lost recently.
		// If the dealer button only should be set to players still in the game
		// or if lost players should be "ghosts"
		
		// The dealer button is set to a player that is still in the game.
		/*while(!players.get(indexOfDealerButton).isStillInGame()){
			indexOfDealerButton++;
		}*/
		return indexOfDealerButton;
	}
	
	/**
	 * 
	 * @return The player who's turn it currently is to bet, fold, raise or check
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
	 * Adds a card to the community cards
	 * 
	 * @throws CommunityCardsFullException
	 *             if there are allready five cards on the table
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
	 * Makes a players cards visible
	 * @param p The player which cards will be set visible
	 */
	//TODO denna i player ist? mindre beroende?
	public void makeHandVisible(IPlayer p) {
		p.getHand().setVisible(true);
	}
	
	/**
	 * @author Mattias Henriksson
	 * @author lisastenberg
	 * 
	 * Calculates the amount of chips the winner(s) will get and distributes it to him.
	 * After the pot is distributed equally among the winner(s), the pot is emptied. 
	 */
	public void distributePot(List<IPlayer> winners, int potAmount) {
		// This assumes that the pot can be distributed equally.
		// TODO: How to do?
		int winnerAmount = potAmount / winners.size();
		
		for (IPlayer p: winners) {
			p.getBalance().addToBalance(winnerAmount);
			EventBus.publish(new Event(Event.Tag.SERVER_DISTRIBUTE_POT, 
					new Bet(p, winnerAmount)));
		}
		//TODO: Remove money from the pot!
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
		for(int i = 0 ; i <= getDealerButtonIndex() ; i++){
			players.add(players.remove(0));
		}

		/*
		 * Every (active) player gets two cards where the first is distributed
		 * directly, and the second after everyone else has gotten their first
		 * card
		 */
		for (int i = 0; i < 2; i++) {
			for (IPlayer player : getActivePlayers()) {

				player.addCard(dealer.popCard());
			}
		}

		// Restores the list to the previous state before it was prepared
		for(int i = 0 ; i <= getDealerButtonIndex() ; i++){
			players.add(0, players.remove(players.size() -1));
		}
		
		Map<IPlayer, IHand> playerHands = new TreeMap<IPlayer, IHand>();
		for(IPlayer p : getActivePlayers()) {
			playerHands.put(p, p.getHand());
		}
		EventBus.publish(new Event(Event.Tag.SERVER_DISTRIBUTE_CARDS, playerHands));
	}
	
    /**
     * @author Oscar Stigter
     * @author lisastenberg
     * @author mattiashenriksson
     * 
     * Performs the Showdown.
     */
    public void doShowdown(List<IPlayer> plrs, int potAmount) {
        // Look at each hand value (calculated in HandEvaluator), sorted from highest to lowest.
        Map<HandValue, List<IPlayer>> rankedPlayers = getRankedPlayers(plrs);
        for (HandValue handValue : rankedPlayers.keySet()) {
            // Get players with winning hand value.
            List<IPlayer> winners = rankedPlayers.get(handValue);
            distributePot(winners, potAmount);
            
            setShowdownDone(true);
            
            /* utskrift för kontroll */
            System.out.println("\n\n-------------------------------\n" + 
            "SHOWDOWN RESULT:\n");
            for (IPlayer p : winners) {
				System.out.println("\nWinner: " + p.getName());
				HandValueType hvt = getHandTypes().get(p);
				System.out.print(hvt);
				System.out.println(getHandTypes().toString());
			}
            System.out.println("potamount: " + potAmount);
            System.out.println("Players:");
            for (IPlayer p : plrs ) {
            	System.out.println(p.getName());
            }
            System.out.println("\n-----------------------------------\n");
            
        }

    }
    
    /**
     * @author Oscar Stigter
     * @author lisastenberg
     * @author mattiashenriksson
     * Returns the active players mapped and sorted by their hand value.
     * 
     * The players are sorted in descending order (highest value first).
     * 
     * @return The active players mapped by their hand value (sorted). 
     */
    private Map<HandValue, List<IPlayer>> getRankedPlayers(List<IPlayer> plrs) {
        Map<HandValue, List<IPlayer>> winners = 
        		new TreeMap<HandValue, List<IPlayer>>();
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
     * This method checks if the players has all done their bets properly and 
     * the betting for the current round is therefore done
     * @return a boolean telling whether betting for the current round is done.
     */
    //TODO denna här eller i kontrollern?
    public boolean isBettingDone() {
    	boolean bettingDone = true;
		List<IPlayer> activePlayers = getActivePlayers();
		
		for (IPlayer ap: activePlayers) {
			
			/* if all players hasn't posted the same bet the betting isn't done,
			 * unless the players who hasn't done this is all-in */
			if (activePlayers.get(0).getOwnCurrentBet() != ap.getOwnCurrentBet()
					//TODO ändra till player.isAllIn?
					&& ap.getBalance().getValue() != 0) {
					bettingDone = false;
			}
			
			/* if all players hasn't got the chance to make a move betting isn't
			 * done */
			if (!ap.getDoneFirstBet()) {
				bettingDone = false;
			}
		}
		return bettingDone;
    }
    
    /**
     * 
     * @return a list containing the players who has currently gone all-in
     */
    public List<IPlayer> getAllInPlayers() {
    	List<IPlayer> allInPlayers = new ArrayList<IPlayer>();
    	
		for (IPlayer ap : getActivePlayers()) {
			//TODO ändra till player.isAllIn?
			if (ap.getBalance().getValue() == 0) {
				allInPlayers.add(ap);
			}
		}
		return allInPlayers;
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
	public TexasHoldemDealer getDealer() {
		return dealer;
	}
	
	/**
	 * This method is used only for testing of the class.
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
		List<IPlayer> activePlayers = new ArrayList<IPlayer>();
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
	 * This method is only used when the showDown is done and we want
	 * to show the winners handtype(s).
	 * @return A map containing a player with the type of his hand.
	 */
	public Map<IPlayer, HandValueType> getHandTypes() {
		return handTypes;
	}
	
	/**
	 * 
	 * @return Possible "side pots" a table might contain.
	 */
	public List<SidePotHandler> getSidePots() {
		return sidePots;
	}
	
	/**
	 * Tostring method for the Table class
	 * @author Mattias Forssen
	 * @author mattiashenriksson
	 * @return Returns a string containing the names of all players, cards, 
	 * who the current player is and what cards are shown.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Players at table:\n");
		for(IPlayer p : this.players) {
			result.append(p.toString() + "\n");
		}
		result.append("\n" + "Current player is " + getCurrentPlayer().getName() + "\n");
		result.append("Player with Dealer button is: " + 
				(players.get(getDealerButtonIndex())).getName() + "\n");
		result.append("Table cards are:" + "\n" + communityCards.toString() + "\n");
		result.append("Pot is: " + round.getPot().getValue() + "\n");
		result.append("Pre-betting pot is: " + round.getPreBettingPot().getValue() + "\n");
		result.append("Current bet is: " + 
				round.getBettingRound().getCurrentBet().getValue() + "\n");
		
		return result.toString();
	}
	
	/**
	 * 
	 * @param index The index indexOfCurrentPlayer should be set to.
	 */
	public void setIndexOfCurrentPlayer(int index) {
		indexOfCurrentPlayer = index;
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
	 * Sets the list of players who won the last round
	 * @param winners
	 */
	public void setShowdownDone(boolean showdownDone) {
		this.showdownDone = showdownDone;
	}
	
	/**
	 * Equals method for the Table class
	 * @author forssenm
	 * @param Object to compare with
	 * @return returns true if they are the same object otherwise false
	 */
	@Override
	public boolean equals(Object o) {
		return (o == this);
	}
	
	//Since we at the current state aren't planning on using any hashtables this code was added
	//for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}
	
}
