package client.main;

import java.util.List;

import client.model.game.SidePotHandler;

public class Main {
	
	/* tempor�r l�sning */
	List<SidePotHandler> sidePots;
	
	/* tempor�r l�sning */
	public List<SidePotHandler> getSidePots() {
		return sidePots;
	}
	
	public static void main(String[] args) {
		
		new Client();
		
	}
	
	
}
