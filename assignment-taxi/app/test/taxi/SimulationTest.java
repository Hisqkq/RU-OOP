package taxi;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimulationTest {

	@Test
	public void runSimulation() {
		Simulation sim = new Simulation();
		sim.start();
		assertEquals(sim.getArrivedPassengerCount(), sim.getDepartedPassengerCount());
	}

}
