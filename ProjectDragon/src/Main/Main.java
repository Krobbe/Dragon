package Main;

import java.util.List;
import java.util.Scanner;

import model.game.Table;
import model.player.Balance;
import model.player.Player;
import model.player.User;
import model.player.iPlayer;
import model.player.hand.HandValueType;
import model.player.hand.TexasHoldemHand;
import utilities.IllegalCheckException;
import utilities.PlayersFullException;
import utilities.TableCardsFullException;
import ctrl.game.GameController;

public class Main {
	public static void main(String[] args) {
		try {
			new Main().run();
		} catch (PlayersFullException e) {
			e.printStackTrace();
		} catch (TableCardsFullException e) {
			e.printStackTrace();
		} catch (IllegalCheckException e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws PlayersFullException, TableCardsFullException, 
	IllegalCheckException {
		Table table = new Table();
		GameController gc = new GameController(table);
		iPlayer player1 = new User(new Player(new TexasHoldemHand(true),
				"Mattias H", new Balance(100)));
		iPlayer player2 = new User(new Player(new TexasHoldemHand(true),
				"Lisa", new Balance(100)));
		iPlayer player3 = new User(new Player(new TexasHoldemHand(true),
				"Robin", new Balance(100)));
		iPlayer player4 = new User(new Player(new TexasHoldemHand(true),
				"Mattias F", new Balance(100)));
		table.addPlayer(player1); table.addPlayer(player2); 
		table.addPlayer(player3); table.addPlayer(player4);
		
		Scanner in = new Scanner(System.in);
		while(true) {
			gc.nextRound();
			while(true) {
				List<iPlayer> winners = null;
				System.out.println(table);
				System.out.println('>');
				String cmd = in.nextLine();

				if (cmd.equals("ch")) {
					gc.check();
				} else if (cmd.equals("r")) {
					gc.raise(10);
				} else if (cmd.equals("f")) {
					gc.fold();
				} else if (cmd.equals("ca")) {
					gc.call();
				} else {
					System.out.println("Command not supported..");
				}
				
				boolean isBettingDone = true;
				List<iPlayer> players = table.getPlayers();
				for (iPlayer p: players) {
					if (p.isActive()) {
						if (table.getCurrentPlayer().getOwnCurrentBet()
								!= p.getOwnCurrentBet()) {
							isBettingDone = false;
						}
						if (p.getOwnCurrentBet() == -1) {
							isBettingDone = false;
						}
					}
				}
				
				if (isBettingDone) {
					winners = gc.nextBettingRound();
				}
				if (winners != null) {
					System.out.println("Round ended...");
					for (iPlayer p : winners) {
						System.out.println("\nWinner: " + p.getName());
						HandValueType hvt = table.getHandTypes().get(p);
						System.out.print(hvt);
						System.out.println(table.getHandTypes().toString());
					}
					break;
				}
			}
		}
	}
	
	
}
