package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import shared.*;
/**
 * Created by Stan on 23-1-2017.
 */
public class Server extends Thread {

	//instance variables
	private ServerSocket server = null;
	private Board board;
	private Gamelogic gamelogic;
	private String functions = "";
	private Lobby lobby;
	private Connection connection;

	//main, creates a new server
	public static void main(String[] args) {
		new Server();
	}
	//constructor
	/*
	 * constructor of Server
	 * the lobby contains the players that will play on the server
	 */
	public Server() {
		lobby = new Lobby();
		start();
	}

	/*
	 * starts a new server
	 */

	public void start() {
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
				connection = new Connection(this, server);
				Thread connectionThread = new Thread(connection);
				connectionThread.start();

				input.close();
			} catch (NumberFormatException | IOException e) {
				System.out.println("Error while trying to make a server, Try another port" + e);
			}
		}
		input.close();
	}

	/*
	 *  sends a message to all the players in the server. For example when a move has been made,
	 *  both players will receive a message.
	 *  
	 *  @param msg (the msg
	 */
	public void sendAll(String msg) {
		System.out.println("sendAll: " + msg);
		if (lobby.getPlayer().size() != 0) {
			for (int i = 0; i < lobby.getPlayer().size(); i++) {
				lobby.getPlayer().get(i).getConnection(
						).write(msg, lobby.getPlayer().get(i).getConnection().getOut());
			}
		} else if (this.getGamelogic().getPlayers().size() != 0) {
			for (int i = 0; i < this.getGamelogic().getPlayers().size(); i++) {
				this.getGamelogic().getPlayers().get(i).getConnection(
						).write(msg, this.getGamelogic(
								).getPlayers().get(i).getConnection().getOut());
			}
		}
	}

	/*
	 * sends a message to an individual player
	 * 
	 * @param player (to player the message is sent to)
	 * @param msg (the String (message) that is sent to to player)
	 */

	public void sendPlayer(Player player, String msg) {
		System.out.print("Send to " + player.getName() + ": " + msg);
		player.getConnection().write(msg, player.getConnection().getOut());
	}
	/*
	 * makes a new connection for a client
	 * 
	 * @param peer (the peer that handles the client)
	 * @param socket (the socket that the client uses)
	 */
	public void connection(Peer peer, Socket socket) {
		try {
			System.out.println("Client connected: " + socket);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			connection = new Connection(this, server);
			connection.read(socket, peer, out, inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startGame() {
		board = new Board();
		gamelogic = new Gamelogic(board);
		Player player1 = lobby.getPlayer().get(0);
		player1.setTile(Tile.RED);
		Player player2 = lobby.getPlayer().get(1);
		player2.setTile(Tile.YELLOW);
		gamelogic.putPlayers(player1, player2);
	}

	public String getFunctions() {
		return functions;
	}

	/*
	 * returns lobby
	 */

	public Lobby getLobby() {
		return lobby;
	}

	/*
	 * returns gamelogic
	 */

	public Gamelogic getGamelogic() {
		return gamelogic;
	}
}
