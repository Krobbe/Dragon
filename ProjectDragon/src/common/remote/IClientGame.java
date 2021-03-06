/**
 * 
 */
package common.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.model.card.ICard;
import common.model.game.Pot;
import common.model.player.Bet;
import common.model.player.IPlayer;
import common.model.player.hand.IHand;


/**
  * Interface for method calls concerning game functions to the Client. 
 * @author robinandersson
 * @author lisastenberg
 */
public interface IClientGame extends Remote {
	
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
	 * Adds a player to the game at the specified index
	 * 
	 * @param players The new player to be added
	 * @param index The index the player will be added at
	 * @throws RemoteException
	 */
	public void addPlayer(int index, IPlayer player) throws RemoteException;
	
	/**
	 * Add players in a list to the game
	 * 
	 * @param players The new players to be added
	 * @throws RemoteException
	 */
	public void addPlayers(List<IPlayer> players) throws RemoteException;
	
	/**
	 * Removes a player from the game
	 * 
	 * @param players The player to be removed from the game
	 * @throws RemoteException
	 */
	public void removePlayer(IPlayer player) throws RemoteException;
	
	/**
	 * Publishes the winner(s)
	 * 
	 * @param winners
	 * @throws RemoteException
	 */
	public void showdownDone(List<IPlayer> winners) throws RemoteException;

	/**
	 * Posts a blind on the table
	 * @param bet The blind
	 */
	public void postBlind(Bet bet) throws RemoteException;
	
	/**
	 * Clears the current bet
	 * @throws RemoteException
	 */
	public void clearCurrentBet() throws RemoteException;

}
