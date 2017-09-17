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
 *    		Enum Colors is a enumeration for the possible colors that a marble could display.
 *	Blank is added at the end, but never included in the getRandom method's output. 
 *
 *    2) What data-structures are used.
 *    		Enum, random method from Java Math
 *
 *    3) What algorithms, techniques, etc. are used in implementing the data structures.
 *    		Java's Math.random, array length, casting
 *
 *    4) What methods are implemented (optional).
 *		Only method getRandom gets random values from BLUE, GREEN, RED, WHITE, YELLOW, BLACK as
 *	possibilities. Used to generate our random code for the player to guess.
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
