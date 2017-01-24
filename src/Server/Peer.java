package Server;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import Shared.*;

public class Peer {
	Board board;
	Player player;
	Gamelogic gamelogic;
	Server server;
	int queuesize = 2;
	

	
	public Peer(Gamelogic gamelogic, Board board,Server server) {
		this.gamelogic = gamelogic;
		this.board = board;
		this.server = server;
		
	}

	 
	public String handleCommand(String cmd, Connection connection) {
		 try {
	 
	      String result = "";
	      Scanner scan = new Scanner(cmd);
	      String str = scan.nextLine();
	      Scanner fullCommand = new Scanner(str);

	      String command = fullCommand.next();

	      if (command.equals("joinrequest")) {
	    	  this.player = new Player(fullCommand.next());
	    	  
	    	  result = "acceptrequest";
	    	    while (fullCommand.hasNext()) {
	    	          String next = fullCommand.next();
	    	          if (server.getFunctions().contains(next)) {
	    	            result = result + next;
	    	          }
	    	    }
	    	    String sendall = "waitforclient"; {
	    	          sendall = sendall + " " + gamelogic.getPlayers().get(0).getName();
	    	          sendall = sendall + " " + gamelogic.getPlayers().get(1).getName();

	    	    }
	    	  
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