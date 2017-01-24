package Server;

import java.util.List;
import java.util.ArrayList;

public class Lobby {
	//TODO putplayer getplayer arraylist
	private List<String> players = new ArrayList<String>();

	public void putPlayer(String name){
		players.add(name);
	}

	public List<String> getPlayer(){
		return players;
	}

	public void clearLobby(){
		players.clear();
	}
}