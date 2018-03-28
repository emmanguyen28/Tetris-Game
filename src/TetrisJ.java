import java.awt.Color;

/**
 * A class to implement a tetris piece in shape J
 * 
 * @author Emma Nguyen
 * @version March 3, 2018
 */
public class TetrisJ extends TetrisPiece {

	/**
	 * Construct a tetris piece and set it in shape J
	 */
	public TetrisJ() {
		
		// create the piece at different angles 
		boolean[][][] tetrisArray = {
				// shape at 0 degree
				{ 
					{ true, true, true, false }, 
					{ false, false, true, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 90 degree
				{ 
					{ false, true, false, false }, 
					{ false, true, false, false }, 
					{ true, true, false, false },
					{ false, false, false, false } },
				// shape at 180 degree
				{ 
					{ true, false, false, false }, 
					{ true, true, true, false },
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 270 degree
				{ 
					{ true, true, false, false }, 
					{ true, false, false, false }, 
					{ true, false, false, false },
					{ false, false, false, false } } 
			};
		filledSquares = tetrisArray;
		
		// set the piece's color 
		setColor(Color.RED); 
	}

}
