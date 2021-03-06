package common.database;

import common.model.player.IPlayer;

/**
 * An interface that need to be implemented to save data about games into the 
 * database.
 * 
 * @author lisastenberg
 *
 */
public interface IDBSaveGame {
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
	public void savePlacement(String gameID, IPlayer player, int placement);
	
	/**
	 * Calculates what gameID a game should have. It does this by checking what 
	 * gameID is the highest in the database an adds one.
	 * @return the gameID.
	 */
	public int calculateGameID();
}
