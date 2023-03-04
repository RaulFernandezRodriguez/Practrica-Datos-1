package ule.edi.parking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ule.edi.parking.vehicles.Vehicle;

public class Space {
	
	private Vehicle vehicle;
	
	private Date timeOfEntry;

	public Space(Date toe, Vehicle v) {
		setTimeOfEntry(toe);
		setVehicle(v);
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Date getTimeOfEntry() {
		return timeOfEntry;
	}

	public void setTimeOfEntry(Date timeOfEntry) {
		this.timeOfEntry = timeOfEntry;
	}

	//	formato con la zona horaria, e.g. "01/03/2017 10:50:56 +0100"
	private static final DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss Z");
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{ \"Vehículo\":" + vehicle.toString() + ", \"FechaDeEntrada\":\"" + format.format(timeOfEntry) + "\"}";
	}

	
}
