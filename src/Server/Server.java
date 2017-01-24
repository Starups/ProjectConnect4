package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Shared.*;
/**
 * Created by Stan on 23-1-2017.
 */
public class Server extends Thread{
	
	ServerSocket server = null;
	private Peer peer;
	private Player player;
	private Board board;
	private Gamelogic gamelogic;
	private String functions = "";
	private Lobby lobby;
	private Connection connection;
	
	public Server() {
		lobby = new Lobby();
		Start();
	}
	
	public void Start() {
		Scanner input = new Scanner(System.in);
		while (server == null) {
		try {
			System.out.println("Port:");
			String port = "";
			port = input.nextLine();
			while (port.equals("")) {
		          System.out.println("Re-enter port: ");
		          port = input.nextLine();
			}
		          server = new ServerSocket(new Integer(port));
		          board = new Board();
		          gamelogic = new Gamelogic(board);
		        
		          System.out.println(
		                "Waiting for client on port " + port + " with IP-adress: " 
		                      + Inet4Address.getLocalHost().getHostAddress());
		          connection = new Connection(this, server);
		          Thread connectionThread = new Thread(connection);
		          connectionThread.start();
		          
		          input.close();
		} catch (Exception e) {
	        System.out.println("Error while trying to make a server, Try another port"+ e);
	      }
	    }
	    input.close();
	  }
	
	
	public static void main(String[] args) {
	    new Server();
	  }
	
	  public Peer getPeer() {
		    return peer;
		  }
	  public String getFunctions() {
		  return functions;
	  }
	  
	  public Lobby getLobby() {
		  return lobby;
	  }
	  
	  public void sendAll(String msg) {
		    System.out.println("sendAll: " + msg);
		    for (int p = 0; p < lobby.getPlayer().size(); p++) {
		      connection.write(
		         msg, lobby.getPlayer().get(p).getConnection().getOut());
		    }
	  }
	  
	  public Gamelogic getGamelogic() {
		  return gamelogic;
	  }
	  public void connection(Peer peer, Socket socket) {
		    try {
		      System.out.println("Client connected: " + socket);
		      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		      BufferedReader inputStream = new BufferedReader(
		          new InputStreamReader(socket.getInputStream()));
		      Connection connection = new Connection(this, server);
		      connection.read(socket, peer, out, inputStream);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }


}
