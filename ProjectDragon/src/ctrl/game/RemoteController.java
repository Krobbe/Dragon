/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
		new Server(this);
	}

	@Override
	public iServerGame getIServerGame() throws RemoteException {
		return null;
	}

}
