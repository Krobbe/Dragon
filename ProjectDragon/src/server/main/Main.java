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
		
		IPlayer player = new Player(new Hand(), "Alf", new Balance(100));
		Player p2 = new Player(new Hand(), "Alf", new Balance(100));
		IPlayer user = new User(p2);
		
		
		System.out.println(player.equals(player));
		System.out.println(player.equals(p2));
		System.out.println(p2.equals(player));
		System.out.println(p2.equals(p2));
		
		System.out.println(player instanceof IPlayer);
		System.out.println(p2 instanceof IPlayer);
		
		
		
		try {
					new RemoteCommunicationController();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}
}
