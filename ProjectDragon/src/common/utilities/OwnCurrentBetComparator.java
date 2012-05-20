package common.utilities;

import java.util.Comparator;

import common.model.player.IPlayer;

/**
 * A comparator comparing different player's respective current bet
 * @author mattiashenriksson
 *
 */

public class OwnCurrentBetComparator implements Comparator<IPlayer> {

	@Override
	public int compare(IPlayer p1, IPlayer p2) {
		return p1.getOwnCurrentBet() - p2.getOwnCurrentBet();
	}

}
