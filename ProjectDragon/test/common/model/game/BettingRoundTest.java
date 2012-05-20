package common.model.game;

import static org.junit.Assert.*;
import org.junit.Test;

import common.model.game.BettingRound;
import common.model.player.Bet;
import common.model.player.Player;
/**
 * A test for a bettinground.
 * 
 * @author lisastenberg
 *
 */
public class BettingRoundTest {

	@Test
	public void testGetCurrentBet() {
		BettingRound b = new BettingRound();
		assertTrue(b.getCurrentBet().getValue() == 0);
	}
	
	@Test
	public void testSetCurrentBet() {
		BettingRound b = new BettingRound();
		b.setCurrentBet(new Bet(new Player(), 10));
		assertTrue(b.getCurrentBet().getValue() == 10);
	}

	@Test
	public void testEquals() {
		BettingRound b1 = new BettingRound();
		BettingRound b2 = new BettingRound();
		BettingRound b3 = new BettingRound();
		Bet bet = new Bet(new Player(), 10);
		b1.setCurrentBet(bet);
		b2.setCurrentBet(bet);
		b3.setCurrentBet(bet);
		
		assertFalse(b1.equals(null));
		//Reflexivity
		assertTrue(b1.equals(b1));
		//Symmetry
		assertTrue(b1.equals(b2) && b2.equals(b1));
		//Transitivity
		if(b2.equals(b3)) {
			assertTrue(b1.equals(b3));
		}
	}
	
	@Test
	public void testToString() {
		BettingRound b1 = new BettingRound();
		System.out.println(b1);
		assertTrue(b1.toString().equals("Current bet is 0"));
	}
}
