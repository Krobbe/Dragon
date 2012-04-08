package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.game.Pot;
import model.game.SidePotHandler;
import model.game.Table;
import model.player.Balance;
import model.player.OwnCurrentBetComparator;
import model.player.Player;
import model.player.User;
import model.player.iPlayer;
import model.player.hand.HandValueType;
import model.player.hand.TexasHoldemHand;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;
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
		} catch (IllegalCallException e) {
			e.printStackTrace();
		} catch (IllegalRaiseException e) {
			e.printStackTrace();
		}
	}
	
	public void run() throws PlayersFullException, TableCardsFullException, 
	IllegalCheckException, IllegalCallException, IllegalRaiseException {
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
				} else if (cmd.equals("r10")) {
					gc.raise(10);
				} else if (cmd.equals("r50")) {
					gc.raise(50);
				} else if (cmd.equals("r100")) {
					gc.raise(100);
				} else if (cmd.equals("f")) {
					gc.fold();
				} else if (cmd.equals("ca")) {
					gc.call();
				} else {
					System.out.println("Command not supported..");
				}
				
				/* dags för ny bettinground? */
				boolean isBettingDone = true;
				List<iPlayer> players = table.getPlayers();
				for (iPlayer p: players) {
					if (p.isActive()) {
						if (table.getCurrentPlayer().getOwnCurrentBet()
								!= p.getOwnCurrentBet() && p.getBalance().getValue() != 0) {
							isBettingDone = false;
						}
						if (p.getOwnCurrentBet() == -1) {
							isBettingDone = false;
						}
					}
				}
				
				if (isBettingDone) {
					/*hantera all-in 
					List<iPlayer> allInPlayers = new ArrayList<iPlayer>();
					for (iPlayer p : players) {
						if (p.getBalance().getValue() == 0) {
							allInPlayers.add(p);
						}
					}
					Collections.sort(allInPlayers, new OwnCurrentBetComparator());
					List<SidePotHandler> sidePots = new ArrayList<SidePotHandler>();
					for (iPlayer p : allInPlayers) {
							List<iPlayer> activePlayers = table.getActivePlayers();
							int sidePotValue = p.getOwnCurrentBet() * activePlayers.size();
							table.getRound().getPot().removeFromPot(sidePotValue);
							Pot sidePot = new Pot(sidePotValue);
							sidePots.add(new SidePotHandler(table.getPlayers(), sidePot));
							p.setActive(false);
					} */
					//Sen utföra showdowns för dessa.
					winners = gc.nextBettingRound();
				}
				
				/* slut på rundan ? */
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
