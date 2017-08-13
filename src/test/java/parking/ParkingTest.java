package parking;

import java.util.Collection;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import in.gojek.parking.FastParking;
import in.gojek.parking.Parking;
import in.gojek.parking.model.Slot;
import in.gojek.parking.model.Vehicle;

public class ParkingTest {
	@Test
	public void testCreate() {
		Parking p = new FastParking(10);
		Assert.assertNotNull(p);
	}

	@Test
	public void testEmpty() {
		Parking p = new FastParking(10);
		Assert.assertTrue(p.isEmpty());
	}

	@Test
	public void testFull() {
		Parking p = new FastParking(0);
		Assert.assertTrue(p.isFull());
	}

	@Test
	public void testPark() {
		Parking p = new FastParking(10);
		Vehicle v = new Vehicle("KA-01-HH-1234", "White");
		Slot slot = p.park(v);
		Assert.assertNotNull(slot);
		Assert.assertEquals(1, slot.getNumber());
	}

	@Test
	public void testLeave() {
		Parking p = new FastParking(10);
		Vehicle v = new Vehicle("KA-01-HH-1234", "White");
		Slot slot = p.park(v);
		Assert.assertNotNull(slot);
		Assert.assertEquals(1, slot.getNumber());
		p.leave(1);
		Assert.assertTrue(p.isEmpty());
	}

	@Test
	public void testNonEmptyStatus() {
		Parking p = new FastParking(10);
		Vehicle v1 = new Vehicle("KA-01-HH-1234", "White");
		Vehicle v2 = new Vehicle("KA-01-HH-1235", "Red");
		Vehicle v3 = new Vehicle("KA-01-HH-1236", "Yellow");
		Vehicle v4 = new Vehicle("KA-01-HH-1237", "White");
		p.park(v1);
		p.park(v2);
		p.park(v3);
		p.park(v4);
		p.leave(2);
		Map<Slot, Vehicle> status = p.status();

		Assert.assertNotNull(status);
		Assert.assertEquals(3, status.size());
		Assert.assertEquals("KA-01-HH-1234", status.get(new Slot(1)));
		Assert.assertEquals("KA-01-HH-1236", status.get(new Slot(3)));
		Assert.assertEquals("KA-01-HH-1237", status.get(new Slot(4)));
	}

	@Test
	public void testEmptyStatus() {
		Parking p = new FastParking(10);
		Map<Slot, Vehicle> status = p.status();
		Assert.assertNotNull(status);
		Assert.assertEquals(0, status.size());
	}

	@Test
	public void testQueryVehicleByColor() {
		Parking p = new FastParking(10);
		Vehicle v1 = new Vehicle("KA-01-HH-1234", "White");
		Vehicle v2 = new Vehicle("KA-01-HH-1235", "Red");
		Vehicle v3 = new Vehicle("KA-01-HH-1236", "Yellow");
		Vehicle v4 = new Vehicle("KA-01-HH-1237", "White");
		p.park(v1);
		p.park(v2);
		p.park(v3);
		p.park(v4);

		Collection<Vehicle> vehsByColor = p.getVehiclesByColor("White");
		Assert.assertNotNull(vehsByColor);
		Assert.assertEquals(2, vehsByColor.size());
		Assert.assertTrue(vehsByColor.contains(v1));
		Assert.assertTrue(vehsByColor.contains(v4));
	}

	@Test
	public void testQuerySlotByColor() {

		Parking p = new FastParking(10);
		Vehicle v1 = new Vehicle("KA-01-HH-1234", "White");
		Vehicle v2 = new Vehicle("KA-01-HH-1235", "Red");
		Vehicle v3 = new Vehicle("KA-01-HH-1236", "Yellow");
		Vehicle v4 = new Vehicle("KA-01-HH-1237", "White");
		Slot s1 = p.park(v1);
		p.park(v2);
		p.park(v3);
		Slot s4 = p.park(v4);

		Collection<Slot> slots = p.getSlotsByColor("White");
		Assert.assertNotNull(slots);
		Assert.assertEquals(2, slots.size());
		Assert.assertTrue(slots.contains(s1));
		Assert.assertTrue(slots.contains(s4));
	}

	@Test
	public void testQuerySlotByRegSuccess() {
		Parking p = new FastParking(10);
		Vehicle v1 = new Vehicle("KA-01-HH-1234", "White");
		Vehicle v2 = new Vehicle("KA-01-HH-1235", "Red");
		Slot slot1 = p.park(v1);
		p.park(v2);
		Slot slot = p.getSlot("KA-01-HH-1234");
		Assert.assertNotNull(slot);
		Assert.assertEquals(slot1, slot);
	}

	@Test
	public void testQuerySlotByRegFailure() {
		Parking p = new FastParking(10);
		Vehicle v1 = new Vehicle("KA-01-HH-1234", "White");
		Vehicle v2 = new Vehicle("KA-01-HH-1235", "Red");
		p.park(v1);
		p.park(v2);
		Slot slot = p.getSlot("KA-01-HH-1245");
		Assert.assertNull(slot);

	}

}
