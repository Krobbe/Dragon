package player;

import static org.junit.Assert.*;

import model.player.Balance;

import org.junit.Test;
/**
 * Test for balance.
 * @author lisastenberg
 *
 */
public class BalanceTest {
	
	@Test
	public void testAddToBalance() {
		Balance b = new Balance();
		b.addToBalance(10);
		assertTrue(b.getValue() == 10);
	}

	@Test
	public void testRemoveFromBalance() {
		Balance b = new Balance();
		b.addToBalance(20);
		b.removeFromBalance(10);
		assertTrue(b.getValue() == 10);
		
	}
	
	 @Test(expected=IllegalArgumentException.class)
     public void testGetBadIndex() {
         Balance b = new Balance();
         b.removeFromBalance(10);//Should give exception!
	 }
	 
	 @Test
	 public void equalsTest() {
			Balance b1 = new Balance();
			Balance b2 = new Balance(0);
			Balance b3 = new Balance();
			
			assertTrue(b1.equals(b1));
			assertTrue(b1.equals(b2));
			if(b2.equals(b3)) {
				assertTrue(b1.equals(b3));
			}
	 }
}
