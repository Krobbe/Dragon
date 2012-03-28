package model.game;

import java.util.Comparator;

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
		return -(arg0.compareTo(arg1));
	}

}
