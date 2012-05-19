package common.model.game;

import java.util.List;

import common.model.player.IPlayer;


/**
 * This class contains information when a "side pot"-event occurs.
 * @author mattiashenriksson
 *
 */
public class SidePotHandler {

	private List<IPlayer> players;
	private Pot pot;
	
	public SidePotHandler(List<IPlayer> players, Pot pot) {
		this.players = players;
		this.pot = pot;
	}
	
	public List<IPlayer> getPlayers() {
		return players;
	}
	
	public Pot getPot() {
		return pot;
	}
}
