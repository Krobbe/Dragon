package client.model.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import server.event.Event;
import server.event.EventBus;

import common.model.card.*;
import common.model.game.Round;
import common.model.player.IPlayer;
import common.utilities.*;

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
	private List<ICard> communityCards;
	private List<IPlayer> players;	
	private IPlayer user;	
	private int meIndex;	// The players index in the table's list
	private int maxPlayers;	// The maximum number of players allowed at the table
	private int indexOfCurrentPlayer;
	private int indexOfDealerButton; //TODO: Vill vi se vem som har dealerbutton?
	
	/**
	 * Creates a new Table.
	 */
	
	public Table(IPlayer user, int meIndex, int maxPlayers) {
		
		this(new LinkedList<IPlayer>(), user, meIndex, maxPlayers);
	}
	
	
	public Table(List<IPlayer> players, IPlayer user, int meIndex,
			int maxPlayers) {
		this.players = players;
		this.user = user;
		
		addPlayer(meIndex, user);
		
		this.meIndex = meIndex;
		this.maxPlayers = maxPlayers;
		
		round = new Round();
		communityCards = new LinkedList<ICard>();
		indexOfCurrentPlayer = 0;
		indexOfDealerButton = 0;
	}
	
	/**
	 * Adds a player to the table at the specified index
	 * @param p The player that will be added to the list of players
	 * @param index The index where the player will be seated
	 * @throws IllegalArgumentException if there are already ten players at the table
	 */
	public void addPlayer(int index, IPlayer player) {
		if(index < getPlayers().size()) {
			players.set(index, player);
		}
		
		else {
			players.add(index, player);
		}
	}
	
	/**
	 * Adds the players in the array to the table.
	 * @param playerArray The players that will be added to the list of players
	 */
	public void addPlayers(List<IPlayer> playerArray) {
			players.addAll(playerArray);
	}
	
	public void removePlayer(IPlayer player) {
		getPlayers().set(getPlayers().indexOf(player), null);
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
	}
	
	/**
	 * This method finds and returns the index of the next active player,
	 * counted after the currentPlayerIndex, which is a parameter provided to
	 * the method by the caller.
	 * 
	 * @param currentPlayerIndex
	 * @return The index of the next player.
	 */
	public int findIndexOfNextActivePlayer(int currentPlayerIndex) {
		int returnIndex = -1;
		int count = 1;

		/* if none is active at the table, do nothing */
		if (getActivePlayers().size() == 0) {
			//TODO: ta bort kontrollutskrift
			return getIndexOfCurrentPlayer();
		}
		
		do {
			returnIndex = (currentPlayerIndex + count) % getPlayers().size();
			count++;
		} while (!getPlayers().get(returnIndex).isActive());
		return returnIndex;
	}
	
	/**
	 * Increases the dealer button index to the next player still in the game
	 * 
	 * @return the next dealer button index.
	 * @author robinandersson
	 * @author mattiashenriksson
	 */
	public int nextDealerButtonIndex() {
		indexOfDealerButton = findIndexOfNextActivePlayer(indexOfDealerButton);

		// TODO Determine what happens if a player has lost recently.
		// If the dealer button only should be set to players still in the game
		// or if lost players should be "ghosts"
		return indexOfDealerButton;
	}
	
	/**
	 * Returns the user object representing the clients player
	 * @return The user object representing the clients player
	 */
	public IPlayer getUser() {
		return user;
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
	 * Adds a card to the "table cards"
	 * 
	 * @param c
	 *            The card which will be added
	 * @throws CommunityCardsFullException
	 *             if there are all ready five cards on the table
	 */
	public void addCommunityCard(ICard c) {
		if (communityCards.size() < 5) {
			communityCards.add(c);
		} else {
			throw new CommunityCardsFullException();
		}
	}
	
	/**
	 * Clears all community from the table.
	 */
	public void clearCommunityCards() {
		communityCards.clear();
	}
	
	/**
	 * 
	 * @return The current round
	 */
	public Round getRound() {
		return round;
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
			if (p != null && p.isActive()) {
				activePlayers.add(p);
			}
		}
		return activePlayers;
	}
	
	/**
	 * This method is used only for testing of the class.
	 * @return The community cards represented as a list of cards.
	 */
	public List<ICard> getCommunityCards() {
		return communityCards;
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
			if(p == null) {
				result.append("null" + "\n");
			}
			
			else {
				result.append(p.toString() + "\n");
			}
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
	 * Equals method for the Table class
	 * @author forssenm
	 * @param Object to compare with
	 * @return returns true if they are the same object otherwise false
	 */
	@Override
	public boolean equals(Object o) {
		return (o == this);
	}
	
	// Since we at the current state aren't planning on using any hashtables this
	// code was added for the cause of good practice
	public int hashCode() {
		  assert false : "hashCode not designed";
		  return 42; // any arbitrary constant will do
	}

	/**
	 * 
	 * @return the index of the client.
	 */
	public int getMeIndex() {
		return meIndex;
	}
	
	/**
	 * Checks which buttons that are supposed to be disabled.
	 * @return A list of strings with the names of the buttons that are supposed
	 * to be disabled.
	 */
	public List<String> getLegalButtons() {
		List<String> legalButtons = new LinkedList<String>();
		
		/* if it's not this client's turn, he should not be able to push any
		 * button. */
		if (getCurrentPlayer() != players.get(meIndex)) {
			return legalButtons;
		}
		
		if(isCheckLegal()) { legalButtons.add("check"); }
		if(isCallLegal()) {	legalButtons.add("call"); }
		legalButtons.add("fold");
		legalButtons.add("raise");
		
		return legalButtons;
	}
	
	/**
	 * Checks if a the current player is allowed to perform a check.
	 * 
	 * @return A boolean telling if the check is legal
	 */
	private boolean isCheckLegal() {
		return !(getCurrentPlayer().getOwnCurrentBet() < getRound()
				.getBettingRound().getCurrentBet().getValue());
	}

	/**
	 * Checks if a the current player is allowed to perform a call.
	 * @return A boolean telling if the call is legal
	 */
	private boolean isCallLegal() {
		return !(getRound().getBettingRound().getCurrentBet().getValue() <= 0);
	}


	public int getMaxPlayers() {
		return maxPlayers;
	}
}
