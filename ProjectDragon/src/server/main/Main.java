package server.main;

import java.rmi.RemoteException;

import ctrl.game.GameController;
import ctrl.game.RemoteNetworkController;



public class Main {
	
	
	public static void main(String[] args) {
		GameController gameController = new GameController();
		
		try {
			
			RemoteNetworkController remoteController = new RemoteNetworkController();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}
}
