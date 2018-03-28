import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Tetris game - GUI version's controller, responsible for taking in user input,
 * proceeding the game accordingly, and updating the GUI view of the game
 * 
 * @author ThuHa
 * @version March 17, 2018
 */
public class TetrisGameGUIController extends JPanel implements KeyListener {

	/* default drop rate of a tetris piece */
	public static final int DEFAULT_DROP_RATE = 1000;

	/* declare instance variables */
	// a tetris game
	private TetrisGame game;

	// a tetris game view
	private TetrisBoardGUIView view;

	// timer for the game
	private Timer gameTimer;

	/**
	 * Construct a controller for the tetris game
	 */
	public TetrisGameGUIController() {

		// call the superclass constructor and create a border layout
		super(new BorderLayout());

		// create a new game
		game = new TetrisGame();

		// create the game's tetris board view
		view = new TetrisBoardGUIView(game.getTetrisBoard(), game);

		// add the view of the game
		add(view, BorderLayout.CENTER);

		// start the timer
		setUpTimer();

		// create the display
		refreshDisplay();

		// add the key listener
		setFocusable(true);
		addKeyListener(this);

	}

	/**
	 * Set up the game's Timer
	 */
	private void setUpTimer() {

		// create the game's timer
		gameTimer = new Timer(DEFAULT_DROP_RATE, new ActionListener() {
			// get the timer to move a tetris piece down
			public void actionPerformed(ActionEvent e) {
				game.attempMove(game.DOWN);
				refreshDisplay();
				if (game.getLevel() != 0)
					gameTimer.setDelay(DEFAULT_DROP_RATE / (game.getLevel() + 1));
			}
		});

		// start the timer
		gameTimer.start();

		// if the game is over, stop the timer
		if (game.isOver())
			gameTimer.stop();
	}

	/**
	 * Refresh the game's display
	 */
	public void refreshDisplay() {

		// refresh view
		repaint();

	}

	/**
	 * Handle user's input through keyboard
	 * 
	 * @param e
	 *            key-pressed event
	 */
	public void keyPressed(KeyEvent e) {
		// get the key code
		int key = e.getKeyCode();

		// process the game according to the key code
		switch (key) {

		// if the key is x, attempt to rotate the current piece clockwise
		case KeyEvent.VK_X:
			game.attempMove(game.CW);
			break;

		// if the key is z, attempt to rotate the current piece counterclockwise;
		case KeyEvent.VK_Z:
			game.attempMove(game.CCW);
			break;

		// if the key is left arrow, move the current piece left
		case KeyEvent.VK_LEFT:
			game.attempMove(game.LEFT);
			break;

		// if the key is right arrow, move the current piece right
		case KeyEvent.VK_RIGHT:
			game.attempMove(game.RIGHT);
			break;

		// if the key is down arrow, move the current piece down
		case KeyEvent.VK_DOWN:
			game.attempMove(game.DOWN);
			break;

		// other keys, do nothing
		default:
			break;
		}

		// refresh the display
		refreshDisplay();
	}

	/**
	 * Necessary for key listener
	 * 
	 * @param e
	 *            key-pressed event
	 */
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Necessary for key listener
	 * 
	 * @param e
	 *            key-pressed event
	 */
	public void keyReleased(KeyEvent e) {

	}
}
