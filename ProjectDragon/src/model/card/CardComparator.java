package model.card;

import java.util.Comparator;


/**
 * A Comparator for card. Compares on a cards rank in descending order,
 * (the highest value first).
 * 
 * @author lisastenberg
 *
 */
public class CardComparator implements Comparator<iCard> {

	@Override
	public int compare(iCard arg0, iCard arg1) {
		return -1*(arg0.compareTo(arg1));
	}

}
