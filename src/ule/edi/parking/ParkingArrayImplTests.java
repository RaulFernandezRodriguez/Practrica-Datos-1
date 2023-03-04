package ule.edi.parking;

import static org.junit.Assert.*;

import org.junit.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import ule.edi.parking.vehicles.Car;
import ule.edi.parking.vehicles.Motorcycle;

public class ParkingArrayImplTests {

	private DateFormat dformat = null;
	
	private Date parseLocalDate(String spec) throws ParseException {
        return dformat.parse(spec);
	}
	
	private Parking cheapParking;

	
	public ParkingArrayImplTests() {
		//	ayuda para las fechas
		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	
	@Before
	public void doSomethingBeforeEachTestMethod() {
		//	este método se ejecuta andes de cada método de test @Test
		//	antes de cada método de test, se crea un nuevo aparcamiento
		
		// crea un parking con 8 plazas y coste por minuto=0.01
		this.cheapParking = new ParkingArrayImpl(8, 0.01);
	}

		
	@Test
	public void testAlreadyParkedHere() throws Exception {
		
		//	ejemplo de método de prueba
		
		//	se crea un objeto sobre el que hacer pruebas :4 plazas y 1.0 de coste/minuto
		Parking p = new ParkingArrayImpl(4, 1.0);
		
		//	llega un coche con matrícula "A"; debe devolver true, porque no está en el aparcamiento
		Assert.assertTrue(p.addVehicle(new Car("A")));
		
		//	llega otra vez ese coche; debe devolver false, porque ya está en el aparcamiento
		Assert.assertFalse(p.addVehicle(new Car("A")));
	}

	@Test
	public void testCheapParking() throws Exception {
		
		//	ejemplo para trabajar con fechas
		
		Assert.assertTrue(cheapParking.addVehicleWithTimeOfEntry(new Motorcycle("A"), parseLocalDate("01/03/2017 16:50:00")));
		Assert.assertTrue(cheapParking.addVehicleWithTimeOfEntry(new Motorcycle("X"), parseLocalDate("01/03/2017 16:51:00")));

		//	a las 16:52:00 del mismo día, la moto A lleva 2' y la X 1', a 0.01 por minuto ; debe ser exáctamente 0.03
		Assert.assertEquals(cheapParking.getAmountWithReferenceDate(parseLocalDate("01/03/2017 16:52:00")), 0.02 + 0.01, 0.0);
	}
	
	@Test
	public void testAddingVehiclesWithTime() throws Exception{
		Car car = new Car("A");
		Date someDate = new Date();
		assertTrue(cheapParking.addVehicleWithTimeOfEntry(car,someDate));
	}

	@Test
	public void testNotAddingVehiclesWithTime() throws Exception{
		Car car = new Car("A");
		Date someDate = new Date();
		assertFalse(cheapParking.addVehicleWithTimeOfEntry(car,someDate));
	}

	@Test
	public void testAddingVehicle() throws Exception{
		Car car = new Car("A");
		assertTrue(cheapParking.addVehicle(car));
	}

	@Test
	public void testNotAddingVehicle() throws Exception{
		Car car = new Car("A");
		assertFalse(cheapParking.addVehicle(car));
	}

	@Test
	public void testNumberOfSpaces() throws Exception{
		assertEquals(8, cheapParking.getNumberOfSpaces());
	}

	@Test
	public void testAvaliableSpaces() throws Exception{
		assertEquals(8, cheapParking.getAvailableSpaces());
	}

	@Test
	public void testRemoveVehicle() throws Exception{
		Motorcycle moto = new Motorcycle("A");
		cheapParking.addVehicle(moto);
		cheapParking.removeVehicle(moto);
		assertEquals(null, cheapParking[0]);
	}

	@Test
	public void testNumberOfCars() throws Exception{
		assertEquals(0, cheapParking.getNumberOfCars());
	}

	@Test
	public void testNumberOfMotorcycles() throws Exception{
		assertEquals(0, cheapParking.getNumberOfMotorcycles());
	}

	@Test
	public void testNumberOfCaravans() throws Exception{
		assertEquals(0, cheapParking.getNumberOfCaravans());
	}

	@Test
	public void testCurrentAmountParking() throws Exception{
		Date actualDate = new Date();
		assertEquals(0, cheapParking.getAmountWithReferenceDate(actualDate));
	}

	@Test
	public void testCostWithDate() throws Exception{
		Date someDate = new Date();
		assertEquals(0, cheapParking.getAmountWithReferenceDate(someDate));
	}

	@Test
	public void testCostPerMinute() throws Exception{
		assertEquals(0.01 , cheapParking.getCostPerMinute());
	}
}