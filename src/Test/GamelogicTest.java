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
        henk = new Player("henk");
        barry = new Player("barry");
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
        board.clear();

        List<Integer> available = new ArrayList<Integer>();
        for(int i = 0; i < 16; i++){
            available.add(i);
        }

        assertEquals(available, gamelogic.availablePuts());

        gamelogic.putTile(Tile.RED, 0);

        available.clear();
        for(int i = 1; i < 17; i++){
            available.add(i);
        }

        assertEquals(available, gamelogic.availablePuts());

        gamelogic.putTile(Tile.RED, 16);
        gamelogic.putTile(Tile.RED, 32);
        gamelogic.putTile(Tile.YELLOW, 48);

        available.clear();
        for(int i = 1; i < 16; i++){
            available.add(i);
        }

        assertEquals(available, gamelogic. availablePuts());
    }

    @Test
    public void putTileTest(){
        gamelogic.putTile(Tile.RED, 0);

        assertEquals(board.getTile(0), Tile.RED);

        gamelogic.putTile(Tile.YELLOW, 1);

        assertEquals(board.getTile(1), Tile.YELLOW);
    }

    @Test
    public void gameEndTest(){
        assertFalse(gamelogic.gameEnd());
        henk.setAmountTiles(0);
        barry.setAmountTiles(0);

        assertTrue(gamelogic.gameEnd());
    }

    @Test
    public void gameEndxasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndxas());

        gamelogic.putTile(Tile.RED, 4);
        gamelogic.putTile(Tile.RED, 5);
        gamelogic.putTile(Tile.RED, 6);
        gamelogic.putTile(Tile.RED, 7);

        assertTrue(gamelogic.gameEndxas());
    }

    @Test
    public void gameEndyasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndyas());

        gamelogic.putTile(Tile.RED, 1);
        gamelogic.putTile(Tile.RED, 5);
        gamelogic.putTile(Tile.RED, 9);
        gamelogic.putTile(Tile.RED, 13);

        assertTrue(gamelogic.gameEndyas());
    }

    @Test
    public void gameEndzasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndzas());

        gamelogic.putTile(Tile.RED, 1);
        gamelogic.putTile(Tile.RED, 17);
        gamelogic.putTile(Tile.RED, 33);
        gamelogic.putTile(Tile.RED, 49);

        assertTrue(gamelogic.gameEndzas());
    }

    @Test
    public void gameEndxyasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndxyas());

        gamelogic.putTile(Tile.RED, 0);
        gamelogic.putTile(Tile.RED, 5);
        gamelogic.putTile(Tile.RED, 10);
        gamelogic.putTile(Tile.RED, 15);

        assertTrue(gamelogic.gameEndxyas());
    }

    @Test
    public void gameEndxzasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndxzas());

        gamelogic.putTile(Tile.RED, 1);
        gamelogic.putTile(Tile.RED, 2);
        gamelogic.putTile(Tile.RED, 18);
        gamelogic.putTile(Tile.RED, 3);
        gamelogic.putTile(Tile.RED, 19);
        gamelogic.putTile(Tile.RED, 35);

        gamelogic.putTile(Tile.YELLOW, 0);
        gamelogic.putTile(Tile.YELLOW, 17);
        gamelogic.putTile(Tile.YELLOW, 34);
        gamelogic.putTile(Tile.YELLOW, 51);

        assertTrue(gamelogic.gameEndxzas());
    }

    @Test
    public void gameEndyzasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndyzas());

        gamelogic.putTile(Tile.RED, 4);
        gamelogic.putTile(Tile.RED, 8);
        gamelogic.putTile(Tile.RED, 24);
        gamelogic.putTile(Tile.RED, 12);
        gamelogic.putTile(Tile.RED, 28);
        gamelogic.putTile(Tile.RED, 44);

        gamelogic.putTile(Tile.YELLOW, 0);
        gamelogic.putTile(Tile.YELLOW, 20);
        gamelogic.putTile(Tile.YELLOW, 40);
        gamelogic.putTile(Tile.YELLOW, 60);

        assertTrue(gamelogic.gameEndyzas());
    }

    @Test
    public void gameEndxyzasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndxyzas());

        gamelogic.putTile(Tile.RED, 5);
        gamelogic.putTile(Tile.RED, 10);
        gamelogic.putTile(Tile.RED, 26);
        gamelogic.putTile(Tile.RED, 15);
        gamelogic.putTile(Tile.RED, 31);
        gamelogic.putTile(Tile.RED, 47);

        gamelogic.putTile(Tile.YELLOW, 0);
        gamelogic.putTile(Tile.YELLOW, 21);
        gamelogic.putTile(Tile.YELLOW, 42);
        gamelogic.putTile(Tile.YELLOW, 63);

        assertTrue(gamelogic.gameEndxyzas());
    }
}
