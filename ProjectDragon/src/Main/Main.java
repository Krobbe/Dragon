package Main;

import java.util.Scanner;

import model.game.Table;
import model.player.Balance;
import model.player.Player;
import model.player.User;
import model.player.iPlayer;
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
	
	/* pŒbšrjad metod som kan anvŠndas nŠr vi vill kšra vŒr textbaserade 
	 * Dragon-variant pŒ torsdag /mattias h 
	 */
	public void run() throws PlayersFullException, TableCardsFullException, IllegalCheckException {
		Table table = new Table();
		GameController gc = new GameController(table);
		iPlayer player1 = new User(new Player(new TexasHoldemHand(true),
				"Mattias", new Balance(100)));
		iPlayer player2 = new User(new Player(new TexasHoldemHand(true),
				"Lisa", new Balance(100)));
		iPlayer player3 = new User(new Player(new TexasHoldemHand(true),
				"Lisa", new Balance(100)));
		table.addPlayer(player1); table.addPlayer(player2); table.addPlayer(player3);
		
		Scanner in = new Scanner(System.in);
		while(true) {
			gc.nextRound();
			while(true) {
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
				}
				//if ()
			}
			
			/*else {
					List<iPlayer> list = gc.doShowdown();
					System.out.println("Round ended...");
					for (iPlayer p : list) {
						System.out.println("\nWinner: " + p.getName());
						HandValueType hvt = table.getHandTypes().get(p);
						System.out.print(hvt);
						System.out.println(table.getHandTypes().toString());
					}
					break;
				}
			} else {
				System.out.println("Command not supported..");
			}*/
			
		}
	}
	
}
