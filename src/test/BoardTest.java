package test;

import shared.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stan on 31-1-2017.
 */
public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }
 
    @Test
    public void putTileTest() {
        Tile tile = Tile.RED;
        board.putTile(tile, 0);

        assertEquals(board.getTile(0), tile);
    }

    @Test
    public void coordToIntTest() {
        int xcoord = 0;
        int ycoord = 0;
        int zcoord = 0;
        assertEquals(board.coordToInt(xcoord, ycoord, zcoord), 0);

        xcoord = 1;
        ycoord = 1;
        zcoord = 2;
        assertEquals(board.coordToInt(xcoord, ycoord, zcoord), 37);
    }

    @Test
    public void intToXCoordTest() {
        assertEquals(board.intToXCoord(24), 0);
        assertEquals(board.intToXCoord(35), 3);
    }

    @Test
    public void intToYCoordTest() {
        assertEquals(board.intToYCoord(24), 2);
        assertEquals(board.intToYCoord(35), 0);
    }

    @Test
    public void intToZCoordTest() {
        assertEquals(board.intToZCoord(24), 1);
        assertEquals(board.intToZCoord(35), 2);
    }

    @Test
    public void clearTest() {
        board.clear();

        for (int i = 0; i < 64; i++) {
            assertEquals(board.getTile(i), null);
        }
    }


}
