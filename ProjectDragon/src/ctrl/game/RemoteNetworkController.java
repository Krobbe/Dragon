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
	
	iServerGame serverGame;
	
	RemoteGameController remoteGameController = new RemoteGameController();
	
	public RemoteNetworkController() throws RemoteException {
		this(new RemoteGameController());
		new ServerStarter(this);
	}
	
	public RemoteNetworkController(iServerGame serverGame) throws RemoteException {
		super();
		new ServerStarter(this);
		this.serverGame = serverGame;
	}


	/* (non-Javadoc)
	 * @see remote.iRemote#getIServerGame()
	 */
	@Override
	public iServerGame getIServerGame() throws RemoteException {
		return serverGame;
	}

	/* (non-Javadoc)
	 * @see remote.iRemote#registerClient(remote.iClient)
	 */
	@Override
	public void registerClient(iClient client) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
