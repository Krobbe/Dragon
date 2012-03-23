package player;

import static org.junit.Assert.*;

import model.player.Account;

import org.junit.Test;

public class AccountTest {
	Account a = new Account("a", "b", "c", "d");
	Account b = new Account("b1", "b2", "b3", "b4");
	
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

}