/**
 * 
 */
package client.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.iClient;
import remote.iRemote;

/**
 * @author robinandersson
 *
 */

// TODO Move the iClient implementation to the client-controller
public class Client implements iClient {
	
	iRemote stub;
	
	public Client(){
		
	    try {
	    	
	        Registry registry = LocateRegistry.getRegistry(null);
	        stub = (iRemote) registry.lookup(iRemote.REMOTE_NAME);	        
	    }
	    
	    catch (Exception e) {
	        System.err.println("Client exception: " + e.toString());
	        e.printStackTrace();
	    }
	    
	    try {
			System.out.println(stub.getIServerGame());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@Override
	public void setActive(boolean active) {
		// TODO Auto-generated method stub
		
	}
	
}
