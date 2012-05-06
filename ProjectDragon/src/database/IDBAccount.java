package database;

import java.util.Map;

import model.player.Account;
import model.player.Player;
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
	 * @param account the account you want to load information from.
	 * @return the accountinfo. Returns null if there is no account with 
	 * 	userName "account".
	 */
	public Account loadAccount(String account);
	
	/**
	 * Saves accountinformation into the database.
	 * 
	 * @param account	The account you want to save information about.
	 */
	public void saveAccount(Account account);
}
