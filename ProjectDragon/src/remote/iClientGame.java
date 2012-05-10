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
import model.player.hand.iHand;

/**
 * @author robinandersson
 *
 */
public interface iClientGame extends Remote {
	
	public void setPot(Pot pot) throws RemoteException;
	
	/**
	 * A method for handling when a player has folded. 
	 * 
	 * @param player	The player who folded.
	 * @return	true if the fold went through.
	 * @throws RemoteException
	 */
	public boolean fold(iPlayer player) throws RemoteException;
	
	/**
	 * A method for handling when a bet has occured.
	 * 
	 * @param b The bet.
	 * @return true if the method went through successfully.
	 * @throws RemoteException
	 */
	public boolean betOccured(Bet b) throws RemoteException;
	
	public boolean nextTurn(iPlayer nextPlayer) throws RemoteException;
	
	public void setTurn(int indexOfCurrentPlayer) throws RemoteException;
	
	/**
	 * Add communitycards to the table.
	 * 
	 * @param cards The cards you want to add.
	 * @throws RemoteException
	 */
	public void addCommunityCards(List<iCard> cards) throws RemoteException;
	
	public void setHand(iPlayer player, iHand hand) throws RemoteException;
		
	/**
	 * A method for setting a player active or inactive.
	 * 
	 * @param player The player.
	 * @param b	The boolean you want to set.
	 * @throws RemoteException
	 */
	public void setActive(iPlayer player, boolean b) throws RemoteException;
	
	public void setPlayerOwnCurrentBet(Bet bet) throws RemoteException;
	
	/**
	 * Set up a new round.
	 * 
	 * @throws RemoteException
	 */
	public void newRound() throws RemoteException;

}
