package common.database;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import common.model.player.Account;


public class IDBAccountTest {
	
	private class DBAccount implements IDBAccount {
		DatabaseCommunicator dbc = DatabaseCommunicator.getInstance();
		
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
				int up =
				myStmt.executeUpdate(updateString);
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
				int up =
				myStmt.executeUpdate(updateString);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			return true;
		}
	}
	@Test
	public void testCreateAccount() {
		Account a = new Account("test", "t1", "t2", "t3");
		DBAccount d = new DBAccount();
		assertTrue(d.createAccount(a));
	}
	
	@Test
	public void testLoadAccount() {
		DBAccount d = new DBAccount();
		Account a = d.loadAccount("t2");
		assertTrue(a.getUserName().equals("t2"));
	}
	
	@Test
	public void testUpdateAccount() {
		DBAccount d = new DBAccount();
		Account a = new Account("f", "l", "t2", "pass");
		assertTrue(d.updateAccount(a, "t3"));
	}
	
	@Test
	public void testDeleteAccount() {
		DBAccount d = new DBAccount();
		Account a = new Account("f", "l", "t2", "pass");
		assertTrue(d.deleteAccount(a, "pass"));
	}

}
