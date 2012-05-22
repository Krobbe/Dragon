package common.database;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DatabaseCommunicatorTest {

	@Test
	public void test() {
		DatabaseCommunicator db = DatabaseCommunicator.getInstance();
		assertTrue(db.getError().equals(""));
	}

}
