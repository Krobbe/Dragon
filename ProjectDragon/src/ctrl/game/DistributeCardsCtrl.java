/**
 * 
 */
package ctrl.game;

import model.game.Card;
import model.game.Dealer;
import model.game.Table;

/**
 *
 * A controller for adding the player-specific cards to everyone in the game
 *
 * @author Robin
 *
 */
public class DistributeCardsCtrl {

	Table table;
	
	/**
	 * Creates the controller to handle the distribution of the player-specific
	 * cards
	 * @param table The game's table
	 */
	public DistributeCardsCtrl(Table table){
		this.table = table;
	}
	
	/**
	 * Distributes the cards to all remaining players in the round
	 */
	public void doDistributeCards() {
		
		Dealer d = table.getDealer();
		List<iPlayer> players = table.getPlayers();
		
		// Prepares the list of players to simplify the distribution of cards
		prepareList(players);
		
		/*
		 *  Every player gets two cards where the first is distributed directly,
		 *  and the second after everyone else has gotten their first card 
		 */
		
		for(int i = 1 ; i <= 2 ; i++) {
			
			for(iPlayer player : players){
				
				if(player.isActive){
					player.getHand.addCard(d.popCard());
				}
				
			}
			
		}
		
		// Restores the list to the previous state before it was prepared
		restoreList(players);
		
	}
	
	/*
	 * Short method for rearranging the list to simplify the distribution of
	 * cards.
	 */
	private void prepareList(List<iPlayer> players) {
		players.add(players.remove(0));
		players.add(players.remove(0));
	}
	
	/*
	 * Short method to reset the list to the previous order of players before
	 * the distribution of cards.
	 */
	private void restoreList(List<iPlayer> players) {
		iPlayer player = players.remove(players.size() - 1);
		players.add(0, player);
		
		player = players.remove(players.size() - 1);
		players.add(0, player);
	}
	
}
