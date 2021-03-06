package common.model.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
/**
 * A test for pot.
 * @author lisastenberg
 *
 */
public class PotTest {

	@Test
	public void testGetValue() {
		Pot p = new Pot();
		assertTrue(p.getValue() == 0);
	}
	
	@Test
	public void testSetValue() {
		Pot p = new Pot();
		p.setValue(10);
		assertTrue(p.getValue() == 10);
	}
	
	@Test
	public void testAddToPot() {
		Pot p = new Pot();
		p.addToPot(10);
		assertTrue(p.getValue() == 10);
	}
	
	@Test
	public void testRemoveFromPot() {
		Pot p = new Pot();
		p.addToPot(10);
		p.removeFromPot(5);
		assertTrue(p.getValue() == 5);
	}
	
	@Test
	public void testEmptyPot() {
		Pot p = new Pot();
		p.addToPot(10);
		p.emptyPot();
		assertTrue(p.getValue() == 0);
	}
	
	@Test
	public void testEquals() {
		Pot p1 = new Pot();
		Pot p2 = new Pot();
		Pot p3 = new Pot();
		
		assertFalse(p1.equals(null));
		
		//Reflexivity
		assertTrue(p1.equals(p2));
		//Symmetry
		assertTrue(p1.equals(p2) && p2.equals(p1));
		//Transitivity
		if(p2.equals(p3)) {
			assertTrue(p1.equals(p3));
		}
	}
	
	@Test
	public void testToString() {
		Pot p = new Pot();
		assertTrue(p.toString().equals("0"));
	}

}
