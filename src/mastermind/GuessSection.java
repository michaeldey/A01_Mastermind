package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;

public class GuessSection {
	
	//set up images
	ImageIcon pegBlue = new ImageIcon(Window.class.getResource("/images/Circle_Blue.png"));
	ImageIcon pegGreen = new ImageIcon(Window.class.getResource("/images/Circle_Green.png"));
	ImageIcon pegRed = new ImageIcon(Window.class.getResource("/images/Circle_Red.png"));
	ImageIcon pegWhite = new ImageIcon(Window.class.getResource("/images/Circle_White.png"));
	ImageIcon pegYellow = new ImageIcon(Window.class.getResource("/images/Circle_Yellow.png"));
	ImageIcon pegBlack = new ImageIcon(Window.class.getResource("/images/Circle_Black.png"));
	ImageIcon pegHole = new ImageIcon(Window.class.getResource("/images/Circle_Hole_843505.png"));
	
	ImageIcon feedbackBlack = new ImageIcon(Window.class.getResource("/images/Peg_Black.PNG"));
	ImageIcon feedbackRed = new ImageIcon(Window.class.getResource("/images/Peg_Red.PNG"));
	ImageIcon feedbackWhite = new ImageIcon(Window.class.getResource("/images/Peg_White.PNG"));
	ImageIcon feedbackHole = new ImageIcon(Window.class.getResource("/images/Peg_Hole.PNG"));
		
	//set up Panels
	public JPanel holdingBox = new JPanel(new GridLayout(1,2)); //holds both pegBox and feedbackBox
	public JPanel marbleBox = new JPanel(new GridLayout(1,4));	//hold 4 peg buttons
	public JPanel feedbackBox = new JPanel(new GridLayout(2,2));	//holds 2x2 feedback icons
	
	//instantiate object arrays that will hold image icons and buttons
	public JButton[] marbles = new JButton[4];
	public JLabel[] feedbacks = new JLabel[4];
	
	//constructor
	public GuessSection()
	{
		for (JButton b : marbles)
		{
			b = new JButton();				//create a new button object
			b.setIcon(pegHole);			//make the image a blank peg
			b.setContentAreaFilled(false);	//clear the gradient and stroke from button
			b.setBorder(null);				//clear border from button
			marbleBox.add(b);				//add the button to marbleBox
		}
		for (JLabel f : feedbacks)
		{
			f = new JLabel();				//create a new JLabel object
			f.setIcon(feedbackHole); 		//set the image to a blank feedback
			feedbackBox.add(f);
		}
		holdingBox.add(marbleBox);
		holdingBox.add(feedbackBox);
	}

	public JPanel getHoldingBox()
	{
		return holdingBox;
	}
	
	public JButton[] getMarbleList()
	{
		return marbles;
	}
}
