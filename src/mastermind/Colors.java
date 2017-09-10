package mastermind;

public enum Colors {

	RED, YELLOW, BLUE, GREEN, WHITE, BLACK;
	
	public static Colors getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
