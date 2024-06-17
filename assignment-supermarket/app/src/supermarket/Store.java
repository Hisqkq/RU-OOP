package supermarket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Store {

	public static final int NUMBER_OF_CHECKOUTS = 5;

	private final List<Register> registers;
	private final ExecutorService executor;
	private final List<Cashier> cashiers;

	public Store(ExecutorService executor) {
		this.executor = executor;

		registers = IntStream.range(0, NUMBER_OF_CHECKOUTS)
				.mapToObj(i -> new Register())
				.collect(Collectors.toList());

		cashiers = registers.stream()
				.map(r -> new Cashier(r))
				.collect(Collectors.toList());
	}

	public List<Future<Void>> open() {
		return cashiers.stream().map(c -> executor.submit(c)).collect(Collectors.toList());
	}

	public List<Item> getItems(int amount) {
		return IntStream.range(0, amount)
				.mapToObj(i -> new Item(i))
				.collect(Collectors.toList());
	}

	public Register claimRegister(int registerNr) {
		return registers.get(registerNr);
	}
}
