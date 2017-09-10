package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;
import java.awt.event.*;

public class ColorSelect extends JFrame {

	//set up images
	ImageIcon pegBlue = new ImageIcon(Window.class.getResource("/images/Circle_Blue.png"));
	ImageIcon pegGreen = new ImageIcon(Window.class.getResource("/images/Circle_Green.png"));
	ImageIcon pegRed = new ImageIcon(Window.class.getResource("/images/Circle_Red.png"));
	ImageIcon pegWhite = new ImageIcon(Window.class.getResource("/images/Circle_White.png"));
	ImageIcon pegYellow = new ImageIcon(Window.class.getResource("/images/Circle_Yellow.png"));
	ImageIcon pegHole = new ImageIcon(Window.class.getResource("/images/Circle_Hole_843505.png"));
	ImageIcon[] images = new ImageIcon[]{pegBlue, pegGreen, pegRed, pegWhite, pegYellow, pegHole};
	
	private JPanel colorGrid = new JPanel(new GridLayout(1,7));	//new JPanel 6 wide, 1 tall
	public JButton[] buttons = new JButton[images.length];
	
	
	//constructor
	public ColorSelect(ActionListener listener)
	{

		//loop through the buttons and add them
		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton();					//create a JButton
			buttons[i].setIcon(images[i]);				//add the corresponding image
			buttons[i].setContentAreaFilled(false);		//get rid of the gradient
			buttons[i].setBorder(null);					//get rid of the border	
			colorGrid.add(buttons[i]);					//add the button to colorGrid
		}
	}
	
	public JPanel getColorGrid()
	{
		return colorGrid;
	}

	public int getButtonIndex()
	{
		return 0;
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
		case 0:
			return pegBlue;
		case 1:
			return pegGreen;
		case 2:
			return pegRed;
		case 3:
			return pegWhite;
		case 4:
			return pegYellow;
		default:
			return pegHole;
		}
	}
	
}
