package mastermind;

public class CodeMaster {
	
	//One field declaration for answer.
	private Colors[] answer = {null, null, null, null};
	
	/*
	 * Constructor for CodeMaster... Currently just the default, no superclass implementation as of yet.
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
