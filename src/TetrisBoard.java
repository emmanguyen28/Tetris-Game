import java.awt.Color;

/**
 * Create a tetris board on which tetris is played. The board maintains the grid
 * and handles the logic of the tetris pieces on it
 * 
 * @author Emma Nguyen
 * @version March 3 2018
 **/

public class TetrisBoard {

	/* num of rows in the tetris board */
	private static final int NUM_ROWS = 18;

	/* num of columns in the tetris board */
	private static final int NUM_COLS = 10;

	/* declare instance variables */

	// number of columns in the board
	private int numCols;

	// number of rows in the board
	private int numRows;

	// the board itself
	private Block[][] blockMatrix;

	// the current tetris piece on the board
	private TetrisPiece currentPiece;

	// the next tetris piece
	private TetrisPiece nextPiece;

	// maintain the upper left position of the current tetris piece
	private int[] currentPieceGP;

	// whether the game is over
	private boolean isOver;

	/**
	 * Create a Tetris Board
	 */
	public TetrisBoard() {

		// initialize the board
		blockMatrix = new Block[NUM_ROWS][NUM_COLS];
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				blockMatrix[row][col] = new Block(false, Color.WHITE);
			}
		}

		// number of rows and columns
		numCols = NUM_COLS;
		numRows = NUM_ROWS;

		// generate a new piece
		generateNextPiece();

		// add the piece to the board
		addNewPiece();

		// intialize the current position of the current piece
		initCurrentGP();

	}

	/**
	 * Initialize the current position of the current tetris piece
	 */
	private void initCurrentGP() {

		// initialize the array that holds the position of the current piece
		currentPieceGP = new int[2];

		// the current row of the piece
		currentPieceGP[0] = 0;

		// the current column of the piece
		currentPieceGP[1] = 3;
	}

	/**
	 * Land the piece when it cannot move down any more
	 */
	public void landPiece() {

		// current position on the board of the current piece
		int currentRow = currentPieceGP[0];
		int currentCol = currentPieceGP[1];

		// loop through the current tetris piece
		for (int i = 0; i < TetrisPiece.NUM_ROWS_PIECE; i++) {
			for (int j = 0; j < TetrisPiece.NUM_COLS_PIECE; j++) {

				// if a filled entry in the piece is found
				if (currentPiece.isFilled(currentPiece.getPieceRotation(), i, j)) {

					// set the entry's corresponding position on the board to be true (occupied)
					blockMatrix[currentRow + i][currentCol + j].setState(true);

					// set the entry's color corresponding to the color o the piece
					blockMatrix[currentRow + i][currentCol + j].setColor(currentPiece.getColor());
				}
			}
		}

		// loop through the first row in the board
		for (int col = 0; col < NUM_COLS; col++) {

			// if any entry in the first row has a block
			if (blockMatrix[0][col].getState())

				// the game is over
				isOver = true;
		}
	}

	/**
	 * Detect when the piece hits another piece
	 * 
	 * @param piece
	 *            the current tetris piece in the board
	 * @param rot
	 *            the current rotation of the piece
	 * @param gridRow
	 *            the attempted row to move the piece to
	 * @param gridCol
	 *            the attempted column to move the piece to
	 * @return true if there is a collision, false otherwise
	 */
	private boolean detectCollision(TetrisPiece piece, int rot, int gridRow, int gridCol) {

		// loop through the given tetris piece
		for (int i = 0; i < TetrisPiece.NUM_ROWS_PIECE; i++) {
			for (int j = 0; j < TetrisPiece.NUM_COLS_PIECE; j++) {

				// if a filled entry in the piece is found and the entry's corresponding
				// position on the board based on the given grid row and grid column is occupied
				if (piece.isFilled(rot, i, j) && blockMatrix[gridRow + i][gridCol + j].getState())

					// return true (there is a collision)
					return true;
			}
		}
		// the loop ends and no collision is found, then return false
		return false;
	}

	/**
	 * Detect whether the piece is trying to move out of the tetris board
	 * 
	 * @param piece
	 *            the current tetris piece in the board
	 * @param rot
	 *            the current rotation of the piece
	 * @param gridRow
	 *            the attempted row to move the piece to
	 * @param gridCol
	 *            the attempted column to move the piece to
	 * @return true if the piece is trying to move out of bounds, false otherwise
	 */
	private boolean detectOutOfBounds(TetrisPiece piece, int rot, int gridRow, int gridCol) {

		// loop through the given tetris piece
		for (int i = 0; i < TetrisPiece.NUM_ROWS_PIECE; i++) {
			for (int j = 0; j < TetrisPiece.NUM_COLS_PIECE; j++) {

				// find each entry's corresponding position on the board based on the given grid
				// row and grid column
				int pieceR = gridRow + i;
				int pieceC = gridCol + j;
				if (piece.isFilled(rot, i, j)) {

					// if the position is out of bounds of the array holding the board
					if (pieceR < 0 || pieceC < 0 || pieceC >= NUM_COLS || pieceR >= NUM_ROWS) {

						// return true (there is a out of bounds error)
						return true;
					}
				}
			}
		}
		// the loop ends and no out of bounds error is detected, then return false
		return false;
	}

	/**
	 * Determine whether the piece is trying to make a valid move
	 * 
	 * @param piece
	 *            piece the current tetris piece in the board
	 * @param rot
	 *            the current rotation of the piece
	 * @param gridRow
	 *            the attempted row to move the piece to
	 * @param gridCol
	 *            the attempted column to move the piece to
	 * @return true if the piece is not hitting another piece and is not moving out
	 *         of the board, false otherwise
	 */
	private boolean validMove(TetrisPiece piece, int rot, int gridRow, int gridCol) {

		// if the attempted move indicates either out of bounds error or collision
		if (detectOutOfBounds(piece, rot, gridRow, gridCol) || detectCollision(piece, rot, gridRow, gridCol)) {

			// return that this is not a valid move
			return false;
		}
		// return true otherwise
		return true;
	}

	/**
	 * Move the piece to the left
	 * 
	 * @return true if the move was successful, false otherwise
	 */
	public boolean moveLeft() {

		// if there is no current piece, then no move is to be performed
		if (currentPiece == null)

			// return that the move failed
			return false;
		else {

			// if the current piece exists and the attempted move is valid
			if (validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGP[0], currentPieceGP[1] - 1)) {
				// move the piece left by one column
				currentPieceGP[1] = currentPieceGP[1] - 1;

				// return that the move succeeded
				return true;
			}

			// if the attempted move is not valid, return that the move failed
			return false;
		}
	}

	/**
	 * Move the piece to the right
	 * 
	 * @return true if the move was successful, false otherwise
	 */
	public boolean moveRight() {

		// if there is no current piece, then no move is to be performed
		if (currentPiece == null)

			// return that the move failed
			return false;
		else {

			// if the current piece exists and the attempted move is valid
			if (validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGP[0], currentPieceGP[1] + 1)) {

				// move the piece right by one column
				currentPieceGP[1] = currentPieceGP[1] + 1;

				// return that the attempted move succeeded
				return true;
			}

			// if the attempted move is not valid, return that the move failed
			return false;
		}
	}

	/**
	 * Move the piece down
	 * 
	 * @return true if the move was successful, false otherwise
	 */
	public boolean moveDown() {

		// if there is no current piece, then no move is to be performed
		if (currentPiece == null)

			// return that the move failed
			return false;
		else {

			// if the current piece exists and the attempted move is valid
			if (validMove(currentPiece, currentPiece.getPieceRotation(), currentPieceGP[0] + 1, currentPieceGP[1])) {

				// move the piece down by one row
				currentPieceGP[0] = currentPieceGP[0] + 1;

				// return that the attempted move succeeded
				return true;
			}

			// if the attempted move is not valid, return that the move failed
			return false;
		}
	}

	/**
	 * Rotate the piece clockwise
	 * 
	 * @return true if the rotation was successful, false otherwise
	 */
	public boolean rotateCW() {

		// get the current rotation of the current piece on the board
		int curr_rot = currentPiece.getPieceRotation();

		// if the current piece does not exist
		if (currentPiece == null)

			// return that the attempted rotation failed
			return false;
		else {

			// if the current piece does exist
			// if the current rotation is 3 (270 degree), then set it back to 0 (0 degree)
			if (curr_rot == 3)
				curr_rot = 0;
			else

				// if the current rotation is not 3, increment it by 1 (by 90 degree)
				curr_rot++;

			// if the attempted rotation is valid
			if (validMove(currentPiece, curr_rot, currentPieceGP[0], currentPieceGP[1])) {

				// rotate the piece clockwise by 90 degree
				currentPiece.rotateCW();

				// return that attempted rotation succeeded
				return true;
			}

			// if the attempted rotation is invalid, return that the attempted rotation
			// failed
			return false;
		}
	}

	/**
	 * Rotate the piece counter-clockwise
	 * 
	 * @return true if the rotation was successful, false otherwise
	 */
	public boolean rotateCCW() {

		// get the current rotation of the current piece on the board
		int curr_rot = currentPiece.getPieceRotation();

		// if the current piece does not exist
		if (currentPiece == null)

			// return that the attempted rotation failed
			return false;

		else {

			// if the current piece does exist
			// if the current rotation is 0 (0 degree), then set it back to 3 (270 degree)
			if (curr_rot == 0)
				curr_rot = 3;
			else

				// if the current rotation is not 0, decrement it by 1 (by 90 degree)
				curr_rot--;

			// if the attempted rotation is valid
			if (validMove(currentPiece, curr_rot, currentPieceGP[0], currentPieceGP[1])) {

				// rotate the piece counter-clockwise by 90 degree
				currentPiece.rotateCCW();

				// return that attempted rotation succeeded
				return true;
			}
			// if the attempted rotation is invalid, return that the attempted rotation
			// failed
			return false;
		}
	}

	/**
	 * Check whether an entry in the tetris board has a block or a piece on top of
	 * it
	 * 
	 * @param row
	 *            the row of the entry to be checked
	 * @param col
	 *            the column of the entry to be checked
	 * @return true if there is a block, false otherwise
	 */
	public boolean hasBlock(int row, int col) {

		// get the current position of the current piece
		int currentRow = currentPieceGP[0];
		int currentCol = currentPieceGP[1];

		// if the given entry is occupied
		if (blockMatrix[row][col].getState()) {

			// return that it does contain a block
			return true;
		} else {

			// if the given entry is not occupied
			// loop through the current tetris piece
			for (int i = 0; i < TetrisPiece.NUM_ROWS_PIECE; i++) {
				for (int j = 0; j < TetrisPiece.NUM_COLS_PIECE; j++) {

					// if the given entry corresponds to a filled entry in the current piece
					if (currentPiece.isFilled(currentPiece.getPieceRotation(), i, j) && (currentRow + i == row)
							&& (currentCol + j == col)) {

						// set the color of the block
						blockMatrix[currentRow + i][currentCol + j].setColor(currentPiece.getColor());

						// return that it does contain a block
						return true;
					}
				}
			}
		}

		// the given entry is not occupied and does not correspond to any filled entry
		// of the current piece, then return false
		return false;
	}

	/**
	 * Generate the next piece
	 */
	private void generateNextPiece() {

		// randomize the shape to be chosen
		double random = Math.random();

		// create a new piece with the chosen shape and assign the piece to be the
		// next piece
		if (random <= 0.15) {
			nextPiece = new TetrisI();

		} else if (random > 15 && random <= 0.3) {
			nextPiece = new TetrisJ();

		} else if (random > 0.3 && random <= 0.45) {
			nextPiece = new TetrisL();

		} else if (random > 0.45 && random <= 0.6) {
			nextPiece = new TetrisO();

		} else if (random > 0.6 && random <= 0.75) {
			nextPiece = new TetrisS();

		} else if (random > 0.75 && random <= 0.9) {
			nextPiece = new TetrisT();

		} else {
			nextPiece = new TetrisZ();
		}
	}

	/**
	 * Add a new piece to the board; the piece is in random shape
	 */
	public void addNewPiece() {

		// set the current piece
		currentPiece = nextPiece;

		// generate the next piece
		generateNextPiece();

		// re-initialize the current location of the current piece
		initCurrentGP();
	}

	/**
	 * Check if there is a full line
	 * 
	 * @param row
	 *            the row to be checked whether it's full
	 * @return true if the row is full, false otherwise
	 */
	private boolean fullLine(int row) {

		// loop through all the entries in the given row
		for (int col = 0; col < blockMatrix[row].length; col++) {

			// if the entry is false, .i.e, does not contain a block
			if (!blockMatrix[row][col].getState())

				// return that the line is not full
				return false;
		}
		// the loop ends, which means no empty entries are found, return that the given
		// row is full
		return true;
	}

	/**
	 * Calculate the number of cleared lines in the board
	 * 
	 * @return the number of cleared lines
	 */
	public int numberOfClearedLines() {

		// declare a variable to count the number of cleared lines
		int clearedLines = 0;

		// loop through the board
		for (int row = 0; row < blockMatrix.length; row++) {

			// check each row whether it is full; if it is full
			if (fullLine(row)) {

				// increment the number of cleared lines
				clearedLines++;

				// remove the line
				removeLine(row);
			}
		}
		// return the number of cleared lines
		return clearedLines;
	}

	/**
	 * Remove a line from the tetris board
	 * 
	 * @param row
	 *            the row to be removed
	 */
	private void removeLine(int row) {

		// loop through the given row and all rows above it
		for (int r = row; r >= 0; r--) {

			// loop through all the entries in the row
			for (int c = 0; c < blockMatrix[r].length; c++) {

				// if the row is the top row
				if (r == 0)
					// set all the entries to be false (empty)
					blockMatrix[r][c].setState(false);

				else
					// for every other row, set its entries' values to be the value of those of the
					// row before it, thus the given row is removed
					blockMatrix[r][c] = blockMatrix[r - 1][c];
			}
		}
	}

	/**
	 * Get the tetris board
	 * 
	 * @return the tetris board
	 */
	public Block[][] getBlockMatrix() {
		return blockMatrix;
	}

	/**
	 * Get the current piece in the board
	 * 
	 * @return the current tetris piece
	 */
	public TetrisPiece getCurrentPiece() {
		return currentPiece;
	}

	/**
	 * Get the current position of the current piece in the board
	 * 
	 * @return the current position of the current tetris piece
	 */
	public int[] getCurrentPieceGP() {
		return currentPieceGP;
	}

	/**
	 * Get the number of rows in the board
	 * 
	 * @return number of rows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * Get the number of columns in the board
	 * 
	 * @return number of columns
	 */
	public int getNumCols() {
		return numCols;
	}

	/**
	 * Check whether the game is over
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isOver() {
		return isOver;
	}

	/**
	 * Get the next tetris piece
	 * 
	 * @return the next tetris piece
	 */
	public TetrisPiece getNextPiece() {
		return nextPiece;
	}

}
