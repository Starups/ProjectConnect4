package client;

import shared.*;

import java.util.List;

/**
 * Created by Stan on 31-1-2017.
 */
public class Ai {
    private Player player;
    private Client client;

    public Ai(Player player , Client client) {
        this.player = player;
        this.client = client;
    }

    public String smartmove() {
        String result = "setmove ";

        List<Integer> places = client.getGamelogic().availablePuts();

        int chosen = places.get((int) (Math.random() * places.size()));

        int xcoord = client.getBoard().intToXCoord(chosen);
        int ycoord = client.getBoard().intToYCoord(chosen);
        result = result + xcoord + " " + ycoord;

        return result;
    }
}
