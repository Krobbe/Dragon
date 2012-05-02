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
public class RemoteNetworkController extends UnicastRemoteObject implements
		iRemote {
	
	public RemoteNetworkController() throws RemoteException {
		super();
		new ServerStarter(this);
	}

	/* (non-Javadoc)
	 * @see remote.iRemote#getIServerGame()
	 */
	@Override
	public iServerGame getIServerGame() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see remote.iRemote#registerClient(remote.iClient)
	 */
	@Override
	public void registerClient(iClient client) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
