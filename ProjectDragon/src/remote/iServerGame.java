package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

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
	
	/**
	 * Starts the game with the present players
	 * 
	 * @throws IllegalCallException, RemoteException
	 */
	public void startGame() throws IllegalCallException, RemoteException;
	
	/**
	 * Returns a list with the players in this particular game
	 * 
	 * @return The list with players in the game
	 * @throws IllegalCallException, RemoteException
	 */
	public LinkedList<iPlayer> getPlayers() throws IllegalCallException, RemoteException;
	
	/**
	 * Performs a call.
	 * 
	 * @param bet A bet object containing the bet's amount and user information
	 * (to verify that the correct player tried to invoka the call method)
	 * @throws IllegalCallException, RemoteException
	 */
	public boolean call(Bet bet) throws IllegalCallException, RemoteException;
	
	/**.
	 * Performs a check.
	 * 
	 * @param bet The placed bet.
	 * @throws IllegalCheckException, RemoteException
	 */
	public boolean check(Bet bet) throws IllegalCheckException, RemoteException;
	
	/**
	 * Performs a raise.
	 * 
	 * @param bet The placed bet.
	 * @throws IllegalRaiseException, RemoteException
	 */
	public boolean raise(Bet bet) throws IllegalRaiseException, RemoteException;
	
	/**
	 * Performs fold.
	 * 
	 * @param player The player that wants to fold.
	 */
	public boolean fold(iPlayer player) throws RemoteException;
	

	
}
