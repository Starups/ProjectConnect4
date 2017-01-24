package Server;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import Shared.*;

public class Peer {
	private Board board;
	private Player player;
	private Gamelogic gamelogic;
	private Server server;
	private Lobby lobby;
	int queuesize = 2;
	

	
	public Peer(Gamelogic gamelogic, Board board,Server server) {
		this.gamelogic = gamelogic;
		this.board = board;
		this.server = server;
		lobby = server.getLobby();
	}

	 
	public String handleCommand(String cmd, Connection connection) {
		 try {
	 
	      String result = "";
	      Scanner scan = new Scanner(cmd);
	      String str = scan.nextLine();
	      Scanner fullCommand = new Scanner(str);

	      String command = fullCommand.next();

	      if (command.equals("joinrequest")) {
	    	  String name = fullCommand.next();
	    	  this.player = new Player(name, connection);
		      lobby.putPlayer(player);
		      result = "acceptrequest";
	    	    while (fullCommand.hasNext()) {
	    	          String next = fullCommand.next();
	    	          if (server.getFunctions().contains(next)) {
	    	            result = result + next;
	    	          }
	    	    }
	    	    String sendall = "waitforclient"; {
	    	          sendall = sendall + " " + lobby.getPlayer().get(0);

	    	    }
	    	    server.sendAll(sendall);
	    	  
	     }
	      scan.close();
	      fullCommand.close();
	      return result;
	    } catch (java.util.NoSuchElementException e) {
	      String result = "Invalid Server command";
	      e.printStackTrace();
	      return result;
	    }
	  }
	public void putPlayer(Player player){
		gamelogic.putPlayers(player, null);
	}
}