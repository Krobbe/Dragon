/**
 * 
 */
package client.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.iRemote;

/**
 * @author robinandersson
 *
 */
public class Client {
	
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
	
}
