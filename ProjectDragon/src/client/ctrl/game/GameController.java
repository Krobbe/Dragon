package client.ctrl.game;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import model.card.Card;
import model.card.ICard;
import model.card.Rank;
import model.card.Suit;
import model.game.Pot;
import model.player.Bet;
import model.player.IPlayer;
import model.player.hand.IHand;
import client.event.Event;
import client.event.EventBus;
import client.model.game.Table;

/**
 * This class contains methods that handles the application during game mode.
 * 
 * @author mattiashenriksson
 * @author lisastenberg
 * @author robinandersson
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

	/**
	 * Sets the current bet of the game
	 * @param bet
	 */
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
	 * @param player The player who folded.
	 * @return	true if the fold went through.
	 * @throws RemoteException
	 */
	public boolean fold(IPlayer player) {
		if(!validPlayerAction(player)) {
			return false;
		}
		IPlayer p = table.getCurrentPlayer();
		p.getHand().discard();
		p.setActive(false);
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
		if(!validPlayerAction(bet.getOwner())) {
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
		 * Make sure that the new bet is not smaller than the current one (this
		 * might occur if player with big blind moves all in when this i
		 * posted). If so the new bet should not be set as a the current bet
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
		if (tmp) {
			EventBus.publish(new Event(Event.Tag.TURN_CHANGED, table
					.getIndexOfCurrentPlayer()));
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
				.getIndexOfCurrentPlayer()));
	}
	

	/**
	 * Set a players hand.
	 * 
	 * @param player The players hand you want to set.
	 * @param hand	The hand
	 * @throws RemoteException
	 */
	public void setHand(IPlayer player, IHand hand) {
		for (IPlayer clientPlayer : table.getActivePlayers()) {
			if (clientPlayer.equals(player)) {
				IHand playerHand = clientPlayer.getHand();
				for (ICard card : hand.getCards()) {
					playerHand.addCard(card);
				}
				EventBus.publish(new Event(Event.Tag.HANDS_CHANGED,
						clientPlayer));
				break;
			}
		}
	}

	/**
	 * Add community cards to the table.
	 * 
	 * @param cards The cards you want to add.
	 * @throws RemoteException
	 */
	public void addCommunityCard(ICard card) {
		table.addCommunityCard(card);
		EventBus.publish(new Event(Event.Tag.COMMUNITY_CARDS_CHANGED, ""));
	}

	/**
	 * Set up a new round.
	 * 
	 * @throws RemoteException
	 */
	public void newRound() {
		table.getCommunityCards().clear();
		for (IPlayer p : table.getActivePlayers()) {
			p.getHand().discard();
			p.setOwnCurrentBet(0);
			EventBus.publish(new Event(Event.Tag.HAND_DISCARDED, p));
			if (p.getBalance().getValue() != 0) {
				p.setActive(true);
			}
		}
		distributeInvisibleCards();
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
				hand.addCard(new Card(Suit.NO_SUIT, Rank.NO_RANK));
			}
			EventBus.publish(new Event(Event.Tag.HANDS_CHANGED,
					player));
		}
	}
	
	/**
	 * Checks if a player is allowed to do an action. In other words this method
	 * checks if player is the currentPlayer at the table.
	 * 
	 * @param player The player
	 * @return true if the player is allowed to do the action.
	 */
	private boolean validPlayerAction(IPlayer player) {
		return player.equals(table.getCurrentPlayer());
	}
}
