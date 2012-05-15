package Main;

import java.util.List;
import java.util.Scanner;

import client.gui.menu.MainView;

import ctrl.game.GameController;

import model.game.*;
import model.player.*;
import model.player.hand.TexasHoldemHand;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;
import utilities.PlayersFullException;
import utilities.CommunityCardsFullException;

public class Main {
	
	public static void main(String[] args) {
		new MainView();
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
		
		Table table = new Table();
		GameController gc = new GameController(table);
		
		IPlayer player1 = new User(new Player(new TexasHoldemHand(),
				"Mattias H", new Balance(60)));
		IPlayer player2 = new User(new Player(new TexasHoldemHand(),
				"Lisa", new Balance(60)));
		IPlayer player3 = new User(new Player(new TexasHoldemHand(),
				"Robin", new Balance(60)));
		IPlayer player4 = new User(new Player(new TexasHoldemHand(),
				"Mattias F", new Balance(60)));
		table.addPlayer(player1); table.addPlayer(player2); 
		table.addPlayer(player3); table.addPlayer(player4);
		
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
				
				if (table.isBettingDone()) {
					gc.nextBettingRound();
				} 
				
				table.nextPlayer();
				
				/* slut på rundan ? */
				if (winners != null) {
					System.out.println("Round ended...");
					break;
				}
			}
		}
	}
	
	
}
