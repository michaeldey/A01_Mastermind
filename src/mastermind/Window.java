package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener{
	
	//set up images
	ImageIcon marbleHole = new ImageIcon(Window.class.getResource("/images/Circle_Hole_843505.png"));
	ImageIcon marbleBlue = new ImageIcon(Window.class.getResource("/images/Circle_Blue.png"));
	ImageIcon marbleGreen = new ImageIcon(Window.class.getResource("/images/Circle_Green.png"));
	ImageIcon marbleRed = new ImageIcon(Window.class.getResource("/images/Circle_Red.png"));
	ImageIcon marbleWhite = new ImageIcon(Window.class.getResource("/images/Circle_White.png"));
	ImageIcon marbleYellow = new ImageIcon(Window.class.getResource("/images/Circle_Yellow.png"));
	ImageIcon marbleBlack = new ImageIcon(Window.class.getResource("/images/Circle_Black.png"));

	ActionListener listener;
	
	Container contentPane = getContentPane();			//overall window
	JPanel grid = new JPanel(new GridLayout(12,1)); 	//Holds Codemaster, 10 guesses, and userTools
	JLabel codeMaster = new JLabel("Code Master");		//Holds Codemaster object

	JPanel userTools =  new JPanel(new GridLayout(1,2)); //holds a grid 1 tall 2 wide
	ColorSelect colorSelect = new ColorSelect(listener);		//create a ColorSelect object
	JButton submitBtn = new JButton("Submit");					//submit button
	
	int currentSelectedColor = 0;
	
	JButton[][] newGuesses = new JButton[10][4]; //array of user guess buttons
	

	
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
		JPanel guessContainer = new JPanel(new GridLayout(10,4));
		for (int i = 0; i < newGuesses.length; i++)
		{
			for (int j = 0; j < newGuesses[0].length; j++)
			{
				newGuesses[i][j] = new JButton();
				guessContainer.add(newGuesses[i][j]);
				newGuesses[i][j].addActionListener(this);
				
				newGuesses[i][j].setIcon(getIcon(0));			//make the image a blank peg
				newGuesses[i][j].setContentAreaFilled(false);	//clear the gradient and stroke from button
				newGuesses[i][j].setBorder(null);				//clear border from button
			}
		}
		

		grid.add(guessContainer);
		
		
		
		
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
		
		if (event.getSource()==colorSelect.buttons[0]) currentSelectedColor=1;
		if (event.getSource()==colorSelect.buttons[1]) currentSelectedColor=2;
		if (event.getSource()==colorSelect.buttons[2]) currentSelectedColor=3;
		if (event.getSource()==colorSelect.buttons[3]) currentSelectedColor=4;
		if (event.getSource()==colorSelect.buttons[4]) currentSelectedColor=5;
		if (event.getSource()==colorSelect.buttons[5]) currentSelectedColor=6;
		if (event.getSource()==colorSelect.buttons[6]) currentSelectedColor=0;
		System.out.println(currentSelectedColor);
		for (int i = 0; i < newGuesses.length; i++)
		{
			for (int j = 0; j<newGuesses[0].length; j++)
			{
				if (event.getSource()==newGuesses[i][j])
					{
						newGuesses[i][j].setIcon(getIcon(currentSelectedColor));
					}
			}
		}


		
	}

	private ImageIcon getIcon(int current)
	{
		switch (current)
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

}//end of Window Class

