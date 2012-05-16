package client.gui.table;

import java.awt.Color;

import model.player.hand.IHand;

public interface IPlayerPanel {
	
	
	/**
	 * Discards the cards in the GUI for the given player
	 */
	public boolean discard();
	
	/**
	 * Sets the balance for the given player to the amount specified
	 */
	public void setBalance(String s);
	
	/**
	 * Shows the cards of the remaining players
	 */
	public void showCards(IHand h);
	
	/**
	 * Sets the background
	 */
	public void setBackground(Color h);
}
