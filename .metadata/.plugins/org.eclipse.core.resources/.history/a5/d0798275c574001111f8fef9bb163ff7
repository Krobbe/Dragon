package test;

import static org.junit.Assert.*;

import model.player.Balance;

import org.junit.Test;

public class BalanceTest {

	@Test
	public void testAddToBalance() {
		Balance b = new Balance();
		b.addToBalance(10);
		assertTrue(b.getBalance() == 10);
	}

	@Test
	public void testRemoveFromBalance() {
		Balance b = new Balance();
		b.addToBalance(20);
		b.removeFromBalance(10);
		assertTrue(b.getBalance() == 10);
		
	}
	
	 @Test(expected=IllegalArgumentException.class)
     public void testGetBadIndex() {
         Balance b = new Balance();
         b.removeFromBalance(10);//Should give exception!
	 }
}
