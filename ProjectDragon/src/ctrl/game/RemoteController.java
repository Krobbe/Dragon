/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import remote.iClient;
import remote.iRemote;
import remote.iServerGame;

/**
 * @author robinandersson
 *
 */
public class RemoteController extends UnicastRemoteObject implements iRemote{

	public RemoteController() throws RemoteException {
		super();
		
        //iRemote stub = (iRemote) UnicastRemoteObject.exportObject(this, 0);
		new ServerStarter(this);
	}

	@Override
	public iServerGame getIServerGame() throws RemoteException {
		return null;
	}

	@Override
	public void registerClient(iClient client) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
