package database;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatabaseCommunicatorTest {

	@Test
	public void test() {
		DatabaseCommunicator db = DatabaseCommunicator.getInstance();
		assertTrue(db.getError().equals(""));
	}

}
