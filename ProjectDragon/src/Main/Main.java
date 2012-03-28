package Main;

import java.util.List;
import java.util.Scanner;

import model.game.Table;
import model.player.Balance;
import model.player.Player;
import model.player.User;
import model.player.iPlayer;
import model.player.hand.TexasHoldemHand;
import ctrl.game.GameController;

public class Main {
	public static void main(String[] args) {
		new Main().run();
	}
	
	/* pŒbšrjad metod som kan anvŠndas nŠr vi vill kšra vŒr textbaserade 
	 * Dragon-variant pŒ torsdag /mattias h 
	 */
	public void run() {
		Table table = new Table();
		GameController gc = new GameController(table);
		iPlayer player = new User(new Player(new TexasHoldemHand(true),
				"Mattias", new Balance()));
		/* iPlayer player2 = new User(new Player(new TexasHoldemHand(true),
				"Lisa", new Balance())); */
		table.addPlayer(player);
		player.setActive(true);
		gc.distributeCards();
		Scanner in = new Scanner(System.in);
		
		while(true) {
			System.out.println(table);
			System.out.println('>');
			String cmd = in.nextLine();
			
			if (cmd.equals("check")) {
				if (table.getTableCards().size() == 0) {
					gc.showFlop();
				} else if (table.getTableCards().size() == 3){
					gc.showRiver();
				} else if (table.getTableCards().size() == 4){
					gc.showRiver();
				} else {
					List<iPlayer> list = gc.doShowdown();
					for (iPlayer p : list) {
						System.out.println("Winner: " + p.getName());
					}
					System.out.println("\nRound ended...");
					break;
				}
			} else {
				System.out.println("Command not supported..");
			}
			
		}
	}
	
}
