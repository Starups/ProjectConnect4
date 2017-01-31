package shared;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stan on 25-12-2016.
 */
public class Board {
	//variables
    private Map<Integer, Tile> tileLocs = new HashMap<Integer, Tile>();
    //main
    /*
     * a small test
     */
    public static void main(String[] args) {
        Board board = new Board();
        board.putTile(Tile.RED, 0);
        board.putTile(Tile.YELLOW, 1);
        board.putTile(Tile.RED, 2);
        board.putTile(Tile.YELLOW, 16);
        board.printBoard();
    }
    /*
     * command that puts a tile in a list, that has the location of the tile
     * 
     * @param tile (the tile)
     * @param place (the place on the board on which the tile will be placed)
     */
    public void putTile(Tile tile, int place) { 
        if (tileLocs.containsKey(place)) {
            tileLocs.replace(place, tile);
        } else {
            tileLocs.put(place, tile);
        }
    }
    
    /*
     * returns the place of a tile
     * 
     * @param place (the place of the tile)
     * @return tileLocs (place of the tile)
     */
    
    public Tile getTile(int place) {
        return tileLocs.get(place);
    }
    
    /*
     * @param xcoord (x value)
     * @param ycoord (y value)
     * @param zcoord (z value)
     * 
     * @return int (all values)
     */

    public int coordToInt(int xcoord, int ycoord, int zcoord) {
        return xcoord + 4 * ycoord + 16 * zcoord;
    }

    /*
     * @param coord (x value)
     * @return int (value)
     */
    public int intToXCoord(int coord) {
        return coord % 4;
    }
    /*
     * @param coord (y value)
     * @return int (value)
     */

    public int intToYCoord(int coord) {
        return (coord % 16) / 4;
    }
    /*
     * @param coord (z value)
     * @return int (value)
     */

    public int intToZCoord(int coord) {
        return coord / 16;
    }
    
    /*
     * returns the location of the tiles
     * 
     * @return tileLocs
     */
    
    public Map<Integer, Tile> getTileLocs() {
        return tileLocs;
    }
    
    /*
     * clears the board
     */

    public void clear() {
        for (int i = 0; i < 64; i++) {
            this.putTile(null, i);
        }
    }
    
    /*
     * prints the board, 4x4x4
     */

    public void printBoard() {
        for (int z = 3; z >= 0; z--) {
            System.out.println("z = " + z);
            for (int y = 3; y >= 0; y--) {
                System.out.println(this.getTile(0 + y * 4 + z * 16) + " " +
                        this.getTile(1 + y * 4 + z * 16) + " " +
                        this.getTile(2 + y * 4 + z * 16) + " " +
                        this.getTile(3 + y * 4 + z * 16));
            }
            System.out.println();
        }
    }
}
