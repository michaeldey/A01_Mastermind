package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener{
	
	//create necessary logic objects
	CodeMaster master = new CodeMaster();
	Colors[] answer = new Colors[4];			//this array will hold the CodeMaster answer
	User user = new User();
	Feedback check = new Feedback();
	
	//set up images
	IconControl imageController = new IconControl();

	//add the ActionListener object
	ActionListener listener;
	
	//set up GUI containers, labels, and buttons
	Container contentPane = getContentPane();			//overall window
	JPanel grid = new JPanel(); 						//Holds Codemaster, 10 guesses, and userTools
	JPanel codeMasterPanel = new JPanel();
	JLabel codeMasterText = new JLabel("Code Master");		//Holds Codemaster object
	JPanel answerHolder = new JPanel();
	JLabel[] answerImages = new JLabel[4];					//holds the images of the answer
	JLabel[] answerImagesBlank = new JLabel[4];				//holds purle blank images
	JPanel guessContainer = new JPanel(new GridLayout(10,4));
	JPanel[] pegContainer = new JPanel[10];
	JPanel userTools =  new JPanel(new GridLayout(1,2)); 		//holds a grid 1 tall 2 wide
	ColorSelect colorSelect = new ColorSelect(listener);		//create a ColorSelect object
	JButton submitBtn = new JButton("Submit");					//submit button
	JButton resetBtn = new JButton("Replay Game");
	JButton[][] marbleButtonArray = new JButton[10][4]; //array of user guess buttons
	JLabel[][] pegImages = new JLabel[10][4]; //individual feedback for guesses (10 guesses x 4 pegs)
	
	//set up arrays for talking with other classes
	Colors currentSelectedColor = null;
	Pegs[] FeedbackReturn = new Pegs[4];	
	Colors[] colorsToCheck = {null, null, null, null}; //temporary array to send to the checker
	Colors[] ColorsGuess = {null, null, null, null};
	
	private boolean resetGame=false;
	
	public Window()
	{
		super("MasterMind Game");
		setSize(700,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		grid.setBackground(new Color(255, 204, 153));
		grid.setLayout(new BorderLayout());
		
		master.setRandomAnswer();					//sets up the game for the answer
		answer = master.getAnswer();				//load the answer into answer[]		
		printAnswer();								//print the answer to console (for testing)

		
		//************ Panel 0 Code Master Object**************
		codeMasterText.setFont(new Font("Tahoma", Font.BOLD, 18));
		codeMasterText.setHorizontalAlignment(SwingConstants.CENTER);	
		
		setAnswerImages(false);
		codeMasterPanel.add(codeMasterText);
		codeMasterPanel.add(answerHolder);
		grid.add("North", codeMasterPanel);		
		
		
		
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
				marbleButtonArray[i][j].setIcon(imageController.getMarbleIcon(Colors.BLANK));	//make the image a blank peg
				marbleButtonArray[i][j].setContentAreaFilled(false);	//clear the gradient and stroke from button
				marbleButtonArray[i][j].setBorder(null);				//clear border from button
				guessContainer.add(marbleButtonArray[i][j]);
			}
			
			//add the pegContainer that holds 2x2 JLabel pegImages
			pegContainer[i] = new JPanel(new GridLayout(2,2)); //set up the 2x2 peg container
			for (int k = 0; k < pegImages[0].length; k++)
			{
				pegImages[i][k]=new JLabel();
				pegImages[i][k].setIcon(imageController.getPegIcon(Pegs.BLANK));
				pegContainer[i].add(pegImages[i][k]);
			}
			guessContainer.add(pegContainer[i]);
		}
		

		grid.add("Center", guessContainer);//sends guessContainer to grid to be displayed in center panel
		
		
		
		
		/*********Panel 11 Color Selection and Submit Button******
		 *  This section propagates the tools at the bottom of the GUI that
		 *  the user uses to change color selection of the marbles and submit their
		 *  guess to the computer
		 *  
		 *  userTools is the JPanel container that holds the colored marbles and the submit button
		 *  colorSelect is an object that creates the colored marble buttons
		 */
		userTools.add(colorSelect.getColorGrid());
		
		//cycle through colorSelect buttons and add them to the ActionListener
		for (JButton x : colorSelect.buttons)
		{
			x.addActionListener(this);
		}
		
		
		userTools.add(submitBtn);							//add the submit button
		submitBtn.addActionListener(this);					//add an actionListener for the submit button
		resetBtn.addActionListener(this);					//add an actionListener for the reset button
		grid.add("South", userTools);						//add userTools to the grid
		
		
		contentPane.add("Center", grid);					//display grid in the center pane
		setVisible(true);									//make gui visible
		
	} //end of Window Constructor
	
	private void setAnswerImages(boolean isGameOver) {
		
		if (isGameOver)
		{
			answerHolder.removeAll();
			for (int i = 0; i < answerImages.length; i++)
			{
				answerImages[i]=new JLabel();
				answerImages[i].setIcon(imageController.getMarbleIcon(answer[i])); //get the image for the appropriate colors object
				answerHolder.add(answerImages[i]);	//add the image to the JPanel answerHolder
			}
		}
		else
		{
			answerHolder.removeAll();
			for (int i = 0; i < answerImages.length; i++)
			{
				answerImages[i]=new JLabel();
				answerImages[i].setIcon(imageController.getMystery()); //get the image for the appropriate colors object
				answerHolder.add(answerImages[i]);	//add the image to the JPanel answerHolder
			}
		}
			
	}

	/**
	 * this method prints out the answer to the console
	 * it is for testing purposes
	 */
	private void printAnswer() {
		System.out.print("Answer: ");
		for (Colors c : answer)
		{
			System.out.print(c + " ");
		}
		System.out.println();
	}

	/**
	 * This is the ActionEvent method that holds all the actions performed for all the buttons
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		//Reset button action
		if (event.getSource()==resetBtn)
		{
			resetGame=true;
			this.dispose();
		} //set resetGame to true
		
		//Submit button actions
		if (event.getSource()==submitBtn){submitAction();}
		
		//Statements to set colorSelect.buttons value to the Colors value
		if (event.getSource()==colorSelect.buttons[0]) currentSelectedColor=Colors.BLUE;//blue	
		if (event.getSource()==colorSelect.buttons[1]) currentSelectedColor=Colors.GREEN;//green
		if (event.getSource()==colorSelect.buttons[2]) currentSelectedColor=Colors.RED;//red
		if (event.getSource()==colorSelect.buttons[3]) currentSelectedColor=Colors.WHITE;//white
		if (event.getSource()==colorSelect.buttons[4]) currentSelectedColor=Colors.YELLOW;//yellow
		if (event.getSource()==colorSelect.buttons[5]) currentSelectedColor=Colors.BLACK;//black
		if (event.getSource()==colorSelect.buttons[6]) currentSelectedColor=Colors.BLANK;//blank

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
						if (i == check.getGuessTurn())
						{
							marbleButtonArray[i][j].setIcon(imageController.getMarbleIcon(currentSelectedColor));
							colorsToCheck[j] = currentSelectedColor;
						}
						
					}
			}
		}	
	}//end of actionPerformed()
	
	
	/**
	 * This method sends information to the feedback object which in turn
	 * propagates the 'FeedbackReturn' array
	 * 
	 * this is the method to do the logic and fill the various arrays when submit is clicked
	 */
	public void sendColorsGuess()   
	{		
		ColorsGuess = colorsToCheck.clone(); //make a copy of the array
		
		user.setGuess(ColorsGuess); //send copied array to User object
		
		//if the game is NOT over propagate the peg icons
		if(!check.isGameOver()) 
		{
			check.blackPegNum(ColorsGuess, master.getAnswer());
			check.whitePegNum(ColorsGuess, master.getAnswer());
			
			//propagate FeedbackReturn[] with correct peg colors and numbers
			FeedbackReturn = check.getPegArray(check.getBlackCorrect(), check.getWhiteCorrect());
			
			setFeedbackPegIcons(FeedbackReturn); //set icon images based on FeedbackReturn array						
		}
		
		//win condition...
		if(check.isGameOver() && check.getBlackCorrect() == 4) 
		{
			//setFeedbackPegIcons(winPegs); //display their winning pegs
			codeMasterText.setText("YOU WIN!!!"); //changes header to let user know they've won
			codeMasterText.setFont(new Font("Tahoma", Font.BOLD, 20));
			setAnswerImages(true);
			userTools.remove(submitBtn);
			userTools.add(resetBtn);
		}
		//lose condition
		if(check.isGameOver() && check.getGuessTurn() == 9) {
			System.out.println("YOU LOSE :(");//temporary console output for coding purposes
			codeMasterText.setText("Sorry, you lose :(");
			codeMasterPanel.add(answerHolder);
			codeMasterText.setFont(new Font("Tahoma", Font.BOLD, 20));
			codeMasterText.setHorizontalAlignment(SwingConstants.LEFT);		
			codeMasterText.setForeground(Color.RED);
			FeedbackReturn = check.getPegArray(check.getBlackCorrect(), check.getWhiteCorrect()); //show last peg outcome
			//setFeedbackPegIcons(FeedbackReturn);
			setAnswerImages(true);
			userTools.remove(submitBtn);
			userTools.add(resetBtn);
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
			pegImages[check.getGuessTurn()][i].setIcon(imageController.getPegIcon(FBR[i])); //find the JPanel and sets the appropriate icon
		}
	}
	
	

	/**
	 *  submitAction() is called from the ActionListener method
	 *  This is called when the user hits "submit"
	 *  
	 *  It counts through the number of user-activated buttons selected
	 *  and validates that there are 4 activated for a specific turn
	 *  
	 *  It processes when there are 4 guesses selected
	 */
	private void submitAction()
	{
		int count = 0;							//int to count valid input slots
			
		//cycle through all 4 user guesses and check that there are no blank guesses
		for (int i = 0; i < colorsToCheck.length; i++)
		{					
			//count all the used slots in userGuessColors
			if (colorsToCheck[i] != null) count++;
		}
		if (count == 4)							//4 means all 4 slots are filled with valid guesses
		{
			sendColorsGuess();					//sends the guess to the Feedback object
			check.nextTurn(); 					//iterate the Feedback object "guessTurn" counter
				
				
			//reset userGuessColors[] by setting all slots to null values
			for (int i = 0; i < colorsToCheck.length; i++)
			{
				colorsToCheck[i] = null;
			}
		}
	}

	public boolean getResetGame()
	{
		System.out.println("getResetGame called. resetGame: " + resetGame);
		return resetGame;
	}
}//end of Window Class