package ule.edi.parking.vehicles;

import java.util.Objects;


public class Car implements Vehicle {
	
	private String registration;

	public Car(String currentRegistration) {
		registration = currentRegistration;
	}

	@Override
	public int hashCode() {
		return Objects.hash("Car", registration);
	}

	@Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        	if(obj instanceof Car) {
            	Car car = (Car)obj;
        	    return(this.getRegistration().equals(car.getRegistration()));
        	}
        return false;
    }

	@Override
	public String getRegistration() {
		return registration;
	}

	@Override
	public String toString() {
		return "{\"Tipo\":\"Car\", \"Matrícula\":\"" + registration + "\"}";
	}

}
