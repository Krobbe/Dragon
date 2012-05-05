/**
 * 
 */
package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
	 * Registers a new client on the server.
	 * 
	 * @param client The client that are to be registered
	 * @param accountName The account's name.
	 * @param accountPassword The password assoicated with the account name.
	 */
	public boolean registerClient(iClient client, String accountName, String accountPassword) throws RemoteException;
	

}
