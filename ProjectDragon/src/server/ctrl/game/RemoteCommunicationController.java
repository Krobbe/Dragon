/**
 * 
 */
package server.ctrl.game;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import common.database.DatabaseCommunicator;
import common.database.IDBAccount;
import common.model.player.Account;
import common.model.player.Balance;
import common.model.player.IPlayer;
import common.model.player.Player;
import common.model.player.hand.Hand;
import common.remote.IClient;
import common.remote.IClientGame;
import common.remote.IServer;
import common.remote.IServerGame;
import common.remote.ServerStarter;





/**
 * This serverside class handles all general communication to and from the 
 * client.
 * 
 * @author robinandersson
 *
 */
public class RemoteCommunicationController extends UnicastRemoteObject
											implements IServer, IDBAccount {
	
	// A map containing all logged in players and references to their
	// respective communication controllers
	private Map<Account, IClient> clients;
	
	// A list showing active games
	private List<RemoteGameController> activeGames;
	
	private DatabaseCommunicator dbc = DatabaseCommunicator.getInstance();
	
	public RemoteCommunicationController() throws RemoteException {
		super();
		
		Scanner in = new Scanner(System.in);
		int port = Registry.REGISTRY_PORT;
		boolean correctValue = false;
		
		// Prompts the user for port number to open the RMI-registry on
		while(!correctValue) {
			
			System.out.println("Input port to open RMI-registry." +
					" (Leave blank for default - 1099)");
			
			String portString = in.nextLine();
			
			if(portString.equals("")){
				correctValue = true;
			}
			
			else {
				try {
					port = Integer.parseInt(portString);
					correctValue = true;
				} catch (NumberFormatException e) {
					System.out.println("*** Incorrect input, try again ***");
					e.printStackTrace();
				}
			}
			
		} //while(!correctValue
		
		new ServerStarter(this, port);
		clients = new TreeMap<Account, IClient>();
		activeGames = new LinkedList<RemoteGameController>();
	}
	
	@Override
	public Account login(IClient client, String accountName,
								String accountPassword) throws RemoteException {
		
		Account account = loadAccount(accountName);
		
		if(account != null && account.getPassWord().equals(accountPassword)){
			
			// TODO Change to player instead?
			// TODO Login failed exception istŠllet fšr att returnera ett
			// null-objekt?
			clients.put(account, client);
			System.out.println("*** " + account.getUserName() + " logged in"
																	+ " ***");
			
			return account;
		}
		
		return null;
	}
	
	@Override
	public boolean logout(Account account) throws RemoteException{
		// TODO Do more when logging out a player? Save active games or
		// something?
		
		if(clients.remove(account) != null) {
			for(RemoteGameController serverGame : activeGames){
				serverGame.leaveGame(account);
			}
			return true;
		}
		
		return false;
	}

	@Override
	public IServerGame createGame(Account account, IClientGame clientGame,
			int entranceFee, int maxPlayers, int playerStartingChips) {

		RemoteGameController newGame = null;
		
		// Checks if the supplied account has been added by the server earlier
		if(isLoggedIn(account)) {
			
			Account serversAccount = null;
			
			/*
			 * Gets the actual Account instance already stored. The supplied
			 * account from the client is merely a clone (to prevent security
			 * issues, for instance to prevent a modified client from changing
			 * the account object illegally
			 */
			// TODO Better way to get the serverSideAccount?
			for(Account acc : clients.keySet()){
				if(account.equals(acc)){
					serversAccount = acc;
					break;
				}
			}
			
			/*
			 *  Checks if the player has enough "money" to pay the game's
			 *	entrance fee
			*/
			if(serversAccount != null && serversAccount.getBalance().getValue() >= entranceFee ){
			
				try {
					newGame = new RemoteGameController(this, maxPlayers,
											entranceFee, playerStartingChips);
					
				} catch (RemoteException e) {
					// TODO Better exception handling for when not able to
					// create game?
					return null;
				}
				
				activeGames.add(newGame);
				// TODO Change the constructor in Balance to receive playerChips
				// as parameter instead of creating a Balance-class here? 
				IPlayer player = new Player(new Hand(), account.getUserName(),
						new Balance(playerStartingChips));
				
				newGame.addPlayer(player, clientGame);
				
				// Removes the entranceFee from the user's Account instance
				// TODO Remove from database. Here or somewhere else?
				serversAccount.getBalance().removeFromBalance(entranceFee);
				System.out.println("*** " + account.getUserName() + " created a"
						+ " game ***");
			}
			
		}
		
		// Returns null if the table-creation was unsuccessful
		return newGame;
	}
	
	@Override
	public IServerGame joinGame(Account account, IClientGame clientGame,
			int gameID) {
		
		RemoteGameController game = null;
		
		if(isLoggedIn(account)) {
			
			try {
				
				for(RemoteGameController serverGame : activeGames) {
					if(serverGame.getGameID() == gameID) {
						game = serverGame;
						break;
					}
				}
			
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(game != null) {
				
				// TODO It is weird that a new Player object has to get a Hand.
				// A new Player always has an empty hand anyway.
				// Fix when possible
				IPlayer player = new Player(
						new Hand(), account.getUserName(),
						new Balance(game.getStartingChips()));
				
				game.addPlayer(player, clientGame);
			}
		}
		
		return game;
	}


	@Override
	public Account loadAccount(String accountName) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			ResultSet rs =
			myStmt.executeQuery("SELECT * FROM Accounts WHERE userName = '" 
			+ accountName + "'");
			if (rs.next()) {
				// Accountinformation
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String passWord = rs.getString(4);
				String balance = rs.getString(5);
				int x = Integer.parseInt(balance);

				Account a = new Account(firstName, lastName, accountName, 
																	passWord);
				a.getBalance().addToBalance(x);
				return a;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean createAccount(Account account) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			
			//Accountinformation
			String userName = account.getUserName();
			if(loadAccount(userName) != null) {
				return false;
			}
			
			String firstName = account.getFirstName();
			String lastName = account.getLastName();
			String passWord = account.getPassWord();
			int balance = account.getBalance().getValue();
			
			String updateString = "INSERT INTO Accounts VALUES('" + userName + 
					"', '" + firstName + "', '" + lastName + "', '" + 
					passWord + "', '" + balance + "')";
			myStmt.executeUpdate(updateString);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		System.out.println("*** Account with username: \"" + account.getUserName() + "\" "
				+ " ***");
		return true;
	}

	@Override
	public boolean updateAccount(Account account, String oldPassword) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			ResultSet rs =
					myStmt.executeQuery("SELECT * FROM Accounts WHERE userName" +
							" = '" + account.getUserName() + "'");
			
			if(rs.next()) {
				if(!rs.getString(4).equals(oldPassword)) {
					System.out.println("*** Wrong password! *** ");
					return false;
				}
			} else {
				System.out.println("*** There exists no account with userName: " +
						account.getUserName() + " ***");
				return false;
			}
			
			//Accountinformation
			String userName = account.getUserName();
			String firstName = account.getFirstName();
			String lastName = account.getLastName();
			String newPass = account.getPassWord();
			int balance = account.getBalance().getValue();
			
			String updateString = "UPDATE Accounts SET firstName = '" + firstName + 
					"', lastName = '" + lastName + "', passWord = '" + 
					newPass + "', balance = '" + balance + "' WHERE userName = '"
					+ userName + "'";
			
			// TODO Why is the variable "up" not used?
			int up = myStmt.executeUpdate(updateString);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public boolean deleteAccount(Account account, String oldPassword) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			ResultSet rs =
					myStmt.executeQuery("SELECT * FROM Accounts WHERE userName" +
							" = '" + account.getUserName() + "'");
			
			if(rs.next()) {
				if(!rs.getString(4).equals(oldPassword)) {
					System.out.println("*** Wrong password! ***");
					return false;
				}
			} else {
				System.out.println("*** There exists no account with userName: " +
						account.getUserName() + " ***");
				return false;
			}
			String updateString = "DELETE FROM Accounts WHERE userName = '"
					+ account.getUserName() + "'";
			myStmt.executeUpdate(updateString);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Checks if the account is registered as logged in
	 * @param account
	 * @return true if the account is logged in
	 */
	public boolean isLoggedIn(Account account){
		return clients.containsKey(account);
	}
	
	@Override
	public List<IServerGame> getActiveGames(Account account)
			throws RemoteException {
		
		List<IServerGame> tempList = new LinkedList<IServerGame>();
		
		for(RemoteGameController serverGame : activeGames) {
			tempList.add(serverGame);
			//TODO: Remove when a game is shutted down.
		}
		
		return tempList;
	}

	@Override
	public String testPrint() throws RemoteException {
		return "Test print successful!";
	}
}
