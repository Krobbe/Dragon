/**
 * 
 */
package server.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import remote.RmiStarter;
import remote.iRemote;


/**
 * @author Robin
 *
 */
public class Server extends RmiStarter implements iRemote {

	public Server() {
		
	    super(iRemote.class);
		
		try {
            iRemote stub = (iRemote) UnicastRemoteObject.exportObject(this, 0);
            
            Registry registry = LocateRegistry.getRegistry();
            registry.bind(iRemote.SERVICE_NAME, stub);

            System.out.println("Server ready");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}
	
	/* 
	 * 
	 */
	@Override
	public String sayHello() {
		return "Method call recieved and executed";
	}

}
