package model.game;

import java.util.ArrayList;
import java.util.List;

import model.player.iPlayer;

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
	List<iPlayer> players;
	int indexOfCurrentPlayer;
	
	/**
	 * Creates a new Table.
	 */
	public Table(Round round, Dealer dealer) {
		this.round = round;
		this.dealer = dealer;
		tableCards = new ArrayList<Card>();
		players = new ArrayList<iPlayer>();
		indexOfCurrentPlayer = 0;
	}
	
	/**
	 * Adds a player to the table.
	 * @param p The player that will be added to the list of players
	 * @throws IllegalArgumentException if there are all ready ten players at the table
	 */
	public void addPlayer(iPlayer p) throws IllegalArgumentException {
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
	public iPlayer getCurrentPlayer() {
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
	public void makeHandVisible(iPlayer p) {
		p.getHand().setVisible(true); //TODO b�ttre att g�ra en metod i Player makeHandVisble f�r att undvika l�nga rader av metodanrop?
	}
	
	public void distributePot() {
		List<iPlayer> winners = round.getWinners();
		int winnerAmount = round.getPot().getValue() / winners.size();
		for (iPlayer p: winners) {
			if (players.contains(p)) {
				
			}
		}
	}
	
	/**
	 * A help method that decides how much chips each player will get
	 */
	private List<Integer> distributeChips() {
		List<iPlayer> winners = round.getWinners();
		return 
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
