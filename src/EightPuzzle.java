import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * 
 * @author Montek
 *
 */
public class EightPuzzle {
	final int BOARD_SIZE;
	private final int SHUFFLE_NUM; //number of times to swap pieces during shuffling
	private Random rand;
	private Piece[][] board;
	
	ImageIcon piece1,piece2,piece3,piece4,piece5,piece6,piece7,piece8,blankPiece;
	ClassLoader cl = this.getClass().getClassLoader();
	
	public EightPuzzle(){
		BOARD_SIZE = 3;
		SHUFFLE_NUM = 500;
		rand = new Random();
		board = new Piece[BOARD_SIZE][BOARD_SIZE];
		
		//fill 2d piece array with value, point, and image
		board[0][0] = new Piece(1, new Point(0,0),new ImageIcon(cl.getResource("Piece1.jpg")));	
		board[0][1] = new Piece(2, new Point(1,0),new ImageIcon(cl.getResource("Piece2.jpg")));
		board[0][2] = new Piece(3, new Point(2,0),new ImageIcon(cl.getResource("Piece3.jpg")));
		board[1][0] = new Piece(4, new Point(0,1),new ImageIcon(cl.getResource("Piece4.jpg")));
		board[1][1] = new Piece(5, new Point(1,1),new ImageIcon(cl.getResource("Piece5.jpg")));
		board[1][2] = new Piece(6, new Point(2,1),new ImageIcon(cl.getResource("Piece6.jpg")));
		board[2][0] = new Piece(7, new Point(0,2),new ImageIcon(cl.getResource("Piece7.jpg")));
		board[2][1] = new Piece(8, new Point(1,2),new ImageIcon(cl.getResource("Piece8.jpg")));
		board[2][2] = new Piece(0, new Point(2,2),new ImageIcon(cl.getResource("blank piece.jpg")));	
	}
	
	/**
	 *  validly rearranges the pieces in the 
	 *  puzzle in order to create a more scrambled puzzle to solve.
	 *  
	 */
	public void scramble(){
		
		for(int i = 0;i<SHUFFLE_NUM;i++){
			int x1 = rand.nextInt(3);
			int y1 = rand.nextInt(3);
			int x2=0;
			int y2=0;
			for(int x=0;x<3;x++){        
				for(int y=0;y<3;y++){
					if(board[x][y].getValue()==0){
						x2=x;
						y2=y;
					}
				}
			}
			/**
			 * if the pieces can't be moved i.e. piece 1 is not adjacent to piece 2, then redo the loop again.
			 */
			if(!move(x1,y1,x2,y2)){
				i--;
			}
		}
	}
	/**
	 * 
	 * @param x1 i location in array of piece 1
	 * @param y1 j location in array of piece 1
	 * @param x2 i location in array of piece 2
	 * @param y2 j location in array of piece 2
	 */
	private void swap(int x1, int y1, int x2, int y2){
		Piece temp = board[x1][y1];
		board[x1][y1] = board[x2][y2];
		board[x2][y2] = temp;
	}
	/**
	 * Takes in a proposed move that the player is attempting to make. If it is 
	 * a valid move, the game performs that move and returns true. If it is an invalid move, the 
	 * method returns false
	 * @param x1 row of piece 1
	 * @param y1 column of piece 1
	 * @param x2 row of piece 2
	 * @param y2 column of piece2
	 * @return
	 */
	public Boolean move(int x1, int y1, int x2, int y2){
		int p1X=0;
		int p1Y=0;
		int p2X=0;
		int p2Y=0;
		
		/**
		 * searches through the array to find the piece1 and piece2
		 * once it finds it, it assigns the respective i and j values to p1x...etc
		 */
		
		Piece piece1 = board[x1][y1];
		Piece piece2 = board[x2][y2];
		
		for(int i = 0;i<BOARD_SIZE;i++){      
			for(int j = 0;j<BOARD_SIZE;j++){
				if(board[i][j].getValue() == piece1.getValue()){
					p1X=i;
					p1Y=j;
				}
				if(board[i][j].getValue() == piece2.getValue()){
					p2X=i;
					p2Y=j;
				}
			}
		}
		
		//if piece2 is not the blank piece, its not a valid move
		if(piece2.getValue()!=0){
			return false;
		}
		
		int xDist = p2X-p1X;
		int yDist = p2Y-p1Y;
		
		if(xDist>1 || p1X-p2X>1||yDist>1||p1Y-p2Y>1)//makes sure the distance between them isn't greater then one
			return false;
		//this checks to make sure the pieces are adjacent
		if((xDist==1&&yDist==1) || (xDist==1&&p1Y-p2Y==1) || (p1Y-p2Y==1&&p1X-p2X==1)|| (yDist==1&&p1X-p2X==1))
			return false;
		swap(p1X,p1Y,p2X,p2Y);
		return true;
		
	}
	/**
	 * this method evaluates whether the win condition has been met
	 * @return true if the solution is correct
	 */
	public boolean isSolved(){
		for(int i=0;i<BOARD_SIZE;i++)
			for(int j=0;j<BOARD_SIZE;j++){
				int x = (int) board[i][j].getPoint().getY();
				int y = (int) board[i][j].getPoint().getX();
				if(x!=i||y!=j)
					return false;
			}
		
		return true;
	}
	/**
	 * returns the board to its initial state where all the pieces are in 
	 * their correct location.
	 */
	public void reset(){
		board[0][0] = new Piece(1, new Point(0,0),new ImageIcon(cl.getResource("Piece1.jpg")));	
		board[0][1] = new Piece(2, new Point(1,0),new ImageIcon(cl.getResource("Piece2.jpg")));
		board[0][2] = new Piece(3, new Point(2,0),new ImageIcon(cl.getResource("Piece3.jpg")));
		board[1][0] = new Piece(4, new Point(0,1),new ImageIcon(cl.getResource("Piece4.jpg")));
		board[1][1] = new Piece(5, new Point(1,1),new ImageIcon(cl.getResource("Piece5.jpg")));
		board[1][2] = new Piece(6, new Point(2,1),new ImageIcon(cl.getResource("Piece6.jpg")));
		board[2][0] = new Piece(7, new Point(0,2),new ImageIcon(cl.getResource("Piece7.jpg")));
		board[2][1] = new Piece(8, new Point(1,2),new ImageIcon(cl.getResource("Piece8.jpg")));
		board[2][2] = new Piece(0, new Point(2,2),new ImageIcon(cl.getResource("blank piece.jpg")));	
	}
	/**
	 *  return a visual representation of the current state of 
	 *  the game. This method will be called by the GUI so that it can update its representation of the 
	 *  current state of the game.
	 * @return imageArray 2d array of imageicon
	 */
	public ImageIcon[][] getGameView(){
		ImageIcon[][] imageArray = new ImageIcon[BOARD_SIZE][BOARD_SIZE];
		for(int i = 0;i<BOARD_SIZE;i++){      //copy piece's image into imageicon array
			for(int j = 0;j<BOARD_SIZE;j++){
				imageArray[i][j] = board[i][j].getImage();
			}
		}
		
		return imageArray;
	}
}
