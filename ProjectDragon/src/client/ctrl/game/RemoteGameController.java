/**
 * 
 */
package client.ctrl.game;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import model.card.ICard;
import model.game.Pot;
import model.player.Bet;
import model.player.IPlayer;
import model.player.hand.IHand;

import remote.IClientGame;
import remote.IServerGame;

import utilities.IllegalCallException;

/**
 * @author robinandersson
 * @author lisastenberg
 */

public class RemoteGameController implements IClientGame, IServerRequest {

	private IServerGame serverGameController;
	private GameController gameController;
	private RemoteCommunicationController remoteCommunicationController;
	
	public RemoteGameController(
				RemoteCommunicationController remoteCommunicationController){
		this(remoteCommunicationController, new GameController());
	}
	
	public RemoteGameController(
				RemoteCommunicationController remoteCommunicationController,
												GameController gameController){
		this.remoteCommunicationController = remoteCommunicationController;
		this.gameController = gameController;
	}
	
	/**
	 * Tells the server that the supplied Player instance ready or not ready to
	 * start the game
	 * @param player The player that is ready or not to start the game
	 * @param isReady True if the player is ready to start the game
	 * @return True if the request was successful
	 */
	public boolean setReadyToPlay(IPlayer player, boolean isReady) {
		
		try {
			return serverGameController.isReadyToStart(
					remoteCommunicationController.getAccount(), player,
					isReady);
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Adds the player in the list to to the game
	 * 
	 * @param player The player to be added
	 * @author robinandersson
	 */
	public void addPlayers(IPlayer player) {
		gameController.addPlayer(player);
	}
	
	/**
	 * Adds the players in the list to to the game
	 * 
	 * @param players The players to be added
	 * @author robinandersson
	 */
	public void addPlayers(Collection<IPlayer> players) {
		gameController.addPlayers(players);
	}

	@Override
	public boolean requestCall(Bet bet) {
		
		if(serverGameController != null){
			
			try {
				return serverGameController.call(bet);
			} catch (IllegalCallException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public boolean requestCheck(Bet bet) {
		
		if(serverGameController != null){
			
			try {
				return serverGameController.check(bet);
			} catch (IllegalCallException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public boolean requestRaise(Bet bet) {
		
		if(serverGameController != null){
			
			try {
				return serverGameController.raise(bet);
			} catch (IllegalCallException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public boolean requestFold(IPlayer player) {
		
		if(serverGameController != null){

			try {
				return serverGameController.fold(player);
			} catch (IllegalCallException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}

	@Override
	public void setActive(IPlayer player, boolean b) {
		gameController.setActive(player, b);
	}
	
	@Override
	public void setPot(Pot pot) {
		gameController.setPot(pot);
	}

	@Override
	public boolean fold(IPlayer player) {
		return gameController.fold(player);
	}

	@Override
	public boolean nextTurn(IPlayer nextPlayer) {
		return gameController.nextTurn(nextPlayer);
	}

	@Override
	public boolean betOccurred(Bet bet) {
		return gameController.betOccurred(bet);
	}

	@Override
	public void addCommunityCard(ICard card) {
		gameController.addCommunityCard(card);

	}

	@Override
	public void setHand(IPlayer player, IHand hand) {
		gameController.setHand(player, hand);
	}

	@Override
	public void setTurn(int indexOfCurrentPlayer) {
		gameController.setTurn(indexOfCurrentPlayer);
	}

	@Override
	public void setPlayerOwnCurrentBet(Bet bet) {
		gameController.setPlayerOwnCurrentBet(bet);		
	}
	
	public void newRound() {
		gameController.newRound();
	}

	@Override
	public void balanceChanged(Bet bet) {
		gameController.balanceChanged(bet);
	}

	@Override
	public void newTable(List<IPlayer> players, int meIndex) {
		gameController.newTable(players, meIndex);
	}

	
}
