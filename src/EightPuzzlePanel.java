import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * 
 * @author Montek
 *	
 */
public class EightPuzzlePanel extends JPanel implements MouseListener{
	
	private JPanel topPanel;
	private JPanel btmPanel;
	private JLabel[][] imageLabel;
	private ImageIcon[][] imageIcon;
	private JButton scramble;
	private JButton reset;
	private EightPuzzle puzzle;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Component lastEntered;
	
	
	/**
	 * sets up the eightpuzzle panel
	 * @param puzzle takes in a EightPuzzle object
	 */
	public EightPuzzlePanel(EightPuzzle puzzle){
		x1=0;
		y1=0;
		x2=0;
		y2=0;
		topPanel = new JPanel();
		btmPanel = new JPanel();
		imageLabel = new JLabel[3][3];
		imageIcon = puzzle.getGameView();
		this.puzzle = puzzle;  
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				imageLabel[i][j] = new JLabel(new ImageIcon(imageIcon[i][j].getImage()));//applies images to the JLabel array
				imageLabel[i][j].addMouseListener(this);
			
			}
		}
		
		
		scramble = new JButton("Scramble");
		reset = new JButton("Reset");
		scramble.addMouseListener(this);
		reset.addMouseListener(this);
		
		btmPanel.setLayout(new BoxLayout(btmPanel, BoxLayout.LINE_AXIS));
		btmPanel.add(reset);
		btmPanel.add(scramble);
		
		
		topPanel.setLayout(new GridLayout(3,3));
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				topPanel.add(imageLabel[i][j]);
			}
		}
		add(topPanel);
		add(btmPanel);
		
		
	}
	
	/**
	 * changes the image displayed by each of the buttons
	 *  to match the internal state of the EightPuzzle game
	 */
	public void updateGUI(){
		imageIcon = puzzle.getGameView();
		/**
		 * first removes the previous imageLabels from the panel
		 */
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				topPanel.remove(imageLabel[i][j]);
			}
		}
		/**
		 * apply the new imageLabels to the panel
		 */
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				imageLabel[i][j] = new JLabel(new ImageIcon(imageIcon[i][j].getImage()));//applies images to the JLabel array
				imageLabel[i][j].addMouseListener(this);
				topPanel.add(imageLabel[i][j]);
			}
		}
		revalidate();
	
	}
	/**
	 * handles what happens when user clicks reset or scramble
	 */
	public void mouseClicked(MouseEvent e){	
		if(lastEntered==reset){
			puzzle.reset();
			updateGUI();
		}else{
			if(lastEntered==scramble){
				puzzle.scramble();
				updateGUI();
			}
		}
	}
			
		
	/**
	 * updates the lastEntered component so that when mouse is released, the comoponet accesed is the correct one
	 */
		
	public void mouseEntered(MouseEvent e) {
		lastEntered = e.getComponent();
		
	}
	public void mouseExited(MouseEvent e) {
	}
	
	/**
	 * this gets the component the user first clicks on
	 */
	public void mousePressed(MouseEvent e) {
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(lastEntered==imageLabel[i][j]){
					x1=i;
					y1=j;
				}
			}
		}
	}
	
	/**
	 * gets the component the user released mouse, this makes it seem if the user is dragging the image to the blank piece
	 * then based on its x,y it sends this and the first object's x,y clicked on to the move method
	 */
	public void mouseReleased(MouseEvent e) {
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(lastEntered==imageLabel[i][j]){
					x2=i;
					y2=j;
				}
			}
		}
		puzzle.move(x1,y1,x2,y2);
		updateGUI();
		
		if(puzzle.isSolved()&&lastEntered!=scramble&&lastEntered!=reset){
			int reply = JOptionPane.showConfirmDialog(null, "You Won! Play Again?", "Winner", JOptionPane.YES_NO_OPTION);
	        if (reply == JOptionPane.NO_OPTION) {
	           JOptionPane.showMessageDialog(null, "Thanks For Playing");
	           System.exit(0);
	        }
		}
		
	}
	
}
