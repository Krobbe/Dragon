/**
 * 
 */
package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Robin
 *
 */
public interface iRemote extends Remote {
	
	public static final String SERVICE_NAME = "Server";
	
	public String sayHello() throws RemoteException;
	

}
