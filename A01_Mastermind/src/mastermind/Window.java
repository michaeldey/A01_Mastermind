/********************************************************
 *
 *  Project :  A01 Mastermind
 *  File    :  Window.java
 *  
 *  Name    :  	Garret Rueckert
 *				Michael Dey
 *
 *  Date    :  September 16, 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		This is the GUI for the Mastermind game.
 *    		It interacts with the other classes in order to get
 *    		and send information of how to display the imagery
 *
 *    2) What data-structures are used.
 *    		Enum, Array, ActionListener, Swing GUI, List
 *
 *    3) What algorithms, techniques, etc. are used in implementing the data structures.
 *    		For Loops, Switch Statements, List
 *
 *    4) What methods are implemented (optional).
 *
 *  Changes :  <Description|date of modifications>
 *
 ********************************************************/
package mastermind;

import javax.swing.*;

import mastermind.Window;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener{
	
	/**create necessary logic objects*/
	CodeMaster master = new CodeMaster();				//class that runs the game
	Colors[] answer = new Colors[4];					//this array will hold the CodeMaster answer
	User user = new User();								//class that controls the user's guesses
	Feedback check = new Feedback();					//class that generates peg information
	IconControl imageController = new IconControl();	//class that generates the GUI images
	ActionListener listener;							//action Listener for buttons

	/**set up GUI containers, labels, and buttons*/
	Container contentPane = getContentPane();					//overall window
	JPanel grid = new JPanel(); 								//Holds Codemaster, 10 guesses, and userTools
	JPanel codeMasterPanel = new JPanel();						//Panel for the top 1/3 of the game board
	JLabel codeMasterText = new JLabel("Code Master");			//Displays text at the top
	JPanel answerHolder = new JPanel();							//displays answer images at the top
	JLabel[] answerImages = new JLabel[4];						//holds the images of the answer
	JPanel guessContainer = new JPanel(new GridLayout(10,4));	//Panel for the middle 1/3 of the game board
	JPanel[] pegContainer = new JPanel[10];						//panel for the 10 user guesses
	JButton[][] marbleButtonArray = new JButton[10][4]; 		//array of user guess buttons
	JLabel[][] pegImages = new JLabel[10][4]; 					//array of feedback peg images
	JPanel userTools =  new JPanel(new GridLayout(1,2)); 		//panel for the bottom 1/3 of the game board
	ColorSelect colorSelect = new ColorSelect(listener);		//create a ColorSelect object
	JButton submitBtn = new JButton("Submit");					//submit button
	
	/**set up arrays for talking with other classes*/
	Colors currentSelectedColor=Colors.BLANK;							//currentSelectedColor holds color for peg buttons
	Pegs[] FeedbackReturn = new Pegs[4];						//Pegs[] that is recieved from Feedback class	
	Colors[] colorsToCheck = {null, null, null, null}; 			//temporary array to send to the checker
	Colors[] ColorsGuess = {null, null, null, null};			//Colors[] that holds the current guess code
	
	//Window Constructor
	public Window()
	{
		/**Set up the content pane*/
		super("MasterMind Game");
		setSize(700,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		grid.setBackground(new Color(255, 204, 153));
		grid.setLayout(new BorderLayout());
		
		master.setRandomAnswer();					//sets up the game for the answer
		answer = master.getAnswer();				//load the answer into answer[]		
//		printAnswer();							//print the answer to console (for testing)

		
		/************ Panel 0 Code Master Object**************/
		generateCodeMasterSection();			//generate codeMasterPanel
		grid.add("North", codeMasterPanel);		//add codeMasterPanel to grid		
		
		
		/********Panels 1 - 10 GuessSection Objects***********/
		generateGuessContainer();			//generate the 40 buttons and feedback images into GuessContainer
		grid.add("Center", guessContainer);//sends guessContainer to grid to be displayed in center panel		
		
		
		/*********Panel 11 UserTools: Color Selection and Submit Button******/
		generateUserToolsContainer();
		grid.add("South", userTools);						//add userTools to the grid
		
		/**Add all of the panels to the content pane*/
		contentPane.add("Center", grid);					//display grid in the center pane
		setVisible(true);									//make gui visible
		
	} //end of Window Constructor

	/**
	*  This method propagates the tools at the bottom of the GUI that
	*  the user uses to change color selection of the marbles and submit their
	*  guess to the computer
	*  
	*  userTools is the JPanel container that holds the colored marbles and the submit button
	*  colorSelect is an object that creates the colored marble buttons
	*/
	private void generateUserToolsContainer() {
		userTools.add(colorSelect.getColorGrid());
		
		//cycle through colorSelect buttons and add them to the ActionListener
		for (JButton x : colorSelect.buttons)
		{
			x.addActionListener(this);
		}		
		
		userTools.add(submitBtn);							//add the submit button
		submitBtn.addActionListener(this);					//add an actionListener for the submit button
	}

	/**
	 * This method sets up the section where the feedback from Code master displays
	 */
	private void generateCodeMasterSection() {
		codeMasterText.setFont(new Font("Tahoma", Font.BOLD, 18));
		codeMasterText.setHorizontalAlignment(SwingConstants.CENTER);	
		
		setAnswerImages(false);
		codeMasterPanel.add(codeMasterText);
		codeMasterPanel.add(answerHolder);
	}

	/**
	*	This section iterates through the marbleButtonArray and pegImages array
	*	and creates 4 buttons for each guess
	*	and creates 4 pegImages for each guess
	*	and places them into JPanel guessContainer in order.
	*/
	private void generateGuessContainer() {
		//loop through each guess and generate buttons and images
		for (int i = 0; i < marbleButtonArray.length; i++)
		{
			//generate 4 buttons and add them to guessContainer
			for (int j = 0; j < marbleButtonArray[0].length; j++)
			{
				marbleButtonArray[i][j] = new JButton();				//create a new JButton				
				marbleButtonArray[i][j].addActionListener(this);		//add new button to actionListener
																		//make the image a blank peg
				marbleButtonArray[i][j].setIcon(imageController.getMarbleIcon(Colors.BLANK));	
				marbleButtonArray[i][j].setContentAreaFilled(false);	//clear the gradient and stroke from button
				marbleButtonArray[i][j].setBorder(null);				//clear border from button
				guessContainer.add(marbleButtonArray[i][j]);			//add new button to JPanel guessContainer
			}
			
			//add the pegContainer that holds 2x2 JLabel pegImages
			pegContainer[i] = new JPanel(new GridLayout(2,2)); 			//set up the 2x2 peg container
			//generate 4 peg image containers and place them in the guessContainer
			for (int k = 0; k < pegImages[0].length; k++)
			{
				pegImages[i][k]=new JLabel();							//make a new JLabel object
																		//add a "blank" peg image to the JLabel
				pegImages[i][k].setIcon(imageController.getPegIcon(Pegs.BLANK));
				pegContainer[i].add(pegImages[i][k]);					//add the JLabel to JPanel pegContainer
			}
			guessContainer.add(pegContainer[i]);						//add pegContainer to guessContainer
		}
	}
	
	/**
	 * 
	 * @param isGameOver is true if the user guessed correctly or if the 10th guess has been made
	 * 
	 * This method propagates the marble images at the top
	 * If the game is not over, the images show the Circle_Blank.png image
	 * If the game is over, the images are swapped out to show the answer from CodeMaster
	 */
	private void setAnswerImages(boolean isGameOver) {		
		if (isGameOver) //game is over
		{
			answerHolder.removeAll();
			for (int i = 0; i < answerImages.length; i++)
			{
				answerImages[i]=new JLabel();
				answerImages[i].setIcon(imageController.getMarbleIcon(answer[i])); //get the image for the appropriate colors object
				answerHolder.add(answerImages[i]);						//add the image to the JPanel answerHolder
			}
		}
		else	//game is not over
		{
//			answerHolder.removeAll();
			for (int i = 0; i < answerImages.length; i++)
			{
				answerImages[i]=new JLabel();
				answerImages[i].setIcon(imageController.getMystery()); 	//get the image for the appropriate colors object
				answerHolder.add(answerImages[i]);						//add the image to the JPanel answerHolder
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
	 * This method sends information to the feedback object which in turn
	 * propagates the 'FeedbackReturn' array
	 * 
	 * this is the method to do the logic and fill the various arrays when submit is clicked
	 */
	public void sendColorsGuess()   
	{		
		ColorsGuess = colorsToCheck.clone(); 					//make a copy of the array
		
		user.setGuess(ColorsGuess); 							//send copied array to User object
		
		//if the game is NOT over propagate the peg icons
		if(!check.isGameOver()) 
		{
			check.blackPegNum(ColorsGuess, master.getAnswer());	//set Feedback class to have correct black pegs
			check.whitePegNum(ColorsGuess, master.getAnswer());	//set Feedback class to have correct white pegs
			
			//propagate FeedbackReturn[] with correct peg colors and numbers
			FeedbackReturn = check.getPegArray(check.getBlackCorrect(), check.getWhiteCorrect());
			
			setFeedbackPegIcons(FeedbackReturn); 				//set icon images based on FeedbackReturn array						
		}
		
		//win condition...
		if(check.isGameOver() && check.getBlackCorrect() == 4) 
		{
			codeMasterText.setText("YOU WIN!!!"); 				//changes header to let user know they've won
			codeMasterText.setFont(new Font("Tahoma", Font.BOLD, 20));
			setAnswerImages(true);								//this boolean will change images at top
		}
		//lose/win condition for last guess
		if(check.getGuessTurn() == 9) {
			
			FeedbackReturn = check.getPegArray(check.getBlackCorrect(), check.getWhiteCorrect()); //show last peg outcome
			setAnswerImages(true);								//this boolean will change images at top
			setFeedbackPegIcons(FeedbackReturn);
			codeMasterPanel.add(answerHolder);
			
			if(check.getBlackCorrect() < 4) 
			{
			codeMasterText.setText("Sorry, you lose :(");
			codeMasterText.setFont(new Font("Tahoma", Font.BOLD, 20));
			codeMasterText.setHorizontalAlignment(SwingConstants.LEFT);		
			codeMasterText.setForeground(Color.RED);
			}
			if(check.getBlackCorrect() == 4) 
			{
				codeMasterText.setText("YOU WIN!!!"); 	//changes header to let user know they've won
				codeMasterText.setFont(new Font("Tahoma", Font.BOLD, 20));
			}
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

	/**
	 * This is the ActionEvent method that holds all the actions performed for all the buttons
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
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
}//end of Window Class
