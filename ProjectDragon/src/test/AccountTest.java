package test;

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
		assertTrue(a.getFirstName().equals("first"));
	}
	
	@Test
	public void testGetLastName() {
		a.setLastName("last");
		assertTrue(a.getLastName().equals("last"));
	}
	
	@Test
	public void testGetUserName() {
		a.setUserName("user");
		assertTrue(a.getUserName().equals("user"));
	}
	
	@Test
	public void testGetPassWord() {
		a.setPassWord("pass");
		assertTrue(a.getPassWord().equals("pass"));
	}

}
