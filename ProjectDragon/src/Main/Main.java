package Main;

import java.util.Scanner;

import model.database.DatabaseCommunicator;
import model.game.Table;
import model.player.User;
import model.player.iPlayer;

public class Main {
	public static void main(String[] args) {
		DatabaseCommunicator db = DatabaseCommunicator.getInstance();	
	}
	
	/* pŒbšrjad metod som kan kšras nŠr vi vill kšra vŒr textbaserade Dragon-variant pŒ torsdag*/
	public void run() {
		Table table = new Table();
		iPlayer player = new User();
		table.addPlayer(player);
		
		Scanner in = new Scanner(System.in);
		
		while(true) {
			System.out.println(table);
			System.out.println('>');
			String cmd = in.nextLine();
			if (cmd.equals("n")) {
				// showflop osv.
			} else {
				System.out.println("Command not supported..");
			}
			
		}
	}
	
}
