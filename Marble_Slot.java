package wip_A01_Mastermind;

public class Marble_Slot {

	private Colors[] guess = {null, null, null, null};
	
	//Constructor
	public Marble_Slot() {
		super();
		//FIXME: If we need a constructor for separate instances, we must initialize the field when object gets created 
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
