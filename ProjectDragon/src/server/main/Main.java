package server.main;

import java.rmi.RemoteException;

import common.model.player.Balance;
import common.model.player.IPlayer;
import common.model.player.Player;
import common.model.player.User;
import common.model.player.hand.Hand;

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
