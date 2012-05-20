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

	@Test
	public void testPreBettingPot() {
		assertTrue(r.getPreBettingPot().getValue() == 0);
	}
	
	@Test
	public void testEquals() {
		Round r2 = new Round();
		assertTrue(r.equals(r));
		assertTrue(!r.equals(r2));
	}
	
	@Test
	public void testToString() {
		String expected = "Round where: Current bet is 0";
		assertTrue(r.toString().equals(expected));
	}
}
