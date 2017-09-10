package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener{

	ActionListener listener;
	
	Container contentPane = getContentPane();			//overall window
	JPanel grid = new JPanel(new GridLayout(12,1)); 	//Holds Codemaster, 10 guesses, and userTools
	JLabel codeMaster = new JLabel("Code Master");		//Holds Codemaster object
	//GuessSection[] userGuesses = new GuessSection[10];	//Holds user guesses (10 GuessSection objects)
	JPanel guessSection = new JPanel(new GridLayout(10,1));	
	JPanel[] guessContainer = new JPanel[10];
	JPanel userTools =  new JPanel(new GridLayout(1,2)); //holds a grid 1 tall 2 wide
	ColorSelect colorSelect = new ColorSelect(listener);		//create a ColorSelect object
	JButton submitBtn = new JButton("Submit");					//submit button
	
//	JLabel woodBKG = new JLabel(new ImageIcon("/images/woodBKG.png"));
	
	Marble[][] userMarbleGuess = new Marble[10][4]; //individual marble guesses (10 guesses x 4 marbles)
	
	public Window()
	{
		super("MasterMind Game");
		setSize(700,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		//************ Panel 0 Code Master Object**************
		codeMaster.setFont(new Font("Tahoma", Font.BOLD, 18));
		codeMaster.setHorizontalAlignment(SwingConstants.CENTER);		
		grid.setBackground(new Color(255, 204, 153));
		
		grid.add(codeMaster);		
		
		
		//********Panels 1 - 10 GuessSection Objects***********
		
		//seed userGuesses with GuessSection Objects
//		for (GuessSection m : userGuesses)
//		{
//			m = new GuessSection();
//			JButton[] foo = m.getMarbles();
////			for (JButton f : foo)
////			{
////				grid.add(f);
////			}
//			
//		}
		
		/*
		 * Create User guess objects (marbles) 
		 * userMarbleGuess[10][4] holds 10 guesses x 4 marbles = 40 marbles
		 * guessContainer[10] holds 10 userMarbleGuess in sets of 4
		 * 
		 */
		for (int i = 0; i < userMarbleGuess.length; i++)
		{
			guessContainer[i] = new JPanel(new GridLayout(1,4));
			for (int j = 0; j < userMarbleGuess[0].length; j++)
			{
				userMarbleGuess[i][j] = new Marble();
				guessContainer[i].add(userMarbleGuess[i][j].getButton());
//				userMarbleGuess[i][j].getButton().addActionListener(this);
			}
			userMarbleGuess[0][0].getButton().addActionListener(this);
			grid.add(guessContainer[i]);
		}
				
		
		
		
		//*********Panel 11 Color Selection and Submit Button******
		userTools.add(colorSelect.getColorGrid());
		
		//cycle through colorSelect buttons and add them to the ActionListener
		for (JButton x : colorSelect.buttons)
		{
			x.addActionListener(this);
		}
		
		
		userTools.add(submitBtn);							//add the submit button
		submitBtn.addActionListener(this);					//add an actionListener for the button
		grid.add(userTools);								//add userTools to the grid
		
		
		contentPane.add("Center", grid);					//display grid in the center pane
		setVisible(true);									//make gui visible
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource()==submitBtn) System.out.println("Submit was pressed");		
		
		if (event.getSource()==colorSelect.buttons[0]) System.out.println("Blue");
		if (event.getSource()==colorSelect.buttons[1]) System.out.println("Green");
		if (event.getSource()==colorSelect.buttons[2]) System.out.println("Red");
		if (event.getSource()==colorSelect.buttons[3]) System.out.println("White");
		if (event.getSource()==colorSelect.buttons[4]) System.out.println("Yellow");
		if (event.getSource()==colorSelect.buttons[5]) System.out.println("Blank");
		if (event.getSource()==userMarbleGuess[0][0]) System.out.println("Guess 0,0");

//		for (int i = 0; i < userMarbleGuess.length; i++)
//		{
//			for (int j = 0; j < userMarbleGuess[0].length; j++)
//			{
//				if (event.getSource()==userMarbleGuess[i][j]) System.out.println("Guess " + i + ", " + " j");
//			}
//		}
		
		//System.out.println(event);
		
	}


}//end of Window Class
