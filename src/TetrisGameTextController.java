import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Tetris game's controller,s responsible for taking in user input, proceeding
 * the game according to user input and the game's logic, and updating the view
 * of the game
 * 
 * @author Emma Nguyen
 * @version March 7 2018
 */
public class TetrisGameTextController {
	
	/* declare instance variables */
	
	//a tetris game 
	private TetrisGame game;
	
	//a tetris view 
	private TetrisBoardTextView view;

	/**
	 * Construct a controller for the Tetris game
	 */
	public TetrisGameTextController() {
		
		// create a new tetris game
		game = new TetrisGame();
		
		// create a new tetris view
		view = new TetrisBoardTextView(game.getTetrisBoard());
		
		// create the display
		refreshDisplay();
		
		// get input from the user and proceed the game
		readInput();
	}

	/**
	 * Get input from the user and loop until the user types "Quit"
	 */
	private void readInput() {

		// read input from the user
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// I/O almost always requires a try-catch block as exceptions might be thrown
		try {
			// create a string to hold user input
			String line = "";

			// loop until the user types Quit
			while ((!line.equals("Quit"))) {
				
				// try to read a line
				line = in.readLine();
				
				// perform movement on the piece according to user input
				moveEntered(line);
				
				// update the view with each input
				refreshDisplay();
				
			}
			
			// catch I/O exception
		} catch (IOException e) {
			
			// tell exception to print its error log
			e.printStackTrace();
		}

	}

	/**
	 * Print the view of the game
	 */
	private void refreshDisplay() {
		
		// prompt the user for input
		System.out.println("Please enter a move (l,r,d,z,x) or type Quit to end");
		
		// instructions for input
		System.out.println("Move details: l-left; r-right; d-down; z-clockwise; x-counter-clockwise");
		
		// print out the statistics of the game
		System.out.println("Number of lines cleared: " + game.getNumLines());
		System.out.println("Number of Tetrises cleared: " + game.getNumTetrises());
		
		// define the border of the board
		System.out.println("----------");
		
		// print the tetris board
		System.out.println(view.getBoardString());
		
		// define the border of the board
		System.out.println("----------");
	}

	/**
	 * Perform movement on the piece according to the given instruction
	 * 
	 * @param move
	 *            movement to be performed
	 */
	private void moveEntered(String move) {
		
		// if the instruction is "l", attempt to move the piece left
		if (move.equals("l")) {
			game.attempMove(TetrisGame.LEFT);
		}
		
		// if the instruction is "r", attempt to move the piece right
		else if (move.equals("r")) {
			game.attempMove(TetrisGame.RIGHT);
		}
		
		// if the instruction is "d", attempt to move the piece down
		else if (move.equals("d")) {
			game.attempMove(TetrisGame.DOWN);
		}
		
		// if the instruction is "z", attempt to rotate the piece clockwise
		else if (move.equals("z")) {
			game.attempMove(TetrisGame.CW);
		}
		
		// if the instruction is "x", attempt to rotate the piece counter-clockwise
		else if (move.equals("x")) {
			game.attempMove(TetrisGame.CCW);
		}
	}
}