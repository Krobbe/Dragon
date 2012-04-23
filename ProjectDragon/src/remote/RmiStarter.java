package remote;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.internal.matchers.SubstringMatcher;

// TODO Do more to point out that this is not my (robinandersson) code? (Code taken from http://code.nomad-labs.com/2010/03/26/an-improved-rmi-tutorial-with-eclipse/)
/**
 * class to do some common things for client & server to get RMI working
 *
 * @author srasul
 * @author robinandersson
 *
 */
public abstract class RmiStarter {

    /**
     *
     * @param clazzToAddToServerCodebase a class that should be in the java.rmi.server.codebase property.
     * @throws IOException 
     */
    public RmiStarter(Class clazzToAddToServerCodebase) {
    	
    	try{
    		Runtime.getRuntime().exec("rmiregistry");
    		
    		//TODO Vad är skillnaden på exec("rmiregistry") och createRegistry(1099)?
    		/*
    		Runtime.getRuntime().addShutdownHook( class CloseRmi extends Thread {
    					public void run(){
    					java.rmi.Naming.unbind(iRemote.SERVICE_NAME);});
    		*/
    		
    		// TODO Fungerar detta för att stänga ner servern?
    		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    		    public void run() {
    		    	System.out.println("RMI-registry shutting down!");
    		    	try {
						java.rmi.Naming.unbind(iRemote.SERVICE_NAME);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		    } //run()
    		}//new Runnable()
    		)//new Thread()
    		);//addShutdownHook()
    		
    		//java.rmi.Naming.unbind(iRemote.SERVICE_NAME)
    		//java.rmi.registry.LocateRegistry.createRegistry(1099);
    	}
    	
    	catch(IOException e){
    		System.out.println("RMI-registry unable to start");
    	}
    	
    	String urlString = clazzToAddToServerCodebase
                .getProtectionDomain().getCodeSource().getLocation().toString();
    	
        System.setProperty("java.rmi.server.codebase", urlString);
        
        System.setProperty("java.security.policy", PolicyFileLocator.getLocationOfPolicyFile());

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

//        doCustomRmiHandling();
    }

    /**
     * extend this class and do RMI handling here
     */
//    public abstract void doCustomRmiHandling();
}