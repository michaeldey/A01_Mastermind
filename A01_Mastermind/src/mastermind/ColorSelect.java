/********************************************************
 *
 *  Project :  A01 Mastermind
 *  File    :  ColorSelect.java
 *  
 *  Name    :  	Garret Rueckert
 *				Michael Dey
 *
 *  Date    :  September 16, 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		This is a game of Mastermind
 *
 *    2) What data-structures are used.
 *    		Enum, Array, ActionListener, Swing GUI, List
 *
 *    3) What algorithms, techniques, etc. are used in implementing the data structures.
 *    		For Loops, Switch Statements, List
 *
 *    4) What methods are implemented (optional).
 *
 *  Changes :  <Description|date of modifications>
 *
 ********************************************************/
package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;
import java.awt.event.*;

public class ColorSelect extends JFrame {

	//set up images
	ImageIcon marbleBlue = new ImageIcon(Window.class.getResource("/images/Circle_Blue.png"));
	ImageIcon marbleGreen = new ImageIcon(Window.class.getResource("/images/Circle_Green.png"));
	ImageIcon marbleRed = new ImageIcon(Window.class.getResource("/images/Circle_Red.png"));
	ImageIcon marbleWhite = new ImageIcon(Window.class.getResource("/images/Circle_White.png"));
	ImageIcon marbleYellow = new ImageIcon(Window.class.getResource("/images/Circle_Yellow.png"));
	ImageIcon marbleBlack = new ImageIcon(Window.class.getResource("/images/Circle_Black.png"));
	ImageIcon marbleHole = new ImageIcon(Window.class.getResource("/images/Circle_Hole_843505.png"));
	ImageIcon[] images = new ImageIcon[]{marbleBlue, marbleGreen, marbleRed, marbleWhite, marbleYellow, marbleBlack, marbleHole};
	
	/**Set up the holders of the images*/
	private JPanel colorGrid = new JPanel(new GridLayout(1,7));	//new JPanel 7 wide, 1 tall
	public JButton[] buttons = new JButton[images.length];		//array of JButtons	
	
	//constructor
	public ColorSelect(ActionListener listener)	{

		//loop through the buttons and add them
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton();							//create a JButton
			buttons[i].setIcon(images[i]);						//add the corresponding image
			buttons[i].setContentAreaFilled(false);				//get rid of the gradient
			buttons[i].setBorder(null);							//get rid of the border	
			colorGrid.add(buttons[i]);							//add the button to colorGrid
		}
	}
	
	/**
	 * 
	 * @return JPanel colorGrid which holds the buttons
	 */
	public JPanel getColorGrid()
	{
		return colorGrid;
	}

	/**
	 * 
	 * @param selection is the item selected from Window
	 * @return image object
	 * 
	 * This method takes an int selection which returns an image that determines the user selection in Window.
	 */
	public ImageIcon returnImage(int selection)
	{
		switch (selection)
		{
		case 1:
			return marbleBlue;
		case 2:
			return marbleGreen;
		case 3:
			return marbleRed;
		case 4:
			return marbleWhite;
		case 5:
			return marbleYellow;
		case 6:
			return marbleBlack;
		default:
			return marbleHole;
		}
	}
	
}
