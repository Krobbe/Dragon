package model.player;

import model.game.Card;
import model.player.hand.iHand;

/**
 * A class that simulates the local player.
 * @author lisastenberg
 * @author mattiashenriksson
 */
public class User implements iPlayer {
	
	Player player;

	public User() {
		//TODO
	}
	
	public User(Player player) {
		this.player = player;
	}

	@Override
	public iHand getHand() {
		return player.getHand();
	}

	@Override
	public void setActive(boolean b) {
		player.setActive(b);
	}
	
	@Override
	public Balance getBalance() {
		return player.getBalance();
	}

	@Override
	public boolean isActive() {
		return player.isActive();
	}

	@Override
	public String getName() {
		return player.getName();
	}
	
	/**
	 * This method preforms a call
	 */
	public void call() {
		//TODO
	}

	@Override
	public void addCard(Card c) {
		player.addCard(c);
		
	}
}
