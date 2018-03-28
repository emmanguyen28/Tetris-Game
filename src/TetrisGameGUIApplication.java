
import javax.swing.JFrame;

/**
 * Create a GUI application for the tetris game that is to be started from the
 * command line
 * 
 * @author Emma Nguyen
 * @version March 17, 2018
 *
 */
public class TetrisGameGUIApplication {

	public static void main(String[] args) {

		// create a frame (window)
		JFrame tetrisFrame = new JFrame("Tetris");

		// set the frame's size
		tetrisFrame.setSize(650, 720);

		// add a tetris game to the frame
		tetrisFrame.getContentPane().add(new TetrisGameGUIController());

		// set exit operation for the frame
		tetrisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set the frame to be visible
		tetrisFrame.setVisible(true);
	}

}
