package supermarket;

import java.util.Random;
import java.util.concurrent.Callable;

public class Customer implements Callable<Integer> {

	public static final int MAX_ITEMS = 20;
	private final Store store;
	private final int customerNumber;
	private final int numberOfItemsWanted;
	private final static Random GENERATOR = new Random();
	
	public int getNumberOfItemsWanted() {
		return numberOfItemsWanted;
	}

	public Customer(int number, Store store) {
		this.store = store;
		customerNumber = number;
		numberOfItemsWanted = GENERATOR.nextInt(MAX_ITEMS) + 1;
	}

	@Override
	public Integer call() {
		int numberOfItemsBought = 0;
		return numberOfItemsBought;
	}
}
