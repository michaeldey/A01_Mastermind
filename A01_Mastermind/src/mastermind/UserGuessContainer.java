package mastermind;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UserGuessContainer {
	Marble[] marbleArray = new Marble[4];	
	boolean active;
	
	public UserGuessContainer()
	{
		active = false;
		for (int i = 0; i < marbleArray.length; i++)
		{
			marbleArray[i]=new Marble();
		}
	}
	
	/**
	 * 
	 * @param selection is the selected marble in the array
	 * @return marble selected from the array
	 */
	public Marble getMarble(int selection)
	{
		return marbleArray[selection];
	}
	
	/**
	 * 
	 * @param selection is the selected marble in the array
	 * @return ImageIcon associated with the marble
	 */
	public ImageIcon getMarbleIcon(int selection)
	{
		return marbleArray[selection].getState();
	}
	
	/**
	 * 
	 * @param selection is the selected marble int he array
	 * @return JButton value of the marble associated with selection
	 */
	public JButton getMarbleButton(int selection)
	{
		return marbleArray[selection].getButton();
	}
	
	public void setActiveFalse()
	{
		active = false;
	}
}
