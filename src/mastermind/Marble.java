package mastermind;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Marble {

	//set up images
	ImageIcon marbleHole = new ImageIcon(Window.class.getResource("/images/Circle_Hole_843505.png"));
	ImageIcon marbleBlue = new ImageIcon(Window.class.getResource("/images/Circle_Blue.png"));
	ImageIcon marbleGreen = new ImageIcon(Window.class.getResource("/images/Circle_Green.png"));
	ImageIcon marbleRed = new ImageIcon(Window.class.getResource("/images/Circle_Red.png"));
	ImageIcon marbleWhite = new ImageIcon(Window.class.getResource("/images/Circle_White.png"));
	ImageIcon marbleYellow = new ImageIcon(Window.class.getResource("/images/Circle_Yellow.png"));
	ImageIcon marbleBlack = new ImageIcon(Window.class.getResource("/images/Circle_Black.png"));

	ImageIcon currentState;		//dynamically changing state
	String myState;
	
	JButton myButton;
	
	/**
	 * constructor
	 */
	public Marble()
	{
		this.currentState = marbleHole;
		myState = "Empty";
		myButton = new JButton();				//create a new button object
		myButton.setIcon(currentState);			//make the image a blank peg
		myButton.setContentAreaFilled(false);	//clear the gradient and stroke from button
		myButton.setBorder(null);				//clear border from button
	}
	
	
	/**
	 * 
	 * @return current state of the Marble (which is an image)
	 */
	public ImageIcon getState()
	{
		return currentState;
	}
	
	/**
	 * 
	 * @param state is an int value of what state to change the image to
	 */
	public void setState(int state)
	{
		switch (state)
		{
		case 1:
			currentState = marbleBlue;
			myState = "Blue";
			break;
		case 2:
			currentState = marbleGreen;
			myState = "Green";
			break;
		case 3:
			currentState = marbleRed;
			myState = "Red";
			break;
		case 4:
			currentState = marbleWhite;
			myState = "White";
			break;
		case 5:
			currentState = marbleYellow;
			myState = "Yellow";
			break;
		case 6:
			currentState = marbleBlack;
			myState = "Black";
			break;
		default:
			currentState = marbleHole;
			myState = "Empty";
			break;
		}
		myButton.setIcon(currentState);
	}

	/**
	 * 
	 * @return JButton version of the current state
	 */
	public JButton getButton()
	{
		return myButton;
	}

	/**
	 * returns the String value of the current state
	 */
	@Override
	public String toString() {
		return myState;
	}
	
	
	
}
