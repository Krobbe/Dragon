package common.model.player;

import static org.junit.Assert.*;


import org.junit.Test;

import common.model.player.Account;
import common.model.player.Balance;
/**
 * Test for Account.
 * @author lisastenberg
 *
 */
public class AccountTest {
	Account a = new Account("a", "b", "c", "d");
	Account b = new Account("b1", "b2", "b3", "b4");
	
	@Test
	public void testGetFirstName() {
		String s = b.getFirstName();
		assertTrue(s.equals("b1"));
	}
	
	@Test
	public void testGetLastName() {
		String s = b.getLastName();
		assertTrue(s.equals("b2"));
	}
	
	@Test
	public void testGetUserName() {
		String s = b.getUserName();
		assertTrue(s.equals("b3"));
	}
	
	@Test
	public void testGetPassWord() {
		String s = b.getPassWord();
		assertTrue(s.equals("b4"));
	}
	
	@Test
	public void testSetFirstName() {
		a.setFirstName("first");
		assertTrue(a.getFirstName().equals("first"));
	}
	
	@Test
	public void testSetLastName() {
		a.setLastName("last");
		assertTrue(a.getLastName().equals("last"));
	}
	
	@Test
	public void testSetUserName() {
		a.setUserName("user");
		assertTrue(a.getUserName().equals("user"));
	}
	
	@Test
	public void testSetPassWord() {
		a.setPassWord("pass");
		assertTrue(a.getPassWord().equals("pass"));
	}
	
	@Test
	public void testGetBalance() {
		Balance bal = b.getBalance();
		System.out.println(bal.getValue());
		Balance bal2 = new Balance(0);
		assertTrue(bal.equals(bal2));
	}
	
	@Test
	public void testEquals() {
		Account c = new Account("a", "b", "c", "d");
		Account d = new Account("a", "b", "c", "c");
		//Reflexivity
		assertTrue(a.equals(a));
		//Symmetry
		assertTrue(a.equals(c) && c.equals(a));
		//Transitivity
		if(c.equals(d)) {
			assertTrue(a.equals(d));
		}
	}
	
	@Test
	public void testToString() {
		String s = a.toString();
		String expected = "Firstname: a\nLastname: b\nUsername: c\nBalance: 0";
		assertTrue(s.equals(expected));
	}

	public void testCompareTo() {
		Account c = new Account("a", "b", "c", "d");
		assertTrue(a.compareTo(c) == 0);
	}
}
