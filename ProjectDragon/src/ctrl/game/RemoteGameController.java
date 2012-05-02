/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.player.Bet;

import remote.iServerGame;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;

/**
 * A class that manages all communication to and from the server
 * 
 * @author robinandersson
 *
 */
public class RemoteGameController extends UnicastRemoteObject implements iServerGame{

	GameController gameController;
	
	public RemoteGameController() throws RemoteException {
		this(new GameController());
	}
	
	public RemoteGameController(GameController gameController) throws RemoteException {
		super();
		this.gameController = gameController;
	}


	@Override
	public boolean call(Bet bet) throws IllegalCallException {
		return gameController.call(bet);
		
	}

	@Override
	public boolean check() throws IllegalCheckException {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean raise(int amount) throws IllegalRaiseException {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean fold() {
		return false;
		// TODO Auto-generated method stub
		
	}


}
