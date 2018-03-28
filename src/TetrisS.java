import java.awt.Color;

/**
 * A class to implement a tetris piece in shape S
 * 
 * @author Emma Nguyen
 * @version March 3, 2018
 **/
public class TetrisS extends TetrisPiece {

	/**
	 * Construct a tetris piece and set it in shape S
	 */
	public TetrisS() {
		
		// create the piece at different rotations 
		boolean[][][] tetrisArray = {
				// shape at 0 degree
				{ 
					{ false, true, true, false }, 
					{ true, true, false, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 90 degree
				{ 
					{ true, false, false, false }, 
					{ true, true, false, false }, 
					{ false, true, false, false },
					{ false, false, false, false } },
				// shape at 180 degree
				{ 
					{ false, true, true, false }, 
					{ true, true, false, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 270 degree
				{ 
					{ true, false, false, false }, 
					{ true, true, false, false }, 
					{ false, true, false, false },
					{ false, false, false, false } } 
			};
		filledSquares = tetrisArray;
		
		// set the color of the piece 
		setColor(Color.ORANGE);
	}

}
