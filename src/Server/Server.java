package Server;


import java.util.*;
import java.io.*;
import java.net.*;

/**
 * Created by Stan on 23-1-2017.
 */
public class Server {
	
	ServerSocket server = null;
	
	
	public Server() {
		Start();
	}
	
	public void Start() {
		Scanner input = new Scanner(System.in);
		System.out.println("Port:");
        String port = "";
        port = input.nextLine();
        
        try {
			server = new ServerSocket(new Integer(port));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			System.out.println(
			        "Waiting for client on port " + port + " with IP-adress: " 
			              + Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Connection connection = new Connection(this, server);
		}
        input.close();
		
		
	}
	
	public static void main(String[] args) {
	    new Server();
	  }
}
