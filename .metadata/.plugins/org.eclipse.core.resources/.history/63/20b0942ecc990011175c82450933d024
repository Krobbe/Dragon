/**
 * 
 */
package client.ctrl.game;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.player.Account;
import model.player.Bet;
import model.player.Player;
import model.player.iPlayer;

import remote.iClient;
import remote.iServer;
import remote.iServerGame;
import utilities.IllegalCallException;

/**
 * @author robinandersson
 *
 */

public class RemoteGameController implements iClient, iServerRequest {
	
	private iServer stub;
	private iServerGame serverGameController;
	private GameController gameController;
	
	// TODO Flytta "lagringen" av account till ett mer passande ställe
	private Account account;
	
	public RemoteGameController(){
		this(new GameController());
	}
	
	
	public RemoteGameController(GameController gameController){
		
		this.gameController = gameController;
		
	    try {
	    	
	        Registry registry = LocateRegistry.getRegistry(null);
	        stub = (iServer) registry.lookup(iServer.REMOTE_NAME);	        
	    }
	    
	    catch (Exception e) {
	        System.err.println("Client exception: " + e.toString());
	        e.printStackTrace();
	        System.exit(0);
	    }
	    
	    try {
			account = stub.login(this, "bengan", "1234");
			
		} catch (RemoteException e) {
			// TODO Handle login failure
			e.printStackTrace();
			System.out.println("Failed to login");
			System.exit(0);
		}
	    
	    try {
			serverGameController = stub.getIServerGame(account);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	    
	}
	
	@Override
	public void setActive(boolean active) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean requestCall(Bet bet) {
		
		try {
			return serverGameController.call(bet);
		} catch (IllegalCallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean requestCheck(Bet bet) {
		
		try {
			return serverGameController.check(bet);
		} catch (IllegalCallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean requestRaise(Bet bet) {
		
		try {
			return serverGameController.raise(bet);
		} catch (IllegalCallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean requestFold(iPlayer player) {
		
		try {
			return serverGameController.fold(player);
		} catch (IllegalCallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
