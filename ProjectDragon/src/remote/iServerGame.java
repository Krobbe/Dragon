package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.player.Bet;
import model.player.iPlayer;

import utilities.*;
/**
 * Interface for method calls concerning game-functions to Server. 
 * 
 * @author lisastenberg
 * @author robinandersson
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
	public boolean call(Bet bet) throws IllegalCallException, RemoteException;
	
	/**.
	 * Performs a check.
	 * 
	 * @param bet The placed bet.
	 * @throws IllegalCheckException
	 */
	public boolean check(Bet bet) throws IllegalCheckException, RemoteException;
	
	/**
	 * Performs a raise.
	 * 
	 * @param bet The placed bet.
	 * @throws IllegalRaiseException
	 */
	public boolean raise(Bet bet) throws IllegalRaiseException, RemoteException;
	
	/**
	 * Performs fold.
	 * 
	 * @param player The player that wants to fold.
	 */
	public boolean fold(iPlayer player) throws RemoteException;
}
