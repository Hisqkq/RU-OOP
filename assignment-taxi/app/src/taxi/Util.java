package taxi;

/**
 * Utilities for taxi simulation:
 * - getRandomNumber: random number between bounds
 */
import java.util.Random;

public class Util {
	private static final Random GENERATOR = new Random();

	/**
	 * Generates a random number between from and to
	 * 
	 * @param from lower limit
	 * @param to   upper limit
	 * @return random number between from and to
	 */
	public static int getRandomNumber(int from, int to) {
		if (from == to) {
			return from;
		} else {
			return from + GENERATOR.nextInt(to - from);
		}
	}
}