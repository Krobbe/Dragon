/**
 * 
 */
package model.player;

import static org.junit.Assert.*;

import model.game.Card;

import org.junit.Test;

/**
 * @author Robin
 *
 */
public class TexasHoldemHandTest {

	@Test
	public void testVisible() {
		OpponentHand opHand = new OpponentHand();
		opHand.addCard(new Card(Card.Suite.CLUBS, 14));
		opHand.addCard(new Card());
		assertTrue(opHand.isVisible == false);

		opHand.setVisible(true);
		assertTrue(opHand.isVisible == true);
		opHand.setVisible(false);

		assertTrue(opHand.isVisible == false);
	}
	
	

}
