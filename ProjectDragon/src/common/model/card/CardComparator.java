package common.model.card;

import java.util.Comparator;


/**
 * A Comparator for card. Compares on a cards rank in descending order,
 * (the highest value first).
 * 
 * @author lisastenberg
 *
 */
public class CardComparator implements Comparator<ICard> {

	@Override
	public int compare(ICard arg0, ICard arg1) {
		return -1*(arg0.compareTo(arg1));
	}

}
