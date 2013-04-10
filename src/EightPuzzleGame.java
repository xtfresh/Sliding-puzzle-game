
import javax.swing.JFrame;

/**
 * 
 * @author Montek
 *
 */
public class EightPuzzleGame extends JFrame{
	/**
	 * creates the JFrame and puts the EightPuzzle Panel onto it
	 * this is the main method that runs the rest of the program
	 * @param args
	 */
	
	
	public static void main(String [] args){
		JFrame frame = new JFrame ("EightPuzzle");
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    EightPuzzle puzzle = new EightPuzzle();
	    EightPuzzlePanel panel = new EightPuzzlePanel(puzzle);
	    frame.add(panel);
	    frame.setSize(400,400);
	    frame.setVisible(true);
	}
}


