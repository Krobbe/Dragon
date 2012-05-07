/**
 * 
 */
package client.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.player.Player;

import remote.iClient;
import remote.iServer;
import remote.iServerGame;

/**
 * @author robinandersson
 *
 */

// TODO Move the iClient implementation to the client-controller
public class Client implements iClient {
	
	iServer stub;
	iServerGame serverGameController;
	
	public Client(){
		
	    try {
	    	
	        Registry registry = LocateRegistry.getRegistry(null);
	        stub = (iServer) registry.lookup(iServer.REMOTE_NAME);	        
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
