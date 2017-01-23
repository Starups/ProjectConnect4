package Client;

import java.util.Scanner;

/**
 * Created by Stan on 23-1-2017.
 */
public class Handle {
    Client client;

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
                System.out.println("This name has already been chosen.");
            }
            if (command.equals("waitforclient")) {
                System.out.println("Waiting for your opponent.");
            }
            if (command.equals("startgame")) {
                System.out.println("A new game will be started.");
            }
            if (command.equals("moverequest")) {
                System.out.println("Please send your move.");
            }
            if (command.equals("denymove")) {
                System.out.println("This move is not allowed.");
            }
            if (command.equals("notifymove")) {
                System.out.println("The following move has been done:");
            }
            if (command.equals("gameover")) {
                System.out.println("Game over");
            }
            if (command.equals("connectionlost")) {
                System.out.println("The connection has been lost");
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
