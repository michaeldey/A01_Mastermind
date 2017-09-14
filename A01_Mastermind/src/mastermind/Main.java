package mastermind;

public class Main {

	public static void main(String[] args) {
		
		Window window = new Window();
		window.master.setRandomAnswer();
		
//		for(Colors el : window.master.getAnswer()){
//			System.out.println(el);
//		}
			
		
		//Don't want user array updated until submit is actually clicked...
		while(!window.check.isGameOver()) {
			//as of now, must do blackPegNum first
			window.check.blackPegNum(window.user.getGuess(), window.master.getAnswer());
			window.check.whitePegNum(window.user.getGuess(), window.master.getAnswer());	
		}
				
//		System.out.println(window.check.getBlackCorrect());		
				
	}

}