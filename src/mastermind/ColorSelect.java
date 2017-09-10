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
