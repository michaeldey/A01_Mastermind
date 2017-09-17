/********************************************************
 *
 *  Project :  A01 Mastermind
 *  File    :  CodeMaster.java
 *  
 *  Name    :  	Garret Rueckert
 *				Michael Dey
 *
 *  Date    :  September 16, 2017
 *
 *  Description : (Narrative desciption, not code)
 *
 *    1) What is the purpose of the code; what problem does the code solve.
 *    		Class CodeMaster is the object that will generate a random code of a Colors array and return
 *	our "answer" as the same.
 *
 *    2) What data-structures are used.
 *    		Enum, Array, For Loops
 *
 *    3) What algorithms, techniques, etc. are used in implementing the data structures.
 *    		For Loops, Getters and Setters
 *
 *    4) What methods are implemented (optional).
 *		getAnswer, setRandomAnswer
 *
 *  Changes :  <Description|date of modifications>
 *
 ********************************************************/
package mastermind;

public class CodeMaster {
	
	//One field declaration for answer.
	private Colors[] answer = {null, null, null, null};
	
	/*
	 * Constructor for CodeMaster.
	 */
	public CodeMaster() {
		super();	
	}
	
	/*
	 * Getter for answer; returns the Colors array of the "code" to be broken.
	 */
	public Colors[] getAnswer() {
		return answer;
	}

	/*
	 * Sets a random answer for the "code." Uses method from Colors enum called getRandom. 
	 */
	public void setRandomAnswer() {
			for(int i = 0; i <= 3; i++){
				this.answer[i] = Colors.getRandom();
		}
	}

	
	
}
