package model.card;

import model.card.Card.*;

public interface ICard extends Comparable<ICard> {
	
	public Rank getRank();
	
	public Suit getSuit();
}
