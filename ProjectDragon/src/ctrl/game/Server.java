/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import remote.RmiStarter;
import remote.iRemote;


/**
 * @author Robin
 *
 */
public class Server extends RmiStarter {

	public Server(iRemote stub) {
		
	    super(iRemote.class);
		
		try {
			
            Registry registry = LocateRegistry.getRegistry();
            //TODO Okej med rebind istället för bind? Verkar lösa några problem
            registry.rebind(iRemote.SERVICE_NAME, stub);

            System.out.println("Server ready");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}
	

}
