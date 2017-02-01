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

        if(client.getBoard().getTile(0) == Tile.YELLOW
                && client.getBoard().getTile(16) == Tile.YELLOW
                && client.getBoard().getTile(32) == Tile.YELLOW
                && client.getBoard().getTile(48) == null){
            xcoord = 0;
            ycoord = 0;
        } else if(client.getBoard().getTile(1) == Tile.YELLOW
                && client.getBoard().getTile(17) == Tile.YELLOW
                && client.getBoard().getTile(33) == Tile.YELLOW
                && client.getBoard().getTile(49) == null){
            xcoord = 1;
            ycoord = 0;
        } else if(client.getBoard().getTile(2) == Tile.YELLOW
                && client.getBoard().getTile(18) == Tile.YELLOW
                && client.getBoard().getTile(34) == Tile.YELLOW
                && client.getBoard().getTile(50) == null){
            xcoord = 2;
            ycoord = 0;
        } else if(client.getBoard().getTile(3) == Tile.YELLOW
                && client.getBoard().getTile(19) == Tile.YELLOW
                && client.getBoard().getTile(35) == Tile.YELLOW
                && client.getBoard().getTile(51) == null){
            xcoord = 3;
            ycoord = 0;
        } else if(client.getBoard().getTile(4) == Tile.YELLOW
                && client.getBoard().getTile(20) == Tile.YELLOW
                && client.getBoard().getTile(36) == Tile.YELLOW
                && client.getBoard().getTile(52) == null){
            xcoord = 0;
            ycoord = 1;
        } else if(client.getBoard().getTile(5) == Tile.YELLOW
                && client.getBoard().getTile(21) == Tile.YELLOW
                && client.getBoard().getTile(37) == Tile.YELLOW
                && client.getBoard().getTile(53) == null){
            xcoord = 1;
            ycoord = 1;
        } else if(client.getBoard().getTile(6) == Tile.YELLOW
                && client.getBoard().getTile(22) == Tile.YELLOW
                && client.getBoard().getTile(37) == Tile.YELLOW
                && client.getBoard().getTile(54) == null){
            xcoord = 2;
            ycoord = 1;
        } else if(client.getBoard().getTile(7) == Tile.YELLOW
                && client.getBoard().getTile(23) == Tile.YELLOW
                && client.getBoard().getTile(39) == Tile.YELLOW
                && client.getBoard().getTile(55) == null){
            xcoord = 3;
            ycoord = 1;
        } else if(client.getBoard().getTile(8) == Tile.YELLOW
                && client.getBoard().getTile(24) == Tile.YELLOW
                && client.getBoard().getTile(40) == Tile.YELLOW
                && client.getBoard().getTile(56) == null){
            xcoord = 0;
            ycoord = 2;
        } else if(client.getBoard().getTile(9) == Tile.YELLOW
                && client.getBoard().getTile(25) == Tile.YELLOW
                && client.getBoard().getTile(41) == Tile.YELLOW
                && client.getBoard().getTile(57) == null){
            xcoord = 1;
            ycoord = 2;
        } else if(client.getBoard().getTile(10) == Tile.YELLOW
                && client.getBoard().getTile(26) == Tile.YELLOW
                && client.getBoard().getTile(42) == Tile.YELLOW
                && client.getBoard().getTile(58) == null){
            xcoord = 2;
            ycoord = 2;
        } else if(client.getBoard().getTile(11) == Tile.YELLOW
                && client.getBoard().getTile(27) == Tile.YELLOW
                && client.getBoard().getTile(43) == Tile.YELLOW
                && client.getBoard().getTile(59) == null){
            xcoord = 3;
            ycoord = 2;
        } else if(client.getBoard().getTile(12) == Tile.YELLOW
                && client.getBoard().getTile(28) == Tile.YELLOW
                && client.getBoard().getTile(44) == Tile.YELLOW
                && client.getBoard().getTile(60) == null){
            xcoord = 0;
            ycoord = 3;
        } else if(client.getBoard().getTile(13) == Tile.YELLOW
                && client.getBoard().getTile(29) == Tile.YELLOW
                && client.getBoard().getTile(45) == Tile.YELLOW
                && client.getBoard().getTile(61) == null){
            xcoord = 1;
            ycoord = 3;
        } else if(client.getBoard().getTile(14) == Tile.YELLOW
                && client.getBoard().getTile(30) == Tile.YELLOW
                && client.getBoard().getTile(46) == Tile.YELLOW
                && client.getBoard().getTile(62) == null){
            xcoord = 2;
            ycoord = 3;
        } else if(client.getBoard().getTile(15) == Tile.YELLOW
                && client.getBoard().getTile(31) == Tile.YELLOW
                && client.getBoard().getTile(47) == Tile.YELLOW
                && client.getBoard().getTile(63) == null){
            xcoord = 3;
            ycoord = 3;
        } else if(client.getBoard().getTile(0) == Tile.YELLOW
                && client.getBoard().getTile(1) == Tile.YELLOW
                && client.getBoard().getTile(2) == Tile.YELLOW
                && client.getBoard().getTile(3) == null){
            xcoord = 3;
            ycoord = 0;
        } else if(client.getBoard().getTile(4) == Tile.YELLOW
                && client.getBoard().getTile(5) == Tile.YELLOW
                && client.getBoard().getTile(6) == Tile.YELLOW
                && client.getBoard().getTile(7) == null) {
            xcoord = 3;
            ycoord = 1;
        } else if(client.getBoard().getTile(8) == Tile.YELLOW
                && client.getBoard().getTile(9) == Tile.YELLOW
                && client.getBoard().getTile(10) == Tile.YELLOW
                && client.getBoard().getTile(11) == null) {
            xcoord = 3;
            ycoord = 2;
        } else if(client.getBoard().getTile(12) == Tile.YELLOW
                && client.getBoard().getTile(13) == Tile.YELLOW
                && client.getBoard().getTile(14) == Tile.YELLOW
                && client.getBoard().getTile(15) == null) {
            xcoord = 3;
            ycoord = 3;
        } else if(client.getBoard().getTile(0) == Tile.YELLOW
                && client.getBoard().getTile(4) == Tile.YELLOW
                && client.getBoard().getTile(8) == Tile.YELLOW
                && client.getBoard().getTile(12) == null) {
            xcoord = 0;
            ycoord = 3;
        } else if(client.getBoard().getTile(1) == Tile.YELLOW
                && client.getBoard().getTile(5) == Tile.YELLOW
                && client.getBoard().getTile(9) == Tile.YELLOW
                && client.getBoard().getTile(13) == null) {
            xcoord = 1;
            ycoord = 3;
        } else if(client.getBoard().getTile(2) == Tile.YELLOW
                && client.getBoard().getTile(6) == Tile.YELLOW
                && client.getBoard().getTile(10) == Tile.YELLOW
                && client.getBoard().getTile(14) == null) {
            xcoord = 2;
            ycoord = 3;
        } else if(client.getBoard().getTile(3) == Tile.YELLOW
                && client.getBoard().getTile(7) == Tile.YELLOW
                && client.getBoard().getTile(11) == Tile.YELLOW
                && client.getBoard().getTile(15) == null) {
            xcoord = 3;
            ycoord = 3;
        } else if (client.getBoard().getTile(0) == null) {
            xcoord = 0;
            ycoord = 0;
        } else if (client.getBoard().getTile(0) == Tile.RED
        		&& client.getBoard().getTile(1) == null) {
            xcoord = 1;
            ycoord = 0;
        } else if (client.getBoard().getTile(0) == Tile.RED
        		&& client.getBoard().getTile(1) == Tile.RED
        		&& client.getBoard().getTile(3) == null) {
            xcoord = 3;
            ycoord = 0;
        } else if (client.getBoard().getTile(0) == Tile.RED
        		&& client.getBoard().getTile(1) == Tile.RED
        		&& client.getBoard().getTile(3) == Tile.RED
        		&& client.getBoard().getTile(2) == null) {
            xcoord = 2;
            ycoord = 0;
        } else if (client.getBoard().getTile(4) == null) {
            xcoord = 0;
            ycoord = 1;
        } else if (client.getBoard().getTile(4) == Tile.RED
                && client.getBoard().getTile(5) == null) {
            xcoord = 1;
            ycoord = 1;
        } else if (client.getBoard().getTile(4) == Tile.RED
                && client.getBoard().getTile(5) == Tile.RED
                && client.getBoard().getTile(7) == null) {
            xcoord = 3;
            ycoord = 1;
        } else if (client.getBoard().getTile(4) == Tile.RED
                && client.getBoard().getTile(5) == Tile.RED
                && client.getBoard().getTile(7) == Tile.RED
                && client.getBoard().getTile(6) == null) {
            xcoord = 2;
            ycoord = 1;
        } else if (client.getBoard().getTile(8) == null) {
            xcoord = 0;
            ycoord = 2;
        } else if (client.getBoard().getTile(8) == Tile.RED
                && client.getBoard().getTile(9) == null) {
            xcoord = 1;
            ycoord = 2;
        } else if (client.getBoard().getTile(8) == Tile.RED
                && client.getBoard().getTile(9) == Tile.RED
                && client.getBoard().getTile(11) == null) {
            xcoord = 3;
            ycoord = 2;
        } else if (client.getBoard().getTile(8) == Tile.RED
                && client.getBoard().getTile(9) == Tile.RED
                && client.getBoard().getTile(11) == Tile.RED
                && client.getBoard().getTile(10) == null) {
            xcoord = 2;
            ycoord = 2;
        } else if (client.getBoard().getTile(12) == null) {
            xcoord = 0;
            ycoord = 3;
        } else if (client.getBoard().getTile(12) == Tile.RED
                && client.getBoard().getTile(13) == null) {
            xcoord = 1;
            ycoord = 3;
        } else if (client.getBoard().getTile(12) == Tile.RED
                && client.getBoard().getTile(13) == Tile.RED
                && client.getBoard().getTile(15) == null) {
            xcoord = 3;
            ycoord = 3;
        } else if (client.getBoard().getTile(12) == Tile.RED
                && client.getBoard().getTile(13) == Tile.RED
                && client.getBoard().getTile(15) == Tile.RED
                && client.getBoard().getTile(14) == null) {
            xcoord = 2;
            ycoord = 3;
        } else {
            List<Integer> places = client.getGamelogic().availablePuts();

            int chosen = places.get((int) (Math.random() * places.size()));

            xcoord = client.getBoard().intToXCoord(chosen);
            ycoord = client.getBoard().intToYCoord(chosen);
        }

       /* for(int i = 0; i < client.getGamelogic().availablePuts().size(); i++){
            client.getBoard().putTile(Tile.YELLOW, client.getGamelogic().availablePuts().get(i));

            if(client.getGamelogic().gameEnd()){
                xcoord = client.getBoard().intToXCoord(i);
                ycoord = client.getBoard().intToYCoord(i);
            }

            client.getBoard().putTile(null, client.getGamelogic().availablePuts().get(i));
        }
        */

        result = result + xcoord + " " + ycoord;

        return result;
    }
}
