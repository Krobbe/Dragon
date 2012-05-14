/**
 * 
 */
package remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



/**
 * @author robinandersson
 *
 */
public class ServerStarter extends RmiStarter{

	public ServerStarter(IServer stub) {
		
	    super(IServer.class);
		
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
