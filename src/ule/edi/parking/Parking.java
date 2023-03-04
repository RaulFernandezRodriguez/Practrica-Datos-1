package ule.edi.parking;

import java.util.Date;

import ule.edi.parking.vehicles.Vehicle;


public interface Parking {
	
	/**
	 * Marca la llegada de un vehículo al aparcamiento.
	 * 
	 * Guarda el vehículo y su fecha de entrada. Si ya existe
	 * en el aparcamiento una posición ocupada por otro vehículo igual al
	 * dado, no se hace nada.
     *
	 * @param r vehículo que llega al aparcamiento.
	 * @return cierto si se guardó con éxito el vehículo en una nueva posición; false en caso contrario
	 */
	public boolean addVehicle(Vehicle  r);
	

	/**
	 * Marca la llegada en una fecha dada de un vehículo al aparcamiento.
	 * 
		 * Guarda el vehículo y su fecha de entrada (la fecha actual).  
	 * Se puede llamar al método anterior pasandole la fecha actual (new Date()) ). 
	 * Si ya existe en el aparcamiento una posición ocupada por otro vehículo igual al
	 * dado, no se hace nada.
	
	 * 
	 * @param r vehículo que llega al aparcamiento.
	 * @param toe fecha de llegada del vehículo.
	 * @return cierto si se guardó con éxito el vehículo en una nueva posición; false en caso contrario
	 */
	public boolean addVehicleWithTimeOfEntry(Vehicle r, Date toe);
	
	
	/**
	 * Indica el número de plazas de aparcamiento ocupadas.
	 * 
	 * @return número de plazas ocupadas.
	 */
	public int getUsedSpaces();
	
	/**
	 * Indica el número de plazas totales en este aparcamiento.
	 * 
	 * @return número total de plazas.
	 */
	public int getNumberOfSpaces();
	
	/**
	 * Indica el número de plazas de aparcamiento disponibles.
	 * 
	 * @return número de plazas disponibles.
	 */
	public int getAvailableSpaces();
	
	/**
	 * Elimina el vehículo dado del aparcamiento.
	 * 
	 * Elimina el vehículo del aparcamiento que sea igual al dado
	 * por parámetro.
	 * 
	 * @param r
	 */
	public void removeVehicle(Vehicle r);
	
	
	/**
	 * Devuelve el número de coches en el aparcamiento.
	 * 
	 * (El número de vehículos de tipo Car)
	 * 
	 * Se puede usar el método getClass para obtener el tipo del objeto que está en la posición i del array: spaces[i].getVehicle().getClass().equals(Car.class)
	 * 
	 * @return número de coches en el aparcamiento.
	 */
	public int getNumberOfCars();
	
	/**
	 * Devuelve el número de motos en el aparcamiento.
	 * 
	 * (El número de vehículos de tipo Motorcycle)
	 * 
	 * @return número de motos en el aparcamiento.
	 */
	public int getNumberOfMotorcycles();
	
	/**
	 * Devuelve el número de caravanas en el aparcamiento.
	 * 
	 * (El número de vehículos de tipo Caravan)
	 * 
	 * @return número de caravanas en el aparcamiento.
	 */
	public int getNumberOfCaravans();
		
	
	/**
	 * Devuelve el valor del aparcamiento para una fecha indicada.
	 * 
	 * Con la fecha dada como referencia, calcula cuánto tiempo ha estado
	 * cada vehículo en el aparcamiento. Con el total y el coste por minuto,
	 * calcula el valor del aparcamiento.
	 * 
	 * Si un vehículo entró en el aparcamiento después de la fecha dada, no
	 * se tiene en cuenta para el cálculo.
	 * 
	 * Los cálculos se realizarán apoyándose en {@link Date#getTime()}.
	 * 
	 * Date.getTime() :devuelve el número de milisegundos que han transcurrido desde 01/01/1970
	 * Restando 
	 * 
	 * @param reference fecha de referencia para el cálculo.
	 * @return valor del aparcamiento con esa referencia.
	 */
	public double getAmountWithReferenceDate(Date reference);
	
	/**
	 * Devuelve el valor actual del aparcamiento.
	 * 
	 * Con la fecha actual como referencia, calcula cuánto tiempo ha estado
	 * cada vehículo en el aparcamiento. Con el total y el coste por minuto,
	 * calcula el valor del aparcamiento.
	 * 
	 * Los cálculos se realizarán apoyándose en {@link Date#getTime()}.
	 * 
	 * @return valor actual del aparcamiento.
	 */
	public double getCurrentAmount();

	
	/**
	 * Indica el coste por minuto de este aparcamiento.
	 * 
	 * @return coste por minuto de este aparcamiento.
	 */
	public double getCostPerMinute();
 }
