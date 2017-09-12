package mastermind;

public class Main {

	public static void main(String[] args) {
		Window window = new Window();
		window.master.setRandomAnswer();
		int turn = 0;
		
		
		//this whole block is either running infinitely or not at all.
		
//		if(turn >= window.check.getGuessTurn());
//		{
//			do
//			{
//				//as of now, must do blackPegNum first
//				window.check.blackPegNum(window.user.getGuess(), window.master.getAnswer());
//				window.check.whitePegNum(window.user.getGuess(), window.master.getAnswer());
//				
//				for(Colors el : window.master.getAnswer()){
//					System.out.println(el);
//				}
//				
//				System.out.println("Black: " + window.check.getBlackCorrect());
//				System.out.println("White: " + window.check.getWhiteCorrect());
//				turn++;
//			}while(window.check.getGuessTurn() < 9 && window.check.getBlackCorrect() != 4);
//			
//		}	
	}

}
