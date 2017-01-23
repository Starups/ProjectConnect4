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
    public void putTileTest(){
        gamelogic.putTile(0);

        assertEquals(board.getTile(0), Tile.RED);

        gamelogic.nextTurn();
        gamelogic.putTile(1);

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

        gamelogic.putTile(4);
        gamelogic.putTile(5);
        gamelogic.putTile(6);
        gamelogic.putTile(7);

        assertTrue(gamelogic.gameEndxas());
    }

    @Test
    public void gameEndyasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndyas());

        gamelogic.putTile(1);
        gamelogic.putTile(5);
        gamelogic.putTile(9);
        gamelogic.putTile(13);

        assertTrue(gamelogic.gameEndyas());
    }

    @Test
    public void gameEndzasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndzas());

        gamelogic.putTile(1);
        gamelogic.putTile(17);
        gamelogic.putTile(33);
        gamelogic.putTile(49);

        assertTrue(gamelogic.gameEndzas());
    }

    @Test
    public void gameEndxyasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndxyas());

        gamelogic.putTile(0);
        gamelogic.putTile(5);
        gamelogic.putTile(10);
        gamelogic.putTile(15);

        assertTrue(gamelogic.gameEndxyas());
    }

    @Test
    public void gameEndxzasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndxzas());

        gamelogic.putTile(1);
        gamelogic.putTile(2);
        gamelogic.putTile(18);
        gamelogic.putTile(3);
        gamelogic.putTile(19);
        gamelogic.putTile(35);

        gamelogic.nextTurn();

        gamelogic.putTile(0);
        gamelogic.putTile(17);
        gamelogic.putTile(34);
        gamelogic.putTile(51);

        assertTrue(gamelogic.gameEndxzas());
    }

    @Test
    public void gameEndyzasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndyzas());

        gamelogic.putTile(4);
        gamelogic.putTile(8);
        gamelogic.putTile(24);
        gamelogic.putTile(12);
        gamelogic.putTile(28);
        gamelogic.putTile(44);

        gamelogic.nextTurn();

        gamelogic.putTile(0);
        gamelogic.putTile(20);
        gamelogic.putTile(40);
        gamelogic.putTile(60);

        assertTrue(gamelogic.gameEndyzas());
    }

    @Test
    public void gameEndxyzasTest(){
        board.clear();

        assertFalse(gamelogic.gameEndxyzas());

        gamelogic.putTile(5);
        gamelogic.putTile(10);
        gamelogic.putTile(26);
        gamelogic.putTile(15);
        gamelogic.putTile(31);
        gamelogic.putTile(47);

        gamelogic.nextTurn();

        gamelogic.putTile(0);
        gamelogic.putTile(21);
        gamelogic.putTile(42);
        gamelogic.putTile(63);

        assertTrue(gamelogic.gameEndxyzas());
    }
}
