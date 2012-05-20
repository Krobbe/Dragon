package common.model.card;

public interface ICard extends Comparable<ICard> {
	
	public Rank getRank();
	
	public Suit getSuit();
}
