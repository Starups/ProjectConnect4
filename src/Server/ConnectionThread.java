package Server;


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import Shared.*;

public class ConnectionThread {
	Socket socket;
	Server server;
	Connection connection;
	
	public ConnectionThread(Socket socket, Server server, Connection connection) {
		this.server = server;
		this.socket = socket;
		this.connection = connection;
	}
	
	 
	 public void run() {
	    Peer peer = new Peer(gamelogic, board, bag, server);
	    server.connection(peer, socket);
	    System.out.println("test");
	    connection.read(peer, socket, out, inputStream);
	  }
	
		
	}
	
