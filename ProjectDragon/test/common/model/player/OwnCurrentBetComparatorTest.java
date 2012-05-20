package common.model.player;

import static org.junit.Assert.*;

import common.model.player.hand.*;
import org.junit.Test;

public class OwnCurrentBetComparatorTest {
	OwnCurrentBetComparator comp = new OwnCurrentBetComparator();
	
	@Test
	public void testCompare() {
		IPlayer p1 = new Player(new TexasHoldemHand(), "name", new Balance());
		IPlayer p2 = new Player(new TexasHoldemHand(), "name", new Balance());
		assertTrue(comp.compare(p1, p2) == 0);
	}

}
