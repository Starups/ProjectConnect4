package shared;

import server.Connection;

/**
 * Created by Stan on 25-12-2016.
 */
public class Player {
	//instance variables
    private int amountTiles;
    private Tile tile;
    private String name;
    private Connection connection;
    
    //constructor
    /*
     * constructor of Player, creates a player, a connection and gives the player 32 tiles
     * made by the server
     * 
     * @param name (name of the player)
     * @param connection 
     */
    public Player(String name, Connection connection) {
        setAmountTiles(32);
        this.name = name;
        this.connection = connection;
    }
    
    /*
     * a second constructor, creates a player
     * made by the client
     * 
     * @param name (name of the player)
     */
    public Player(String name) {
        setAmountTiles(32);
        this.name = name;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void setAmountTiles(int amount) {
        amountTiles = amount;
    }
    
    /*
     * removes a tile after the player used it in their turn
     */

    public void minusOne() {
        amountTiles = amountTiles - 1;
    }
   /*
    * amount of tiles the player has left
    */
    public int getAmountTiles() {
        return amountTiles;
    }

    public Tile getTile() {
        return tile;
    }

    public String getName() {
        return name;
    }

    public Connection getConnection() {
        return connection;
    }
}