package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener, MouseMotionListener{
	
	//create necessary logic objects
	CodeMaster master = new CodeMaster();
	Marble_Slot user = new Marble_Slot();
	Feedback check = new Feedback();
	
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
	
	Container contentPane = getContentPane();			//overall window
	JPanel grid = new JPanel(); 	//Holds Codemaster, 10 guesses, and userTools
	JLabel codeMaster = new JLabel("Code Master");		//Holds Codemaster object
	JPanel guessContainer = new JPanel(new GridLayout(10,4));
	JPanel[] pegContainer = new JPanel[10];
	JPanel userTools =  new JPanel(new GridLayout(1,2)); //holds a grid 1 tall 2 wide
	ColorSelect colorSelect = new ColorSelect(listener);		//create a ColorSelect object
	JButton submitBtn = new JButton("Submit");					//submit button
	
	Colors currentSelectedColor = null;
	
	JButton[][] marbleButtonArray = new JButton[10][4]; //array of user guess buttons
	JLabel[][] pegImages = new JLabel[10][4]; //individual feedback for guesses (10 guesses x 4 pegs)
	
	//int[] ColorsGuess = new int[4];
	Pegs[] FeedbackReturn = new Pegs[4];
	Pegs[] winPegs = {Pegs.BLACK,Pegs.BLACK,Pegs.BLACK,Pegs.BLACK,}; //Garret
	boolean stop = false;
	
	Colors[] tempColors = {null, null, null, null};
	Colors[] ColorsGuess = {null, null, null, null};
	
	int mouseX, mouseY;
	JLabel mouseImage = new JLabel();

	public Window()
	{
		super("MasterMind Game");
		setSize(700,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//add the mouse listener
		grid.addMouseMotionListener(this);
		mouseImage.setIcon(marbleRed);
		
		
		//************ Panel 0 Code Master Object**************
		codeMaster.setFont(new Font("Tahoma", Font.BOLD, 18));
		codeMaster.setHorizontalAlignment(SwingConstants.CENTER);		
		grid.setBackground(new Color(255, 204, 153));
		grid.setLayout(new BorderLayout());
		
		grid.add("North", codeMaster);		
		
		
		
		//********Panels 1 - 10 GuessSection Objects***********

		for (int i = 0; i < marbleButtonArray.length; i++)
		{
			for (int j = 0; j < marbleButtonArray[0].length; j++)
			{
				marbleButtonArray[i][j] = new JButton();				
				marbleButtonArray[i][j].addActionListener(this);				
				marbleButtonArray[i][j].setIcon(getIcon(Colors.BLANK));			//make the image a blank peg
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
		

		grid.add("Center", guessContainer);
		
		
		
		
		//*********Panel 11 Color Selection and Submit Button******
		userTools.add(colorSelect.getColorGrid());
		
		//cycle through colorSelect buttons and add them to the ActionListener
		for (JButton x : colorSelect.buttons)
		{
			x.addActionListener(this);
		}
		
		
		userTools.add(submitBtn);							//add the submit button
		submitBtn.addActionListener(this);					//add an actionListener for the button
		grid.add("South", userTools);								//add userTools to the grid
		
		
		contentPane.add("Center", grid);					//display grid in the center pane
		setVisible(true);									//make gui visible
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource()==submitBtn)
			{	
				
				int count = 0;
				for (int i = 0; i < tempColors.length; i++)
				{
					if (tempColors[i] != null) count++;
				}
				if (count == 4)
				{
					sendColorsGuess();
					check.nextTurn();
					
					
					//reset userGuess[]
					for (int i = 0; i < tempColors.length; i++)
					{
						tempColors[i] = null;
					}
				}
			}
		
		//Statements to set colorSelect.buttons value to the Colors value
		if (event.getSource()==colorSelect.buttons[0]) currentSelectedColor=Colors.BLUE;//blue	
		if (event.getSource()==colorSelect.buttons[1]) currentSelectedColor=Colors.GREEN;//green
		if (event.getSource()==colorSelect.buttons[2]) currentSelectedColor=Colors.RED;//red
		if (event.getSource()==colorSelect.buttons[3]) currentSelectedColor=Colors.WHITE;//white
		if (event.getSource()==colorSelect.buttons[4]) currentSelectedColor=Colors.YELLOW;//yellow
		if (event.getSource()==colorSelect.buttons[5]) currentSelectedColor=Colors.BLACK;//black
		if (event.getSource()==colorSelect.buttons[6]) currentSelectedColor=Colors.BLANK;//blank

		for (int i = 0; i < marbleButtonArray.length; i++)
		{
			for (int j = 0; j<marbleButtonArray[0].length; j++)
			{
				if (event.getSource()==marbleButtonArray[i][j])
					{
						if (i == check.getGuessTurn())
						{
							marbleButtonArray[i][j].setIcon(getIcon(currentSelectedColor));
							tempColors[j] = currentSelectedColor;
						}
						
					}
			}
		}

		System.out.print("Temp array: ");
		for (Colors t : tempColors)
		{
			System.out.print(t + " ");
		}
		System.out.println();

		
	}

	public void sendColorsGuess()   //essentially, this is the method to do when submit is clicked
	{
		System.out.println("Sending userGuess values");
		ColorsGuess = tempColors.clone();
		
		user.setGuess(ColorsGuess);
		System.out.println("Sent array:");
		for (int i = 0; i < ColorsGuess.length; i++)
		{
			System.out.println(ColorsGuess[i]);
		}
		if(!check.isGameOver()) 
		{
			//check.blackPegNum(ColorsGuess, master.getAnswer());
			//check.whitePegNum(ColorsGuess, master.getAnswer());
			
			FeedbackReturn = check.getPegArray(check.getBlackCorrect(), check.getWhiteCorrect());
			
			setFeedbackPegIcons(FeedbackReturn);
			
			for(Pegs el : FeedbackReturn) {
				System.out.println(el);
			}
			
		}
			//win condition...    winning on first turn is not changing the peg icons... temporary fix by having a "winPegs" array of all blacks
			else if(check.isGameOver() && check.getBlackCorrect() == 4) 
			{
				setFeedbackPegIcons(winPegs);
				System.out.println("YOU WIN!!!");//temporary console output for coding purposes
				codeMaster.setText("YOU WIN!!!");
				codeMaster.setFont(new Font("Tahoma", Font.BOLD, 20));
				codeMaster.setHorizontalAlignment(SwingConstants.LEFT);		
				codeMaster.setForeground(Color.GREEN);
			
			}
			//lose condition
			else if(check.isGameOver() && check.getGuessTurn() == 9) {
				System.out.println("YOU LOSE :(");//temporary console output for coding purposes
				codeMaster.setText("Sorry, you lose :(");
				codeMaster.setFont(new Font("Tahoma", Font.BOLD, 20));
				codeMaster.setHorizontalAlignment(SwingConstants.LEFT);		
				codeMaster.setForeground(Color.RED);
			}	
	}
	
	
	private ImageIcon getIcon(Colors currentSelectedColor2)
	{
		switch (currentSelectedColor2)
		{
		case BLUE:
			return marbleBlue;
		case GREEN:
			return marbleGreen;
		case RED:
			return marbleRed;
		case WHITE:
			return marbleWhite;
		case YELLOW:
			return marbleYellow;
		case BLACK:
			return marbleBlack;
		default:
			return marbleHole;
		}
	}

	/**
	 * 
	 * @param FBR is the FeedbackReturn[] propagated in sendColorsGuess()
	 * This method loops through the JPanels representing the current guess'
	 * feedback pegs and changes their icons to represent the proper feedback icons
	 */
	public void setFeedbackPegIcons(Pegs[] FBR)
	{
		for (int i = 0; i < pegImages[check.getGuessTurn()].length; i++) //loops through the 4 JPanels
		{
			pegImages[check.getGuessTurn()][i].setIcon(getPegIcon(FBR[i])); //find the JPanel and sets the appropriate icon
		}
	}
	
	
	/**
	 * 
	 * @param pegNum represents the int value of the current selected peg
	 * @return the ImageIcon associated with the current selected color
	 * 
	 * 0 = blank, 1 = white, 2 = black
	 */
	public ImageIcon getPegIcon(Pegs peg)
	{
		switch (peg)
		{
		case WHITE:
			return pegWhite;
		case BLACK:
			return pegBlack;
		default:
			return pegHole;
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