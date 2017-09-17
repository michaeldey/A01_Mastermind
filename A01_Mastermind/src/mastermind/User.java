/********************************************************
 *
 *  Project :  A01 Mastermind
 *  File    :  User.java
 *  
 *  Name    :  	Garret Rueckert
 *				Michael Dey
 *
 *  Date    :  September 16, 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		Class User is the object that will hold the user's guess as an array of enum Colors.
 *	It has a simple getter and setter for the one field guess.
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

public class User {

	private Colors[] guess = {null, null, null, null};
	
	//Constructor
	public User() {
		super();
	}

	//getter for guess
	public Colors[] getGuess() {
		return guess;
	}

	//setter for guess
	public void setGuess(Colors[] guess) {
		this.guess = guess;
	}
	
	
}
