package Server;

import java.util.List;

import Shared.Player;

import java.util.ArrayList;

public class Lobby {
	
	/*
	 * A list of all the players in the lobby. Players who are in the lobby are waiting for the game, 
	 * when the game has started the lobby gets cleared again.
	 */
	
	private List<Player> players = new ArrayList<Player>();

	public void putPlayer(Player name){
		players.add(name);
	}

	public List<Player> getPlayer(){
		return players;
	}
	
	/*
	 * command that can clear the lobby, happens when a game has started
	 */
	public void clearLobby(){
		players.clear();
	}
}