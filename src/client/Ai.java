package client;

import shared.*;

import java.util.List;

/**
 * Created by Stan on 31-1-2017.
 */
public class Ai {
    private Player player;
    private Client client;

    public Ai(Player player, Client client) {
        this.player = player;
        this.client = client;
    }

    public String smartmove() {
        String result = "setmove ";
        int xcoord;
        int ycoord;

        if(client.getBoard().getTile(0) == null){
            xcoord = 0;
            ycoord = 0;
        }
        else if(client.getBoard().getTile(0) == Tile.RED && client.getBoard().getTile(1) == null){
            xcoord = 1;
            ycoord = 0;
        }
        else if(client.getBoard().getTile(0) == Tile.RED && client.getBoard().getTile(1) == Tile.RED && client.getBoard().getTile(2) == null){
            xcoord = 2;
            ycoord = 0;
        }
        else if(client.getBoard().getTile(0) == Tile.RED && client.getBoard().getTile(1) == Tile.RED && client.getBoard().getTile(2) == Tile.RED && client.getBoard().getTile(3) == null){
            xcoord = 3;
            ycoord = 0;
        }
        else {
            List<Integer> places = client.getGamelogic().availablePuts();

            int chosen = places.get((int) (Math.random() * places.size()));

            xcoord = client.getBoard().intToXCoord(chosen);
            ycoord = client.getBoard().intToYCoord(chosen);
        }
        result = result + xcoord + " " + ycoord;

        return result;
    }
}
