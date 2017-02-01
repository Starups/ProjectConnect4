package server;



import java.net.ServerSocket;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Connection extends Thread {
	//instance variables
	Server server;
	ServerSocket serversock;
	String clientMessage = "";
	Socket connection;
	PrintWriter out;
	BufferedReader inputStream;
	Peer peer;
	
	//constructor
	
	/*
	 * constructor of Connection
	 * 
	 * @param server 
	 * @param serversock
	 */
	public Connection(Server server, ServerSocket serversock) {
		this.server = server;
		this.serversock = serversock;
		
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
	    try {
		      
		    while (true) {
		        Socket socket = serversock.accept();
		        ConnectionThread connectionThread = new ConnectionThread(
		        		socket, server, out, inputStream, this);
		        connectionThread.start();
		        System.out.println("Awaiting next connection");
		    }
	    } catch (IOException e1) {
	        System.out.println("Error" + e1);
		    e1.printStackTrace();
	    }

    }
	/*
	 * Keeps reading the socket.
	 */
	public void read(Socket connection, Peer peer, PrintWriter out, BufferedReader inputStream) {
		this.connection = connection;
		this.peer = peer;
		this.out = out;
		this.inputStream = inputStream;
	    System.out.println("Reading: " + connection);
	    try {
	        while (!clientMessage.equals("Close")) {
	        	if (!clientMessage.equals("")) {
	        		System.out.println("clientMessage:" + clientMessage);
	        		String handledCommand = peer.handleCommand(clientMessage, this);

	        		write(handledCommand, out);
	        	}

	        	clientMessage = inputStream.readLine();
	        }
	        this.close(connection);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	/*
	 * 
	 */
		  
	public void write(String str, PrintWriter out) {
	    out.println(str);
    }
	
	/*
	 * @return out
	 */
	public PrintWriter getOut() {
		return out;
	}
	
	/*
	 * command that closes the socket
	 * 
	 * @param connection 
	 */
	public void close(Socket connection) {
	    try {
	    	connection.close();
	    	System.out.println("The connection has been closed.");
	    } catch (IOException e) {
	    	System.out.println("Error while closing: " + e);
	    }
	}
}