/**
 * 
 */
package common.remote;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.List;

import common.model.card.ICard;
import common.model.game.Pot;
import common.model.player.Bet;
import common.model.player.IPlayer;
import common.model.player.hand.IHand;


/**
 * @author robinandersson
 * @author lisastenberg
 */
public interface IClientGame extends Remote {
	
	/**
	 * Creates a new table.
	 * 
	 * @param players The players at the table.
	 * @param meIndex The index in the list of players that is the user.
	 */
	public void newTable(List<IPlayer> players, int meIndex) throws RemoteException;
	
	/**
	 * Set the pot.
	 * 
	 * @param pot The value you want to set the pot to.
	 * @throws RemoteException
	 */
	public void setPot(Pot pot) throws RemoteException;
	
	/**
	 * A method for handling when a player has folded. 
	 * 
	 * @param player	The player who folded.
	 * @return	true if the fold went through.
	 * @throws RemoteException
	 */
	public boolean fold(IPlayer player) throws RemoteException;
	
	/**
	 * A method for handling when a bet has occurred.
	 * 
	 * @param b The bet.
	 * @return true if the method went through successfully.
	 * @throws RemoteException
	 */
	public boolean betOccurred(Bet bet) throws RemoteException;
	
	/**
	 * A method that transfer the turn to the nextPlayer. Does a check that
	 * nextPlayer is the same on the clients table as it should be.
	 * 
	 * @param nextPlayer
	 * @return true if nextPlayer is the player that should have the turn.
	 * @throws RemoteException
	 */
	public boolean nextTurn(IPlayer nextPlayer) throws RemoteException;
	
	/**
	 * Set turn to indexOfCurrentPlayer. This method should only be used then
	 * the table is created.
	 * 
	 * @param indexOfCurrentPlayer
	 * @throws RemoteException
	 */
	public void setTurn(int indexOfCurrentPlayer) throws RemoteException;
	
	/**
	 * Add community-cards to the table.
	 * 
	 * @param card The added card.
	 * @throws RemoteException
	 */	
	public void addCommunityCard(ICard card) throws RemoteException;
	
	/**
	 * Set a players hand.
	 * 
	 * @param player The players hand you want to set.
	 * @param hand	The hand
	 * @throws RemoteException
	 */
	public void setHand(IPlayer player, IHand hand) throws RemoteException;
		
	/**
	 * A method for setting a player active or inactive.
	 * 
	 * @param player The player.
	 * @param b	The boolean you want to set.
	 * @throws RemoteException
	 */
	public void setActive(IPlayer player, boolean b) throws RemoteException;
	
	/**
	 * Set a players ownCurrentBet
	 * 
	 * @param bet	The bet
	 * @throws RemoteException
	 */
	public void setPlayerOwnCurrentBet(Bet bet) throws RemoteException;
	
	/**
	 * Set up a new round.
	 * 
	 * @throws RemoteException
	 */
	public void newRound() throws RemoteException;
	
	/**
	 * Changes a persons balance.
	 * 
	 * @param bet Holds a player and how much the player should add to his 
	 * balance.
	 * @throws RemoteException
	 */
	public void balanceChanged(Bet bet) throws RemoteException;
	
	/**
	 * Notifies the client that a player has been added to the game
	 * 
	 * @param players The new player
	 * @param playerIndex The index (or seat if you will) of the new player at
	 * the table
	 * @throws RemoteException
	 */
	public void addPlayer(IPlayer player) throws RemoteException;
	
	/**
	 * Notifies the client that a player has been removed from the game
	 * 
	 * @param players The new player
	 * the table
	 * @throws RemoteException
	 */
	public void removePlayer(IPlayer player) throws RemoteException;
	
	

}
