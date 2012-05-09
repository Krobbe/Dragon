package client.main;

import client.ctrl.game.RemoteCommunicationController;



/**
 * @author robinandersson
 *
 */
public class Main {

	//Varför går det inte att sätta private? Lika i kontrollerna för servern
	
	public static void main(String[] args) {
		
		new RemoteCommunicationController(); 
		
	}
	
	
}
