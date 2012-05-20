package common.model.game;

import static org.junit.Assert.*;

import org.junit.Test;

import common.model.game.Round;
/**
 * A test for round. 
 * @author lisastenberg
 *
 */
public class RoundTest {
	Round r = new Round();

	@Test
	public void testGetBettingRound() {
		assertTrue(r.getBettingRound().getCurrentBet().getValue() == 0);
	}

	@Test
	public void testGetPot() {
		assertTrue(r.getPot().getValue() == 0);
	}
	
	//TODO: Implement testClone
	@Test
	public void testClone() {
		fail("Not yet implemented");
	}
}
