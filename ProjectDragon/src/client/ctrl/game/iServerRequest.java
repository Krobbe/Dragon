/**
 * 
 */
package client.ctrl.game;

import model.player.Bet;
import model.player.IPlayer;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;

/**
 * @author robinandersson
 *
 */
public interface iServerRequest {
	
	/**
	 * Requests a call.
	 * 
	 * @param bet A bet object containing the bet's amount and user information
	 * (to verify that the correct player tried to invoke the call method)
	 * @throws IllegalCallException
	 */
	public boolean requestCall(Bet bet);
	
	/**.
	 * Requests a check.
	 * 
	 * @param bet The placed bet.
	 * @throws IllegalCheckException
	 */
	public boolean requestCheck(Bet bet);
	
	/**
	 * Requests a raise.
	 * 
	 * @param bet The placed bet.
	 * @throws IllegalRaiseException
	 */
	public boolean requestRaise(Bet bet);
	
	/**
	 * Requests a fold.
	 * 
	 * @param player The player that wants to fold.
	 */
	public boolean requestFold(IPlayer player);
	
}
