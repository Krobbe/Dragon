/**
 * 
 */
package client.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.iRemote;

/**
 * @author Robin
 *
 */
public class Client {
	
	public Client(){
		
	    try {
	    	
	        Registry registry = LocateRegistry.getRegistry(null);
	        iRemote stub = (iRemote) registry.lookup("test");
	        String response = stub.sayHello();
	        System.out.println("response: " + response);
	        
	    }
	    
	    catch (Exception e) {
	        System.err.println("Client exception: " + e.toString());
	        e.printStackTrace();
	    }
	    
	}
	
}
