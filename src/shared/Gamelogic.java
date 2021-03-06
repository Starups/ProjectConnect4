package shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stan on 25-12-2016.
 */

public class Gamelogic {
	//instance variables
	private int turn;
	private Board board;
	private Tile tile;

	// @ private invariant players.length >= 0;
	private Map<Integer, Player> players = new HashMap<Integer, Player>();

	// Constructor
	/*
	 * the constructor of Gamelogic
	 * @param board (the gamelogic is applied on the board)
	 */

	//@ ensures getBoard() = board
	public Gamelogic(Board board) {
		this.board = board;
	}

	/*
	 * add both players to the list of players in the game
	 * 
	 * @param player1
	 * @param player2
	 */

	//@ ensures getPlayers().size() = /old(getPlayers().size()) + 2
	public void putPlayers(Player player1, Player player2) {
		players.put(0, player1);
		players.put(1, player2);
	}
	/*
	 * when a player made its move (and it is a valid move), 
	 * this method gives the other player the turn
	 */

	//@ ensures getTurn() = (/old(getTurn()) + 1) % getPlayers().size()
	public void nextTurn() {
		turn = (turn + 1) % players.size();
	}
	/*
	 * 
	 * @param turn
	 */

	//@ requires turn >= 0 & turn <= 1
	//@ ensures getTurn() = turn
	public void setTurn(int turn) {
		this.turn = turn;
	}
	/*
	 * @return turn
	 */

	//@ *pure*
	public int getTurn() {
		return turn;
	}
	/*
	 *  a list of places where you can place a tile (all the valid moves).
	 *  
	 *  @return Integer (list of all the available places to play a tile)
	 */

	//@ensures \result.size() <= 16
	public List<Integer> availablePuts() {
		List<Integer> places = new ArrayList<Integer>();

		for (int i = 0; i < 64; i++) {
			if (i < 16 && board.getTile(i) == null) {
				places.add(i);
			} else if (board.getTile(i) == null && board.getTile(i - 16) != null) {
				places.add(i);
			}
		}

		return places;
	}
	/*
	 * places the tile on a place
	 * 
	 * @param tile (the tile being placed)
	 * @param place (the place where the tile is being placed)
	 */
	//@ requires tile = Tile.RED || tile = Tile.YELLOW
	//@ requires place <= 63 & place >= 1
	//@ requires getBoard().getTile(place) == null
	//@ ensures getBoard().getTile(place) = tile
	public void putTile(Tile tile, int place) {
		if (place < 0 || place > 63) {
			System.out.println("ERROR " + place + " is not in range");
		} else if (!availablePuts().contains(place)) {
			System.out.println("ERROR " + place + 
					" is not a permitted place. You can place a tile at the following places:");
			for (int i = 0; i < availablePuts().size(); i++) {
				System.out.println(board.intToXCoord(availablePuts().get(i)) + 
						"," + board.intToYCoord(availablePuts().get(i)) + "," 
						+ board.intToZCoord(availablePuts().get(i)));
			}
		} else {
			Player player = players.get(getTurn());
			board.putTile(tile, place);
			player.minusOne();
		}

		this.gameEnd();
	}
	/*
	 * all the possible ways that a game can end
	 *  
	 * @return boolean (has the game ended or not)
	 */

	//@ensures gameEndxas == true ==> \return == true;
	//@ensures gameEndyas == true ==> \return == true;
	//@ensures gameEndzas == true ==> \return == true;
	//@ensures gameEndxyas == true ==> \return == true;
	//@ensures gameEndyzas == true ==> \return == true;
	//@ensures gameEndxzas == true ==> \return == true;
	//@ensures gameEndxyzas == true ==> \return == true;
	public boolean gameEnd() {
		Boolean bool = false;

		if (players.get(0).getAmountTiles() == 0 && players.get(1).getAmountTiles() == 0) {
			bool = true;
		} else if (gameEndxas() || gameEndyas() || gameEndzas() || gameEndxyas() || gameEndxzas() ||
				gameEndyzas() || gameEndxyzas()) {
			bool = true;
		}

		if (bool) {
			System.out.println("GAME IS AFGELOPEN!");
		}

		return bool;
	}

	//Four in a row in the x as.
	/*
	 * @return boolean (is there 4 in a row on the x as)
	 */
	//@ pure
	public boolean gameEndxas() {
		Boolean bool = false;

		for (int x = 0; x < 16; x++) {
			if (board.getTile(x * 4) != null && board.getTile(x * 4 + 1) != null
					&& board.getTile(x * 4 + 2) != null && board.getTile(x * 4 + 3) != null) {
				if (board.getTile(x * 4).equals(board.getTile(x * 4 + 1)) 
						&& board.getTile(x * 4).equals(board.getTile(x * 4 + 2)) 
						&& board.getTile(x * 4).equals(board.getTile(x * 4 + 3))) {
					bool = true;
					tile = board.getTile(x * 4);
				}
			}

			if (bool) {
				break;
			}
		}

		return bool;
	}

	//Four in a row in the y as
	/*
	 * @return boolean (is there 4 in a row on the y as)
	 */
	//@ pure
	public boolean gameEndyas() {
		Boolean bool = false;

		for (int y = 0; y < 16; y++) {
			if (y < 4) {
				if (board.getTile(y) != null && board.getTile(y + 4) != null 
						&& board.getTile(y + 8) != null && board.getTile(y + 12) != null) {
					if (board.getTile(y).equals(board.getTile(y + 4)) 
							&& board.getTile(y).equals(board.getTile(y + 8)) 
							&& board.getTile(y).equals(board.getTile(y + 12))) {
						bool = true;
						tile = board.getTile(y);
					}
				}
			} else if (y < 8) {
				if (board.getTile(y + 12) != null && board.getTile(y + 16) != null 
						&& board.getTile(y + 20) != null && board.getTile(y + 24) != null) {
					if (board.getTile(y + 12).equals(board.getTile(y + 16))
							&& board.getTile(y + 12).equals(board.getTile(y + 20))
							&& board.getTile(y + 12).equals(board.getTile(y + 24))) {
						bool = true;
						tile = board.getTile(y + 12);
					}
				}
			} else if (y < 12) {
				if (board.getTile(y + 24) != null && board.getTile(y + 28) != null 
						&& board.getTile(y + 32) != null && board.getTile(y + 36) != null) {
					if (board.getTile(y + 24).equals(board.getTile(y + 28)) 
							&& board.getTile(y + 24).equals(board.getTile(y + 32)) 
							&& board.getTile(y + 24).equals(board.getTile(y + 36))) {
						bool = true;
						tile = board.getTile(y + 24);
					}
				}
			} else {
				if (board.getTile(y + 36) != null && board.getTile(y + 40) != null 
						&& board.getTile(y + 44) != null && board.getTile(y + 48) != null) {
					if (board.getTile(y + 36).equals(board.getTile(y + 40)) 
							&& board.getTile(y + 36).equals(board.getTile(y + 44)) 
							&& board.getTile(y + 36).equals(board.getTile(y + 48))) {
						bool = true;
						tile = board.getTile(y + 36);
					}
				}
			}

			if (bool) {
				break;
			}
		}

		return bool;
	}

	//Four in a row in the z as.
	/*
	 * @return boolean (is there 4 in a row on the z as)
	 */
	//@ pure
	public boolean gameEndzas() {
		Boolean bool = false;

		for (int z = 0; z < 16; z++) {
			if (board.getTile(z) != null && board.getTile(z + 16) != null 
					&& board.getTile(z + 32) != null && board.getTile(z + 48) != null) {
				if (board.getTile(z).equals(board.getTile(z + 16)) 
						&& board.getTile(z).equals(board.getTile(z + 32)) 
						&& board.getTile(z).equals(board.getTile(z + 48))) {
					bool = true;
					tile = board.getTile(z);
				}
			}

			if (bool) {
				break;
			}
		}

		return bool;
	}

	//Four in a row from 0,0,0 to 3,3,0 and 0,3,0 to 3,0,0
	/*
	 *  @return boolean 
	 */
	//@ pure
	public boolean gameEndxyas() {
		Boolean bool = false;

		for (int x = 0; x < 4; x++) {
			if (board.getTile(x * 16) != null && board.getTile(x * 16 + 5) != null 
					&& board.getTile(x * 16 + 10) != null && board.getTile(x * 16 + 15) != null) {
				if (board.getTile(x * 16).equals(board.getTile(x * 16 + 5)) 
						&& board.getTile(x * 16).equals(board.getTile(x * 16 + 10)) 
						&& board.getTile(x * 16).equals(board.getTile(x * 16 + 15))) {
					bool = true;
					tile = board.getTile(x * 16);
				}
			}

			if (board.getTile(x * 16 + 3) != null && board.getTile(x * 16 + 6) != null 
					&& board.getTile(x * 16 + 9) != null && board.getTile(x * 16 + 12) != null) {
				if (board.getTile(x * 16 + 3).equals(board.getTile(x * 16 + 6)) 
						&& board.getTile(x * 16 + 3).equals(board.getTile(x * 16 + 9)) 
						&& board.getTile(x * 16 + 3).equals(board.getTile(x * 16 + 12))) {
					bool = true;
					tile = board.getTile(x * 16 + 3);
				}
			}

			if (bool) {
				break;
			}
		}

		return bool;
	}

	//Four in a row from 0,0,0 to 3,0,3 and 3,0,0 to 0,0,3
	/*
	 * @return boolean
	 */
	//@ pure
	public boolean gameEndxzas() {
		Boolean bool = false;

		for (int x = 0; x < 4; x++) {
			if (board.getTile(x * 4) != null && board.getTile(x * 4 + 17) != null 
					&& board.getTile(x * 4 + 34) != null && board.getTile(x * 4 + 51) != null) {
				if (board.getTile(x * 4).equals(board.getTile(x * 4 + 17)) 
						&& board.getTile(x * 4).equals(board.getTile(x * 4 + 34)) 
						&& board.getTile(x * 4).equals(board.getTile(x * 4 + 51))) {
					bool = true;
					tile = board.getTile(x * 4);
				}
			}

			if (board.getTile(x * 4 + 3) != null && board.getTile(x * 4 + 15) != null 
					&& board.getTile(x * 4 + 30) != null && board.getTile(x * 4 + 45) != null) {
				if (board.getTile(x * 4 + 3).equals(board.getTile(x * 4 + 15)) 
						&& board.getTile(x * 4).equals(board.getTile(x * 4 + 30)) 
						&& board.getTile(x * 4).equals(board.getTile(x * 4 + 45))) {
					bool = true;
					tile = board.getTile(x * 4 + 3);
				}
			}

			if (bool) {
				break;
			}
		}

		return bool;
	}

	//Four in a row from 0,0,0 to 0,3,3 and 0,3,0 to 0,0,3
	/*
	 * @return boolean
	 */
	//@ pure
	public boolean gameEndyzas() {
		Boolean bool = false;

		for (int y = 0; y < 4; y++) {
			if (board.getTile(y) != null && board.getTile(y + 20) != null 
					&& board.getTile(y + 40) != null && board.getTile(y + 60) != null) {
				if (board.getTile(y).equals(board.getTile(y + 20)) 
						&& board.getTile(y).equals(board.getTile(y + 40)) 
						&& board.getTile(y).equals(board.getTile(y + 60))) {
					bool = true;
					tile = board.getTile(y);
				}
			}

			if (board.getTile(y + 12) != null && board.getTile(y + 24) != null 
					&& board.getTile(y + 36) != null && board.getTile(y + 48) != null) {
				if (board.getTile(y + 12).equals(board.getTile(y + 24)) 
						&& board.getTile(y + 12).equals(board.getTile(y + 36)) 
						&& board.getTile(y + 12).equals(board.getTile(y + 48))) {
					bool = true;
					tile = board.getTile(y + 12);
				}
			}

			if (bool) {
				break;
			}
		}

		return bool;
	}

	//Four in a row diagonal
	/*
	 * @return boolean
	 */
	//@ pure
	public boolean gameEndxyzas() {
		Boolean bool = false;

		if ((board.getTile(0) != null && board.getTile(21) != null 
				&& board.getTile(42) != null && board.getTile(63) != null) ||
				(board.getTile(3) != null && board.getTile(22) != null 
				&& board.getTile(41) != null && board.getTile(60) != null) ||
				(board.getTile(12) != null && board.getTile(25) != null 
				&& board.getTile(38) != null && board.getTile(51) != null) ||
				(board.getTile(15) != null && board.getTile(26) != null 
				&& board.getTile(37) != null && board.getTile(48) != null)) {
			if ((board.getTile(0).equals(board.getTile(21)) 
					&& board.getTile(0).equals(board.getTile(42)) 
					&& board.getTile(0).equals(board.getTile(63))) ||
					(board.getTile(3).equals(board.getTile(22)) 
							&& board.getTile(3).equals(board.getTile(41)) 
							&& board.getTile(0).equals(board.getTile(60))) ||
					(board.getTile(12).equals(board.getTile(25)) 
							&& board.getTile(12).equals(board.getTile(38)) 
							&& board.getTile(12).equals(board.getTile(51))) ||
					(board.getTile(15).equals(board.getTile(26)) 
							&& board.getTile(15).equals(board.getTile(37)) 
							&& board.getTile(15).equals(board.getTile(48)))) {
				bool = true;
				tile = board.getTile(0);
			}
		}

		return bool;
	}
	
	/*
	 * @return list of players in the game
	 */
	//@ ensures \result.size() >= 0 & \result.size() <= 2
	public Map<Integer, Player> getPlayers() {
		return players;
	}
	
	/*
	 * @return board
	 */
	//@ ensures \result != null
	public Board getBoard() {
		return board;
	}
	
	/*
	 * @return the winning tile
	 */
	//@ ensures \result == Tile.RED || \result == Tile.YELLOW
	public Tile getWinningTile() {
		return tile;
	}
}