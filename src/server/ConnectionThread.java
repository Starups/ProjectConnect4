package server;


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import shared.*;

public class ConnectionThread extends Thread {
	//instance variables
	Socket socket;
	Server server;
	PrintWriter out;
	BufferedReader inputStream;
	Connection connection;
	Board board;
	  
	  //constructor
	  /*
	   * constructor for ConnectionThread
	   * 
	   * @param socket
	   * @param server
	   * @param out
	   * @param inputStream
	   * @param connection
	   */
	public ConnectionThread(Socket socket, Server server,
		      PrintWriter out, BufferedReader inputStream, Connection connection) {
		this.socket = socket;
	    this.server = server;
	    this.out = out;
	    this.inputStream = inputStream;
	    this.connection = connection;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	  public void run() {
	    Peer peer = new Peer(server);
	    server.connection(peer, socket);
	    System.out.println("test");
	    connection.read(socket, peer, out, inputStream);
	}
	
		
}
	
