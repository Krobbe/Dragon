/**
 * 
 */
package client.ctrl.game;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.event.Event;
import client.event.EventBus;
import client.event.EventHandler;
import client.model.game.Table;

import common.model.player.Account;
import common.model.player.Balance;
import common.model.player.IPlayer;
import common.model.player.Player;
import common.model.player.User;
import common.model.player.hand.Hand;
import common.remote.IClient;
import common.remote.IServer;
import common.remote.IServerGame;



/**
 * This clientside class handles all general communication to and from the
 * server.
 * 
 * @author robinandersson
 */
public class RemoteCommunicationController extends UnicastRemoteObject
											implements IClient, EventHandler {

	// A map with games the user is currently playing represented by the remote
	// game controller and Player object for that specific game
	private Map<RemoteGameController, IPlayer> activeGames;
	
	// The reference to the server
	private IServer serverComm;
	
	// TODO Flytta "lagringen" av account till ett mer passande st�lle?
	private Account account;
	
	public RemoteCommunicationController() throws RemoteException{
		activeGames = new HashMap<RemoteGameController, IPlayer>();
		account = null;
		// TODO Set where to search for server. Comment that son'uvabitch

		EventBus.register(this);
	}
	
	/** 
	 * Tries to get a connection with the server on the default port and returns
	 * a reference if successful
	 * 
	 * @return The reference to the server
	 */
	public IServer connectToServer(){
		return connectToServer("", Registry.REGISTRY_PORT);
	}
	
	/** 
	 * Tries to get a connection with the server on the specified port number
	 * and returns a reference if successful
	 * 
	 * @param port The port number of the searched server
	 * @return The reference to the server
	 */
	public IServer connectToServer(String ipAdress, int port){
		IServer server = null;
		Registry registry;
		
	    try {
	    	if(ipAdress.equals("") && port == 0) {
	    		ipAdress = "localhost";
	    		port = Registry.REGISTRY_PORT;
	    	}
	    	else if(port == 0) {
	    		port = Registry.REGISTRY_PORT;
	    	}
	    	
	    	else if(ipAdress.equals("")) {
	    		ipAdress = "localhost";
	    	}
	    		registry = LocateRegistry.getRegistry(ipAdress, port);

	        server = (IServer) registry.lookup(IServer.REMOTE_NAME);
	        System.out.println("*** Connection established on port: " + port
	        								+ " at IP: " + ipAdress + " ***");
	    }
	    
	    catch (Exception e) {
	    	System.out.println("*** Failed to connect to server: ***");
	    	System.out.println();
	        System.err.println("Client exception: " + e.toString());
	        e.printStackTrace();
	    }
	    return server;
	}
	
	
	/** 
	 * Tries to login with the provided account name and password. Also passes
	 * a reference from the clients controller that handles game communication
	 * with the server
	 * 
	 * @param clientGame The reference to this clients game controller
	 * @param accountName The name of the account
	 * @param accountPassword The password associated with the account name.
	 * @return The Account instance containing useful information and used as
	 * security clearance
	 */
	public boolean login(IClient client, String accountName, 
			String accountPassword, String ipAdress, int port){
		
		serverComm = connectToServer(ipAdress, port);
		
		if(serverComm != null){
			
			// TODO Remove this first try-catch part after connection through
			// network has been established. Also remove testPrint from
			// IServerGame
			try {
				System.out.println(serverComm.testPrint());
			} catch (RemoteException e) {
				System.out.println("Test print unsuccessful");
				e.printStackTrace();
			}
		
			try {	
				this.account = serverComm.login(client, accountName, accountPassword);
				
				if(this.account != null){
					EventBus.publish(new Event(Event.Tag.LOGIN_SUCCESS, ""));
					return true;
				} else {
					EventBus.publish(new Event(Event.Tag.LOGIN_FAILED, ""));
				}
				
			} catch (RemoteException e) {
				// TODO Handle login failure better
				EventBus.publish(new Event(Event.Tag.LOGIN_FAILED, ""));
				System.out.println("*** Connection problem, failed to login ***");
				e.printStackTrace();
			}
		}
		
		return false;
		
	}
	
	public boolean logout() {
		
		try {
			serverComm.logout(this.account);
			return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public Account getAccount(){
		return this.account;
	}	
	
	private boolean tryRegisterAccount(String userName, String firstName, 
			String lastName, String passWord) {
		
		serverComm = connectToServer();

		Account tmp = new Account(firstName, lastName, userName, passWord);
		try {
			
			if (serverComm.createAccount(tmp)) {
				EventBus.publish(new Event(Event.Tag.REGISTER_SUCCESS, ""));
				return true;
			} else {
				EventBus.publish(new Event(Event.Tag.REGISTER_FAILED, ""));
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean createGame(int entranceFee, int playerStartingChips,
			int maxPlayers) {
		
		if(serverComm == null) {
			return false;
		}
		
		Player player = new Player(new Hand(),
						getAccount().getUserName(),
						new Balance(playerStartingChips));
		
		IPlayer user = new User(player);
		
		Table table = new Table(user, 0, maxPlayers);
		
		try {
			
			RemoteGameController clientGame = new RemoteGameController(this, 
					table);
			
			IServerGame serverGame = serverComm.createGame(getAccount(),
					clientGame, entranceFee, maxPlayers, playerStartingChips);
			clientGame.setServerGame(serverGame);
			
			activeGames.put(clientGame, user);
			
			
			
			return true;
			
		} catch (RemoteException e) {
			// TODO Better handling when not able to create a game
			System.out.println("*** Communication error," + 
												"couldn not create game ***");
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Returns the active games that players are able to join 
	 * @return A list with the active games that players are able to join
	 */
	public List<IServerGame> getActiveGames() {
		
		try {
			return serverComm.getActiveGames(this.account);
		} catch (RemoteException e) {
			System.out.println("*** Could not get active games! ***");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/** 
	 * Tries to join the table with the specified index. Passes the unique
	 * Account object along for security clearance. Returns the iPlayer object
	 * that has been assigned to the table if successful.
	 * 
	 * @param account The users unique Account object
	 * @param gameIndex The index of the game that the user wants to join
	 * @return true if the game was successfully joined
	 */
	public boolean joinGame(int gameID) {

		if(serverComm == null) {
			return false;
		}
		
		IServerGame serverGame = null;
		
		Account account = getAccount();

		try {
			
			RemoteGameController clientGame = new RemoteGameController(this);
			
			serverGame = serverComm.joinGame(account, clientGame,
					gameID);
			
			if(serverGame == null) {
				return false;
			}
			
			IPlayer user = null;
			List<IPlayer> playerList = serverGame.getPlayers();
			
			//TODO Smelly code!!! Improve!
			for(IPlayer player : playerList) {
				if(player != null && player.getName().
						equals(account.getUserName())) {
					user = new User((Player) player);
				}
			}
			
			if(user == null) {
				return false;
			}
			
			int userIndex = serverGame.getPlayers().indexOf(user);
			
			clientGame.setServerGame(serverGame);
			clientGame.newTable(playerList, user, userIndex,
					serverGame.getMaxPlayers());
			activeGames.put(clientGame, user);
			
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return true;
		
	}
	
	public void terminateGame(RemoteGameController clientGame) {
		activeGames.remove(clientGame);
	}
	
	private void updateAccount() {
		try {
			serverComm.updateAccount(this.account, account.getPassWord());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onEvent(Event evt) {
		
		switch(evt.getTag()) {
		
		case TRY_LOGIN:
			ArrayList<char[]> login;
			if(!(evt.getValue() instanceof ArrayList)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				login = (ArrayList<char[]>)evt.getValue();
				String userName, passWord, ipAdress;
				int port = 0;
				userName = new String(login.get(0));
				passWord = new String(login.get(1));
				ipAdress = new String(login.get(2));
				try {
					port = Integer.parseInt(new String(login.get(3)));
				} catch(NumberFormatException e) {
					System.out.println("Wrong evt.getValue() for evt.getTag(): "
							+ evt.getTag());
				}
				login(this, userName, passWord, ipAdress, port);
			}
			break;
			
		case TRY_LOGOUT:
			
			// TODO Handle logout better
			logout();
			EventBus.publish(new Event(Event.Tag.LOGOUT_SUCCESS, ""));
			
			
		case TRY_REGISTER:
			ArrayList<char[]> accountInfo;
			if(!(evt.getValue() instanceof ArrayList)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				accountInfo = (ArrayList<char[]>)evt.getValue();
				String userName, firstName, lastName, passWord;
				userName = new String(accountInfo.get(0));
				firstName = new String(accountInfo.get(1));
				lastName = new String(accountInfo.get(2));
				passWord = new String(accountInfo.get(3));
				tryRegisterAccount(userName, firstName, lastName, passWord);
			}
			break;
			
		case CREATE_TABLE:
			
			ArrayList<String> tableInfo;
			if(!(evt.getValue() instanceof ArrayList)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				tableInfo = (ArrayList<String>) evt.getValue();
				
				ArrayList<Integer> tableInfoParsed =
													new ArrayList<Integer>();
				
				for(String numberString : tableInfo) {
					try{
						tableInfoParsed.add(Integer.parseInt(numberString));
						
					} catch (NumberFormatException e){
						System.out.println("Wrong evt.getValue() for evt.getTag(): "
								+ evt.getTag());
					}
				}
				
				if(tableInfoParsed.size() == 3 &&
						createGame(tableInfoParsed.get(0),
						tableInfoParsed.get(1), tableInfoParsed.get(2))) {
					EventBus.publish(new Event(Event.Tag.PLAYERS_CHANGED, ""));
				}
			}
			
			break;
			
		case GET_ACTIVE_GAMES:
			EventBus.publish(new Event(Event.Tag.PUBLISH_ACTIVE_GAMES,
															getActiveGames()));
			break;
			
		case GET_ACCOUNT_INFORMATION:
			EventBus.publish(new Event(Event.Tag.PUBLISH_ACCOUNT_INFORMATION,
															getAccount()));
			break;
		
		case ACCOUNT_CHANGED:
			Account acc;
			if(!(evt.getValue() instanceof Account)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				acc = (Account) evt.getValue();
				this.account.getBalance().addToBalance(acc.getBalance().getValue());	
				updateAccount();
				EventBus.publish(new Event(Event.Tag.PUBLISH_ACCOUNT_INFORMATION, getAccount()));
			}
			break;
			
		case JOIN_TABLE:
			int index = (Integer)evt.getValue();
			IServerGame sg = getActiveGames().get(index-1);
			try {
				int gameID = sg.getGameID();
				
				if(joinGame(gameID)) {
					//TODO: publish here?
					EventBus.publish(new Event(Event.Tag.PLAYERS_CHANGED, ""));
				} else {
					System.out.println("Unable to join table with id: " + evt.getValue());
				}
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
