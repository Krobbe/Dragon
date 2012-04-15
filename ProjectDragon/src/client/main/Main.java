package client.main;

import java.util.List;

import client.model.game.SidePotHandler;

public class Main {
	
	/* temporär lösning */
	List<SidePotHandler> sidePots;
	
	/* temporär lösning */
	public List<SidePotHandler> getSidePots() {
		return sidePots;
	}
	
	public static void main(String[] args) {
		
		new Client();
		
	}
	
	
}
