package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener, MouseMotionListener{
	
	CodeMaster master = new CodeMaster(); //set up the CodeMaster to drive the back end logic
	
	//set up images
	ImageIcon marbleHole = new ImageIcon(Window.class.getResource("/images/Circle_Hole_843505.png"));
	ImageIcon marbleBlue = new ImageIcon(Window.class.getResource("/images/Circle_Blue.png"));
	ImageIcon marbleGreen = new ImageIcon(Window.class.getResource("/images/Circle_Green.png"));
	ImageIcon marbleRed = new ImageIcon(Window.class.getResource("/images/Circle_Red.png"));
	ImageIcon marbleWhite = new ImageIcon(Window.class.getResource("/images/Circle_White.png"));
	ImageIcon marbleYellow = new ImageIcon(Window.class.getResource("/images/Circle_Yellow.png"));
	ImageIcon marbleBlack = new ImageIcon(Window.class.getResource("/images/Circle_Black.png"));	
	ImageIcon pegBlack = new ImageIcon(Window.class.getResource("/images/Peg_Black.png"));
	ImageIcon pegWhite = new ImageIcon(Window.class.getResource("/images/Peg_White.png"));
	ImageIcon pegHole = new ImageIcon(Window.class.getResource("/images/Peg_Hole.png"));

	//add the ActionListener object
	ActionListener listener;
	
	Container contentPane = getContentPane();				//overall window
	JPanel grid = new JPanel(); 							//Holds Codemaster, 10 guesses, and userTools
	JLabel computersGuess = new JLabel("Code Master");		//this section is to display the game answer at the end
	JPanel guessContainer = new JPanel(new GridLayout(10,4));
	JPanel[] pegContainer = new JPanel[10];
	JPanel userTools =  new JPanel(new GridLayout(1,2)); 	//holds a grid 1 tall 2 wide
	ColorSelect colorSelect = new ColorSelect(listener);	//create a ColorSelect object
	JButton submitBtn = new JButton("Submit");				//submit button
	
	int currentSelectedColor = 7;							//7 is the default for blank marbles
	
	JButton[][] marbleButtonArray = new JButton[10][4]; 	//array of user guess buttons (10 guesses x 4 marbles)
	JLabel[][] pegImages = new JLabel[10][4]; 				//individual feedback for guesses (10 guesses x 4 pegs)
	
	int[] userGuess = new int[4];			// int array that is sent to CodeMaster to check against random code
	int[] FeedbackReturn = new int[4]; 		// int array returned that represents the feedback pegs
	
	int guessTurn = 0;						//initialize the guesses at 0
	
	Colors[] ColorsGuess = new Colors[4];
	
	int mouseX, mouseY;					//this is used for mouse movement events
	JLabel mouseImage = new JLabel();	//this holds the image of the selected color
	
	

	public Window()
	{
		//set up the window
		super("MasterMind Game");
		setSize(500,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//add the mouse listener
		grid.addMouseMotionListener(this);
		mouseImage.setIcon(marbleRed);
		
		
		//************ Panel 0 Computer's Guess Section**************
		computersGuess.setFont(new Font("Tahoma", Font.BOLD, 18));
		computersGuess.setHorizontalAlignment(SwingConstants.CENTER);		
		grid.setBackground(new Color(255, 204, 153));
		grid.setLayout(new BorderLayout());
		
		grid.add("North", computersGuess);		
		
		
		/********Panels 1 - 10 GuessSection Objects***********
		*
		*	This section iterates through the marbleButtonArray and pegImages array
		*	and creates 4 buttons for each guess
		*	and creates 4 pegImages for each guess
		*	and places them into JPanel guessContainer in order.
		*/
		for (int i = 0; i < marbleButtonArray.length; i++)
		{
			for (int j = 0; j < marbleButtonArray[0].length; j++)
			{
				marbleButtonArray[i][j] = new JButton();				
				marbleButtonArray[i][j].addActionListener(this);				
				marbleButtonArray[i][j].setIcon(getIcon(0));			//make the image a blank peg
				marbleButtonArray[i][j].setContentAreaFilled(false);	//clear the gradient and stroke from button
				marbleButtonArray[i][j].setBorder(null);				//clear border from button
				guessContainer.add(marbleButtonArray[i][j]);
			}
			
			//add the pegContainer that holds 2x2 JLabel pegImages
			pegContainer[i] = new JPanel(new GridLayout(2,2)); //set up the 2x2 peg container
			for (int k = 0; k < pegImages[0].length; k++)
			{
				pegImages[i][k]=new JLabel();
				pegImages[i][k].setIcon(pegHole);
				pegContainer[i].add(pegImages[i][k]);
			}
			guessContainer.add(pegContainer[i]);
		}		

		grid.add("Center", guessContainer); //sends guessContainer to grid to be displayed in center panel
		
		
		/*********Panel 11 Color Selection and Submit Button******
		 *  This section propagates the tools at the bottom of the GUI that
		 *  the user uses to change color selection of the marbles and submit their
		 *  guess to the computer
		 *  
		 *  userTools is the JPanel container that holds the colored marbles and the submit button
		 *  colorSelect is an object that creates the colored marble buttons
		 */
		userTools.add(colorSelect.getColorGrid()); //get colored marble buttons
		
		//cycle through colorSelect buttons and add them to the ActionListener
		for (JButton x : colorSelect.buttons)
		{
			x.addActionListener(this);
		}		
		
		userTools.add(submitBtn);					//add the submit button
		submitBtn.addActionListener(this);			//add an actionListener for the button
		grid.add("South", userTools);				//add userTools to the grid
		
		
		contentPane.add("Center", grid);			//display grid in the center pane
		setVisible(true);							//make gui visible
		
	}//end of Window constructor
	
	/**
	 * This is the ActionEvent method that holds all the actions performed for all the buttons
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		//Submit button actions
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
		
		/**
		 * Color selection buttons
		 * If a colorSelect button has been pressed, change
		 * 'currentSelectedColor' to the int associated with that color
		 */
		if (event.getSource()==colorSelect.buttons[0]) currentSelectedColor=1;//blue	
		if (event.getSource()==colorSelect.buttons[1]) currentSelectedColor=2;//green
		if (event.getSource()==colorSelect.buttons[2]) currentSelectedColor=3;//red
		if (event.getSource()==colorSelect.buttons[3]) currentSelectedColor=4;//white
		if (event.getSource()==colorSelect.buttons[4]) currentSelectedColor=5;//yellow
		if (event.getSource()==colorSelect.buttons[5]) currentSelectedColor=6;//black
		if (event.getSource()==colorSelect.buttons[6]) currentSelectedColor=7;//blank

		/**
		 * Cycle through the marbleButtonArray buttons for individual buttons pressed
		 * When the button that has been pressed is found, change that button's image
		 * to be the one represented by 'currentSelectedColor'
		 */
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

		//print the information sent to 'master' for feedback
		//this is for troubleshooting and can be removed
		System.out.print("User array: ");
		for (int t : userGuess)
		{
			System.out.print(t + " ");
		}
		System.out.println();

		
	}

	/**
	 * This method sends information to the feedback object which in turn
	 * propagates the 'FeedbackReturn' array
	 */
	public void sendColorsGuess()
	{
//		System.out.print("Sending userGuess values");
		for (int i = 0; i < userGuess.length; i++)
		{
//			System.out.print(userGuess[i] + " ");
			ColorsGuess[i] = Colors.values()[userGuess[i]-1];
		}
//		System.out.println();
		for (int i = 0; i < ColorsGuess.length; i++)
		{
//			System.out.println(ColorsGuess[i]);
			//FeedbackReturn= Feedback.getPegArray(); //0 = blank, 1 = white, 2 = black
		}
	}
	
	/**
	 * 
	 * @param current represents the int value of the current selected color
	 * @return the ImageIcon associated with the current selected color
	 */
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouseImage.setLocation(mouseY, mouseY);
		
	}

}//end of Window Class