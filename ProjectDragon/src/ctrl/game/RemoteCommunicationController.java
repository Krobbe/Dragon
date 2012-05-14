/**
 * 
 */
package ctrl.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import database.DatabaseCommunicator;
import database.IDBAccount;

import model.player.Account;
import model.player.Balance;
import model.player.Player;
import model.player.iPlayer;
import model.player.hand.Hand;

import remote.ServerStarter;
import remote.iClient;
import remote.iClientGame;
import remote.iServer;
import remote.iServerGame;

/**
 * @author robinandersson
 *
 */
public class RemoteCommunicationController extends UnicastRemoteObject
											implements iServer, IDBAccount {
	
	// A map containing all logged in players and references to their
	// respective communication controllers
	Map<Account, iClient> clients;
	
	LinkedList<iServerGame> activeGames;
	
	DatabaseCommunicator dbc = DatabaseCommunicator.getInstance();
	
	public RemoteCommunicationController() throws RemoteException {
		super();
		new ServerStarter(this);
		clients = new HashMap<Account, iClient>();
		activeGames = new LinkedList<iServerGame>();
	}
	
	@Override
	public Account login(iClient client, String accountName,
								String accountPassword) throws RemoteException {
		
		Account account = loadAccount(accountName);
		
		if(account != null && account.getPassWord().equals(accountPassword)){
			
			// TODO Change to player instead?
			// TODO Login failed exception istŠllet fšr att returnera ett null-objekt?
			clients.put(account, client);
			
			return account;
		}
		
		return null;
	}
	
	@Override
	public void logOut(Account account) throws RemoteException{
		// TODO Do more when logging out a player? Save active games or something?
		clients.remove(account);
	}

	@Override
	public iServerGame createGame(Account account, iClientGame clientGame,
			int entranceFee, int maxPlayers, int playerStartingChips) {
		
		RemoteGameController newGame = null;
		
		// Checks if the supplied account has been added by the server earlier
		if(isLoggedIn(account)) {
			
			Account serverSideAccount = null;
			
			/*
			 * Gets the actual Account instance from the server. The supplied
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
			
			// Checks if the player has enough "money" to pay the game's
			// entrance fee
			if(serverSideAccount.getBalance().getValue() >= entranceFee ){
			
				try {
					newGame = new RemoteGameController(this, maxPlayers, entranceFee,
							playerStartingChips);
					
				} catch (RemoteException e) {
					// TODO Better exception handling for when not able to create game?
					return null;
				}
				
				activeGames.add(newGame);
				// TODO Change the constructor in Balance to receive playerChips
				// as parameter instead of creating a Balance-class here? 
				iPlayer player = new Player(new Hand(), account.getUserName(),
						new Balance(playerStartingChips));
				
				newGame.addPlayer(player, clientGame);
				
				// Removes the entranceFee from the user's Account instance
				serverSideAccount.getBalance().removeFromBalance(entranceFee);	
			}
			
		}
		
		// Returns null if the table-creation was unsuccessful
		return newGame;
	}
	
	@Override
	public iServerGame joinGame(Account account, iPlayer player,
			iClientGame clientGame, int gameIndex) {
		
		iServerGame game = null;
		
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

				Account a = new Account(firstName, lastName, accountName, passWord);
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
			String firstName = account.getFirstName();
			String lastName = account.getLastName();
			String passWord = account.getPassWord();
			int balance = account.getBalance().getValue();
			
			String updateString = "INSERT INTO Accounts VALUES('" + userName + 
					"', '" + firstName + "', '" + lastName + "', '" + 
					passWord + "', '" + balance + "')";
			int up =
			myStmt.executeUpdate(updateString);
			
			if(up == 0) {
				System.out.println("Account with userName " + userName + " " +
						"already exists");
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
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

}
