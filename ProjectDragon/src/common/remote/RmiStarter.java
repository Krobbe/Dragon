package common.remote;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * class to do some common things for server to get RMI working
 *
 * @author srasul
 * @author robinandersson
 *
 */
public abstract class RmiStarter {

    /**
     *
     * @param clazzToAddToServerCodebase a class that should be in the java.rmi.server.codebase property.
     */
    public RmiStarter(Class clazzToAddToServerCodebase) {
    	
    	/**
    	 * @author robinandersson 
    	 */
    	
		/*
		 * Starts the rmi-process. Also returns it so that it may be
		 * terminated when shutting down the server 
		 */
    	try {
    		final Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

			/*
			 * When the server is shutting down a new thread is executed where
			 * the RMI-registry is terminated
			 */
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			    
				public void run() {
			    	
			    	System.out.println("RMI-registry shutting down");
			    	try {
						registry.unbind(IServer.REMOTE_NAME);
						UnicastRemoteObject.unexportObject(registry, true);
					} catch (AccessException e) {
						e.printStackTrace();
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (NotBoundException e) {
						e.printStackTrace();
					}
			    	
			    } //run()
			}//new Runnable()
			)//new Thread()
			);//addShutdownHook()
	    	
    	} catch (RemoteException e) {
    		e.printStackTrace();
    		System.exit(0);
    	}
    	
    	/**
    	 * @author srasul 
    	 */
    	
    	String urlString = clazzToAddToServerCodebase
                .getProtectionDomain().getCodeSource().getLocation().toString();
    	
        System.setProperty("java.rmi.server.codebase", urlString);
        
        System.setProperty("java.security.policy",
        		PolicyFileLocator.getLocationOfPolicyFile());
        
        System.setProperty("java.rmi.server.hostname", IServer.REMOTE_NAME);
        //System.setProperty("java.rmi.server.hostname", "129.16.179.143");
        
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

    }

}