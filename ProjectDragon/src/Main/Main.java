package Main;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import common.model.player.Balance;
import common.model.player.Bet;
import common.model.player.IPlayer;
import common.model.player.Player;
import common.model.player.User;
import common.model.player.hand.TexasHoldemHand;
import common.utilities.CommunityCardsFullException;
import common.utilities.IllegalCallException;
import common.utilities.IllegalCheckException;
import common.utilities.IllegalRaiseException;
import common.utilities.PlayersFullException;

import server.ctrl.game.GameController;
import server.model.game.Table;

public class Main {
	
	public static void main(String[] args) {
		
		//new MainView();
		try {
			new Main().run();
		} catch (PlayersFullException e) {
			e.printStackTrace();
		} catch (CommunityCardsFullException e) {
			e.printStackTrace();
		} catch (IllegalCheckException e) {
			e.printStackTrace();
		} catch (IllegalCallException e) {
			e.printStackTrace();
		} catch (IllegalRaiseException e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws PlayersFullException, CommunityCardsFullException, 
	IllegalCheckException, IllegalCallException, IllegalRaiseException {
		
		IPlayer player1 = new User(new Player(new TexasHoldemHand(),
				"Mattias H", new Balance(60)));
		IPlayer player2 = new User(new Player(new TexasHoldemHand(),
				"Lisa", new Balance(60)));
		IPlayer player3 = new User(new Player(new TexasHoldemHand(),
				"Robin", new Balance(60)));
		IPlayer player4 = new User(new Player(new TexasHoldemHand(),
				"Mattias F", new Balance(60)));
		List<IPlayer> plrs = new LinkedList<IPlayer>();
		
		plrs.add(player1); plrs.add(player2); plrs.add(player3); plrs.add(player4);
		
		// TODO Specify "Real" values for maxPlayers etc. to be used in this
		// text-based version?
		Table table = new Table(plrs, 10, 100, 1000);
		GameController gc = new GameController(table);
		
		Scanner in = new Scanner(System.in);
		
		while(true) {
		
			gc.nextRound();
			
			while(true) {
				List<IPlayer> winners = null;
				System.out.println(table);
				System.out.println('>');
				String cmd = in.nextLine();

				if (cmd.equals("ch")) {
					gc.check(new Bet(table.getCurrentPlayer(), 0));
				} else if (cmd.equals("r10")) {
					gc.raise(new Bet(table.getCurrentPlayer(), table.getRound().
							getBettingRound().getCurrentBet().getValue() + 10));
				} else if (cmd.equals("r20")) {
					gc.raise(new Bet(table.getCurrentPlayer(), table.getRound().
							getBettingRound().getCurrentBet().getValue() + 20));
				} else if (cmd.equals("r30")) {
					gc.raise(new Bet(table.getCurrentPlayer(), table.getRound().
							getBettingRound().getCurrentBet().getValue() + 30));
				} else if (cmd.equals("r50")) {
					gc.raise(new Bet(table.getCurrentPlayer(), table.getRound().
							getBettingRound().getCurrentBet().getValue() + 50));
				} else if (cmd.equals("r100")) {
					gc.raise(new Bet(table.getCurrentPlayer(), table.getRound().
							getBettingRound().getCurrentBet().getValue() + 100));
				} else if (cmd.equals("f")) {
					gc.fold(table.getCurrentPlayer());
				} else if (cmd.equals("ca")) {
					gc.call(new Bet(table.getCurrentPlayer(), table.getRound().
							getBettingRound().getCurrentBet().getValue()));
				} else {
					throw new IllegalArgumentException("Command not supported!!");
				}
				
			}
		}
	}
	
	
}
