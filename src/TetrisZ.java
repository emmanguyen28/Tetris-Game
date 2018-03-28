import java.awt.Color;

/**
 * A class to implement the tetris piece in shape Z
 * 
 * @author Emma Nguyen
 * @version March 3, 2018
 **/
public class TetrisZ extends TetrisPiece {

	/**
	 * Construct a tetris piece and set it in shape Z
	 */
	public TetrisZ() {
		boolean[][][] tetrisArray = {
				// shape at 0 degree
				{ 
					{ true, true, false, false }, 
					{ false, true, true, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 90 degree
				{ 
					{ false, true, false, false }, 
					{ true, true, false, false }, 
					{ true, false, false, false },
					{ false, false, false, false } },
				// shape at 180 degree
				{ 
					{ true, true, false, false }, 
					{ false, true, true, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 270 degree
				{ 
					{ false, true, false, false },
					{ true, true, false, false }, 
					{ true, false, false, false },
					{ false, false, false, false } } 
			};
		setColor(Color.BLUE); 
		
		// set the color for the piece 
		filledSquares = tetrisArray;
	}

}
