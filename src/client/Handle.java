package client;

import java.util.Scanner;

import shared.Tile;

/**
 * Created by Stan on 23-1-2017.
 */
public class Handle {
    Client client;

    /*
     * main, creates the client and the handle between the client and the server.
     */
    public static void main(String[] args) {
        Client client = new Client();
        Handle handle = new Handle(client);
        handle.handleCommand("acceptrequest");
        handle.handleCommand("denyrequest");
        handle.handleCommand("waitforclient");
        handle.handleCommand("moverequest");
        handle.handleCommand("denymove");
        handle.handleCommand("gameover hebk");
        handle.handleCommand("connectionlost");
        handle.handleCommand("invalidcommand");

    }
    
    /*
     * constructor of Handle
     * 
     * @param client (the client that is playing the game)
     */

    public Handle(Client client) {
        this.client = client;
    }
    
    /*
     * handles all the commands that are sent by the server, 
     * gives the appropriate response to the client.
     * 
     * @param cmd (this is the String sent by the server)
     */
    public void handleCommand(String cmd) {
        Scanner scan = new Scanner(cmd);
        String str = scan.nextLine();
        try {
            Scanner fullCommand = new Scanner(str);

            String command = fullCommand.next();
            command = command.replace("", ""); 
            if (command.equals("acceptrequest")) {
                System.out.println("The server has accepted the connection.");
            }
            if (command.equals("denyrequest")) {
                System.out.println("This name has already been chosen. Reconnect with a new name.");
            }
            if (command.equals("waitforclient")) {
                System.out.println("Waiting for your opponent.");
            }
            if (command.equals("startgame")) {
                System.out.println("A new game will be started.");
                client.startGame();
                System.out.println("You are " + client.getPlayer().getTile());
            }
            if (command.equals("moverequest")) {
                System.out.println("Please send your move.");
                if (client.getAibool()) {
                    String move = client.getAi().smartmove();
                    client.getSc().write(move);
                }
            }
            if (command.equals("denymove")) {
                System.out.println("This move is not allowed. Please send an allowed move.");
            }
            if (command.equals("notifymove")) {
                String name = fullCommand.next();
                Tile tile = client.getPlayer().getTile();
                boolean bool = client.getPlayer().getName().equals(name);
                if (!bool) {
                    if (tile == Tile.RED) {
                        tile = Tile.YELLOW;
                    } else {
                        tile = Tile.RED;
                    }
                }

                String xas = fullCommand.next();
                String zas = fullCommand.next();
                String yas = fullCommand.next();
                client.getGamelogic().putTile(tile, client.getBoard().coordToInt(
                	new Integer(xas), new Integer(yas), new Integer(zas)));
                System.out.println(
                		name + " has done the following move: " + xas + "," + zas + "," + yas);
                client.getBoard().printBoard();
            }
            if (command.equals("gameover")) {
                String name = fullCommand.next();
                if (name.equals(client.getPlayer().getName())) {
                    System.out.println("Congratulations! You have won the game!");
                } else {
                    System.out.println("Unlucky! You have lost the game!");
                }
                System.out.println("Please restart the client to play another game.");
            }
            if (command.equals("connectionlost")) {
                System.out.println("The connection has been lost with your opponent");
            }
            if (command.equals("invalidcommand")) {
                System.out.println("This command is not valid");
            }
            scan.close();
            fullCommand.close();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Invalid Server command: " + str);
            scan.close();
        }
    }
}
