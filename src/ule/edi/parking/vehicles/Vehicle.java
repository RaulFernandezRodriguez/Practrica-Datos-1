package ule.edi.parking.vehicles;

/**
 * Dos vehículos se considerarán iguales si son del mismo tipo y 
 * su matrícula es la misma.
 * 
 * @author profesor
 *
 */
public interface Vehicle {
	
	/**
	 * Devuelve la matrícula de este vehículo.
	 * 
	 * @return matrícula de este vehículo.
	 */
	String getRegistration();

}
