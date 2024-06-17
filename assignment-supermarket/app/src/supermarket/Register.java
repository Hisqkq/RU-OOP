package supermarket;

public class Register {

	// Make sure that CONVEYOR_SIZE + BIN_SIZE >= Customer.MAX_ITEMS, otherwise
	// danger of deadlock
	private static final int CONVEYER_SIZE = 10, BIN_SIZE = 10;

	public void putOnBelt(Item article) {
	}

	public Item removeFromBelt() {
		return null;
	}

	public void putInBin(Item article) {
	}

	public Item removeFromBin() {
		return null;
	}

	public void claim() {
	}

	public void free() {
	}
}
