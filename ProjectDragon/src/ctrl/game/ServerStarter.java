/**
 * 
 */
package ctrl.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.RmiStarter;
import remote.iRemote;


/**
 * @author robinandersson
 *
 */
public class ServerStarter extends RmiStarter implements ActionListener{

	public ServerStarter(iRemote stub) {
		
	    super(iRemote.class);
		
		try {
			
            Registry registry = LocateRegistry.getRegistry();
            //TODO Okej med rebind istället för bind? Verkar lösa några problem
            registry.rebind(iRemote.REMOTE_NAME, stub);

            System.out.println("Server ready");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	

}
