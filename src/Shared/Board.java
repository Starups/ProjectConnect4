package Shared;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stan on 25-12-2016.
 */
public class Board {
    private Map<Integer, Tile> tileLocs = new HashMap<Integer, Tile>();

    public static void main(String[] args) {
        Board board = new Board();
        board.putTile(Tile.RED, 0);
        board.putTile(Tile.YELLOW, 1);
        board.putTile(Tile.RED, 2);
        board.putTile(Tile.YELLOW, 16);
        board.printBoard();
    }

    public void putTile(Tile tile, int place){
        if(tileLocs.containsKey(place)){
            tileLocs.replace(place, tile);
        }
        else {
            tileLocs.put(place, tile);
        }
    }

    public Tile getTile(int place){
        return tileLocs.get(place);
    }

    public int coordToInt(int xcoord, int ycoord, int zcoord){
        return xcoord + 4*ycoord + 16*zcoord;
    }

    public int intToXCoord(int coord){
        return coord % 4;
    }

    public int intToYCoord(int coord){
        return (coord % 16)/4;
    }

    public int intToZCoord(int coord){
        return coord/16;
    }

    public Map<Integer, Tile> getTileLocs(){
        return tileLocs;
    }

    public void printBoard(){
        for(int z = 3; z >= 0; z--){
            System.out.println("z = " + z);
            for(int y = 3; y >= 0; y--){
                System.out.println(this.getTile(0 + y * 4 + z * 16) + " " +
                        this.getTile(1 + y * 4 + z * 16) + " " +
                        this.getTile(2 + y * 4 + z * 16) + " " +
                        this.getTile(3 + y * 4 + z * 16));
            }
            System.out.println();
        }
    }
}
