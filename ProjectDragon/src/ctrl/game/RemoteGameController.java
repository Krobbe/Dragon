/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
 * A class that manages all general communication to and from the server
 * 
 * @author robinandersson
 * @author lisastenberg
 *
 */
public class RemoteGameController extends UnicastRemoteObject
										implements iServerGame, EventHandler{

	GameController gameController;
	RemoteCommunicationController remoteCommunicationController;
	
	/* A map containing all logged in players and another map containing their
	 * active players and references to every respective player objects remote
	 * game controller
	*/
	Map<iPlayer, iClientGame> playerReferences;
	
	int entranceFee;
	int playerStartingChips;
	int maxPlayers;
	
	// TODO Simpler (less parameters) constructors?
	// - Convention? Only two constructors?
	public RemoteGameController(
				RemoteCommunicationController remoteCommunicationController)
													throws RemoteException {
		this(remoteCommunicationController, 8, 0, 1000);
	}
	
	public RemoteGameController(
			RemoteCommunicationController remoteCommunicationController,
			int maxPlayers,	int entranceFee, int playerStartingChips)
													throws RemoteException {
		
		this(remoteCommunicationController, new GameController(), maxPlayers,
				entranceFee, playerStartingChips);
	}
	
	public RemoteGameController(
			RemoteCommunicationController remoteCommunicationController,
			GameController gameController, int maxPlayers, int entranceFee,
			int playerStartingChips) throws RemoteException {
		
		super();
		this.remoteCommunicationController = remoteCommunicationController;
		playerReferences = new TreeMap<iPlayer, iClientGame>();
		this.maxPlayers = maxPlayers;
		this.entranceFee = entranceFee;
		this.playerStartingChips = playerStartingChips;
	}
	
	/**
	 * Adds a player to the game and a reference to the player's client
	 * 
	 * @param player The player to be added to the game
	 * @param clientGame The reference to the added player
	 */
	public void addPlayer(iPlayer player, iClientGame clientGame) {
		playerReferences.put(player, clientGame);
	}
	
	/**
	 * Removes a player from the game and the reference to the player's client
	 * 
	 * @param player The player to be removed from the game
	 * @param clientGame The reference to the removed player
	 */
	public void removePlayer(iPlayer player, iClientGame clientGame) {
		playerReferences.remove(player);
	}
	
	@Override
	public Set<iPlayer> getPlayers() throws IllegalCallException,
			RemoteException {
		return playerReferences.keySet();
	}
	
	@Override
	public int getMaxPlayers() throws RemoteException {
		return maxPlayers;
	}

	@Override
	public int getEntranceFee() throws RemoteException {
		return entranceFee;
	}

	@Override
	public boolean call(Bet bet) {
		if(gameController == null){
			return false;
		}
		return gameController.call(bet);
	}

	@Override
	public boolean check(Bet bet) throws IllegalCheckException {
		if(gameController == null){
			return false;
		}
		return gameController.check(bet);
	}

	@Override
	public boolean raise(Bet bet) throws IllegalRaiseException {
		if(gameController == null){
			return false;
		}
		return gameController.raise(bet);
	}

	@Override
	public boolean fold(iPlayer player) {
		if(gameController == null){
			return false;
		}
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
						//TODO: Den här metoden returnerar en boolean. Vad ska vi göra med den?
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
						//TODO: Den här metoden returnerar en boolean. Vad ska vi göra med den?
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

		case SERVER_ADD_TABLE_CARD:
			iCard card;
			if (!(evt.getValue() instanceof List)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				card = (iCard)evt.getValue();
				for (iClientGame client : playerReferences.values()) {
					try {
						client.addCommunityCard(card);
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
	public boolean isReadyToStart(Account account, iPlayer player, boolean isReady)
													throws RemoteException {
		
		/*
		 * Security check, checks if the Account instance is stored as logged in
		 * and so that the player object has got the same username as said
		 * Account instance.
		 */
		if(remoteCommunicationController.isLoggedIn(account) ||
				account.getUserName().equals(player.getName())){
			
			//Finds and sets the server-side Player object's variable that
			//shows if the player is ready to play or not
			for(iPlayer storedPlayer : playerReferences.keySet()){
				if(storedPlayer.getName().equals(player.getName())){
					storedPlayer.setStillInGame(isReady);
					return true;
				}
			}
		}
		
		// Returns false if the request to set the Player object as active was
		// illegal
		return false;
		
	}
	
	@Override
	public void tryStartGame() throws RemoteException {
		
		boolean isReadyToStart = true;
		
		// Checks if all the players are ready to start the game
		for(iPlayer player : playerReferences.keySet()){
			if(!player.isStillInGame()){
				// TODO a way to exit the whole method instead of setting the
				// variable?
				isReadyToStart = false;
			}
		}
		
		if(isReadyToStart){
			// TODO Handle start game scenario
			//gameController.startGame();
		}

	}




}
