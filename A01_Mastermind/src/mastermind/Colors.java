/********************************************************
 *
 *  Project :  A01 Mastermind
 *  File    :  Colors.java
 *  
 *  Name    :  	Garret Rueckert
 *				Michael Dey
 *
 *  Date    :  September 16, 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		This is a game of Mastermind
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

public enum Colors {

	BLUE, GREEN, RED, WHITE, YELLOW, BLACK, BLANK;
	
	public static Colors getRandom() {
        return values()[(int) (Math.random() * values().length - 1)];
    }
}
