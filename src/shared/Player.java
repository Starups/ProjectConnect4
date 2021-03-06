package shared;

import server.Connection;

/**
 * Created by Stan on 25-12-2016.
 */
public class Player {
	//instance variabls
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
    /*
     * @param tile
     */

    //@ ensures getTile() = tile
    public void setTile(Tile tile) {
        this.tile = tile;
    }
    /*
     * @param amount
     */

    //@ ensures getAmountTiles() = amount
    public void setAmountTiles(int amount) {
        amountTiles = amount;
    }
    
    /*
     * removes a tile after the player used it in their turn
     */

    //@ ensures getAmountTiles() = /old(getAmountTiles()) - 1
    public void minusOne() {
        amountTiles = amountTiles - 1;
    }
   /*
    * amount of tiles the player has left
    */

    //@ \result <= 32 & \result >= 0
    public int getAmountTiles() {
        return amountTiles;
    }
    /*
     * @return tile 
     */
    //@ \result == null || \result == Tile.RED || \result == Tile.Yellow
    public Tile getTile() {
        return tile;
    }
    
    /*
     * return name (name of the player)
     */

    //@ \result != null
    public String getName() {
        return name;
    }
    
    /*
     * return connection
     */

    //@ \result != null
    public Connection getConnection() {
        return connection;
    }
}