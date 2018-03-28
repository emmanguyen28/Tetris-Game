import java.awt.Color;

/**
 * A class to implement a tetris piece in shape I
 * 
 * @author Emma Nguyen
 * @version March 3, 2018
 **/
public class TetrisI extends TetrisPiece {

	/**
	 * Construct a tetris piece and set it in shape I
	 */
	public TetrisI() {
		
		//create the piece at different rotation
		boolean[][][] tetrisArray = {
				// shape at 0 degree
				{ 
					{ true, true, true, true }, 
					{ false, false, false, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 90 degree
				{ 
					{ true, false, false, false }, 
					{ true, false, false, false }, 
					{ true, false, false, false },
					{ true, false, false, false } },
				// shape at 180 degree
				{ 
					{ true, true, true, true },
					{ false, false, false, false }, 
					{ false, false, false, false },
					{ false, false, false, false } },
				// shape at 270 degree
				{ 
					{ true, false, false, false }, 
					{ true, false, false, false }, 
					{ true, false, false, false },
					{ true, false, false, false } } 
			};
		filledSquares = tetrisArray;
		
		//set the piece's color 
		setColor(Color.PINK); 
	}


}
