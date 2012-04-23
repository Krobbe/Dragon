package model.game;

import java.util.Comparator;

import model.card.Card;

/**
 * A Comparator for card. Compares on a cards rank in descending order,
 * (the highest value first).
 * 
 * @author lisastenberg
 *
 */
public class CardComparator implements Comparator<Card> {

	@Override
	public int compare(Card arg0, Card arg1) {
		return -1*(arg0.compareTo(arg1));
	}

}
