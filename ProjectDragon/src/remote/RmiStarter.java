package remote;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.internal.matchers.SubstringMatcher;

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
    	try{
    		
    		/*
    		 * Starts the rmi-process. Also returns it so that it may be
    		 * terminated when shutting down the server 
    		 */
    		final Process rmi = Runtime.getRuntime().exec("rmiregistry");
    		
    		/*
    		 * When the server is shutting down a new thread is executed where
    		 * the RMI-registry is terminated
    		 */
    		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    		    
    			public void run() {
    		    	
    		    	System.out.println("RMI-registry shutting down");
    		    	
    		    	try {
						java.rmi.Naming.unbind(iRemote.REMOTE_NAME);
						
					} catch (RemoteException e) {
						e.printStackTrace();
						
					} catch (MalformedURLException e) {
						e.printStackTrace();
						
					} catch (NotBoundException e) {
						e.printStackTrace();
					}
    		    	
    		    	rmi.destroy();
    		    	
    		    } //run()
    		}//new Runnable()
    		)//new Thread()
    		);//addShutdownHook()
    		
    	}
    	
    	catch(IOException e){
            System.err.println("RMI-registry unable to start: " + e.toString());
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

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

    }

}