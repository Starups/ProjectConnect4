package Shared;

import Server.Connection;

/**
 * Created by Stan on 25-12-2016.
 */
public class Player {
    private int amountTiles;
    private Tile tile;
    private String name;
    private Connection connection;

    public Player(String name, Connection connection){
        setAmountTiles(32);
        this.name = name;
        this.connection = connection;
    }

    public Player(String name){
        setAmountTiles(32);
        this.name = name;
    }

    public void setTile(Tile tile){
        this.tile = tile;
    }

    public void setAmountTiles(int amount){
        amountTiles = amount;
    }

    public void minusOne(){
        amountTiles = amountTiles - 1;
    }

    public int getAmountTiles(){
        return amountTiles;
    }

    public Tile getTile(){
        return tile;
    }

    public String getName(){
        return name;
    }

    public Connection getConnection(){
        return connection;
    }
}