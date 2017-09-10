package sandbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener{

	Container contentPane = getContentPane();			//overall window	
	JPanel grid = new JPanel(new GridLayout(12,1)); 	//Holds Codemaster, 10 guesses, and userTools
	JLabel codeMaster = new JLabel("Code Master");		//Holds Codemaster object
	GuessSection[] userGuesses = new GuessSection[10];	//Holds user guesses (10 GuessSection objects)
	JPanel userTools =  new JPanel(new GridLayout(1,2)); //holds a grid 1 tall 2 wide
	ColorSelect colorSelect = new ColorSelect();		//create a ColorSelect object
	JButton submitBtn = new JButton("Submit");					//submit button
	
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
		for (GuessSection m : userGuesses)
		{
			m = new GuessSection();
			grid.add(m.getHoldingBox());
		}		
		

		//*********Panel 11 Color Selection and Submit Button******
		userTools.add(colorSelect.getColorGrid());
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
			System.out.println("Submit was pressed");
		}
		if (event.getSource()==colorSelect.pegBlue)
		{
			System.out.println("pegBlue was pressed");
		}
	}


}//end of Window Class

