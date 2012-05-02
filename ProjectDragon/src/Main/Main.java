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
import model.player.hand.TexasHoldemHand;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;
import utilities.PlayersFullException;
import utilities.TableCardsFullException;
import view.menu.MenuView;
import view.table.TableView;
import ctrl.game.GameController;

public class Main {
	
	public static void main(String[] args) {
		new MenuView();
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
				"Mattias H", new Balance(60)));
		iPlayer player2 = new User(new Player(new TexasHoldemHand(true),
				"Lisa", new Balance(60)));
		iPlayer player3 = new User(new Player(new TexasHoldemHand(true),
				"Robin", new Balance(60)));
		iPlayer player4 = new User(new Player(new TexasHoldemHand(true),
				"Mattias F", new Balance(60)));
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
				} else if (cmd.equals("r20")) {
					gc.raise(20);
				} else if (cmd.equals("r50")) {
					gc.raise(50);
				} else if (cmd.equals("r100")) {
					gc.raise(100);
				} else if (cmd.equals("f")) {
					gc.fold();
				} else if (cmd.equals("ca")) {
					gc.call();
				} else {
					throw new IllegalArgumentException("Command not supported!!");
				}
				
				/* dags för ny bettinground? */
				boolean isBettingDone = true;
				List<iPlayer> activePlayers = table.getActivePlayers();
				for (iPlayer ap: activePlayers) {
					if (activePlayers.get(0).getOwnCurrentBet() != ap.getOwnCurrentBet()) {
						//TODO slå ihop två if-metoder till en med && ?
						if (ap.getBalance().getValue() != 0) {
							isBettingDone = false;
						}
					}
					if (!ap.getDoneFirstBet()) {
						isBettingDone = false;
					}
				}
				
				if (isBettingDone) {
					/*hantera all-in */
					
					/* vilka är all-in? */
					List<iPlayer> allInPlayers = new ArrayList<iPlayer>();
					for (iPlayer ap : activePlayers) {
						if (ap.getBalance().getValue() == 0) {
							allInPlayers.add(ap);
						}
					}
					/* lägg undan i sidePotHandlers */
					Collections.sort(allInPlayers, new OwnCurrentBetComparator());
					List<SidePotHandler> sidePots = table.getSidePots();
					for (iPlayer p : allInPlayers) {
							int allInAmount = p.getOwnCurrentBet();
							int sidePotValue = allInAmount * activePlayers.size() 
									+ table.getRound().getPreBettingPot().getValue();
							for (iPlayer ap : activePlayers) {
								ap.setOwnCurrentBet(ap.getOwnCurrentBet() - allInAmount);
							}
							table.getRound().getPreBettingPot().emptyPot();
							table.getRound().getPot().removeFromPot(sidePotValue);
							Pot sidePot = new Pot(sidePotValue);
							sidePots.add(new SidePotHandler(table.getActivePlayers(), sidePot));
					
							/* utskrifter för kontroll.. */
				            System.out.println("\n\n-------------------------------\n" + 
				            "SIDEPOT ADDED\n");
				            System.out.println("sidePotValue: " + sidePotValue + "\n");
				            System.out.println("ADDED PLAYERS:");
				            for (iPlayer ap : table.getActivePlayers() )
				            	System.out.println(ap.getName());
				            System.out.println("\n-----------------------------------\n");
							p.setActive(false);
					} 
					/* vid showdown hanteras alla sidePots */
					winners = gc.nextBettingRound();
				} 
				table.nextPlayer();
				
				/* slut på rundan ? */
				if (winners != null) {
					System.out.println("Round ended...");
					/*for (iPlayer p : winners) {
						System.out.println("\nWinner: " + p.getName());
						HandValueType hvt = table.getHandTypes().get(p);
						System.out.print(hvt);
						System.out.println(table.getHandTypes().toString());
					}*/
					break;
				}
			}
		}
	}
	
	
}
