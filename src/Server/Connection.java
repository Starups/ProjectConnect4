package Server;



import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import Shared.*;

public class Connection {
	Server server;
	  ServerSocket serversock;
	  Gamelogic gamelogic;
	  Board board;
	  String clientMessage = "";
	  Socket connection;
	  PrintWriter out;
	  BufferedReader inputStream;
	
	public Connection(Server server, ServerSocket serversock) {
		this.server = server;
		this.serversock = serversock;
		
	}
	
	public void run() {
		  try {
		      while (true) {
		        Socket socket = serversock.accept();
		        System.out.println("Awaiting next connection");
		      }
		    } catch (IOException e1) {
		      e1.printStackTrace();
		    }

		  }
	
	public void read(Socket connection) {
		this.connection = connection;
	}
	
	public Server getServer() {
		    return server;
		  }
}