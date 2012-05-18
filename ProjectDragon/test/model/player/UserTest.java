package model.player;

/**
 * A test of the class "User"
 * 
 * A User has a player, and doesn't have any methods of its own,
 * thus we do not find it necessarily to test User to the fully.
 * @author mattiashenriksson
 */

import static org.junit.Assert.*;


import org.junit.Test;

import common.model.card.Card;
import common.model.player.Balance;
import common.model.player.Player;
import common.model.player.User;
import common.model.player.hand.TexasHoldemHand;

public class UserTest {

	@Test
	public void testSetActive() {
		Player p = new Player(new TexasHoldemHand(), "Mattias", 
				new Balance());
		User u = new User(p);
		u.setActive(true);
		assertTrue(u.isActive());
		u.setActive(false);
		assertTrue(!u.isActive());
	}

}
