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
import model.player.User;

import remote.IClientGame;
import remote.IServerGame;

import utilities.IllegalCallException;

/**
 * @author robinandersson
 * @author lisastenberg
 */

public class RemoteGameController implements IClientGame, IServerRequest {
	
	private IServerGame serverGame;
	private RemoteCommunicationController clientComm;
	
	private GameController gameController;
	
	private IPlayer user;

	
	public RemoteGameController(RemoteCommunicationController clientComm,
			IPlayer user){
		this(clientComm, user, new GameController());
	}
	
	public RemoteGameController(RemoteCommunicationController clientComm, 
								IPlayer user, GameController gameController){
		this.clientComm = clientComm;
		this.gameController = gameController;
		this.user = user;
		
		this.gameController.addPlayer(user);
	}
	
	/**
	 * Sets the reference to the controller that is handling this particular
	 * game, together with other client's instances of this class.  
	 * @param serverGame The reference to the server's instance controlling this
	 * particular game
	 */
	public void setServerGame(IServerGame serverGame){
		this.serverGame = serverGame;
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
			return serverGame.isReadyToStart(
					clientComm.getAccount(), player,
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
		
		if(serverGame != null){
			
			try {
				return serverGame.call(bet);
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
		
		if(serverGame != null){
			
			try {
				return serverGame.check(bet);
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
		
		if(serverGame != null){
			
			try {
				return serverGame.raise(bet);
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
		
		if(serverGame != null){

			try {
				return serverGame.fold(player);
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
