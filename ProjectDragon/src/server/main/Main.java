package server.main;

import java.rmi.RemoteException;

import server.ctrl.game.RemoteCommunicationController;


/**
 * @author robinandersson
 * 
 * The main class for the server
 */
public class Main {
	
	
	public static void main(String[] args) {

		
		try {
					new RemoteCommunicationController();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}
}
