package test;

import org.junit.Before;
import org.junit.Test;
import shared.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stan on 1-2-2017.
 */
public class PlayerTest {
    Tile tile;
    String name;
    Player player;

    @Before
    public void setUp() {
        name = "Henk";
        player = new Player(name);
    }

    @Test
    public void setTileTest() {
        tile = Tile.RED;
        player.setTile(tile);
        assertEquals(player.getTile(), tile);
    }

    @Test
    public void setAmountTilesTest(){
        player.setAmountTiles(2);

        assertEquals(player.getAmountTiles(), 2);
    }

    @Test
    public void minusOneTest(){
        player.setAmountTiles(32);

        player.minusOne();

        assertEquals(player.getAmountTiles(), 31);
    }

}
