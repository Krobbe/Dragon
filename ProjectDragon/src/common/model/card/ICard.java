package common.model.card;

import java.io.Serializable;

public interface ICard extends Comparable<ICard>, Serializable {
	
	public Rank getRank();
	
	public Suit getSuit();
}
