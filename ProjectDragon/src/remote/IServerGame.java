package remote;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import model.player.Account;
import model.player.Bet;
import model.player.IPlayer;

import common.utilities.*;
/**
 * Interface for method calls concerning game-functions to Server. 
 * 
 * @author lisastenberg
 * @author robinandersson
 *
 */
public interface IServerGame extends Remote {
	
	/**
	 * Set player ready to start the game. Passes along an Account instance for
	 * security clearance.
	 * 
	 * @author robinandersson
	 * @param account The account containing user name and password for security
	 * clearance
	 * @param isReady True if the player is ready to start
	 * @param player The player Object that is ready to start the game
	 * @return True if the player was allowed to set the ready status of the
	 * passed along player
	 * @throws RemoteException
	 */
	public boolean isReadyToStart(Account account, IPlayer player,
										boolean isReady) throws RemoteException;
	
	// TODO Do you have to write specifically what the pre-conditions are, or
	// should'nt this be allowed? This interface should not know about the
	// implementation, right?
	/**
	 * Starts the game if pre-conditions are met.
	 * @throws RemoteException
	 */
	public void tryStartGame() throws RemoteException;
	
	/**
	 * Returns a list with the players in this particular game
	 * 
	 * @author robinandersson
	 * @return The list with players in the game
	 * @throws IllegalCallException, RemoteException
	 */
	public List<IPlayer> getPlayers() throws IllegalCallException,
																RemoteException;
	
	/**
	 * Returns the maximum amount of players allowed in the game
	 * 
	 * @author robinandersson
	 * @return The maximum amount of players allowed in the game
	 * @throws RemoteException
	 */
	public int getMaxPlayers() throws RemoteException;
	
	/**
	 * Returns the entrance fee players' have to pay to join the game
	 * 
	 * @author robinandersson
	 * @return The entrance fee
	 * @throws RemoteException
	 */
	public int getEntranceFee() throws RemoteException;

	
	/**
	 * Performs a call.
	 * 
	 * @param bet A bet object containing the bet's amount and user information
	 * (to verify that the correct player tried to invoke the call method)
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
	public boolean fold(IPlayer player) throws RemoteException;
	

	
}
