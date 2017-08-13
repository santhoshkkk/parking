package in.gojek.parking.consts;

public interface Commands {
	String CREATE = "create_parking_lot";
	String PARK = "park";
	String LEAVE = "leave";
	String STATUS = "status";
	String QUERY_VEHICLE_BY_COLOR = "registration_numbers_for_cars_with_colour";
	String QUERY_SLOT_BY_COLOR = "slot_numbers_for_cars_with_colour";
	String QUERY_SLOT_BY_NUMBER = "slot_number_for_registration_number";
}
