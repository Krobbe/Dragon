package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import client.gui.MenuView;

import ctrl.game.GameController;

import model.game.*;
import model.player.*;
import model.player.hand.TexasHoldemHand;
import utilities.IllegalCallException;
import utilities.IllegalCheckException;
import utilities.IllegalRaiseException;
import utilities.PlayersFullException;
import utilities.TableCardsFullException;

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
				
				/*
				if (table.isBettingDone()) {
					gc.handleAllIn();
					gc.nextBettingRound();
				} 
				
				table.nextPlayer();
				
				/* slut på rundan ? */ /*
				if (winners != null) {
					System.out.println("Round ended...");
					break;
				}
				*/
			}
		}
	}
	
	
}
