package remote;

import java.rmi.Remote;

import model.player.Bet;

import utilities.*;
/**
 * Interface for method calls concerning game-functions to Server. 
 * 
 * @author lisastenberg
 *
 */
public interface iServerGame extends Remote {

	public static final String SERVERGAME_NAME = "Game";
	
	/**
	 * Performs a call.
	 * 
	 * @param bet A bet object containing the bet's amount and user information
	 * (to verify that the correct player tried to invoka the call method)
	 * @throws IllegalCallException
	 */
	public boolean call(Bet bet) throws IllegalCallException;
	
	/**.
	 * Performs a check
	 * 
	 * @throws IllegalCheckException
	 */
	public boolean check() throws IllegalCheckException;
	
	/**
	 * Performs a raise.
	 * 
	 * @param amount The amount to raise the pot with.
	 * @throws IllegalRaiseException
	 */
	public boolean raise(int amount) throws IllegalRaiseException;
	
	/**
	 * Performs fold.
	 */
	public boolean fold();
}
