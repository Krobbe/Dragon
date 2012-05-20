/**
 * 
 */
package common.remote;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



/**
 * A class for setting up the game server
 * @author robinandersson
 *
 */
public class ServerStarter extends RmiStarter{

	public ServerStarter(IServer stub) {
		
	    super(IServer.class, Registry.REGISTRY_PORT);
	    
	    try {
			System.out.println((InetAddress.getLocalHost()).toString());
		} catch (UnknownHostException e1) {
			System.out.println("Can't get inet adress");
			e1.printStackTrace();
		}
		
		try {
			
            Registry registry = LocateRegistry.getRegistry();
            //TODO Okej med rebind istället för bind? Verkar lösa några problem
            registry.rebind(IServer.REMOTE_NAME, stub);

            System.out.println("Server ready");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}

}
