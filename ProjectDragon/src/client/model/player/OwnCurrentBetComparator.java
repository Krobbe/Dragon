package client.model.player;

import java.util.Comparator;

/**
 * A comparator comparing different player's respective current bet
 * @author mattiashenriksson
 *
 */

public class OwnCurrentBetComparator implements Comparator<iPlayer> {

	@Override
	public int compare(iPlayer p1, iPlayer p2) {
		return p1.getOwnCurrentBet() - p2.getOwnCurrentBet();
	}

}
