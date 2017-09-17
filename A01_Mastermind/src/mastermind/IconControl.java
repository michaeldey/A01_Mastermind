/********************************************************
 *
 *  Project :  A01 Mastermind
 *  File    :  IconControl.java
 *  
 *  Name    :  	Garret Rueckert
 *				Michael Dey
 *
 *  Date    :  September 16, 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		Class IconControl is a class that will hold our images for our GUI. It has a getter
 *	(controlled by a switch statement) for the marble images, a getter for mystery image (so that we would not
 *	need to add "MYSTERY" into the Colors enum, and a getter for the peg images.
 *
 *    2) What data-structures are used.
 *    		Enum, Array, ImageIcon
 *
 *    3) What algorithms, techniques, etc. are used in implementing the data structures.
 *    		Switch Statements, Fields
 *
 *    4) What methods are implemented (optional).
 *
 *  Changes :  <Description|date of modifications>
 *
 ********************************************************/
package mastermind;

import javax.swing.ImageIcon;

public class IconControl {

	//ImageIcon fields
	private ImageIcon marbleHole = new ImageIcon(Window.class.getResource("/images/Circle_Hole_843505.png"));
	private ImageIcon marbleBlue = new ImageIcon(Window.class.getResource("/images/Circle_Blue.png"));
	private ImageIcon marbleGreen = new ImageIcon(Window.class.getResource("/images/Circle_Green.png"));
	private ImageIcon marbleRed = new ImageIcon(Window.class.getResource("/images/Circle_Red.png"));
	private ImageIcon marbleWhite = new ImageIcon(Window.class.getResource("/images/Circle_White.png"));
	private ImageIcon marbleYellow = new ImageIcon(Window.class.getResource("/images/Circle_Yellow.png"));
	private ImageIcon marbleBlack = new ImageIcon(Window.class.getResource("/images/Circle_Black.png"));	
	private ImageIcon pegBlack = new ImageIcon(Window.class.getResource("/images/Peg_Black.png"));
	private ImageIcon pegWhite = new ImageIcon(Window.class.getResource("/images/Peg_White.png"));
	private ImageIcon pegHole = new ImageIcon(Window.class.getResource("/images/Peg_Hole.png"));
	private ImageIcon mysteryMarble = new ImageIcon(Window.class.getResource("/images/Circle_Blank.png"));
	
	public IconControl() {
		
	}

	public ImageIcon getMarbleIcon(Colors c)
	{
		switch (c)
		{
		case BLUE:
			return marbleBlue;
		case GREEN:
			return marbleGreen;
		case RED:
			return marbleRed;
		case WHITE:
			return marbleWhite;
		case YELLOW:
			return marbleYellow;
		case BLACK:
			return marbleBlack;
		default:
			return marbleHole;
		}
	}
	
	public ImageIcon getMystery()
	{
		return mysteryMarble;
	}
	
	/**
	 * 
	 * @param pegNum represents the int value of the current selected peg
	 * @return the ImageIcon associated with the current selected color
	 * 
	 */
	public ImageIcon getPegIcon(Pegs peg)
	{
		switch (peg)
		{
		case WHITE:
			return pegWhite;
		case BLACK:
			return pegBlack;
		default:
			return pegHole;
		}
	}
	
}
