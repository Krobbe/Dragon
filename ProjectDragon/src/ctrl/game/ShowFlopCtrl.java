package ctrl.game;

import java.util.List;
import model.game.Card;
import model.game.Dealer;
import model.game.Table;

public class ShowFlopCtrl {
	
	private Table table;
	
	public ShowFlopCtrl(Table table) {
		this.table = table;
	}
	
	public void doShowFlopCtrl() {
		Dealer dealer = table.getDealer();
		List<Card> flop = dealer.getFlop();
		for (Card c : flop) {
			table.addTableCard(c);
		}
	}

}
