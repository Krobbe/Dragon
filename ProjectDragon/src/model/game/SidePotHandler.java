package model.game;

import java.util.List;

import model.player.iPlayer;

/**
 * This class contains information when a "side pot"-event occurs.
 * @author mattiashenriksson
 *
 */
public class SidePotHandler {

	private List<iPlayer> players;
	private Pot pot;
	
	public SidePotHandler(List<iPlayer> players, Pot pot) {
		this.players = players;
		this.pot = pot;
	}
	
	public List<iPlayer> getPlayers() {
		return players;
	}
	
	public Pot getPot() {
		return pot;
	}
}
