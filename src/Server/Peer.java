package Server;


import java.util.Scanner;

import Shared.*;

public class Peer {
	Server server;
	
	public Peer(Server server) {
		this.server = server;
	}

	 
	public String handleCommand(String cmd, Connection connection) {
		 try {
	 
	      String result = "";
	      Scanner scan = new Scanner(cmd);
	      String str = scan.nextLine();
	      Scanner fullCommand = new Scanner(str);

	      String command = fullCommand.next();

	      if (command.equals("joinrequest")) {
	     }
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