package Client;

import Shared.Tile;

import java.util.Scanner;

/**
 * Created by Stan on 23-1-2017.
 */
public class Handle {
    Client client;

    public static void main(String[] args) {
        Client client = new Client();
        Handle handle = new Handle(client);
        handle.handleCommand("acceptrequest");
        handle.handleCommand("denyrequest");
        handle.handleCommand("waitforclient");
        handle.handleCommand("moverequest");
        handle.handleCommand("denymove");
        handle.handleCommand("notifymove henk 1 1 0");
        handle.handleCommand("gameover hebk");
        handle.handleCommand("connectionlost");
        handle.handleCommand("invalidcommand");

    }

    public Handle(Client client){
        this.client = client;
    }

    public void handleCommand(String cmd){
        System.out.println("handlingCommand: " + cmd);
        Scanner scan = new Scanner(cmd);
        String str = scan.nextLine();
        try {
            Scanner fullCommand = new Scanner(str);

            String command = fullCommand.next();
            command = command.replace("", ""); //To fix a weird bug where it would place 's in front of messages from the server
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
                System.out.println("What color would you like to be?");
                Scanner in = new Scanner(System.in);
                String color = in.nextLine();
                while(!color.equals("Red") && !color.equals("Yellow")){
                    System.out.println("The color has to be: 'Red' or 'Yellow'");
                }
                client.startGame(color);
                System.out.println("You are " + client.getPlayer().getTile());
            }
            if (command.equals("moverequest")) {
                System.out.println("Please send your move.");
            }
            if (command.equals("denymove")) {
                System.out.println("This move is not allowed. Please send an allowed move.");
            }
            if (command.equals("notifymove")) {
                String name = fullCommand.next();
                Tile tile = client.getPlayer().getTile();
                if(!client.getPlayer().getName().equals(name)){
                    if(tile == Tile.RED){
                        tile = Tile.YELLOW;
                    }
                    else{
                        tile = Tile.RED;
                    }
                }

                String xas = fullCommand.next();
                String zas = fullCommand.next();
                String yas = fullCommand.next();
                client.getGamelogic().putTile(tile, client.getBoard().coordToInt(new Integer(xas), new Integer(yas), new Integer(zas)));
                System.out.println(name + " has done the following move: " + xas + "," + zas + "," + yas);
            }
            if (command.equals("gameover")) {
                String name = fullCommand.next();
                if(name.equals(client.getPlayer().getName())){
                    System.out.println("Congratulations! You have won the game!");
                }
                else{
                    System.out.println("Unlucky! You have lost the game!");
                }
                client.getSc().close();
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
