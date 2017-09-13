package mastermind;

public enum Colors {

	BLUE, GREEN, RED, WHITE, YELLOW, BLACK, BLANK;
	
	public static Colors getRandom() {
        return values()[(int) (Math.random() * values().length - 1)];
    }
}
