/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.player.Bet;
import model.player.iPlayer;

import remote.iServerGame;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;

/**
 * A class that manages all communication to and from the server
 * 
 * @author robinandersson
 * @author lisastenberg
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
	public boolean call(Bet bet) {
		return gameController.call(bet);
	}

	@Override
	public boolean check(Bet bet) throws IllegalCheckException {
		return gameController.check(bet);
	}

	@Override
	public boolean raise(Bet bet) throws IllegalRaiseException {
		return gameController.raise(bet);
	}

	@Override
	public boolean fold(iPlayer player) {
		return gameController.fold(player);
	}

}
