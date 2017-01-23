package Server;


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import Shared.*;

public class ConnectionThread extends Thread{
	  Socket socket;
	  Server server;
	  PrintWriter out;
	  BufferedReader inputStream;
	  Connection connection;
	
	public ConnectionThread(Socket socket, Server server,
		      PrintWriter out, BufferedReader inputStream, Connection connection) {
		this.socket = socket;
	    this.server = server;
	    this.out = out;
	    this.inputStream = inputStream;
	    this.connection = connection;
	}
	
	@Override
	  public void run() {
	    Peer peer = new Peer(server);
	    server.connection(peer, socket);
	    System.out.println("test");
	    connection.read(socket, peer, out, inputStream);
	  }
	
		
	}
	
