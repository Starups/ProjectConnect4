package Client;

import Shared.*;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * Created by Stan on 23-1-2017.
 */
public class Client {
    private Player player;
    private Handle handle;
    private ServerCommunication sc = null;
    private Thread scThread = null;
    private Gamelogic gamelogic;
    private Board board;
    String name = "";

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start() {
        System.out.println("Welcome to Connect4, to connect to a server, enter the following");
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
        System.out.println("Playing alias:");
        name = in.nextLine();
        while (name.equals("")) {
            System.out.println("Re-enter name: ");
            name = in.nextLine();
        }
        System.out.println("Playing alias: " + name);

        player = new Player(name);
        handle = new Handle(this);
        sc = new ServerCommunication(ip, port, handle);

        if (sc.socket != null) {
            scThread = new Thread(sc);
            scThread.start();
            sc.write("joinrequest " + name + " 0 0 0 0");
            System.out.println("joinrequest " + name + " 0 0 0 0 has been written.");
        }

        while (true) {
            String typedMessage = in.nextLine();
            sc.write(typedMessage);
        }
    }

    public void startGame(){
        board = new Board();
        gamelogic = new Gamelogic(board);
        player = new Player(name);
        gamelogic.putPlayers(player, new Player("Opponent"));
    }
}
