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
public interface iClientGame extends Remote {
	
	/**
	 * Method to set the player active to check, fold, raise etc.
	 * 
	 * @param active The boolean determining if it's the client's turn.
	 */
	public void setActive(boolean isActive) throws RemoteException;

}
