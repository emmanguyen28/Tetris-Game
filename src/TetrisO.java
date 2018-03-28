import java.awt.Color;

/**
 * A class to implement a tetris piece in shape O
 * 
 * @author Emma Nguyen
 * @version March 3, 2018
 **/
public class TetrisO extends TetrisPiece {

	/**
	 * Construct a tetris piece and set it in shape O
	 */
	public TetrisO() { 
		
		// create the piece at different rotation 
		boolean[][][] tetrisArray = {
				// shape at 0 degree
				{ 
					{ true, true, false, false }, 
					{ true, true, false, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 90 degree
				{ 
					{ true, true, false, false }, 
					{ true, true, false, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 180 degree
				{ 
					{ true, true, false, false }, 
					{ true, true, false, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 270 degree
				{ 
					{ true, true, false, false }, 
					{ true, true, false, false }, 
					{ false, false, false, false },
					{ false, false, false, false } } 
			};
		filledSquares = tetrisArray;
		
		// set the piece's color 
		setColor(Color.MAGENTA);
	}

}
