package Server;

import java.util.List;

import Shared.Player;

import java.util.ArrayList;

public class Lobby {
	
	private List<Player> players = new ArrayList<Player>();

	public void putPlayer(Player name){
		players.add(name);
	}

	public List<Player> getPlayer(){
		return players;
	}

	public void clearLobby(){
		players.clear();
	}
}