package mastermind;

import javax.swing.ImageIcon;

public class IconControl {

	//ImageIcon fields
	private ImageIcon marbleHole = new ImageIcon(Window.class.getResource("/images/Circle_Hole_843505.png"));
	private ImageIcon marbleBlue = new ImageIcon(Window.class.getResource("/images/Circle_Blue.png"));
	private ImageIcon marbleGreen = new ImageIcon(Window.class.getResource("/images/Circle_Green.png"));
	private ImageIcon marbleRed = new ImageIcon(Window.class.getResource("/images/Circle_Red.png"));
	private ImageIcon marbleWhite = new ImageIcon(Window.class.getResource("/images/Circle_White.png"));
	private ImageIcon marbleYellow = new ImageIcon(Window.class.getResource("/images/Circle_Yellow.png"));
	private ImageIcon marbleBlack = new ImageIcon(Window.class.getResource("/images/Circle_Black.png"));	
	private ImageIcon pegBlack = new ImageIcon(Window.class.getResource("/images/Peg_Black.png"));
	private ImageIcon pegWhite = new ImageIcon(Window.class.getResource("/images/Peg_White.png"));
	private ImageIcon pegHole = new ImageIcon(Window.class.getResource("/images/Peg_Hole.png"));
	private ImageIcon mysteryMarble = new ImageIcon(Window.class.getResource("/images/Circle_Blank.png"));
	
	public IconControl() {
		
	}

	public ImageIcon getMarbleIcon(Colors c)
	{
		switch (c)
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
	
	public ImageIcon getMystery()
	{
		return mysteryMarble;
	}
	
	/**
	 * 
	 * @param pegNum represents the int value of the current selected peg
	 * @return the ImageIcon associated with the current selected color
	 * 
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
	
}
