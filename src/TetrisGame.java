/**
 * Create a tetris game
 *
 * @author Emma Nguyen
 * @version March 6 2018
 */
public class TetrisGame {

	/* declare the constants: the move types of the piece */
	public static final int RIGHT = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int CW = 3;
	public static final int CCW = 4;

	/* declare instance variables */

	// number of cleared lines
	private int numLines;

	// number of cleared tetrises
	private int numTetrises;

	// level of the game
	private int level;

	// a tetris board
	private TetrisBoard tetrisBoard;

	/**
	 * Construct a tetris game
	 */
	public TetrisGame() {

		// initialize the number of cleared lines
		numLines = 0;

		// initialize the number of cleared tetrises
		numTetrises = 0;

		// initialize the level of the game
		level = 0;

		// create a tetris board
		tetrisBoard = new TetrisBoard();

	}

	/**
	 * Attempt to move the current piece with the specified move type
	 *
	 * @param moveType
	 *            the move type of the piece
	 */
	public void attempMove(int moveType) {

		// attempt to move the piece according to the given move type
		if (moveType == RIGHT)
			tetrisBoard.moveRight();

		else if (moveType == LEFT)
			tetrisBoard.moveLeft();

		else if (moveType == CW)
			tetrisBoard.rotateCW();

		else if (moveType == CCW)
			tetrisBoard.rotateCCW();

		else if (moveType == DOWN) {

			// check whether the piece has been moved down successfully
			boolean movedSuccessful = tetrisBoard.moveDown();

			// if it is no longer possible to move the piece down
			if (!movedSuccessful)

				// end the round
				endRound();
		}

	}

	/**
	 * End the round of the current piece
	 */
	private void endRound() {

		// land the current piece
		tetrisBoard.landPiece();

		// update the number of cleared lines
		numLines += tetrisBoard.numberOfClearedLines();

		// update the number of cleared tetrises
		if (tetrisBoard.numberOfClearedLines() == 4) {
			numTetrises++;
		}

		// update the level (increase the level by one with every 5 lines cleared)
		level = numLines / 5;

		// add a new piece
		if (!tetrisBoard.isOver()) {
			tetrisBoard.addNewPiece();
		}
	}

	/**
	 * Check whether the game is over
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isOver() {
		return (tetrisBoard.isOver());
	}

	/**
	 * Get the number of lines cleared
	 *
	 * @return the number of cleared lines
	 */
	public int getNumLines() {
		return numLines;
	}

	/**
	 * Get the number of tetrises cleared
	 *
	 * @return the number of cleared tetrises
	 */
	public int getNumTetrises() {
		return numTetrises;
	}

	/**
	 * Get the tetris board
	 *
	 * @return the tetris board
	 */
	public TetrisBoard getTetrisBoard() {
		return tetrisBoard;
	}

	/**
	 * Get the level of the game
	 * 
	 * @return the game's level
	 */
	public int getLevel() {
		return level;
	}

}
