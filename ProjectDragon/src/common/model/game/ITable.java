package common.model.game;

import java.util.List;
import common.model.player.IPlayer;

public interface ITable {
	
	public void addPlayer(IPlayer player);
	
	public IPlayer getCurrentPlayer();
	
	public int getIndexOfCurrentPlayer();
	
	public List<IPlayer> getPlayers();

	//TODO: påbörjad av matte h!
	
	//Distribute cards, distribute pot, findIndexOfNextPlayer, getDealer ?
	
}
