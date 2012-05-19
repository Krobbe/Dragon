package common.database;

import java.util.List;
import java.util.Map;

import common.model.player.Player;

/**
 * An interface that needs to be implemented to load data about games from the
 * database.
 * 
 * @author lisastenberg
 *
 */
public interface IDBLoadGame {
	
	/**
	 * A method that loads the statistics for a game from the database.
	 * 
	 * @param gameID	The gameID is unique for a game.
	 * @return	A map with the key as the placement and a String with the
	 * 			players userName.
	 */
	public Map<Integer, String> loadGamePlacements(String gameID);
	
	/**
	 * Load number of won games.
	 * @param userName the players username who you want to know the number of 
	 * won games of.
	 * @return the number of won games. If the account with "userName" as 
	 * userName doesn't exist the method returns -1.
	 */
	public int loadNbrOfWonGames(String userName);
	
	/**
	 * Load number of played games. 
	 * @param userName the player.
	 * @return the number of games player has played.
	 */
	public int loadNbrOfPlayedGames(String userName);
	
	/**
	 * Load played games
	 * 
	 * @param userName the player
	 * @return a list containing gameID of games the player has played.
	 */
	public List<Integer> loadPlayedGames(String userName);
}
