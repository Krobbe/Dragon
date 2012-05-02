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
	
	public void registerClient(iClient client) throws RemoteException;
	

}
