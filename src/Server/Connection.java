package Server;



import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import Shared.*;

public class Connection extends Thread {
	Server server;
	ServerSocket serversock;
	String clientMessage = "";
	Socket connection;
	PrintWriter out;
	BufferedReader inputStream;
	Peer peer;
	
	
	public Connection(Server server, ServerSocket serversock) {
		this.server = server;
		this.serversock = serversock;
		
	}
	@Override
	public void run() {
		  try {
		      
		      while (true) {
		        Socket socket = serversock.accept();
		        ConnectionThread connectionThread = new ConnectionThread(socket, server, out,inputStream, this);
		        connectionThread.start();
		        System.out.println("Awaiting next connection");
		      }
		    } catch (IOException e1) {
		    	 System.out.println("Error"+ e1);
		      e1.printStackTrace();
		    }

		  }
	
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
	          
	          if (handledCommand.equals("gameover")) {
	            this.close(connection);
	          }
	          write(handledCommand, out);
	        }

	        clientMessage = inputStream.readLine();
	      }
	      this.close(connection);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	
		  
	public void write(String str, PrintWriter out) {
		    out.println(str);
		  }

	public PrintWriter getOut() {
		return out;
	}

	public void close(Socket connection) {
	    try {
	      connection.close();
	      System.out.println("The connection has been closed.");
	    } catch (IOException e) {
	      System.out.println("Error while closing: " + e);
	    }
	  }
}