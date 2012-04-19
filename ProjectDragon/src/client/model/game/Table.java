package client.model.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import client.model.player.iPlayer;
import client.model.player.hand.FullTHHand;
import client.model.player.hand.HandValue;
import client.model.player.hand.HandValueType;
import utilities.PlayersFullException;
import utilities.TableCardsFullException;

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
	private Dealer dealer;
	private List<Card> tableCards;
	private List<iPlayer> players;
	private int indexOfCurrentPlayer;
	private int indexOfDealerButton;
	private Map<iPlayer, HandValueType> handTypes = 
			new TreeMap<iPlayer, HandValueType>();
	
	/**
	 * Creates a new Table.
	 */
	public Table() {
		round = new Round();
		dealer = new Dealer();
		tableCards = new ArrayList<Card>();
		players = new ArrayList<iPlayer>();
		indexOfCurrentPlayer = 0;
		indexOfDealerButton = 0;
	}
	
	/**
	 * Adds a player to the table.
	 * @param p The player that will be added to the list of players
	 * @throws IllegalArgumentException if there are all ready ten players at the table
	 */
	public void addPlayer(iPlayer p) throws PlayersFullException {
		if (players.size() < 10) {
			players.add(p);
		} else {
			throw new PlayersFullException();
		}
	}
	
	/**
	 * Sets the turn to the next player in order.
	 */
	//TODO mod-l�sning snyggare?, otestat
	public void nextPlayer() {
		if (indexOfCurrentPlayer < players.size() - 1) {
			indexOfCurrentPlayer++;
		} else {
			indexOfCurrentPlayer = 0;
		}
		if (!getCurrentPlayer().isActive()) {
			nextPlayer();
		}
	}
	
	/**
	 * Increases the dealer button index to the next player still in the game
	 * 
	 * @author robinandersson
	 * @author mattiashenriksson
	 */
	//TODO Test nextDealerButtonPlayer()
	//TODO Discuss and implement a possible better solution to dealer button
	public void nextDealerButtonIndex() {

		indexOfDealerButton++;
		// Reset the index if it is at the end of the player-list
		if (indexOfDealerButton == players.size()) {
			indexOfDealerButton = 0;
		}
		
		// TODO Determine what happens if a player has lost recently.
		// If the dealer button only should be set to players still in the game
		// or if lost players should be "ghosts"
		
		// The dealer button is set to a player that is still in the game.
		while(!players.get(indexOfDealerButton).isStillInGame()){
			indexOfDealerButton++;
		}
	}
	
	/**
	 * 
	 * @return The player who's turn it currently is to bet, fold, raise or check
	 */
	public iPlayer getCurrentPlayer() {
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
	 * @param c The card which will be added
	 * @throws IllegalArgumentException if there are all ready five cards on the table 
	 */
	public void addTableCard(Card c) throws TableCardsFullException {
		if (tableCards.size() < 5) {
			tableCards.add(c);
		} else {
			throw new TableCardsFullException();
		}
	}
	
	/**
	 * Clears all "table cards" from the table.
	 */
	public void clearTableCards() {
		tableCards.clear();
	}
	
	/**
	 * Makes a players cards visible
	 * @param p The player which cards will be set visible
	 */
	public void makeHandVisible(iPlayer p) {
		p.getHand().setVisible(true);
	}
	
	/**
	 * @author Mattias Henriksson
	 * @author lisastenberg
	 * 
	 * Calculates the amount of chips the winner(s) will get and distributes it to him.
	 * After the pot is distributed equally among the winner(s), the pot is emptied. 
	 */
	public void distributePot(List<iPlayer> winners) {
		// This assumes that the pot can be distributed equally.
		// TODO: How to do?
		int winnerAmount = round.getPot().getValue() / winners.size();
		
		for (iPlayer p: winners) {
			p.getBalance().addToBalance(winnerAmount);
		}
		round.getPot().emptyPot();
	}
	
	// TODO Write test for distribute cards-method
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
		for (int i = 1; i <= 2; i++) {

			for (iPlayer player : players) {

				if (player.isActive()) {
					player.addCard(dealer.popCard());
				}
			}
		}

		// Restores the list to the previous state before it was prepared
		for(int i = 0 ; i <= getDealerButtonIndex() ; i++){
			players.add(0, players.remove(players.size() -1));
		}
	}
	
    /**
     * @author Oscar Stigter
     * @author lisastenberg
     * 
     * Performs the Showdown.
     */
    public List<iPlayer> doShowdown() {
        // Look at each hand value (calculated in HandEvaluator), sorted from highest to lowest.
        Map<HandValue, List<iPlayer>> rankedPlayers = getRankedPlayers();
        for (HandValue handValue : rankedPlayers.keySet()) {
            // Get players with winning hand value.
            List<iPlayer> winners = rankedPlayers.get(handValue);
            distributePot(winners);
            return winners;
        }
        // No person is the winner. This should never happen.
        return null;
    }
    
    /**
     * @author Oscar Stigter
     * @author lisastenberg
     * Returns the active players mapped and sorted by their hand value.
     * 
     * The players are sorted in descending order (highest value first).
     * 
     * @return The active players mapped by their hand value (sorted). 
     */
    private Map<HandValue, List<iPlayer>> getRankedPlayers() {
        Map<HandValue, List<iPlayer>> winners = 
        		new TreeMap<HandValue, List<iPlayer>>();
		for (iPlayer player : players) {
			if (player.isActive()) {
				// Create a hand with the community cards and the player's hole
				// cards.
				FullTHHand hand = new FullTHHand(tableCards);
				hand.addCards(player.getHand());
				
				// Store the player together with other players with the same
				// hand value.
				HandValue handValue = new HandValue(hand);
				
				// Store the player with its handvaluetype for later purpose.
				handTypes.put(player, handValue.getType());
				
				List<iPlayer> playerList = winners.get(handValue);
				if (playerList == null) {
					playerList = new LinkedList<iPlayer>();
				}
				playerList.add(player);
				winners.put(handValue, playerList);
				
				hand.discard();
			}
		}
        return winners;
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
	public Dealer getDealer() {
		return dealer;
	}
	
	/**
	 * This method is used only for testing of the class.
	 * @return A list of players at the table.
	 */
	public List<iPlayer> getPlayers() {
		return players;
	}
	
	/**
	 * 
	 * @return A list of the players at the table who are currently active
	 */
	public List<iPlayer> getActivePlayers() {
		List<iPlayer> activePlayers = new ArrayList<iPlayer>();
		for (iPlayer p : players) {
			if (p.isActive()) {
				activePlayers.add(p);
			}
		}
		return activePlayers;
	}
	
	/**
	 * This method is used only for testing of the class.
	 * @return The "table cards" represented as a list of cards.
	 */
	public List<Card> getTableCards() {
		return tableCards;
	}
	
	/**
	 * This method is only used when the showDown is done and you want we want
	 * to show the winners handtype.
	 * @return A map containing a player with the type of his hand.
	 */
	public Map<iPlayer, HandValueType> getHandTypes() {
		return handTypes;
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
		result.append("Players at table: " + "\n");
		for(iPlayer p : this.players) {
			result.append(p.toString() + "\n");
		}
		result.append("\n" + "Current player is " + getCurrentPlayer().getName() + "\n");
		result.append("Player with Dealer button is: " + 
				(players.get(getDealerButtonIndex())).getName() + "\n");
		result.append("Table cards are:" + "\n" + tableCards.toString() + "\n");
		result.append("Pot is: " + round.getPot().getValue() + "\n");
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
	 * @return The players-list index of the current player
	 */
	public int getIndexOfCurrentPlayer() {
		return indexOfCurrentPlayer;
	}
	
	/**
	 * 
	 * @return The amount of players at the table that are active (that 
	 * 			hasn't folded)
	 */
	public int getNumberOfActivePlayers() {
		int activePlayers = 0;
		for (iPlayer p : players) {
			if (p.isActive()) {
				activePlayers++;
			}
		}
		return activePlayers;
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