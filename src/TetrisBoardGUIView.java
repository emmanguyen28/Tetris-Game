import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Create the view for the Tetris game - GUI version
 * 
 * @author Emma Nguyen
 * @version March 17, 2018
 */
public class TetrisBoardGUIView extends JComponent {

	/* create a tetris board */
	private TetrisBoard board;

	/* create a tetris game */
	private TetrisGame game;

	/**
	 * Construct a tetris board GUI view
	 * 
	 * @param b
	 *            a tetris board
	 * @param g
	 *            a tetris game
	 */
	public TetrisBoardGUIView(TetrisBoard b, TetrisGame g) {

		// set the board
		this.board = b;

		// set the game
		this.game = g;

		// repaint
		repaint();
	}

	/**
	 * Paint the game
	 * 
	 * @param g
	 *            a graphics object to draw on
	 */
	public void paint(Graphics g) {

		// compute the block size
		int blockSize = computeBlockSize();

		// get the tetris board
		Block[][] block = board.getBlockMatrix();

		// loop through the board
		for (int row = 0; row < board.getNumRows(); row++) {
			for (int col = 0; col < board.getNumCols(); col++) {

				// if an entry in the board is filled (has a block)
				if (board.hasBlock(row, col)) {

					// paint the block
					paintBlock(g, row, col, blockSize, block[row][col].getColor());

				}
			}
		}

		// the x-coordinate of the side panel that includes the next piece and the
		// statistics
		int SIDE_PANEL_X = board.getNumCols() * blockSize
				+ (getWidth() - board.getNumCols() * blockSize - getWidth() / 3) / 2;

		// paint the next piece
		paintNextPiece(g, SIDE_PANEL_X, 0, blockSize);

		// paint the statistics of the game
		paintStatistics(g, SIDE_PANEL_X, blockSize * 4 + 250);

		// if the game is over, then paint game over
		paintGameOver(g, 50, getHeight() / 2);

		// paint the board outline
		paintBoardOutline(g, blockSize);

	}

	/**
	 * Paint the outline of the board
	 * 
	 * @param g
	 *            a graphics object to draw on
	 * @param blockSize
	 *            size of a block
	 */
	private void paintBoardOutline(Graphics g, int blockSize) {

		// create a graphics 2d object and cast it to the current graphics object
		Graphics2D g2 = (Graphics2D) g;

		// set the color of the outline
		g2.setColor(Color.GRAY);

		// set the outline's thickness
		g2.setStroke(new BasicStroke(6.0f));

		// draw the tetris board
		g2.drawRect(0, 0, (board.getNumCols() * blockSize), (board.getNumRows() * blockSize));
	}

	/**
	 * Paint the block
	 * 
	 * @param g
	 *            a graphics object to draw on
	 * @param row
	 *            the row of the block
	 * @param col
	 *            the column of the block
	 * @param blockSize
	 *            size of the block
	 */
	private void paintBlock(Graphics g, int row, int col, int blockSize, Color color) {

		// set color for the block
		g.setColor(color);

		// paint the block
		g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);

		// create a graphics 2d object and cast it to the current graphics object
		Graphics2D g2 = (Graphics2D) g;

		// set the color of the grid
		g2.setColor(Color.BLACK);

		// set the stroke size
		g2.setStroke(new BasicStroke(3.0f));

		// paint the outline of the block
		g2.drawRect(col * blockSize, row * blockSize, blockSize, blockSize);

	}

	/**
	 * Draw the statement "Game over"
	 * 
	 * @param g
	 *            a Graphics object to draw on
	 * @param x
	 *            the x-coordinate of the statement
	 * @param y
	 *            the y-coordinate of the statement
	 */
	private void paintGameOver(Graphics g, int x, int y) {

		// if the game is over
		if (board.isOver()) {

			// set the color of the font
			g.setColor(new Color(66, 128, 244));

			// create a new font
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, (50));

			// set the current graphics object's font
			g.setFont(font);

			// draw the statement game over
			g.drawString("Game over!", x, y);
		}
	}

	/**
	 * Paint the next piece
	 * 
	 * @param g
	 *            a Graphics object to draw on
	 * @param x
	 *            the x coordinate of the next piece
	 * @param y
	 *            the y coordinate of the next piece
	 * @param blockSize
	 *            the size of the block
	 */
	private void paintNextPiece(Graphics g, int x, int y, int blockSize) {

		// get the next piece
		TetrisPiece nextPiece = board.getNextPiece();

		// loop through the piece
		for (int row = 0; row < nextPiece.NUM_ROWS_PIECE; row++) {
			for (int col = 0; col < nextPiece.NUM_COLS_PIECE; col++) {

				// paint the piece
				if (nextPiece.isFilled(nextPiece.getPieceRotation(), row, col)) {

					paintBlock(g, row + 1, col + board.getNumCols() + 2, blockSize, nextPiece.getColor());
				}
			}
		}

		// create a new font
		Font next = new Font(Font.SERIF, Font.PLAIN, 20);

		// set the font
		g.setFont(next);

		// set the font's color
		g.setColor(Color.BLUE);

		// draw "Next Piece"
		g.drawString("Next Piece", x + 60, y + blockSize * 4 + 30);

		// create a graphics 2D object and cast it to the current graphics object
		Graphics2D g2 = (Graphics2D) g;

		// set the color for the object
		g2.setColor(Color.GRAY);

		// set the thickness of the stroke
		g2.setStroke(new BasicStroke(6.0f));

		// draw the rectangle surrounding the piece
		g2.drawRect(x, y, getWidth() / 3, blockSize * 4);

	}

	/**
	 * Paint the statistics of the game
	 * 
	 * @param g
	 *            a Graphics object to draw on
	 * @param x
	 * @param y
	 */
	private void paintStatistics(Graphics g, int x, int y) {

		// set the color of the stats
		g.setColor(Color.BLUE);

		// draw the statistics
		g.drawString("Level: " + game.getLevel(), x, y);
		g.drawString("Lines cleared: " + game.getNumLines(), x, y + 60);
		g.drawString("Tetrises cleared: " + game.getNumTetrises(), x, y + 120);
		g.drawString("Emma Nguyen @2018", x, y + 180);
	}

	/**
	 * Compute the best block size possible for the current window's width and
	 * height
	 * 
	 * @return the best block size
	 */
	private int computeBlockSize() {

		// compute the width
		int maxWidth = getWidth() * 2 / 3 / board.getNumCols();

		// compute the height
		int maxHeight = getHeight() / board.getNumRows();

		// get the smaller one to be the size of the block
		return Math.min(maxWidth, maxHeight);
	}

}
