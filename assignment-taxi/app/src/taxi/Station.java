package taxi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that holds the number of persons arriving by train at the station and
 * waiting for a taxi
 */
public class Station {
	private static int nrOfPassengersAtStation = 0;
	private int totalNrOfPassengers = 0;
	private static boolean isClosed = false;

	final Lock lock = new ReentrantLock();
	final Condition waitingPassengers = lock.newCondition();
	final Condition noPassengers = lock.newCondition();

	// put-method
	public void enterStation(int nrOfPassengers) throws InterruptedException{
		lock.lock();
		try{
			while(nrOfPassengersAtStation > 0) noPassengers.await();
			nrOfPassengersAtStation += nrOfPassengers;
			totalNrOfPassengers += nrOfPassengers;
			System.out.println(nrOfPassengers + " passengers arrived at station");
			waitingPassengers.signalAll();
		}
		finally{
			lock.unlock();
		}
	}

	// public void closeStation() throws InterruptedException{
	// 	lock.lock();
	// 	try{
	// 		while(nrOfPassengersAtStation > 0) noPassengers.await();
	// 		this.close();
	// 	}
	// 	finally{
	// 		lock.unlock();
	// 	}
	// }

	// take-method
	/**
	 * Ask for nrOfPassengers Passengers to leave the station
	 *
	 * @param requestedNrOfPassengers
	 * @return number of passengers actually leaving
	 */
	public int leaveStation(int requestedNrOfPassengers) throws InterruptedException{
		lock.lock();
		try{
			while(nrOfPassengersAtStation == 0) waitingPassengers.await();
			int actuallyLeaving = Math.min(requestedNrOfPassengers, nrOfPassengersAtStation);
			nrOfPassengersAtStation -= actuallyLeaving;
			noPassengers.signalAll();
			return actuallyLeaving;
		}
		finally{
			lock.unlock();
		}		
	}

	public static int waitingPassengers() {
		return nrOfPassengersAtStation;
	}

	public void close() {
		isClosed = true;
		System.out.println("Last train, station closed!");
	}

	public static boolean isClosed() {
		return isClosed;
	}

	public int getTotalNrOfPassengers() {
		return totalNrOfPassengers;
	}
}