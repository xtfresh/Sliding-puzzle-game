
import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Montek
 * @version 1.0
 */

public class Piece {
	private final int VALUE; //face value of piece
	private final Point point; //holds the default(correct) position of the piece
	private ImageIcon image;
	
	public Piece(int value, Point point, ImageIcon image){
		this.VALUE = value;
		this.point = new Point((int)point.getX(), (int)point.getY()); //since getX(), getY() returns a type double, parsed it to int
		this.image = new ImageIcon(image.getImage());
	}
	
	
	public int getValue(){
		return VALUE;
	}
	
	public Point getPoint(){
		return point;
	}
	
	public ImageIcon getImage(){
		return image;
	}
	
}
