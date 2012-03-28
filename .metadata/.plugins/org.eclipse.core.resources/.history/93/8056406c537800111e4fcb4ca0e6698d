package model.game;

import java.util.ArrayList;
import java.util.List;

import model.player.iPlayer;

/**
 * A class that represent table at which a poker game takes place. This class has a central
 * role in Dragon. It has access to all the other classes in the application and is the 
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
	public Table() {
		this(new Round(),new Dealer());
	}
	
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
		p.getHand().setVisible(true); //TODO bŠttre att gšra en metod i Player makeHandVisble fšr att undvika lŒnga rader av metodanrop?
	}
	
	/**
	 * Calculates the amount of chips the winner(s) will get and distributes it to him.
	 */
	public void distributePot() { //TODO Ev dela upp i tvŒ metoder?
		List<iPlayer> winners = round.getWinners();
		int winnerAmount = round.getPot().getValue() / winners.size();
		for (iPlayer p: winners) {
			p.getBalance().addToBalance(winnerAmount);
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
	
	/**
	 * This method is used only for testing of the class.
	 * @return A list of players at the table.
	 */
	public List<iPlayer> getPlayers() {
		return players;
	}
	
	/**
	 * This method is used only for testing of the class.
	 * @return The "table cards" represented as a list of cards.
	 */
	public List<Card> getTableCards() {
		return tableCards;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Players:" + "\n");
		//TODO Lista skall inte innehålla iPlayers utan Players väl? Finns ingen Player-klass
		for(Player p : this.players) {
			result.append(p.getName() + "\n");
		}
		result.append("Current player is " + this.players.indexOf(this.indexOfCurrentPlayer).getName() + "\n");
		result.append("Shown cards are:" + "\n");
		for(Card c : this.tableCards) {
			result.append(c.toString());
		}
		return result.toString();
	}
	
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
