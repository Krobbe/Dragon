/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import model.player.Player;

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
	Map<Player, iClient> clients;
	
	RemoteGameController remoteGameController = new RemoteGameController();
	
	public RemoteNetworkController() throws RemoteException {
		this(new RemoteGameController());
	}
	
	public RemoteNetworkController(iServerGame serverGame) throws RemoteException {
		super();
		new ServerStarter(this);
		this.serverGame = serverGame;
		clients = new HashMap();
	}

	@Override
	public iServerGame getIServerGame() throws RemoteException {
		return serverGame;
	}

	@Override
	public boolean registerClient(iClient client, String accountName, String accountPassword) throws RemoteException {
		//TODO Connect to database to get the account information
		/*
		 * if(correctpassword){
		 * 	clients.put(database.getPlayer(), client);
		 * 	return true;
		 * }
		 */
		return false;
	
	}

}
