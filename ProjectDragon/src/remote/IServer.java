/**
 * 
 */
package remote;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import database.IDBAccount;

import model.player.Account;
import model.player.IPlayer;

/**
 * @author robinandersson
 *
 */
public interface IServer extends Remote, IDBAccount, Serializable {
	
	public static final String REMOTE_NAME = "Server";

	/** 
	 * Tries to login with the provided account name and password. Stores the
	 * associated account and reference to client if successful
	 * 
	 * @param client The client that are to be registered
	 * @param accountName The account's name.
	 * @param accountPassword The password associated with the account name.
	 */
	public Account login(IClient client, String accountName, String accountPassword) throws RemoteException;
	
	/** 
	 * Logs the client out from the server.
	 * 
	 * @param account The Account object associated with the client
	 */
	public void logOut(Account account) throws RemoteException;
	
	/** 
	 * Tries to create a game. Passes the unique Account object along for
	 * security clearance. Returns the RemoteGameController object that has been
	 * assigned to the table if successful.
	 * 
	 * @param account The users unique Account object
	 * @param clientGame The reference to the client's
	 * client-server/server-client game communicator
	 * @param entranceFee The fee players' have to pay to join the table
	 * @param maxPlayers The maximum amount of players allowed in the game 
	 * @param playerChips The amount of chips players' get at the start of the game
	 */
	public IServerGame createGame(Account account, IClientGame clientGame,
			int entranceFee, int maxPlayers, int playerStartingChips)  throws RemoteException;
	
	/** 
	 * Tries to join the game with the specified index. Passes the unique
	 * Account object along for security clearance. Returns the
	 * RemoteGameController for the game if successful.
	 * 
	 * @param account The users unique Account object
	 * @param player The Player object representing the client that wants to
	 * join
	 * @param clientGame The reference to the client's
	 * client-server/server-client game communicator
	 * @param gameIndex The index of the game that the user wants to join
	 */
	public IServerGame joinGame(Account account, IPlayer player,
				IClientGame clientGame, int gameIndex) throws RemoteException;
	
	/**
	 * Test method for connectivity
	 * 
	 * @return Test string for testing method call
	 * @throws RemoteException
	 */
	public String testPrint() throws RemoteException;

}