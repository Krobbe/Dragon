package model.card;

import model.card.Card.*;

public interface iCard extends Comparable<iCard> {
	
	public Rank getRank();
	
	public Suit getSuit();
}
