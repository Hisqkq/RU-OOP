package taxi;

public class Simulation {

	/**
	 * Constants for the size of the simulation
	 */
	public static final int TRAIN_TRIPS = 10;
	public static final int MIN_TRAVELLERS = 60;
	public static final int MAX_TRAVELLERS = 90;
	public static final int CAPACITY_SMALL = 4;
	public static final int CAPACITY_LARGE = 7;
	public static final int TIME_SMALL = 2;
	public static final int TIME_LARGE = 3;
	public static final int NR_OF_TAXIS = 4;
	public static final int NR_OF_SMALL_TAXIS = 2;

	/**
	 * main elements of the simulation
	 */
	private final Taxi[] taxis;
	private final Train train;
	private final Station station;

	/**
	 * Constructor: create station and small and large taxis
	 */
	public Simulation() {
		station = new Station();
		taxis = new Taxi[NR_OF_TAXIS];
		for (int i = 0; i < NR_OF_TAXIS; i++) {
			taxis[i] = i < NR_OF_SMALL_TAXIS ? new Taxi(i + 1, CAPACITY_SMALL, TIME_SMALL, station)
					: new Taxi(i + 1, CAPACITY_LARGE, TIME_LARGE, station);
		}
		train = new Train(station);
	}

	public void start() {
		Thread[] threads = new Thread[NR_OF_TAXIS+1];
		for (int i = 0; i < NR_OF_TAXIS; i++){
			Thread thread = new Thread(new TaxiRunner(taxis[i]));
			threads[i] = thread;
			thread.start();
		}
		Thread thread = new Thread(new TrainRunner(train));
		threads[NR_OF_TAXIS] = thread;
		thread.start();
		
		for (Thread i : threads) {
            try {
                i.join();
            } catch (InterruptedException e) { }
        }
	}

	public void showStatistics() {
		System.out.println("All persons have been transported");
		System.out.println("Total transport time in this simulation:" + calcTotalTime());
		System.out.println("Total number of train travelers: " + getArrivedPassengerCount());
		System.out.println("Total number of persons transported in this simulation: " + getDepartedPassengerCount());
	}

	/**
	 * Calculates the total time of the simulation by looping over all taxis
	 *
	 * @return total time
	 */
	private int calcTotalTime() {
		int time = 0;
		for (Taxi taxi : taxis) {
			time += taxi.calcTotalTime();
		}
		return time;
	}

	/**
	 * Calculates the total number of passengers that has been transported by
	 * looping over all taxis
	 *
	 * @return total number of passengers
	 */
	public int getDepartedPassengerCount() {
		int total = 0;
		for (Taxi taxi : taxis) {
			total += taxi.getTotalNrOfPassengers();
		}
		return total;
	}

	public int getArrivedPassengerCount() {
		return station.getTotalNrOfPassengers();
	}
}
