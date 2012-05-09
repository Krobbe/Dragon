/**
 * 
 */
package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.game.Pot;
import model.player.Bet;
import model.player.Player;
import model.player.iPlayer;

/**
 * @author robinandersson
 *
 */
public interface iClient extends Remote {
	
	/**
	 * Method to set the player active to check, fold, raise etc.
	 * 
	 * @param active The boolean determining if it's the client's turn.
	 */
	public void setActive(boolean isActive) throws RemoteException;
	
	public void setPot(Pot pot);
	
	public void fold(iPlayer player);
	
	public void updateBet(Bet b);
	
	public void nextTurn();
}
