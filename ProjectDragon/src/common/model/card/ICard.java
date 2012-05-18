package common.model.card;

import common.model.card.Card.*;

public interface ICard extends Comparable<ICard> {
	
	public Rank getRank();
	
	public Suit getSuit();
}
