package client.ctrl.game;

import model.game.Pot;
import model.player.Bet;
import model.player.iPlayer;
import client.event.Event;
import client.event.EventHandler;
import client.event.EventBus;
import model.game.Table;

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
		table = new Table();
	}
	
	public GameController(Table table) {
		this.table = table;
	}
	
	public void setCurrentBet(Bet bet) {
		table.getRound().getBettingRound().setCurrentBet(bet);
	}
	
	public void setPot(Pot pot) {
		table.getRound().getPot().setValue(pot.getValue());
	}
	
	public void fold(iPlayer player) {
		player.getHand().discard();
		player.setActive(false);
		player.setDoneFirstTurn(true);
	}
	
	public void makeBet(Bet bet) {
		
	}
		
}




