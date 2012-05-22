package client.main;

import java.rmi.RemoteException;

import client.ctrl.game.RemoteCommunicationController;
import client.gui.menu.MainView;


/**
 * @author robinandersson
 * 
 * The main class for the client
 */
public class Main {
	
	public static void main(String[] args) {
		
		try {
			new RemoteCommunicationController();
		} catch (RemoteException e) {
			System.out.println("*** Unable to start server ***");
			e.printStackTrace();
			System.exit(0);
		}
		
		new MainView();
		
	}
	
	
}
