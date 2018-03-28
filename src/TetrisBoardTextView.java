/**
 * Create the view for the tetris game text version
 * 
 * @author Emma Nguyen
 * @version March 3 2018
 **/
public class TetrisBoardTextView {
	
	/* create an instance of a tetris board */
	TetrisBoard board;

	/**
	 * Construct a tetris board text view
	 * 
	 * @param b
	 *            a tetrisboard
	 */
	public TetrisBoardTextView(TetrisBoard b) {
		//set the board 
		this.board = b;
	}

	/**
	 * create the view for the tetris board
	 * 
	 * @return the string that holds the tetris board
	 */
	public String getBoardString() {
		
		// create a string to hold the board
		String tetris = "";
		
		// get the tetris board block
		Block[][] tetrisBoard = board.getBlockMatrix();
		
		// loop through the board
		for (int row = 0; row < tetrisBoard.length; row++) {
			for (int col = 0; col < tetrisBoard[row].length; col++) {
				
				// if the entry in the board contains a block
				if (board.hasBlock(row, col)) {
					
					// add a character "x" to the string
					tetris += "x";
					
				} else {
					
					// if the entry does not contain a block, add an empty space to the string
					tetris += " ";
				}
			}
			
			// if the row is not the last row, then add a line to separate each row
			if (row != tetrisBoard.length - 1)
				tetris += "\n";
		}
		 
		// return the string holding the board
		return tetris;
	}
}
