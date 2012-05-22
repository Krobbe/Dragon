package common.model.player;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * Test for balance.
 * @author lisastenberg
 *
 */
public class BalanceTest {
	@Test
	public void testGetValue() {
		Balance b = new Balance();
		assertTrue(b.getValue() == 0);
	}
	
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
	 
	//Testar symmetri, reflexivitet och transitivitet.
	@Test
	public void testEquals() {
		Balance b1 = new Balance();
		Balance b2 = new Balance(0);
		Balance b3 = new Balance();

		assertFalse(b1.equals(null));
		//Reflexivity
		assertTrue(b1.equals(b1));
		//Symmetry
		assertTrue(b1.equals(b2));
		assertTrue(b2.equals(b1));
		//Transitivity
		if (b2.equals(b3)) {
			assertTrue(b1.equals(b3));
		}
	}
	 
	 @Test
	 public void testToString() {
		 Balance b = new Balance();
		 assertTrue(b.toString().equals("" + 0));
	 }
}
