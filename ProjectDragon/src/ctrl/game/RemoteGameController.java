/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import remote.iClient;
import remote.iRemote;
import remote.iServerGame;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;

/**
 * A class that manages all communication to and from the server
 * 
 * @author robinandersson
 *
 */
public class RemoteGameController extends UnicastRemoteObject implements iServerGame{

	public RemoteGameController() throws RemoteException {
		
		super();
	}


	@Override
	public void call() throws IllegalCallException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void check() throws IllegalCheckException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raise(int amount) throws IllegalRaiseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fold() {
		// TODO Auto-generated method stub
		
	}


}
