/****************************************
 * OTHELLO
 * A Board Game
 * 
 * Gerald Brady & Patrick Bradshaw
 * January 27, 2018
 * CSIS 2410
 * 
 ****************************************/

package othello;

import javax.swing.JButton;

/**
 * Square represents 1 of 64 buttons on an 8x8 grid that makes up the board
 * in Othello.
 * @author Patrick Bradshaw & Gerald Brady
 *
 */
public class Square extends JButton {
	private static final long serialVersionUID = -5701300650369169039L;
	
	private Status status;
	private int row;
	private int col;
	
	/**
	 * Each square has a row and column (it's location) as well as a status
	 * which says if it is open, contains a black or white disc, or is a
	 * potential spot for disc placement (aka legal).
	 * @param row
	 * @param col
	 * @param status
	 */
	public Square(int row, int col, Status status) {
		this.row = row;
		this.col = col;
		this.status = status;
	}

	/**
	 * Returns the vertical position.
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the horizontal position.
	 * @return
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Returns what state the square is: open, legal, black, white.
	 * @return
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Changes the status of the square.
	 * @param status
	 */
	public void updateStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * String representation of the square.
	 */
	@Override
	public String toString() {
		return "Sq[" + row + ", " + col + "]: " + status;
	}
}
