package ule.edi.parking;

import java.util.Date;

import ule.edi.parking.vehicles.Car;
import ule.edi.parking.vehicles.Caravan;
import ule.edi.parking.vehicles.Motorcycle;
import ule.edi.parking.vehicles.Vehicle;

/**
 * Implementa la interfaz parking.
 * 
 * Almacena las plazas de parking en un array de objetos. Las posiciones no
 * utilizadas son <code>null</code>, de forma que en el array pueden aparecer
 * "huecos" cuando se borran vehículos.
 * 
 * El constructor recibe como parámetro el número de plazas de parking. El
 * número de las plazas de aparcamiento empezará en 1.
 * 
 * @author profesor
 *
 */
public class ParkingArrayImpl implements Parking {
	
	private Space[] spaces;
	
	private double costPerMinute;

	public ParkingArrayImpl(int maxNumberOfSpaces, double costPerMinute) {
			this.spaces = new Space[maxNumberOfSpaces];
			this.costPerMinute = costPerMinute;
	}

	@Override
	public boolean addVehicleWithTimeOfEntry(Vehicle r, Date toe) {
		boolean added = false;
		boolean existing = false;
		int counter = 0;
		Space newVehicle = new Space(toe,r);
		if(spaces.length > 0){
			for(int i = 0; i < spaces.length; i++){
				if(spaces[i] != null && spaces[i].getVehicle().equals(r) && spaces[i].getTimeOfEntry().equals(toe))
				existing = true;
			}
			do{
				if(spaces[counter] == null){
				spaces[counter] = newVehicle;
				added = true;
				}
				counter++;
			} while(added != true && existing != true);
			}		
		return added;
	}	
			
	
	@Override
	public boolean addVehicle(Vehicle r) {
		Date someDate = new Date();
		return addVehicleWithTimeOfEntry(r, someDate);
	}
	
	@Override
	public int getUsedSpaces() {
		int count = 0;
		for(int i = 0; i < spaces.length; i++){
			if(spaces[i]!=null){
				count++;
			}
		}
		return count;
	}

	
	@Override
	public int getNumberOfSpaces() {
		return spaces.length;
	}

	
	@Override
	public int getAvailableSpaces() {
		return this.getNumberOfSpaces() - this.getUsedSpaces();
	}

	@Override
	public void removeVehicle(Vehicle r){
		if (spaces.length > 0){
 			for (int i=0; i < spaces.length;i++){
				if(spaces[i] != null){
					if (spaces[i].getVehicle().equals(r)){
					spaces[i] = null;
					}
				}
			}
		}
	}

	@Override
	public int getNumberOfCars() {
		int numOfCars = 0;
		if(spaces.length != 0)
		for(int i = 0; i < spaces.length; i++){
			if((spaces[i] != null) && (spaces[i].getVehicle().getClass() == Car.class)){
			numOfCars++;
			}
		}
		return numOfCars;
	}

	@Override
	public int getNumberOfMotorcycles() {
		int numOfMoto = 0;
		if(spaces.length > 0)
		for(int i = 0; i < spaces.length; i++){
			if((spaces[i] != null) && (spaces[i].getVehicle().getClass() == Motorcycle.class)){
			numOfMoto++;
			}
		}
		return numOfMoto;
	}

	@Override
	public int getNumberOfCaravans() {
		int numOfCaravan = 0;
		if(spaces.length > 0)
		for(int i = 0; i < spaces.length; i++){
			if((spaces[i] != null) && (spaces[i].getVehicle().getClass() == Caravan.class)){
			numOfCaravan++;
			}
		}
		return numOfCaravan;
	}

	/**
	 * Devuelve una representación en texto del objeto.
	 * 
	 * Aquí se ha decidido usar un formato similar a
	 * <a href="http://www.json.org/">JSON</a>.
	 */
	@Override
	public String toString() {

		// Indicado para construir el resultado añadiendo varias cadenas
		StringBuffer buffer = new StringBuffer();

		// Las comillas dobles hay que protegerlas con \, para que no sean
		// interpretadas como el final del literal.
		buffer.append("{\"Aparcamiento\":[");
		for (int i = 0; i < spaces.length; i++) {

			if (spaces[i] != null) {
				// A su vez usa el toString() que definan las clases de Vehicle
				buffer.append(spaces[i].toString());
				buffer.append(", ");
			} else {
				buffer.append("null, ");
			}
		}
		// Quita la última coma añadida
		if (spaces.length > 0) {
			buffer.delete(buffer.length() - 2, buffer.length());
		}
		buffer.append("]}");

		return buffer.toString();
	}


	@Override
	public double getCurrentAmount() {
		Date current = new Date();
		return this.getAmountWithReferenceDate(current);
	}


	@Override
	public double getAmountWithReferenceDate(Date reference) {
		double cost = 0;
		for(int i = 0; i < spaces.length; i++){
			if(spaces[i] != null){
			cost = cost + (reference.getTime() - spaces[i].getTimeOfEntry().getTime());
			}
		}
		cost = (cost / 60000) * costPerMinute;
		return cost;
	}


	@Override
	public double getCostPerMinute() {
		return costPerMinute;
	}	
}
