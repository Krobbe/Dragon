package client.ctrl.game;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import client.model.game.Table;

import model.card.Card;
import model.card.ICard;
import model.game.Pot;
import model.player.Bet;
import model.player.IPlayer;
import model.player.hand.*;
import client.event.*;

/**
 * This class contains methods that handles the application during game mode.
 * 
 * @author mattiashenriksson
 * @author lisastenberg
 * @author robinandersson
 * 
 */


public class GameController {


	Table table;

	public GameController() {
		// TODO: Request for newTable?

		// TODO Constructor without parameters plz!
		// this(new Table(players, meIndex));
	}

	public GameController(Table table) {
		this.table = table;
		distributeInvisibleCards();
	}

	/**
	 * Creates a new table.
	 * 
	 * @param players The players at the table.
	 * @param meIndex The index in the list of players that is the user.
	 */
	public void newTable(List<IPlayer> players, int meIndex) {
		table = new Table(players, meIndex);
		distributeInvisibleCards();
	}


	/**
	 * Adds a player to the game table
	 * 
	 * @param player
	 *            The player to be added
	 * @author robinandersson
	 */
	public void addPlayer(IPlayer player) {
		this.table.addPlayer(player);
	}
	
	/**
	 * Adds the players in the list to to the game
	 * 
	 * @param player The players to be added
	 * @author robinandersson
	 */
	public void addPlayers(Collection<IPlayer> players) {
		this.table.addPlayers(players);
	}

	public void setCurrentBet(Bet bet) {
		table.getRound().getBettingRound().setCurrentBet(bet);
		EventBus.publish(new Event(Event.Tag.CURRENT_BET_CHANGED, bet
				.getValue()));
	}

	/**
	 * Set the pot.
	 * 
	 * @param pot The value you want to set the pot to.
	 * @throws RemoteException
	 */
	public void setPot(Pot pot) {
		table.getRound().getPot().setValue(pot.getValue());
		EventBus.publish(new Event(Event.Tag.POT_CHANGED, pot.getValue()));
	}

	/**
	 * A method for handling when a player has folded. 
	 * 
	 * @param player	The player who folded.
	 * @return	true if the fold went through.
	 * @throws RemoteException
	 */
	public boolean fold(IPlayer player) {

		if (!table.getCurrentPlayer().equals(player)) {
			return false;
		}
		IPlayer p = table.getCurrentPlayer();
		p.getHand().discard();
		p.setActive(false);
		p.setDoneFirstTurn(true); //Behšvs denna hŠr? hanteras vŠl bara i server?
		EventBus.publish(new Event(Event.Tag.HAND_DISCARDED, player));
		return true;
	}

	/**
	 * A method for handling when a bet has occurred.
	 * 
	 * @param b The bet.
	 * @return true if the method went through successfully.
	 * @throws RemoteException
	 */
	public boolean betOccurred(Bet bet) {
		if (!table.getCurrentPlayer().equals(bet.getOwner())) {
			return false;
		}
		IPlayer p = table.getCurrentPlayer();
		int tmp = bet.getValue() - p.getOwnCurrentBet();
		p.getBalance().removeFromBalance(tmp);
		EventBus.publish(new Event(Event.Tag.BALANCE_CHANGED, new Bet(p, p
				.getBalance().getValue())));
		p.setOwnCurrentBet(bet.getValue());
		EventBus.publish(new Event(Event.Tag.OWN_CURRENT_BET_CHANGED, new Bet(
				p, bet.getValue())));

		/*
		 * kolla sŒ att inte bigblind Šr mindre Šn smallblind men blir inskickad
		 * efter smallblind
		 */
		if (bet.getValue() >= table.getRound().getBettingRound()
				.getCurrentBet().getValue()) {
			table.getRound().getBettingRound().setCurrentBet(bet);
			EventBus.publish(new Event(Event.Tag.CURRENT_BET_CHANGED, bet
					.getValue()));
		}

		table.getRound().getPot().addToPot(bet.getValue());
		EventBus.publish(new Event(Event.Tag.POT_CHANGED, table.getRound()
				.getPot().getValue()));
		return true;
	}

	/**
	 * A method that transfer the turn to the nextPlayer. Does a check that
	 * nextPlayer is the same on the clients table as it should be.
	 * 
	 * @param nextPlayer
	 * @return true if nextPlayer is the player that should have the turn.
	 * @throws RemoteException
	 */
	public boolean nextTurn(IPlayer nextPlayer) {

		Boolean tmp = table.nextPlayer().equals(nextPlayer);
		if(tmp) {
			EventBus.publish(new Event(Event.Tag.TURN_CHANGED, table
					.getCurrentPlayer()));
			return true;
		} 
		return false;
	}

	/**
	 * Set turn to indexOfCurrentPlayer. This method should only be used then
	 * the table is created.
	 * 
	 * @param indexOfCurrentPlayer
	 * @throws RemoteException
	 */
	public void setTurn(int indexOfCurrentPlayer) {
		table.setIndexOfCurrentPlayer(indexOfCurrentPlayer);
		EventBus.publish(new Event(Event.Tag.TURN_CHANGED, table
				.getCurrentPlayer()));
	}
	

	/**
	 * Set a players hand.
	 * 
	 * @param player The players hand you want to set.
	 * @param hand	The hand
	 * @throws RemoteException
	 */
	public void setHand(IPlayer player, IHand hand) {
		for(IPlayer tmp : table.getActivePlayers()) {
			if(tmp.equals(player)) {
				IHand playerHand = tmp.getHand();
				for(ICard card : hand.getCards()) {
					playerHand.addCard(card);
				}
				EventBus.publish(new Event(Event.Tag.HANDS_CHANGED, tmp));
				break;
			}
		}
	}

	/**
	 * Add communitycards to the table.
	 * 
	 * @param cards The cards you want to add.
	 * @throws RemoteException
	 */
	public void addCommunityCard(ICard card) {
		table.addTableCard(card);
		EventBus.publish(new Event(Event.Tag.COMMUNITY_CARDS_CHANGED, card));

	}

	/**
	 * Set up a new round.
	 * 
	 * @throws RemoteException
	 */
	public void newRound() {
		table.getTableCards().clear();
		for (IPlayer p : table.getActivePlayers()) {
			p.getHand().discard();
			EventBus.publish(new Event(Event.Tag.HAND_DISCARDED, p));
			if (p.getBalance().getValue() != 0) {
				p.setActive(true);
			}
		}
	}

	/**
	 * A method for setting a player active or inactive.
	 * 
	 * @param player The player.
	 * @param b	The boolean you want to set.
	 * @throws RemoteException
	 */
	public void setActive(IPlayer player, boolean b) {
		for(IPlayer p : table.getActivePlayers()) {

			if(p.equals(player)) {
				p.setActive(b);
				break;
			}
		}
	}

	/**
	 * Set a players ownCurrentBet
	 * 
	 * @param bet	The bet
	 * @throws RemoteException
	 */
	public void setPlayerOwnCurrentBet(Bet bet) {
		IPlayer player = bet.getOwner();
		for(IPlayer p : table.getActivePlayers()) {
			if(p.equals(player)) {
				p.setOwnCurrentBet(bet.getValue());
				break;
			}
		}
		EventBus.publish(new Event(Event.Tag.OWN_CURRENT_BET_CHANGED, bet));
	}
	
	/**
	 * Changes a persons balance.
	 * 
	 * @param bet Holds a player and how much the player should add to his 
	 * balance.
	 */
	public void balanceChanged(Bet bet) {
		IPlayer player = bet.getOwner();
		for(IPlayer p : table.getActivePlayers()) {
			if(p.equals(player)) {
				p.getBalance().addToBalance(bet.getValue());
				break;
			}
		}
		EventBus.publish(new Event(Event.Tag.BALANCE_CHANGED, bet));
	}
	
	/**
	 * Distributes cards with no suit and no rank. 
	 * This method should be ran in the constructor. 
	 */
	private void distributeInvisibleCards() {
		IHand hand;
		for(IPlayer player : table.getActivePlayers()) {
			hand = player.getHand();
			for(int i = 0; i < 2; i++) {
				hand.addCard(new Card(Card.Suit.NO_SUIT, Card.Rank.NO_RANK));
			}
		}
	}
}
