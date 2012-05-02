package server.main;

import java.rmi.RemoteException;

import ctrl.game.GameController;
import ctrl.game.RemoteGameController;
import ctrl.game.RemoteNetworkController;



public class Main {
	
	
	public static void main(String[] args) {
		GameController gameController = new GameController();
		RemoteGameController remoteGameController;
		RemoteNetworkController remoteNetworkController;
		
		try {
			

			remoteGameController = new RemoteGameController(gameController);
			remoteNetworkController = new RemoteNetworkController(remoteGameController);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}
}
