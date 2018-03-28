import java.awt.Color;

/**
 * A class to implement the tetris piece in shape T
 * 
 * @author Emma Nguyen
 * @version March 3, 2018
 **/
public class TetrisT extends TetrisPiece {

	/**
	 * Construct a tetris piece and set it in shape T
	 */
	public TetrisT() {
		
		// create the piece at different rotations  
		boolean[][][] tetrisArray = {
				// shape at 0 degree
				{ 
					{ true, true, true, false }, 
					{ false, true, false, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 90 degree
				{ 
					{ false, true, false, false }, 
					{ true, true, false, false }, 
					{ false, true, false, false },
					{ false, false, false, false } },
				// shape at 180 degree
				{ 
					{ false, true, false, false }, 
					{ true, true, true, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 270 degree
				{ 
					{ true, false, false, false }, 
					{ true, true, false, false }, 
					{ true, false, false, false },
					{ false, false, false, false } } 
			};
		filledSquares = tetrisArray;
		
		// set the color for the piece 
		setColor(Color.YELLOW);
	}

}
