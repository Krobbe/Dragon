package client.main;

import java.util.List;

import client.ctrl.game.GameController;
import client.ctrl.game.RemoteGameController;
import client.model.game.SidePotHandler;


/**
 * @author robinandersson
 *
 */
public class Main {
	
	/* tempor�r l�sning */
	List<SidePotHandler> sidePots;
	
	/* tempor�r l�sning */
	public List<SidePotHandler> getSidePots() {
		return sidePots;
	}
	
	//Varf�r g�r det inte att s�tta private? Lika i kontrollerna f�r servern
	
	public static void main(String[] args) {
		
		GameController gameController = new GameController(); 
		RemoteGameController remoteGameController = new RemoteGameController();
		
	}
	
	
}
