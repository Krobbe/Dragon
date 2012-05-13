/**
 * 
 */
package client.ctrl.game;

import java.rmi.RemoteException;
import java.util.List;

import model.card.Card;
import model.card.iCard;
import model.game.Pot;
import model.player.Account;
import model.player.Bet;
import model.player.iPlayer;
import model.player.hand.iHand;

import remote.iClient;
import remote.iClientGame;
import remote.iServer;
import remote.iServerGame;

import utilities.IllegalCallException;

/**
 * @author robinandersson
 * @author lisastenberg
 */

public class RemoteGameController implements iClientGame, iServerRequest {
	
	private iServerGame serverGameController;
	private GameController gameController;
	
	// TODO Flytta "lagringen" av account till ett mer passande st�lle?
	private Account account;
	
	
	public RemoteGameController(){
		this(new GameController());
	}
	
	public RemoteGameController(GameController gameController){
		this.gameController = gameController;
	}

	@Override
	public boolean requestCall(Bet bet) {
		
		try {
			return serverGameController.call(bet);
		} catch (IllegalCallException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean requestCheck(Bet bet) {
		
		try {
			return serverGameController.check(bet);
		} catch (IllegalCallException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean requestRaise(Bet bet) {
		
		try {
			return serverGameController.raise(bet);
		} catch (IllegalCallException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean requestFold(iPlayer player) {
		
		try {
			return serverGameController.fold(player);
		} catch (IllegalCallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public void setActive(iPlayer player, boolean b) {
		gameController.setActive(player, b);
	}
	
	@Override
	public void setPot(Pot pot) {
		gameController.setPot(pot);
	}

	@Override
	public boolean fold(iPlayer player) {
		return gameController.fold(player);
	}

	@Override
	public boolean nextTurn(iPlayer nextPlayer) {
		return gameController.nextTurn(nextPlayer);
	}

	@Override
	public boolean betOccurred(Bet bet) {
		return gameController.betOccurred(bet);
	}

	@Override
	public void addCommunityCards(List<iCard> cards) {
		gameController.addCommunityCards(cards);
	}

	@Override
	public void setHand(iPlayer player, iHand hand) {
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

	@Override
	public void newRound() {
		gameController.newRound();
	}

	@Override
	public void balanceChanged(Bet bet) throws RemoteException {
		gameController.balanceChanged(bet);
	}

	@Override
	public void newTable(List<iPlayer> players, int meIndex) {
		gameController.newTable(players, meIndex);
	}

	
}
