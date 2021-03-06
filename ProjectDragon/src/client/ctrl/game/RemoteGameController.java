/**
 * 
 */
package client.ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import client.event.Event;
import client.event.EventBus;
import client.event.EventHandler;
import client.model.game.Table;

import common.model.card.ICard;
import common.model.game.Pot;
import common.model.player.Bet;
import common.model.player.IPlayer;
import common.model.player.hand.IHand;
import common.remote.IClientGame;
import common.remote.IServerGame;



/**
 * @author robinandersson
 * @author lisastenberg
 * 
 * This clientside class manages all game communication to and from the server.
 */

public class RemoteGameController extends UnicastRemoteObject
						implements IClientGame, IServerRequest, EventHandler {
	
	private IServerGame serverGame;
	
	private RemoteCommunicationController clientComm;
	private GameController gameController;
	
	public RemoteGameController(RemoteCommunicationController clientComm)
			throws RemoteException{
		
		this.clientComm = clientComm;
		gameController = new GameController();
		EventBus.register(this);
	}
	
	public RemoteGameController(RemoteCommunicationController clientComm,
			Table table) throws RemoteException{
		
		this.clientComm = clientComm;
		this.gameController = new GameController(table);
		EventBus.register(this);
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
	 * Try to start a game.
	 */
	public void tryStartGame() {
		try {
			serverGame.tryStartGame();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addPlayer(int index, IPlayer player)
			throws RemoteException {
		gameController.addPlayer(index, player);
		EventBus.publish(new Event(Event.Tag.PLAYERS_CHANGED, ""));
	}
	
	@Override
	public void addPlayers(List<IPlayer> players) {
		gameController.addPlayers(players);
	}
	
	@Override
	public void removePlayer(IPlayer player) throws RemoteException {
		gameController.removePlayer(player);
		EventBus.publish(new Event(Event.Tag.PLAYERS_CHANGED, ""));
	}

	@Override
	public boolean requestCall(Bet bet) {
		
		if(serverGame != null){
			
			try {
				return serverGame.call(bet);
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
	/**
	 * Creates a new table
	 * 
	 * @param players A list with players that will be added to the table
	 * @param user The object representing the client's own player
	 * @param meIndex The index of where the client's own player is seated at
	 * the table
	 * @param maxPlayers The maximum allowed players at the table
	 */
	public void newTable(List<IPlayer> players, IPlayer user, int meIndex,
			int maxPlayers) {
		gameController.newTable(players, user, meIndex, maxPlayers);
	}

	@Override
	public void onEvent(Event evt) {
		Bet bet;
		
		switch(evt.getTag()) {
		case REQUEST_CALL:
			requestCall(new Bet(gameController.getUser(), 0));
			break;
		case REQUEST_CHECK:
			requestCheck(new Bet(gameController.getUser(), 0));
			break;
		case REQUEST_FOLD:
			requestFold(gameController.getUser());
			break;
		case REQUEST_RAISE:
			if(!(evt.getValue() instanceof Integer)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag() + "\nYou sent: " + evt.getValue().getClass());
			} else {
				Integer amount = (Integer) evt.getValue();
				IPlayer user = gameController.getUser();
				bet = new Bet(user, gameController.getCurrentBet().getValue() + amount);
				requestRaise(bet);
			}
			break;
		case PLAYER_SET_ACTIVE:
			if(!(evt.getValue() instanceof Boolean)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag() + "\nYou sent: " + evt.getValue().getClass());
			} else {
				if(setReadyToPlay(gameController.getUser(), true)) {
					tryStartGame();
				}
			}
			break;
			
		case LEAVE_TABLE:
			//TODO: Enough when leaving table?
			clientComm.terminateGame(this);
			
			try {
				serverGame.leaveGame(clientComm.getAccount());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			EventBus.unRegister(this);
			
			break;
			
		default:
			break;
			
		} // switch(Evt.getTag())
	} // OnEvent(Event evt)

	@Override
	public void showdownDone(List<IPlayer> winners) throws RemoteException {
		EventBus.publish(new Event(Event.Tag.PUBLISH_SHOWDOWN, winners));
	}

	@Override
	public void postBlind(Bet bet) {
		gameController.postBlind(bet);
	}

	@Override
	public void clearCurrentBet() {
		gameController.clearCurrentBet();
	}
	
}
