/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import model.player.Player;
import model.player.iPlayer;

import remote.iClient;
import remote.iRemote;
import remote.iServerGame;

/**
 * @author robinandersson
 *
 */
public class RemoteCommunicationController extends UnicastRemoteObject
											implements iRemote {
	
	iServerGame serverGame;
	Map<iPlayer, iClient> clients;
	
	RemoteGameController remoteGameController = new RemoteGameController();
	
	public RemoteCommunicationController() throws RemoteException {
		this(new RemoteGameController());
	}
	
	public RemoteCommunicationController(iServerGame serverGame)
			throws RemoteException {
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
	public void registerClient(iPlayer player, iClient client)
			throws RemoteException {
		clients.put(player, client);
	}
	
	@Override
	public void unRegisterClient(iPlayer player)
			throws RemoteException {
		clients.remove(player);
		
	}

	@Override
	public boolean login(iClient client, String accountName,
			String accountPassword) throws RemoteException {
		// TODO Auto-generated method stub
		
		
		/*
		 * if(correctpassword){
		 * 	clients.put(database.getPlayer(), client);
		 * 	return true;
		 * }
		 */
		
		return false;
	}



}
