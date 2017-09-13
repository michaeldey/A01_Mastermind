package mastermind;

import java.util.Scanner;

public class Application_Test {
	
	public static void main(String[] args){
		

		
		//create the objects
		CodeMaster maestro = new CodeMaster();
		Marble_Slot marbles = new Marble_Slot();
		Feedback check = new Feedback();
		
		//Set random answer on run
		maestro.setRandomAnswer();
		
		//Create Scanner
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		//do while loop to keep guesses going
		do{
			for (Colors c : maestro.getAnswer())
			{
				System.out.println(c);
			}
			
			
			//Prompt user
			System.out.println("Colors: RED, YELLOW, BLUE, GREEN, WHITE, BLACK");
			System.out.print("Enter your 4-color guess[" + check.getGuessTurn() + "]: ");
		
			//user guess null... could be put into the object instead...
			Colors[] userGuess = {null, null, null, null};
		
			//assign user input to the userGuess array
			for(int i = 0; i <= 3; i++){
				if(input.hasNextLine()){
					userGuess[i] = Colors.valueOf(input.next());
				}
			}
		
			//set the Marble_Slot object to our userGuess variable
			marbles.setGuess(userGuess);
		
			//print out your guess
			for(Colors el : marbles.getGuess()){
			System.out.println(el);
			}
	
			//as of now, must do blackPegNum first
			check.blackPegNum(marbles.getGuess(), maestro.getAnswer());
			check.whitePegNum(marbles.getGuess(), maestro.getAnswer());
			
			
			
			
			System.out.println("White Pegs: " + check.getWhiteCorrect());
			System.out.println("Black Pegs: " + check.getBlackCorrect());
			System.out.println();
			
		}while(check.getGuessTurn() < 11 && check.getBlackCorrect()!= 4);
		
		
		if(check.getGuessTurn() > 10 && check.getBlackCorrect() != 4){
			System.out.println("You lose :(");
			System.out.println("ANSWER: ");
			for(Colors el : maestro.getAnswer()){
				System.out.println(el);
			}
		}
		
		if(check.getBlackCorrect() == 4){
			System.out.println("You win!");
			System.out.println("ANSWER: ");
			for(Colors el : maestro.getAnswer()){
				System.out.println(el);
			
			}
			
		
		
		}
	}
}
