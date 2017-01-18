package Test;

import Shared.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Stan on 18-1-2017.
 */
public class GamelogicTest {
    Board board;
    Gamelogic gamelogic;
    Player henk;
    Player barry;

    @Before
    public void setUp() {
        board = new Board();
        gamelogic = new Gamelogic(board);
        henk = new Player(Tile.RED, "henk");
        barry = new Player(Tile.YELLOW, "barry");
        gamelogic.putPlayers(henk, barry);
    }

    @Test
    public void putPlayersTest(){
        Map<Integer, Player> players = new HashMap<Integer, Player>();
        players.put(0, henk);
        players.put(1, barry);

        assertEquals(gamelogic.getPlayers(), players);
    }

    @Test
    public void nextTurnTest(){
        assertEquals(gamelogic.getTurn(), 0);
        gamelogic.nextTurn();
        assertEquals(gamelogic.getTurn(), 1);
        gamelogic.nextTurn();
        assertEquals(gamelogic.getTurn(), 0);
    }

    @Test
    public void availablePutsTest(){
        List<Integer> available = new ArrayList<Integer>();
        for(int i = 0; i < 16; i++){
            available.add(i);
        }

        assertEquals(available, gamelogic.availablePuts());

        gamelogic.putTile(0);

        available.clear();
        for(int i = 1; i < 17; i++){
            available.add(i);
        }

        assertEquals(available, gamelogic.availablePuts());

        gamelogic.putTile(16);
        gamelogic.putTile(32);
        gamelogic.nextTurn();
        gamelogic.putTile(48);

        available.clear();
        for(int i = 1; i < 16; i++){
            available.add(i);
        }

        assertEquals(available, gamelogic. availablePuts());
    }

    @Test
    public void gameEndTest(){
        assertFalse(gamelogic.gameEnd());
        henk.setAmountTiles(0);
        barry.setAmountTiles(0);

        assertTrue(gamelogic.gameEnd());
    }

    public void gameEndxasTest(){
        assertFalse(gamelogic.gameEnd());
    }
}
