package mastermind;

import java.util.ArrayList;
import java.util.Arrays;

public class Feedback {
	//Field declarations.
	private int guessTurn = 1;
	private int blackCorrect = 0;
	private int whiteCorrect = 0;
	
	/*
	 * Getter for guessTurn.
	 */
		public int getGuessTurn() {
			return guessTurn;
		}
		
	/*
	 * Getter for blackCorrect.
	 */
	public int getBlackCorrect() {
		return blackCorrect;
	}
		
		
	/*
	 * Getter for whiteCorrect.
	 */
	public int getWhiteCorrect() {
		return whiteCorrect;
	}
	
	/*
	 * Increment guessTurn field. Called when we want to advance to the next turn.
	 */
	public void nextTurn(){
		this.guessTurn++;
	}
	
	
	/*
	 * Compare for black pegs method.
	 * @params guess, answer [two arrays of type Colors].
	 */
	public void blackPegNum(Colors[] guess, Colors[] answer){
		int blackCount = 0;
		for(int i = 0; i<=3; i++){
			if(guess[i] == answer[i]){
				blackCount++;
			}
		}
		this.guessTurn++;
		this.blackCorrect = blackCount;
			
	}
		
	/*
	 * Compare for white pegs; clone answer array into a temp Colors[],
	 * removes the ones we've checked as we go along the nested loop.
	 */
	public void whitePegNum(Colors[] guess, Colors[] answer){
		Colors[] temp = answer.clone();
		int whitePegs = 0;
			
		for(int i = 0; i<= 3; i++){
			for(int j = 0; j <= 3; j++){
				if(guess[i] == temp[j]){
					temp[j] = null;
					whitePegs++;
					break;
				}
			}
		}
			
		this.whiteCorrect = whitePegs - this.blackCorrect;
	}
	
	/**
	 * Method to give an array of Pegs with correct number of black/white pegs
	 */
	public Pegs[] getPegArray (int b, int w){
		Pegs[] pegArray = {};
		ArrayList<Pegs> pegList = new ArrayList<Pegs>(Arrays.asList(pegArray));
		for(int i = 0; i < b; i++){
			pegList.add(Pegs.BLACK);
		}
		
		for(int j = 0; j < w; j++){
			pegList.add(Pegs.WHITE);
		}
		
		while(pegList.size() < 4){
			pegList.add(Pegs.BLANK);
		}
		
		pegArray = pegList.toArray(new Pegs[pegList.size()]);
		return pegArray;
	}
	
}
