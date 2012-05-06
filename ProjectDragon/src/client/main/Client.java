/**
 * 
 */
package client.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.player.Player;

import remote.iClient;
import remote.iRemote;
import remote.iServerGame;

/**
 * @author robinandersson
 *
 */

// TODO Move the iClient implementation to the client-controller
public class Client implements iClient {
	
	iRemote stub;
	iServerGame serverGameController;
	
	public Client(){
		
	    try {
	    	
	        Registry registry = LocateRegistry.getRegistry(null);
	        stub = (iRemote) registry.lookup(iRemote.REMOTE_NAME);	        
	    }
	    
	    catch (Exception e) {
	        System.err.println("Client exception: " + e.toString());
	        e.printStackTrace();
	    }
	    
	    /*
	    try {
			serverGameController = stub.getIServerGame(account);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		*/
	    
	}
	
	@Override
	public void setActive(boolean active) {
		// TODO Auto-generated method stub
		
	}
	
}
