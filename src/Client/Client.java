package Client;

import Shared.*;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * Created by Stan on 23-1-2017.
 */
public class Client {
    private Player player;
    private Player opponent;
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
        System.out.println("User name:");
        name = in.nextLine();
        while (name.equals("")) {
            System.out.println("Re-enter name: ");
            name = in.nextLine();
        }
        System.out.println("User name: " + name);

        player = new Player(name);
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

    public void startGame(String color){
        board = new Board();
        gamelogic = new Gamelogic(board);
        player = new Player(name);
        opponent = new Player("Opponent");
        if(color.equals("Red")){
            player.setTile(Tile.RED);
            opponent.setTile(Tile.YELLOW);
        }
        else{
            player.setTile(Tile.YELLOW);
            opponent.setTile(Tile.RED);
        }
        gamelogic.putPlayers(player, new Player("Opponent"));
    }

    public Player getPlayer(){
        return player;
    }

    public Gamelogic getGamelogic(){
        return gamelogic;
    }

    public Board getBoard(){
        return board;
    }

    public ServerCommunication getSc(){
        return sc;
    }
}
