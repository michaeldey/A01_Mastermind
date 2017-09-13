package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;
import java.util.Arrays;

public class GuessSection {
	
	//set up images
	ImageIcon marbleBlue = new ImageIcon(Window.class.getResource("/images/Circle_Blue.png"));
	ImageIcon marbleGreen = new ImageIcon(Window.class.getResource("/images/Circle_Green.png"));
	ImageIcon marbleRed = new ImageIcon(Window.class.getResource("/images/Circle_Red.png"));
	ImageIcon marbleWhite = new ImageIcon(Window.class.getResource("/images/Circle_White.png"));
	ImageIcon marbleYellow = new ImageIcon(Window.class.getResource("/images/Circle_Yellow.png"));
	ImageIcon marbleBlack = new ImageIcon(Window.class.getResource("/images/Circle_Black.png"));
	ImageIcon marbleHole = new ImageIcon(Window.class.getResource("/images/Circle_Hole_843505.png"));
	
	ImageIcon pegBlack = new ImageIcon(Window.class.getResource("/images/Peg_Black.PNG"));
	ImageIcon pegRed = new ImageIcon(Window.class.getResource("/images/Peg_Red.PNG"));
	ImageIcon pegWhite = new ImageIcon(Window.class.getResource("/images/Peg_White.PNG"));
	ImageIcon pegHole = new ImageIcon(Window.class.getResource("/images/Peg_Hole.PNG"));
		
	//set up Panels
	public JPanel holdingBox = new JPanel(new GridLayout(1,2)); //holds both pegBox and feedbackBox
	public JPanel marbleBox = new JPanel(new GridLayout(1,4));	//hold 4 peg buttons
	public JPanel pegBox = new JPanel(new GridLayout(2,2));	//holds 2x2 feedback icons
	
	//instantiate object arrays that will hold image icons and buttons
	public JButton[] marbles = new JButton[4];
	public JLabel[] pegs = new JLabel[4];
	
	//constructor
	public GuessSection()
	{
		for (JButton b : marbles)
		{
			b = new JButton();				//create a new button object
			b.setIcon(marbleHole);			//make the image a blank peg
			b.setContentAreaFilled(false);	//clear the gradient and stroke from button
			b.setBorder(null);				//clear border from button
			marbleBox.add(b);				//add the button to marbleBox
		}
		for (JLabel f : pegs)
		{
			f = new JLabel();				//create a new JLabel object
			f.setIcon(pegHole); 		//set the image to a blank feedback
			pegBox.add(f);
		}
		holdingBox.add(marbleBox);
		holdingBox.add(pegBox);
	}

	public JPanel getHoldingBox()
	{
		return holdingBox;
	}
	
	public JButton[] getMarbles()
	{
		return marbles;
	}
	
	public JLabel[] getPegs()
	{
		return pegs;
	}

	@Override
	public String toString() {
		StringBuilder sb = null;
		int i = 0;
		for (JButton m : marbles){
			sb.append(String.valueOf(++i) + " ");
		}
		return sb.toString();
	}
	
	
}
