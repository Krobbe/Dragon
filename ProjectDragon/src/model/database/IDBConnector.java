package model.database;

import model.player.Account;
/**
 * An interface that needs to be implemented to connect to the database.
 * 
 * @author lisastenberg
 *
 */
public interface IDBConnector {

	/**
	 * Loads information from an account in the database.
	 * @param account the account you want to load information from
	 * @return the accountinfo.
	 */
	public Account loadAccount(String account);
	
	public void saveAccount(String userName, String firstName, String lastName, 
			String passWord);
	
	public void saveGame(String gameID, String date);
	
	public void savePlacement(String gameID, String player, String placement);
	
	//loadPlacement
}
