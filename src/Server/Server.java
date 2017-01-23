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
	Peer peer;
	
	
	public Server() {
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
		        
		          System.out.println(
		                "Waiting for client on port " + port + " with IP-adress: " 
		                      + Inet4Address.getLocalHost().getHostAddress());
		          Connection connection = new Connection(this, server);
		          Thread connectionThread = new Thread(connection);
		          connectionThread.start();
		          input.close();
		} catch (Exception e) {
	        System.out.println("Error while trying to make a server, Try another port");
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
