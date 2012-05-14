/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import event.Event;
import event.EventHandler;

import model.card.iCard;
import model.game.Pot;
import model.player.*;
import model.player.hand.iHand;

import remote.iClientGame;
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
public class RemoteGameController extends UnicastRemoteObject implements iServerGame, EventHandler{

	GameController gameController;
	//TODO Ta bort den "clientGames" som inte beh�vs (n�r det blir tydligt vad som beh�vs)
	// A map containing all logged in players and another map containing their
	// active players and references to every respective player objects remote
	// game controller
	//	Map<Account, Map<iPlayer, client.ctrl.game.RemoteGameController>> clientGames;
	//	Map<RemoteGameController, Map<iPlayer, client.ctrl.game.RemoteGameController>> clientGames;
	Map<iPlayer, iClientGame> playerReferences;
	
	LinkedList<iPlayer> playerList;
	
	public RemoteGameController() throws RemoteException {
		this(new GameController());
	}
	
	public RemoteGameController(GameController gameController) throws RemoteException {
		super();
		playerList = new LinkedList<iPlayer>();
		playerReferences = new TreeMap<iPlayer, iClientGame>();
		this.gameController = gameController;
	}
	
	/**
	 * Adds a player to the game and a reference to the player's client
	 * 
	 * @param player The player to be added to the game
	 * @param clientGame The reference to the added player
	 */
	public void addPlayer(iPlayer player, iClientGame clientGame) {
		playerReferences.put(player, clientGame);
		playerList.add(player);
	}
	
	/**
	 * Removes a player from the game and the reference to the player's client
	 * 
	 * @param player The player to be removed from the game
	 * @param clientGame The reference to the removed player
	 */
	public void removePlayer(iPlayer player, iClientGame clientGame) {
		playerReferences.remove(player);
		playerList.remove(player);
	}
	
	@Override
	public LinkedList<iPlayer> getPlayers() throws IllegalCallException,
			RemoteException {
		return playerList;
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
	
	@Override
	public void onEvent(Event evt) {

		switch (evt.getTag()) {

		case SERVER_FOLD:
			iPlayer player;
			if (!(evt.getValue() instanceof iPlayer)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				player = (iPlayer) evt.getValue();
				for (iClientGame client : playerReferences.values()) {
					try {
						//TODO: Den h�r metoden returnerar en boolean. Vad ska vi g�ra med den?
						client.fold(player);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;
			
		case SERVER_UPDATE_BET:
			Bet bet;
			if (!(evt.getValue() instanceof Bet)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				bet = (Bet)evt.getValue();
				for (iClientGame client : playerReferences.values()) {
					try {
						//TODO: Den h�r metoden returnerar en boolean. Vad ska vi g�ra med den?
						client.betOccurred(bet);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;

		case SERVER_DISTRIBUTE_CARDS:
			Map<iPlayer, iHand> playerHands;
			if (!(evt.getValue() instanceof Map)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				playerHands = (Map<iPlayer, iHand>) evt.getValue();
				iHand hand;
				iClientGame client;
				for (iPlayer p : playerHands.keySet()) {
					hand = p.getHand();
					client = playerReferences.get(p);
					try {
						client.setHand(p, hand);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}

			break;

		case SERVER_CREATE_TABLE:
			List<iPlayer> players;
			if (!(evt.getValue() instanceof List)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				players = (List<iPlayer>)evt.getValue();
				iClientGame client;
				int meIndex = 0;
				for(iPlayer p: players) {
					client = playerReferences.get(p);
					try {
						client.newTable(players, meIndex);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					meIndex ++;
				}
			}
			break;

		case SERVER_DISTRIBUTE_POT:

			Bet b;
			if (!(evt.getValue() instanceof Bet)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				b = (Bet)evt.getValue();
				for(iClientGame client : playerReferences.values()) {
					try {
						client.balanceChanged(b);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}

			break;

		case SERVER_UPDATE_POT:
			Pot pot;
			if (!(evt.getValue() instanceof Pot)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				pot = (Pot) evt.getValue();
				for (iClientGame client : playerReferences.values()) {
					try {
						client.setPot(pot);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}

			break;

		case SERVER_NEW_ROUND:
			for (iClientGame client : playerReferences.values()) {
				try {
					client.newRound();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			break;

		case SERVER_SET_TURN:
			int i;
			if (!(evt.getValue() instanceof Integer)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				i = (Integer) evt.getValue();
				for (iClientGame client : playerReferences.values()) {
					try {
						client.setTurn(i);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;

		case SERVER_SET_PLAYER_UNACTIVE:
			iPlayer p;
			if (!(evt.getValue() instanceof iPlayer)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				p = (iPlayer) evt.getValue();
				for (iClientGame client : playerReferences.values()) {
					try {
						client.setActive(p, false);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;

		case SERVER_SET_OWN_CURRENT_BET:
			if (!(evt.getValue() instanceof Bet)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				b = (Bet) evt.getValue();
				for (iClientGame client : playerReferences.values()) {
					try {
						client.setPlayerOwnCurrentBet(b);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;

		case SERVER_ADD_TABLE_CARDS:
			List<iCard> cards;
			if (!(evt.getValue() instanceof List)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				cards = (List<iCard>) evt.getValue();
				for (iClientGame client : playerReferences.values()) {
					try {
						client.addCommunityCards(cards);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void startGame() throws IllegalCallException, RemoteException {

		for (int i = 0; i < playerList.size(); i++) {
			gameController.addPlayer(playerList.get(i));
		}

		// TODO Handle start game scenario

	}
}
