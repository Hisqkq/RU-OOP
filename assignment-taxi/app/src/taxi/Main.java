package taxi;

/**
 * main Class: create a SImulation and execute it.
 */
public class Main {
	public static void main(String[] args) {
		Simulation sim = new Simulation();
		sim.start();
		sim.showStatistics();
	}
}
