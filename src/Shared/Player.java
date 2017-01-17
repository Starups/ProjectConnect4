package Shared;

/**
 * Created by Stan on 25-12-2016.
 */
public class Player {
    private int amountTiles;
    private Tile tile;
    private String name;

    public Player(Tile tile, String name){
        setAmountTiles(32);
        this.tile = tile;
        this.name = name;
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
}