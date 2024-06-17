package supermarket;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConveyorBelt<T> {

	private final int[] elements;
	private int amount, begin, end;
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	public ConveyorBelt(int size) {
		elements = new int[size];
		amount = 0;
		begin = 0;
		end = 0;
	}

	public void putIn(int item) throws InterruptedException {
		lock.lock();
		try {
			while (amount == elements.length) {
				notFull.await();
			}
			elements[end] = item;
			end = (end + 1) % elements.length;
			amount = amount + 1;
		} finally {
			lock.unlock();
		}
		
	}

	public int removeFrom() throws InterruptedException { // Assumes there is at least one element
		lock.lock();
		try {
			while(amount == 0){
				notEmpty.await();
			}
			int item = elements[begin];
			begin = (begin + 1) % elements.length;
			amount = amount - 1;
			return item;
		} finally {
			lock.unlock();
		}
		
	}
}
