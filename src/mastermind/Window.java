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
	JPanel grid = new JPanel(new GridLayout(3,1)); 	//Holds Codemaster, 10 guesses, and userTools
	JLabel codeMaster = new JLabel("Code Master");		//Holds Codemaster object
	JPanel guessContainer = new JPanel(new GridLayout(10,4));
	JPanel userTools =  new JPanel(new GridLayout(1,2)); //holds a grid 1 tall 2 wide
	ColorSelect colorSelect = new ColorSelect(listener);		//create a ColorSelect object
	JButton submitBtn = new JButton("Submit");					//submit button
	
	int currentSelectedColor = 7;
	
	JButton[][] marbleButtonArray = new JButton[10][4]; //array of user guess buttons
	int[] userGuess = new int[4];
	
	int guessTurn = 0;
	
	Marble[][] userMarbleGuess = new Marble[10][4]; //individual marble guesses (10 guesses x 4 marbles)
	
	Colors[] ColorsGuess = new Colors[4];
	

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
		guessContainer.setSize(1000,1000);

		for (int i = 0; i < marbleButtonArray.length; i++)
		{
			for (int j = 0; j < marbleButtonArray[0].length; j++)
			{
				marbleButtonArray[i][j] = new JButton();
				
				marbleButtonArray[i][j].addActionListener(this);
				
				marbleButtonArray[i][j].setIcon(getIcon(0));			//make the image a blank peg
				marbleButtonArray[i][j].setContentAreaFilled(false);	//clear the gradient and stroke from button
				marbleButtonArray[i][j].setBorder(null);				//clear border from button
				marbleButtonArray[i][j].setPreferredSize(new Dimension (400, 400));
				guessContainer.add(marbleButtonArray[i][j]);
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
		if (event.getSource()==submitBtn)
			{	
				int count = 0;
				for (int i = 0; i < userGuess.length; i++)
				{
					if (userGuess[i]!=7&&userGuess[i]!=0) count++;
				}
				if (count == 4)
				{
					sendColorsGuess();
					guessTurn++;
					
					//reset userGuess[]
					for (int i = 0; i < userGuess.length; i++)
					{
						userGuess[i] = 7;
					}
				}
			}
		
		if (event.getSource()==colorSelect.buttons[0]) currentSelectedColor=1;
		if (event.getSource()==colorSelect.buttons[1]) currentSelectedColor=2;
		if (event.getSource()==colorSelect.buttons[2]) currentSelectedColor=3;
		if (event.getSource()==colorSelect.buttons[3]) currentSelectedColor=4;
		if (event.getSource()==colorSelect.buttons[4]) currentSelectedColor=5;
		if (event.getSource()==colorSelect.buttons[5]) currentSelectedColor=6;
		if (event.getSource()==colorSelect.buttons[6]) currentSelectedColor=7;//blank

		for (int i = 0; i < marbleButtonArray.length; i++)
		{
			for (int j = 0; j<marbleButtonArray[0].length; j++)
			{
				if (event.getSource()==marbleButtonArray[i][j])
					{
						if (i == guessTurn)
						{
							marbleButtonArray[i][j].setIcon(getIcon(currentSelectedColor));
							userGuess[j] = currentSelectedColor;
						}
						
					}
			}
		}

		System.out.print("User array: ");
		for (int t : userGuess)
		{
			System.out.print(t + " ");
		}
		System.out.println();

		
	}

	public void sendColorsGuess()
	{
		System.out.print("Sending userGuess values");
		for (int i = 0; i < userGuess.length; i++)
		{
			System.out.print(userGuess[i] + " ");
			ColorsGuess[i] = Colors.values()[userGuess[i]-1];
		}
		System.out.println();
		for (int i = 0; i < ColorsGuess.length; i++)
		{
			System.out.println(ColorsGuess[i]);
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