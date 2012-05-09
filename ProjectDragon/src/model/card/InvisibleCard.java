package model.card;

import model.card.Card.Rank;
import model.card.Card.Suit;

public class InvisibleCard implements iCard {
	
	@Override
	public Rank getRank() {
		return null;
	}

	@Override
	public Suit getSuit() {
		return null;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

}
