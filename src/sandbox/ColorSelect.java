package sandbox;

import javax.swing.*;
import sandbox.Window;
import java.awt.*;
import java.awt.event.*;

public class ColorSelect extends JFrame implements ActionListener {

	//set up images
	ImageIcon pegBlue = new ImageIcon(Window.class.getResource("/sandbox/Circle_Blue.png"));
	ImageIcon pegGreen = new ImageIcon(Window.class.getResource("/sandbox/Circle_Green.png"));
	ImageIcon pegRed = new ImageIcon(Window.class.getResource("/sandbox/Circle_Red.png"));
	ImageIcon pegWhite = new ImageIcon(Window.class.getResource("/sandbox/Circle_White.png"));
	ImageIcon pegYellow = new ImageIcon(Window.class.getResource("/sandbox/Circle_Yellow.png"));
	ImageIcon pegHole = new ImageIcon(Window.class.getResource("/sandbox/Circle_Hole_843505.png"));
	ImageIcon[] images = new ImageIcon[]{pegBlue, pegGreen, pegRed, pegWhite, pegYellow, pegHole};
	
	private JPanel colorGrid = new JPanel(new GridLayout(1,7));	//new JPanel 6 wide, 1 tall
	private JButton[] buttons = new JButton[images.length];
	
	//constructor
	public ColorSelect()
	{

		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setIcon(images[i]);
			buttons[i].setContentAreaFilled(false);
			colorGrid.add(buttons[i]);
		}
	}
	
	public JPanel getColorGrid()
	{
		return colorGrid;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()==buttons[0])
		{
			System.out.println("Color 0 was pressed");
		}
			
		
	}
	
}
