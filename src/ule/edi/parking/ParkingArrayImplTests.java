package ule.edi.parking;

import static org.junit.Assert.*;

import org.junit.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import ule.edi.parking.vehicles.Car;
import ule.edi.parking.vehicles.Motorcycle;
import ule.edi.parking.vehicles.Caravan;

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
		Assert.assertEquals( 0.02 + 0.01, 0.0, cheapParking.getAmountWithReferenceDate(parseLocalDate("01/03/2017 16:52:00")));
	}
	
	@Test
	public void testAddingVehicleWithTime() throws Exception{
		Date someDate = new Date();
		Car car = new Car("A");
		Motorcycle moto = new Motorcycle("B");
		Caravan caravan = new Caravan("C");
		assertTrue(cheapParking.addVehicleWithTimeOfEntry(car,someDate));
		assertTrue(cheapParking.addVehicleWithTimeOfEntry(moto,someDate));
		assertTrue(cheapParking.addVehicleWithTimeOfEntry(caravan,someDate));
	}

	@Test
	public void testNotAddingVehicleWithTime() throws Exception{
		Date someDate = new Date();
		Car car = new Car("A");
		Motorcycle moto = new Motorcycle("B");
		Caravan caravan = new Caravan("C");
		cheapParking.addVehicleWithTimeOfEntry(car, someDate);
		cheapParking.addVehicleWithTimeOfEntry(moto, someDate);
		cheapParking.addVehicleWithTimeOfEntry(caravan, someDate);
		assertFalse(cheapParking.addVehicleWithTimeOfEntry(car,someDate));
		assertFalse(cheapParking.addVehicleWithTimeOfEntry(moto,someDate));
		assertFalse(cheapParking.addVehicleWithTimeOfEntry(caravan,someDate));
	}

	@Test
	public void testAddingCarWithTime() throws Exception{
		Date someDate = new Date();
		Car car = new Car("A");
		assertTrue(cheapParking.addVehicleWithTimeOfEntry(car,someDate));
	}

	@Test
	public void testNotAddingCarWithTime() throws Exception{
		Date someDate = new Date();
		Car car = new Car("A");
		cheapParking.addVehicleWithTimeOfEntry(car, someDate);
		assertFalse(cheapParking.addVehicleWithTimeOfEntry(car,someDate));
	}

	@Test
	public void testAddingMotocycleWithTime() throws Exception{
		Date someDate = new Date();
		Motorcycle moto = new Motorcycle("B");
		assertTrue(cheapParking.addVehicleWithTimeOfEntry(moto,someDate));
	}

	@Test
	public void testNotAddingMotocycleWithTime() throws Exception{
		Date someDate = new Date();
		Motorcycle moto = new Motorcycle("B");
		cheapParking.addVehicleWithTimeOfEntry(moto, someDate);
		assertFalse(cheapParking.addVehicleWithTimeOfEntry(moto,someDate));
	}

	@Test
	public void testAddingCaravanWithTime() throws Exception{
		Date someDate = new Date();
		Caravan caravan = new Caravan("C");
		assertTrue(cheapParking.addVehicleWithTimeOfEntry(caravan,someDate));
	}

	@Test
	public void testNotAddingCaravanWithTime() throws Exception{
		Date someDate = new Date();
		Caravan caravan = new Caravan("C");
		cheapParking.addVehicleWithTimeOfEntry(caravan, someDate);
		assertFalse(cheapParking.addVehicleWithTimeOfEntry(caravan,someDate));
	}

	@Test
	public void testAddingVehicle() throws Exception{
		Car car = new Car("A");
		Motorcycle moto = new Motorcycle("B");
		Caravan caravan = new Caravan("C");
		assertTrue(cheapParking.addVehicle(car));
		assertTrue(cheapParking.addVehicle(moto));
		assertTrue(cheapParking.addVehicle(caravan));
	}

	@Test
	public void testNotAddingVehicle() throws Exception{
		Car car = new Car("A");
		Motorcycle moto = new Motorcycle("B");
		Caravan caravan = new Caravan("C");
		cheapParking.addVehicle(car);
		cheapParking.addVehicle(moto);
		cheapParking.addVehicle(caravan);
		assertFalse(cheapParking.addVehicle(car));
		assertFalse(cheapParking.addVehicle(moto));
		assertFalse(cheapParking.addVehicle(caravan));
	}

	@Test
	public void testAddingCar() throws Exception{
		Car car = new Car("A");
		assertTrue(cheapParking.addVehicle(car));
	}

	@Test
	public void testNotAddingCar() throws Exception{
		Car car = new Car("A");
		cheapParking.addVehicle(car);
		assertFalse(cheapParking.addVehicle(car));
	}

	@Test
	public void testAddingMotorcycle() throws Exception{
		Motorcycle moto = new Motorcycle("A");
		assertTrue(cheapParking.addVehicle(moto));
	}

	@Test
	public void testNotAddingMotorcycle() throws Exception{
		Motorcycle moto = new Motorcycle("A");
		cheapParking.addVehicle(moto);
		assertFalse(cheapParking.addVehicle(moto));
	}

	@Test
	public void testAddingCaravan() throws Exception{
		Caravan caravan = new Caravan("A");
		assertTrue(cheapParking.addVehicle(caravan));
	}

	@Test
	public void testNotAddingCaravan() throws Exception{
		Caravan caravan = new Caravan("A");
		cheapParking.addVehicle(caravan);
		assertFalse(cheapParking.addVehicle(caravan));
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
		assertEquals(0, cheapParking.getNumberOfMotorcycles());
	}

	@Test
	public void testNotRemoveVehicle() throws Exception{
		Motorcycle moto = new Motorcycle("A");
		Car car = new Car("A");
		cheapParking.addVehicle(car);
		cheapParking.addVehicle(moto);
		cheapParking.removeVehicle(car);
		assertEquals(1, cheapParking.getNumberOfMotorcycles());
	}

	@Test
	public void testNumberOfCars() throws Exception{
		Car car = new Car("A");
		cheapParking.addVehicle(car);
		assertEquals(1, cheapParking.getNumberOfCars());
	}

	@Test
	public void testNumberOfMotorcycles() throws Exception{
		Motorcycle moto = new Motorcycle("A");
		cheapParking.addVehicle(moto);
		assertEquals(1, cheapParking.getNumberOfMotorcycles());
	}

	@Test
	public void testNumberOfCaravans() throws Exception{
		Caravan caravan = new Caravan("A");
		cheapParking.addVehicle(caravan);
		assertEquals(1, cheapParking.getNumberOfCaravans());
	}

	@Test
	public void testGetAmountWithReferenceDate() throws Exception{
        Car car = new Car("A");
		cheapParking.addVehicleWithTimeOfEntry(car, parseLocalDate("01/03/2017 16:50:00"));
		assertEquals(0.02, cheapParking.getAmountWithReferenceDate(parseLocalDate("01/03/2017 16:52:00")), 0.00);
    }	
	
	@Test
	public void testNotGetAmountWithReferenceDate() throws Exception{
        Car car = new Car("A");
		cheapParking.addVehicleWithTimeOfEntry(car, parseLocalDate("01/03/2017 16:50:00"));
		assertNotEquals(0.04, cheapParking.getAmountWithReferenceDate(parseLocalDate("01/03/2017 16:52:00")), 0.00);
    }	

    @Test
    public void testGetCurrentAmount() throws Exception{
        assertEquals(this.cheapParking.getAmountWithReferenceDate(new Date()), this.cheapParking.getCurrentAmount(), 0.00);
    }

	@Test
	public void testCostPerMinute() throws Exception{
		assertEquals(0.01 , cheapParking.getCostPerMinute(), 0.00);
	}
}