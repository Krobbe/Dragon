package server.main;

import java.rmi.RemoteException;

import ctrl.game.GameController;
import ctrl.game.RemoteController;



public class Main {
	
	
	public static void main(String[] args) {
		GameController gameController = new GameController();
		
		try {
			
			RemoteController remoteController = new RemoteController();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}
}
