package client;

import shared.*;

import java.util.Scanner;

/**
 * Created by Stan on 23-1-2017.
 */
public class Client {
	
	// instant variables
    private Player player;
    private Player opponent;
    private Handle handle;
    private ServerCommunication sc = null;
    private Thread scThread = null;
    private Gamelogic gamelogic;
    private Board board;
    private boolean aibool;
    private Ai ai;
    String name = "";
    String color = "";

    
    /*
     * main, creates a new client 
     */
    
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
   
    /*
     * handles the commands sent by the server, you can enter a lobby when you got the right
     *  IP and port number.
     */

    public void start() {
        System.out.println(
        		"Welcome to Connect4, to connect to a server, enter the following things:");
        Scanner in = new Scanner(System.in);
        System.out.println("Ip:");
        String ip = "";
        ip = in.nextLine();
        while (ip.equals("")) {
            System.out.println("Re-enter IP: ");
            ip = in.nextLine();
        }
        System.out.println("Ip: " + ip);
        System.out.println("port:");
        String portEntry = in.nextLine();
        int port = new Integer(portEntry);
        while (portEntry.equals("")) {
            System.out.println("Re-enter Port: ");
            portEntry = in.nextLine();
        }
        port = new Integer(portEntry);
        System.out.println("port: " + port);
        System.out.println("Ai? (Yes or No)");
        String yesorno = "";
        yesorno = in.nextLine();
        if(yesorno.equals("No")){
            this.aibool = false;
            System.out.println("User name:");
            name = in.nextLine();
            while (name.equals("")) {
                System.out.println("Re-enter name: ");
                name = in.nextLine();
            }
            System.out.println("User name: " + name);
            System.out.println("What color would you like to be?");
            color = in.nextLine();
            while (!color.equals("Red") && !color.equals("Yellow")) {
                System.out.println("The color has to be: 'Red' or 'Yellow'");
            }
            System.out.println("You are : " + color);
        }
        else{
            this.aibool = true;
            name = "ComputerPlayer" + Math.random();
            System.out.println(name);
            color = "Red";

        }
        
        /*
         * choose which color your tile will be for this game, you type either 'Red' or 'Yellow'
         */

        player = new Player(name);
        ai = new Ai(player, this);
        handle = new Handle(this);
        sc = new ServerCommunication(ip, port, handle);

        if (sc.socket != null) {
            scThread = new Thread(sc);
            scThread.start();
            sc.write("joinrequest " + name + " 0 0 0 0");
        }


        while (true) {
            String typedMessage = in.nextLine();
            sc.write(typedMessage);
        }
    }
    /*
     *  Starts the game, sets up both players on the board and gives them both a color
     */
    public void startGame() {
        board = new Board();
        gamelogic = new Gamelogic(board);
        player = new Player(name);
        opponent = new Player("Opponent");
        if (color.equals("Red")) {
            player.setTile(Tile.RED);
            opponent.setTile(Tile.YELLOW);
        } else {
            player.setTile(Tile.YELLOW);
            opponent.setTile(Tile.RED);
        }
        gamelogic.putPlayers(player, new Player("Opponent"));
    }
    
    /*
     * returns the player
     * 
     * @return player
     */

    public Player getPlayer() {
        return player;
    }
   
    /*
     * returns the gameLogic
     * 
     * @return gameLogic
     */
    
    public Gamelogic getGamelogic() {
        return gamelogic;
    }
    
    /*
     * returns the board
     * 
     * @return board
     */
    public Board getBoard() {
        return board;
    }
    /*
     * returns the server communication
     * 
     * @return serverCommunication
     */
    public ServerCommunication getSc() {
        return sc;
    }

    /*
     * returns true if player is ai and false if player is human
     *
     * @return boolean
     */
    public boolean getAibool(){
        return aibool;
    }

    public Ai getAi(){
        return ai;
    }
}
