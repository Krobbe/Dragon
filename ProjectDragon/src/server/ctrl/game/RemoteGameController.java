/**
 * 
 */
package server.ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import server.event.Event;
import server.event.EventBus;
import server.event.EventHandler;

import common.database.DatabaseCommunicator;
import common.database.IDBSaveGame;
import common.model.card.ICard;
import common.model.game.Pot;
import common.model.player.Account;
import common.model.player.Balance;
import common.model.player.Bet;
import common.model.player.IPlayer;
import common.model.player.Player;
import common.model.player.hand.Hand;
import common.model.player.hand.IHand;
import common.remote.IClientGame;
import common.remote.IServerGame;
import common.utilities.IllegalCheckException;
import common.utilities.IllegalRaiseException;

/**
 * This serverside class manages all game communication to and from the client.
 * The class does also stores the gameID which is a unique number for each game
 * ever made.
 * 
 * @author robinandersson
 * @author lisastenberg
 *
 */
public class RemoteGameController extends UnicastRemoteObject
										implements IServerGame, EventHandler,
										IDBSaveGame {

	private GameController gameController;
	private RemoteCommunicationController serverComm;
	private DatabaseCommunicator dbc = DatabaseCommunicator.getInstance();
	private LinkedList<IPlayer> gamePlacements = new LinkedList<IPlayer>();
	
	/* A map containing all logged in players and another map containing their
	 * active players and references to every respective player objects remote
	 * game controller
	*/
	private Map<IPlayer, IClientGame> playerReferences;
	
	private int gameID;
	
	public RemoteGameController(
			RemoteCommunicationController remoteCommunicationController,
			int maxPlayers,	int entranceFee, int startingChips)
													throws RemoteException {
		
		this(remoteCommunicationController, new GameController(maxPlayers, entranceFee, startingChips));
	}
	
	public RemoteGameController(
			RemoteCommunicationController remoteCommunicationController,
			GameController gameController) throws RemoteException {
		
		super();
		this.serverComm = remoteCommunicationController;
		this.gameController = gameController;
		playerReferences = new TreeMap<IPlayer, IClientGame>();
		
		gameID = calculateGameID();
		Calendar cal = Calendar.getInstance();
		String dateString = "" + cal.get(Calendar.DAY_OF_MONTH) + 
				cal.get(Calendar.MONTH) + cal.get(Calendar.YEAR);
		saveGame(gameID, dateString);
		EventBus.register(this);
	}
	
	/**
	 * Adds a player to the game and a reference to the player's client
	 * 
	 * @param player The player to be added to the game
	 * @param clientGame The reference to the added player
	 */
	public void addPlayer(IPlayer player, IClientGame clientGame) {

		LinkedList<IPlayer> clientPlayers =
				new LinkedList<IPlayer>(playerReferences.keySet());
		
		playerReferences.put(player, clientGame);
		
		
		gameController.addPlayer(player);
		int newPlayerIndex = gameController.getTable().
				getPlayers().indexOf(player);

		
		try {
			for(IPlayer clientPlayer : clientPlayers) {
					playerReferences.get(clientPlayer).
						addPlayer(newPlayerIndex, player);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void leaveGame(Account account) throws RemoteException {
		if(serverComm.isLoggedIn(account)) {
			
			IPlayer player = new Player(new Hand(),
					account.getUserName(), new Balance());
			
			leaveGame(player);

		}
	}
	
	/**
	 * Leave a game
	 * @param player
	 * @throws RemoteException
	 */
	public void leaveGame(IPlayer player) throws RemoteException {
		IPlayer playerToLeave = player;
		
		playerReferences.remove(playerToLeave);
		gameController.removePlayer(playerToLeave);
		
		for(IClientGame clientGame : playerReferences.values()) {
			clientGame.removePlayer(playerToLeave);
		}
	}
	
	@Override
	public List<IPlayer> getPlayers() throws RemoteException {
		
		// TODO Is this a deep copy?
		return new LinkedList<IPlayer>(gameController.getTable().getPlayers());
	}

	@Override
	public int getGameID() throws RemoteException {
		return gameID;
	}
	
	@Override
	public int getMaxPlayers() throws RemoteException {
		return gameController.getMaxPlayers();
	}

	@Override
	public int getEntranceFee() throws RemoteException {
		return gameController.getEntranceFee();
	}
	
	@Override
	public int getStartingChips() {
		return gameController.getStartingChips();
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
	public boolean fold(IPlayer player) {
		if(gameController == null){
			return false;
		}
		return gameController.fold(player);
	}
	
	@Override
	public void onEvent(Event evt) {

		switch (evt.getTag()) {

		case SERVER_FOLD:
			IPlayer player;
			if (!(evt.getValue() instanceof IPlayer)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				player = (IPlayer) evt.getValue();
				for (IClientGame client : playerReferences.values()) {
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
				for (IClientGame client : playerReferences.values()) {
					try {
						//TODO: Den h�r metoden returnerar en boolean. Vad ska vi g�ra med den?
						client.betOccurred(bet);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;
			
		case SERVER_POST_BLIND:
			Bet blindBet;
			if (!(evt.getValue() instanceof Bet)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				blindBet = (Bet) evt.getValue();
				for (IClientGame client : playerReferences.values()) {
					try {
						client.postBlind(blindBet);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				
			}
			break;
			
		case SERVER_DISTRIBUTE_CARDS:
			Map<IPlayer, IHand> playerHands;
			if (!(evt.getValue() instanceof Map)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());			
			} else {
				playerHands = (Map<IPlayer, IHand>) evt.getValue();
				IHand hand;
				IClientGame client;
				for (IPlayer p : playerHands.keySet()) {
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
			
		case SERVER_CLEAR_CURRENT_BET:
			for (IClientGame client : playerReferences.values()) {
				try {
					client.clearCurrentBet();
				} catch (RemoteException e) {
					e.printStackTrace();
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
				for(IClientGame client : playerReferences.values()) {
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
				for (IClientGame client : playerReferences.values()) {
					try {
						client.setPot(pot);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}

			break;

		case SERVER_NEW_ROUND:
			for (IClientGame client : playerReferences.values()) {
				try {
					client.newRound();
					for(IPlayer pl : gameController.getTable().getActivePlayers()) {
						client.setActive(pl, true);
					}
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
				for (IClientGame client : playerReferences.values()) {
					try {
						client.setTurn(i);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;
			
		case SERVER_NEXT_TURN:
			IPlayer nextPlayer;
			if (!(evt.getValue() instanceof IPlayer)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				nextPlayer = (IPlayer) evt.getValue();
				for (IClientGame client : playerReferences.values()) {
					try {
						client.nextTurn(nextPlayer);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;

		case SERVER_SET_PLAYER_UNACTIVE:
			IPlayer p;
			if (!(evt.getValue() instanceof IPlayer)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				p = (IPlayer) evt.getValue();
				for (IClientGame client : playerReferences.values()) {
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
				for (IClientGame client : playerReferences.values()) {
					try {
						client.setPlayerOwnCurrentBet(b);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;


		case SERVER_ADD_TABLE_CARD:
			ICard card;

			if (!(evt.getValue() instanceof ICard)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {

				card = (ICard)evt.getValue();
				for (IClientGame client : playerReferences.values()) {

					try {
						client.addCommunityCard(card);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
			break;
			
		case SERVER_SHOWDOWN_DONE:
			List<IPlayer> winners;
			if (!(evt.getValue() instanceof List)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				winners = (List<IPlayer>)evt.getValue();
				for (IClientGame client : playerReferences.values()) {

					try {
						client.showdownDone(winners);
						for(IPlayer pl : gameController.getTable().getPlayers()) {
							pl.setStillInGame(false);
							client.setHand(pl, pl.getHand());
						}
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
	public boolean isReadyToStart(Account account, IPlayer player, boolean isReady)
													throws RemoteException {
		
		/*
		 * Security check, checks if the Account instance is stored as logged in
		 * and so that the player object has got the same username as said
		 * Account instance.
		 */
		if(serverComm.isLoggedIn(account) ||
				account.getUserName().equals(player.getName())){
			
			//Finds and sets the server-side Player object's variable that
			//shows if the player is ready to play or not
			for(IPlayer storedPlayer : playerReferences.keySet()){
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

		boolean allReady = true;

		if(gameController != null) {
			if (playerReferences.keySet().size() > 1) {
				// Checks if all the players are ready to start the game
				for (IPlayer player : playerReferences.keySet()) {
					if (!player.isStillInGame()) {
						allReady = false;
					}
					
					if(player.getBalance().getValue() == 0 && !gamePlacements.contains(player)) {
						gamePlacements.addFirst(player);
						leaveGame(player);
					}
				}
				if(allReady) {
					// TODO Handle start game scenario
					gameController.nextRound();
				}
			} else if(!gamePlacements.isEmpty()) {
				IPlayer winner = null;
				for(IPlayer player : playerReferences.keySet()) {
					if(player.getBalance().getValue() != 0) {
						winner = player;
					}
				}
				gamePlacements.addFirst(winner);
				gameOver();
			}
		}
	}
	
	private void gameOver() {
		for(IPlayer player : gamePlacements) {
			savePlacement("" + gameID, player, (gamePlacements.indexOf(player) + 1));
		}
	}

	@Override
	public void saveGame(int gameID, String date) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			if(gameID > 0) {
				int up = myStmt.executeUpdate("INSERT INTO Games VALUES('" + gameID + "', '"
								+ date + "')");
				if(up == 0) {
					System.out.println("Game with gameID" + gameID + "already exists");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void savePlacement(String gameID, IPlayer player, int placement) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			int up = myStmt.executeUpdate("INSERT INTO PlayedGames VALUES('" + gameID + "', '"
							+ player.getName() + "', '" + placement + "')");
			if(up == 0) {
				System.out.println("Game with gameID" + gameID + " has already" +
						"saved a placement for " + player);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int calculateGameID() {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			ResultSet rs =
					myStmt.executeQuery("SELECT max(gameID) FROM Games");
			if(rs.next()) {
				int max = Integer.parseInt(rs.getString(1));
				return (max + 1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}

}
