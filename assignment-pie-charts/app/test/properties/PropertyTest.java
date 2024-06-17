package properties;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PropertyTest {

	@Test
	public void identity() {
		SimpleIntegerProperty propIn = new SimpleIntegerProperty(10);
		SimpleIntegerProperty propOut = new SimpleIntegerProperty(42);
		
		// Add a property binding here.
		// propOut should be the same as propIn
		// BEGIN your code here
		propOut.bind(propIn);
		// END your code here
		
		assertEquals(10, propIn.get());
		assertEquals(10, propOut.get());
		propIn.set(1337);
		assertEquals(1337, propIn.get());
		assertEquals(1337, propOut.get());
	}
	
	
	@Test
	public void plusTwo() {
		SimpleIntegerProperty propIn = new SimpleIntegerProperty(10);
		SimpleIntegerProperty propOut = new SimpleIntegerProperty(42);
		
		// Add a property binding here.
		// propOut should be propIn + 2
		// BEGIN your code here
		propOut.bind(propIn.add(2));
		// END your code here

		assertEquals(10, propIn.get());
		assertEquals(12, propOut.get());
		propIn.set(1337);
		assertEquals(1337, propIn.get());
		assertEquals(1339, propOut.get());
	}
	

	@Test
	public void addTwoProperties() {
		SimpleIntegerProperty propIn1 = new SimpleIntegerProperty(5);
		SimpleIntegerProperty propIn2 = new SimpleIntegerProperty(7);
		SimpleIntegerProperty propOut = new SimpleIntegerProperty(42);
		
		// Add a property binding here.
		// propOut should be propIn1 + propIn2
		// BEGIN your code here
		propOut.bind(propIn1.add(propIn2));
		// END your code here

		assertEquals(12, propOut.get());
		propIn1.set(100);
		assertEquals(107, propOut.get());
		propIn2.set(-34);
		assertEquals(66, propOut.get());
	}

	@Test
	public void addThreeProperties() {
		SimpleIntegerProperty propIn1 = new SimpleIntegerProperty(5);
		SimpleIntegerProperty propIn2 = new SimpleIntegerProperty(7);
		SimpleIntegerProperty propIn3 = new SimpleIntegerProperty(3);
		SimpleIntegerProperty propOut = new SimpleIntegerProperty(42);
		SimpleIntegerProperty prop1plus2 = new SimpleIntegerProperty();
		
		// Add two property binding here.
		// prop1plus2 should be propIn1 + propIn2
		// propOut should be prop1plus2 + propIn3
		// BEGIN your code here
		prop1plus2.bind(propIn1.add(propIn2));
		propOut.bind(prop1plus2.add(propIn3));
		// END your code here

		assertEquals(12, prop1plus2.get());
		assertEquals(15, propOut.get());
		propIn3.set(100);
		assertEquals(12, prop1plus2.get());
		assertEquals(112, propOut.get());
		propIn2.set(-34);
		assertEquals(71, propOut.get());
	}

	@Test
	public void convertToString() {
		SimpleIntegerProperty propIn = new SimpleIntegerProperty(5);
		SimpleStringProperty propOut = new SimpleStringProperty("nothing to see here");
		
		// Add a property binding here.
		// propOut should be propIn as string
		// BEGIN your code here
		propOut.bind(propIn.asString());
		// END your code here

		assertEquals("5", propOut.get());
		propIn.set(100);
		assertEquals("100", propOut.get());
	}

	@Test
	public void convertToFormattedString() {
		SimpleDoubleProperty propIn = new SimpleDoubleProperty(12.000000001);
		SimpleStringProperty propOut = new SimpleStringProperty("nothing to see here");
		
		// Add a property binding here.
		// propOut should be the a formatted string using propIn
		// BEGIN your code here
		propOut.setValue(String.format("foo %.2f bar", propIn.get()).replace(",", "."));
		propIn.addListener((obs, oldValue, newValue) -> {
			propOut.setValue(String.format("foo %.2f bar", newValue).replace(",", "."));
		});
		
		// END your code here

		assertEquals("foo 12.00 bar", propOut.get());
		propIn.set(100.5555555555);
		assertEquals("foo 100.56 bar", propOut.get());
	}

}
