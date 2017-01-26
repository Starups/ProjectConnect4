package Server;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import Shared.*;

public class Peer {
	private Board board;
	private Player player;
	private Player opponent;
	private Player askmove;
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
	    	  boolean bool = false;
	    	  for(int i = 0; i < lobby.getPlayer().size(); i++){
	    		  if(name.equals(lobby.getPlayer().get(i).getName())){
	    			  bool = true;
	    		  }
	    	  }
	    	  if(bool){
	    		  result = "denyrequest";
	    	  }
	    	  else{
		    	  this.player = new Player(name, connection);
			      lobby.putPlayer(player);
			      result = "acceptrequest";
	    	  }
	      }
	      if(command.equals("gamerequest")) {
	    	  if(lobby.getPlayer().size() == 1) {
	      
	    	    String sendall = "waitforclient"; 
	    	    sendall = sendall + " " + lobby.getPlayer().get(0).getName();
	    	    System.out.println(lobby.getPlayer().size());
	    	    
	    	    server.sendAll(sendall);
	    	  } else { 
	    	  server.startGame();
	    	  String sendall = "startgame";
	    	  server.sendAll(sendall);
	    	  lobby.clearLobby();
	    	  
	    	  int turn;
	    	  if(Math.random() < 0.5){
	    		  turn = 0;
	    	  }
	    	  else {
	    		  turn = 1;
	    	  }
	    	  server.getGamelogic().setTurn(turn);
	    	  
	    	  Player turnPlayer = server.getGamelogic().getPlayers().get(server.getGamelogic().getTurn());
	    	  String send = "moverequest";
	    	  server.sendPlayer(turnPlayer, send);
	    	  askmove = turnPlayer;
	    	  }
	    	  
	      }
	      
	      if(command.equals("setmove")) {
			  for(int i = 0; i < server.getGamelogic().getPlayers().size(); i++){
				  if(connection == server.getGamelogic().getPlayers().get(i).getConnection()){
					  player = server.getGamelogic().getPlayers().get(i);
				  }
				  else{
					  opponent = server.getGamelogic().getPlayers().get(i);
				  }
			  }

			  System.out.println("Degene die het zend is: " + (player == askmove));
			  if(player == askmove) {
				  String xas = fullCommand.next();
				  String yas = fullCommand.next();
				  List<Integer> availablespaces = new ArrayList<Integer>();
				  availablespaces = server.getGamelogic().availablePuts();

				  boolean valid = false;

				  int putplace = -1;

				  for (int i : availablespaces) {
					  int modulo = i % 16;
					  if (modulo == server.getGamelogic().getBoard().coordToInt(new Integer(xas), new Integer(yas), 0)) {
						  valid = true;
						  putplace = i;
						  System.out.println("Valid is set to: " + valid + " and putplace is set to: " + putplace);
					  }
				  }

				  int zas = server.getGamelogic().getBoard().intToZCoord(putplace);

				  System.out.println("Tweede if wordt: " + new Boolean(valid && putplace != -1));
				  if (valid && putplace != -1) {
					  server.getGamelogic().putTile(player.getTile(), putplace);
					  server.sendAll("notifymove " + player.getName() + " " + xas + " " + yas + " " + zas);

					  if (server.getGamelogic().gameEnd()) {
						  if (server.getGamelogic().getWinningTile() == Tile.RED) {
							  server.sendAll("gameover " + server.getGamelogic().getPlayers().get(0).getName());
						  } else {
							  server.sendAll("gameover " + server.getGamelogic().getPlayers().get(1).getName());
						  }
					  } else {
						  server.sendPlayer(opponent, "moverequest");
					  }
				  } else {
					  server.sendPlayer(player, "denymove");
					  server.sendPlayer(player, "moverequest");
				  }
				  server.getGamelogic().getBoard().printBoard();
			  }
			  else{
				  server.sendPlayer(player, "denymove");
			  }
		  }
	      
	      /*if(lobby.getPlayer().size() == 0){
	      boolean playerConnection = false;
    	  for(int i = 0; i < server.getGamelogic().getPlayers().size(); i++) {
    		  if (server.getGamelogic().getPlayers().get(i).getConnection().isAlive()) {
    			  playerConnection = true;
    		  }
    		  if (!playerConnection) {
    			  result = "connectionlost";
    		  }
    	  }
	      }*/
	      
	      scan.close();
	      fullCommand.close();
	      return result;
	    } catch (java.util.NoSuchElementException e) {
	      String result = "Invalid Server command";
	      e.printStackTrace();
	      return result;
	    }
	  }
}