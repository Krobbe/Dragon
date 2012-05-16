package database;

import java.rmi.RemoteException;

import model.player.Account;
/**
 * An interface that needs to be implemented to connect to the database 
 * account-tables.
 * 
 * @author lisastenberg
 *
 */
public interface IDBAccount {

	/**
	 * Loads information about an account in the database.
	 * 
	 * @param accountName the account you want to load information from.
	 * @return the accountinfo. Returns null if there is no account with 
	 * 	userName "account".
	 */
	public Account loadAccount(String accountName) throws RemoteException;
	
	/**
	 * Saves accountinformation into the database.
	 * 
	 * @param account	The account you want to save information about.
	 * @return false if it already exists an account with that userName.
	 */
	public boolean createAccount(Account account) throws RemoteException;
	
	/**
	 * Updates information about an account.
	 * 
	 * @param account The account you want to update
	 * @param oldPassword	The oldPassword, in case you want to change your
	 * 	password you have to type in your old one.
	 * @return false if it's the wrong password according to the database.
	 */
	public boolean updateAccount(Account account, String oldPassword) throws RemoteException;
	
	/**
	 * Deletes information about an account in the database.
	 * 
	 * @param account	The account you want to delete.
	 * @param oldPassword	The password for the account.
	 * @return	true if the deletion went through successfully.
	 */
	public boolean deleteAccount(Account account, String oldPassword) throws RemoteException;
}
