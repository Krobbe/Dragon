package model.player;

import model.game.Card;
import model.player.hand.iHand;

/**
 * This class contains common methods and variables for all classes 
 * implementing iPlayer.
 * @author mattiashenriksson
 *
 */
public class Player implements iPlayer {
	
	private iHand hand;
	private boolean active = false;
	private String name;
	private Balance balance;
	
	public Player(iHand hand, String name, Balance balance) {
		this.hand = hand;
		this.name = name;
		this.balance = balance;
	}

	@Override
	public iHand getHand() {
		return hand;
	}

	@Override
	public void setActive(boolean b) {
		active = b;
	}

	@Override
	public Balance getBalance() {
		return balance;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void addCard(Card c) {
		hand.addCard(c);
		
	}

}
