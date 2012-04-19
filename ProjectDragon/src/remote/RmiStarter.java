package remote;

import org.junit.internal.matchers.SubstringMatcher;

// TODO Do more to point out that this is not my (robinandersson) code? (Code taken from http://code.nomad-labs.com/2010/03/26/an-improved-rmi-tutorial-with-eclipse/)
/**
 * class to do some common things for client & server to get RMI working
 *
 * @author srasul
 *
 */
public abstract class RmiStarter {

    /**
     *
     * @param clazzToAddToServerCodebase a class that should be in the java.rmi.server.codebase property.
     */
    public RmiStarter(Class clazzToAddToServerCodebase) {
    	
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