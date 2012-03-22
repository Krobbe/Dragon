package Game;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represent table at which a poker game takes place. This class has a central
 * roll in Dragon. It has access to all the other classes in the application and is the 
 * class through which the game is controlled.
 * 
 * @author Mattias Henriksson
 * 
 */

public class Table {
	Round round;
	Dealer dealer;
	List<Card> tableCards;
	List<Player> players;
	int indexOfCurrentPlayer;
	
	/**
	 * Creates a new Table.
	 */
	public Table(Round round, Dealer dealer) {
		this.round = round;
		this.dealer = dealer;
		tableCards = new ArrayList<Card>();
		players = new ArrayList<Player>();
		indexOfCurrentPlayer = 0;
	}
	
	/**
	 * Adds a player to the table (if there aren't already 10 players at the table)
	 * @param p The player that will be added to the list of players
	 */
	public void addPlayer(Player p) {
		if (players.size() < 10) {
			players.add(p);
		} else {
			//TODO kasta nŒt exception?
		}
	}
	
	/**
	 * Sets the turn to the next player in order.
	 */
	public void nextPlayer() {
		if (indexOfCurrentPlayer < players.size() - 1) {
			indexOfCurrentPlayer++;
		} else {
			indexOfCurrentPlayer = 0;
		}
	}
	
	/**
	 * 
	 * @return The player who's turn it currently is
	 */
	public Player getCurrentPlayer() {
		return players.get(indexOfCurrentPlayer);
	}
	
	/**
	 * Adds a card to the "table cards" (if there aren't already five cards)
	 * @param c The card which will be added
	 */
	public void addTableCard(Card c) {
		if (tableCards.size() < 5) {
			tableCards.add(c);
		} else {
			//TODO kasta nŒt exeption?
		}
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
}
