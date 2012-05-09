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

public class GameController implements EventHandler {

	Table table;
	
	public GameController() {
		this(new Table());
	}
	
	public GameController(Table table) {
		EventBus.register(this);
		this.table = table;
	}
	
	/**
	 * Adds a player to the game table
	 * 
	 * @param player The player to be added
	 * @author robinandersson
	 */
	public void addPlayer(iPlayer player) {
		this.table.addPlayer(player);
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
	
	@Override
	public void onEvent(Event evt) {
		
		switch (evt.getTag()) {
		
			case REQUEST_CHECK:
			
				//

			case REQUEST_FOLD:
			
			//Skicka en fold-förfrågan till servern
			
			
			case REQUEST_CALL:
			
			//
			
			case REQUEST_RAISE:
			
			//raise(new Bet(table.getCurrentPlayer(),table.getRound()
			//	.getBettingRound().getCurrentBet().getValue()) + amount);
			break;
			
		default:
			break;
		}
	
	}
		
}




