package database;

import java.util.Map;

import model.player.Player;
/**
 * An interface that needs to be implemented to connect to the database 
 * game- and placement-tables.
 * 
 * @author lisastenberg
 *
 */
public interface IDBGame {

	/**
	 * Calculates what gameID a game should have. It does this by checking what 
	 * gameID is the highest in the database an adds one.
	 * @return the gameID.
	 */
	public int calculateGameID();
	
	/**
	 * Inputs information about a game into the database, which contains
	 * the date the game was played at.
	 * 
	 * @param gameID	The gameID is unique for a game.
	 * @param date		The date the game was played. 
	 */
	public void saveGame(int gameID, String date);
	
	/**
	 * Input a placement into the database.
	 * 
	 * @param gameID	The gameID is unique for a game.
	 * @param userName	The player 
	 * @param placement	The placement the player got in the game.
	 */
	public void savePlacement(String gameID, Player player, int placement);
	
	/**
	 * A method that loads the statistics for a game from the database.
	 * 
	 * @param gameID	The gameID is unique for a game.
	 * @return	A map with the key as the placement and a String with the
	 * 			players userName.
	 */
	public Map<Integer, String> loadGamePlacements(String gameID);
	
	/**
	 * Load number of one games.
	 * @param userName the players username who you want to know the number of 
	 * won games of.
	 * @return the number of one games. If the account with "userName" as 
	 * userName doesn't exist the method returns -1.
	 */
	public int loadNbrOfWonGames(String userName);
}
