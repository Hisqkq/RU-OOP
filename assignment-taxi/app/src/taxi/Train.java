package taxi;

/**
 * Trains bring a number of passengers to a station in the Taxi simulation
 */
public class Train {
	private int nrOfPassengers;
	private final Station station;
	private int nrOfTrips = 0;

	public Train(Station station) {
		this.station = station;
		this.nrOfPassengers = 0;
	}

	/**
	 * Populate this train with number nrOfPassengers
	 *
	 * @param number the number of passengers of this train
	 */
	public void loadPassengers(int number) {
		nrOfPassengers = number;
	}

	/**
	 * empties this train and augment the number of Passengers at the station with
	 * this nrOfPassenegers
	 */
	public void unloadPassengers() {
		try{
			nrOfTrips += 1;
			station.enterStation(nrOfPassengers);
		}
		catch(InterruptedException ex){
			ex.printStackTrace();
		}
	}

	public void closeStation() {
		station.close();
		// try{
		// 	station.closeStation();
		// }
		// catch(InterruptedException ex){
		// 	ex.printStackTrace();
		// }
	}

	public int getNrOfTrips() {
		return nrOfTrips;
	}
}