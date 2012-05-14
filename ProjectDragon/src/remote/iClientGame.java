/**
 * 
 */
package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.card.Card;
import model.card.iCard;
import model.game.Pot;
import model.player.Bet;
import model.player.iPlayer;

/**
 * @author robinandersson
 *
 */
public interface iClientGame extends Remote {
	
	/**
	 * Method to set the player active to check, fold, raise etc.
	 * 
	 * @param active The boolean determining if it's the client's turn.
	 */
	public void setActive(boolean isActive) throws RemoteException;
	
	// TODO Javadoc this sun'ufa'biatchs'
	public void setPot(Pot pot);
	
	public void fold(iPlayer player);
	
	public void betOccured(Bet b);
	
	public void nextTurn();
	
	public void distributeCards();
	
	public void addCommunityCards(List<iCard> cards);
	
	

}
