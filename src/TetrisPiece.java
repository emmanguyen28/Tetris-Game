import java.awt.Color;

/**
 * Abstract class to create a tetris piece and define functions to be used with
 * it
 * 
 * @author Emma Nguyen
 * @version February 28 2018
 **/
public abstract class TetrisPiece {

	/* declare constants holding the dimension of a piece */
	public static final int NUM_ROWS_PIECE = 4;
	public static final int NUM_COLS_PIECE = 4;
	public static final int NUM_ROTS_PIECE = 4;

	/* instance variables holding the piece */

	// the piece
	protected boolean[][][] filledSquares;

	// rotation of the piece
	protected int pieceRotation;

	// color of the piece
	protected Color color;

	/**
	 * Construct a Tetris piece
	 */
	public TetrisPiece() {

		// set the initial rotation to be 0 (0 degree)
		pieceRotation = 0;
	}

	/**
	 * rotate the tetris piece clockwise
	 */
	public void rotateCW() {

		// if the rotation is 3 (270 degree)
		if (pieceRotation == 3)

			// set it to be 0 (0 degree)
			pieceRotation = 0;
		else

			// otherwise, increment the rotation by 1 (90 degree)
			pieceRotation++;
	}

	/**
	 * rotate the piece counterclockwise
	 */
	public void rotateCCW() {

		// if the rotation is 0 (0 degree)
		if (pieceRotation == 0)

			// set it to be 3 (270 degree)
			pieceRotation = 3;

		else

			// otherwise, decrement the rotation by 1 (90 degree)
			pieceRotation--;
	}

	/**
	 * get the current rotation of the piece
	 * 
	 * @return the current rotation of the piece
	 */
	public int getPieceRotation() {
		return pieceRotation;
	}

	/**
	 * check whether an entry in the piece is filled
	 * 
	 * @param rot
	 *            the rotation that the entry is in
	 * @param row
	 *            the row of the entry
	 * @param col
	 *            the column of the entry
	 * @return true if the entry is filled, false otherwise
	 */
	public boolean isFilled(int rot, int row, int col) {
		return filledSquares[rot][row][col];
	}

	/**
	 * Set the color for the piece
	 * 
	 * @param color
	 *            color to be set
	 */
	public void setColor(Color color) {

		// set the color
		this.color = color;
	}

	/**
	 * Get the color of the piece
	 * 
	 * @return the color of the piece
	 */
	public Color getColor() {
		return color;
	}

}
