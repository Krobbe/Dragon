/**
 * 
 */
package ctrl.game;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.RmiStarter;
import remote.iServer;


/**
 * @author robinandersson
 *
 */
public class ServerStarter extends RmiStarter{

	public ServerStarter(iServer stub) {
		
	    super(iServer.class);
		
		try {
			
            Registry registry = LocateRegistry.getRegistry();
            //TODO Okej med rebind istället för bind? Verkar lösa några problem
            registry.rebind(iServer.REMOTE_NAME, stub);

            System.out.println("Server ready");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}

}
