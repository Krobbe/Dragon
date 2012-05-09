package client.ctrl.game;

import java.util.List;

import client.model.game.Table;

import model.card.Card;
import model.game.Pot;
import model.player.Bet;
import model.player.iPlayer;
import model.player.hand.*;

/**
 * This class contains methods that handles the application during game mode.
 * 
 * @author mattiashenriksson
 * @author robinandersson
 * 
 */

public class GameController {

	Table table;
	
	public GameController() {
		//TODO: Request for newTable?
	}
	
	public void newTable(List<iPlayer> players, int meIndex) { 
		table = new Table(players, meIndex);
	}
	
	public GameController(Table table) {
		this.table = table;
	}
	
	public void setPot(Pot pot) {
		table.getRound().getPot().setValue(pot.getValue());
	}
	
	public boolean fold(iPlayer player) {
		if(!table.getCurrentPlayer().equals(player)) {
			return false;
		} 
		iPlayer p = table.getCurrentPlayer();
		p.getHand().discard();
		p.setActive(false);
		p.setDoneFirstTurn(true);
		return true;
	}
	
	public boolean betOccured(Bet bet) {
		if(!table.getCurrentPlayer().equals(bet.getOwner())) {
			return false;
		}
		iPlayer p = table.getCurrentPlayer();
		int tmp = bet.getValue() - p.getOwnCurrentBet();
		p.getBalance().removeFromBalance(tmp);
		p.setOwnCurrentBet(bet.getValue());
		table.getRound().getBettingRound().setCurrentBet(bet);
		return true;
	}
	
	public boolean nextTurn(iPlayer nextPlayer) {
		return table.nextPlayer().equals(nextPlayer);
	}
	
	public void setHand(iHand hand) {
		iHand myHand = table.getPlayers().get(table.getMeIndex()).getHand();
		for(Card c : hand.getCards()) {
			myHand.addCard(c);
		}
	}
	
	/**
	 * Add cards to the table.
	 * 
	 * @param cards The cards you want to add.
	 */
	public void addCommunityCards(List<Card> cards) {
		for(Card c: cards) {
			table.addTableCard(c);
		}
	}
	
	/**
	 * Clear the tables cards.
	 */
	public void clearCommunityCards() {
		table.clearTableCards();
	}
	
	
}




