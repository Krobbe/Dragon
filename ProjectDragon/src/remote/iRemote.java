/**
 * 
 */
package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.player.iPlayer;

/**
 * @author robinandersson
 *
 */
public interface iRemote extends Remote {
	
	public static final String REMOTE_NAME = "Server";
	
	/**
	 * Returns the stub for the server's game methods.
	 * 
	 * @return the stub for the server's game methods
	 */
	public iServerGame getIServerGame() throws RemoteException;

	/** 
	 * Tries to login with the provided account name and password
	 * 
	 * @param client The client that are to be registered
	 * @param accountName The account's name.
	 * @param accountPassword The password assoicated with the account name.
	 */
	public boolean login(iClient client, String accountName, String accountPassword) throws RemoteException;
	
	/** 
	 * Registers a new client on the server.
	 * 
	 * @param player The Player-object associated to the client
	 * @param client The client that are to be registered
	 */
	public void registerClient(iPlayer player, iClient client) throws RemoteException;
	
	/** 
	 * Unregisters a client from the server.
	 * 
	 * @param player The Player-object associated to the client
	 * @param client The client that are to be unregistered
	 */
	public void unRegisterClient(iPlayer player) throws RemoteException;

}
