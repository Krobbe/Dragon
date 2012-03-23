package model.game;

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
	 * Adds a player to the table.
	 * @param p The player that will be added to the list of players
	 * @throws IllegalArgumentException if there are all ready ten players at the table
	 */
	public void addPlayer(Player p) throws IllegalArgumentException {
		if (players.size() < 10) {
			players.add(p);
		} else {
			throw new IllegalArgumentException();
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
	 * Adds a card to the "table cards" 
	 * @param c The card which will be added
	 * @throws IllegalArgumentException if there are all ready five cards on the table 
	 */
	public void addTableCard(Card c) throws IllegalArgumentException {
		if (tableCards.size() < 5) {
			tableCards.add(c);
		} else {
			throw new IllegalArgumentException();
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
	public void makeHandVisible(Player p) {
		p.getHand().setVisible(true); //TODO bŠttre att gšra en metod i Player makeHandVisble fšr att undvika lŒnga rader av metodanrop?
	}
	
	public void distributePot() {
		
	}
	
	/**
	 * A help method that decides how much chips each player will get
	 */
	private void distributeChips() {
		List<Player> winners = round.getWinners();
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
