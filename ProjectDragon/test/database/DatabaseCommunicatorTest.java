package database;

import static org.junit.Assert.*;

import org.junit.Test;

import common.database.DatabaseCommunicator;

public class DatabaseCommunicatorTest {

	@Test
	public void test() {
		DatabaseCommunicator db = DatabaseCommunicator.getInstance();
		assertTrue(db.getError().equals(""));
	}

}
