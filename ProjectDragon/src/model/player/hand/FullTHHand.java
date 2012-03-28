package model.player.hand;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.game.Card;
import model.game.CardComparator;

public class FullTHHand implements iHand {
	private List<Card> cards;
	private boolean isVisible = true;
	
	public FullTHHand() {
		this(new LinkedList<Card>());
	}
	
	public FullTHHand(List<Card> cards) {
		this.cards = cards;
	}
	
	@Override
	public void discard() {
		cards.clear();

	}

	@Override
	public List<Card> getCards() {
		return cards;
	}

	@Override
	public void addCard(Card c) {
		cards.add(c);
		Collections.sort(cards, new CardComparator());
	}
	
	public void addCards(List<Card> c) {
		for(Card card : c) {
			addCard(card);
		}
	}
	
	public void addCards(iHand hand) {
		for(Card card : hand.getCards()) {
			addCard(card);
		}
	}

	@Override
	public void setVisible(boolean b) {
		isVisible = b;
	}

	@Override
	public boolean isVisible() {
		return isVisible;
	}

}
