import java.awt.Color;

/**
 * Create a single block on the tetris board
 * 
 * @author Emma Nguyen
 * @version March 19, 2018
 */
public class Block {
	/* declare instance variables */

	// whether a block is filled
	private boolean isFilled;

	// color of the block
	private Color blockColor;

	/**
	 * Construct a block
	 * 
	 * @param isFilled
	 *            whether the block is filled
	 * @param blockColor
	 *            color of the block
	 */
	public Block(boolean isFilled, Color blockColor) {

		// assign values for instance variables
		this.isFilled = isFilled;
		this.blockColor = blockColor;
	}

	/**
	 * Set the state of the block (filled or not)
	 * 
	 * @param isFilled
	 *            whether the block is filled
	 */
	public void setState(boolean isFilled) {

		// set the state
		this.isFilled = isFilled;
	}

	/**
	 * Get the state of the block
	 * 
	 * @return true if the block is filled, false otherwise
	 */
	public boolean getState() {
		return isFilled;
	}

	/**
	 * Set the color of the block
	 * 
	 * @param blockColor
	 *            the color to be set
	 */
	public void setColor(Color blockColor) {

		// set the color
		this.blockColor = blockColor;
	}

	/**
	 * Get the color of the block
	 * 
	 * @return the color of the block
	 */
	public Color getColor() {
		return blockColor;
	}
}
