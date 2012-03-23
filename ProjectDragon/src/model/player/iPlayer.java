package model.player;
/**
 * An interface that represents a player.
 * @author lisastenberg
 *
 */
public interface iPlayer {

	/**
	 * @author lisastenberg
	 * @return the hand of the player.
	 */
	public iHand getHand();
	
	/**
	 * @author lisastenberg
	 * A method that can set a player active or unactive. 
	 * @param b true if you want to set the player active. 
	 */
	public void setActive(boolean b);
	
	/**
	 * @author lisastenberg
	 * @return the balance of the player.
	 */
	public Balance getBalance();
	
	@Override
	public boolean equals(Object o);
	
	@Override
	public String toString();
}
