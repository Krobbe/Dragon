package client.main;

import client.ctrl.game.RemoteCommunicationController;
import client.gui.menu.MainView;
import client.gui.table.TableView;



/**
 * @author robinandersson
 *
 */
public class Main {

	//Varf�r g�r det inte att s�tta private? Lika i kontrollerna f�r servern
	
	public static void main(String[] args) {
		
		new MainView();
		new RemoteCommunicationController(); 
		
	}
	
	
}
