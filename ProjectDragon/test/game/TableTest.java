package game;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.game.Dealer;
import model.game.Round;
import model.game.Table;
import model.player.User;
import model.player.iPlayer;

import org.junit.Test;

public class TableTest {

	@Test
	public void testAddPlayer() {
		Table t = new Table(new Round(), new Dealer());
		iPlayer p1 = new User();
		iPlayer p2 = new User();
		t.addPlayer(p1);
		t.addPlayer(p2);
		List<iPlayer> players = t.getPlayers();
		assertTrue(players.size() == 2);
	}

}
