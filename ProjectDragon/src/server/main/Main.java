package server.main;

import java.rmi.RemoteException;

import ctrl.game.GameController;
import ctrl.game.RemoteGameController;
import ctrl.game.RemoteCommunicationController;



public class Main {
	
	
	public static void main(String[] args) {
		GameController gameController = new GameController();
		RemoteGameController remoteGameController;
		RemoteCommunicationController remoteCommunicationController;
		
		try {
			

			remoteGameController = new RemoteGameController(gameController);
			remoteCommunicationController = new RemoteCommunicationController(remoteGameController);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}
}
