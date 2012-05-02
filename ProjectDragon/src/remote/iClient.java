/**
 * 
 */
package remote;

/**
 * @author Robin
 *
 */
public interface iClient {
	
	/**
	 * Method to set the player active to check, fold, raise etc.
	 * 
	 * @param active The boolean determining if it's the client's turn.
	 */
	public void setActive(boolean active);

}
