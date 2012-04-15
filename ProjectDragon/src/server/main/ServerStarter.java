package server.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import remote.RmiStarter;


/**
 * start the server component. this exposes the an implementation of the Compute interface as a service over RMI
 *
 * @author srasul
 *
 */
public class ServerStarter
    extends RmiStarter {

    public ServerStarter() {
        super(Server.class);
    }

    /*
    @Override
    public void doCustomRmiHandling() {
        try {
            Server server = new Server();
            Server serverStub = (Server)UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(Server.SERVICE_NAME, serverStub);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    */
}