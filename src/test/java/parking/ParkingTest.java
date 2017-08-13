package parking;

import org.junit.Assert;
import org.junit.Test;

import in.gojek.parking.FastParking;
import in.gojek.parking.Parking;

public class ParkingTest {
	@Test
	public void testCreate() {
		Parking p = new FastParking(10);
		Assert.assertTrue(p.isEmpty());
	}

}
