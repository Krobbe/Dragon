/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.postgresql.util.PSQLException;

import database.DatabaseCommunicator;
import database.IDBAccount;

import model.player.Account;
import model.player.Balance;
import model.player.Player;
import model.player.IPlayer;
import model.player.hand.Hand;


import remote.ServerStarter;
import remote.IClient;
import remote.IClientGame;
import remote.IServer;
import remote.IServerGame;

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
		new ServerStarter(this);
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
		
		
		// Uncomment and fix!
		/*
		
		if(clients.remove(account) != null) {
			for(RemoteGameController serverGame : activeGames){
				serverGame.
			}
			return true;
		}
		
		*/
		
		return true;
	}

	@Override
	public IServerGame createGame(Account account, IClientGame clientGame,
			int entranceFee, int maxPlayers, int playerStartingChips) {

		RemoteGameController newGame = null;
		
		// Checks if the supplied account has been added by the server earlier
		if(isLoggedIn(account)) {
			
			Account serverSideAccount = null;
			
			/*
			 * Gets the actual Account instance already stored. The supplied
			 * account from the client is merely a clone (to prevent security
			 * issues, for instance to prevent a modified client from changing
			 * the account object illegally
			 */
			// TODO Better way to get the serverSideAccount?
			for(Account acc : clients.keySet()){
				if(account.equals(acc)){
					serverSideAccount = acc;
					break;
				}
			}
			
			/*
			 *  Checks if the player has enough "money" to pay the game's
			 *	entrance fee
			*/
			if(serverSideAccount != null && serverSideAccount.getBalance().getValue() >= entranceFee ){
			
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
				serverSideAccount.getBalance().removeFromBalance(entranceFee);
				System.out.println("*** " + account.getUserName() + " created a"
						+ " game ***");
			}
			
		}
		
		// Returns null if the table-creation was unsuccessful
		return newGame;
	}
	
	@Override
	public IServerGame joinGame(Account account, IPlayer player,
			IClientGame clientGame, int gameIndex) {
		
		IServerGame game = null;
		
		if(isLoggedIn(account) && gameIndex < activeGames.size() ) {
			
			game = activeGames.get(gameIndex);
			((RemoteGameController) game).addPlayer(player, clientGame);
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
					System.out.println("Wrong password!");
					return false;
				}
			} else {
				System.out.println("There exists no account with userName: " +
						account.getUserName());
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
					System.out.println("Wrong password!");
					return false;
				}
			} else {
				System.out.println("There exists no account with userName: " +
						account.getUserName());
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
		}
		
		return tempList;
	}

	@Override
	public String testPrint() throws RemoteException {
		return "Woop woop!";
	}
}
