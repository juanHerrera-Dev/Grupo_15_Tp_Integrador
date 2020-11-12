package sEM_Multa;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.*;

import sEM_Multa.Multa;

public class MultaTest {
	
	
	Multa multa;
	int IdMulta;
	int IdZonaDeEstacionamiento;
	LocalTime tiempo;
	LocalDate hoy;
	int IdInspector;
	
	
	//No puedo usar un beforeEach para poder tener contro sobre el id de la multa.

	void setUp()
	{
		IdMulta = 1;
		IdZonaDeEstacionamiento = 1;
		hoy = LocalDate.now();
		tiempo = LocalTime.now();
		IdInspector = 29000111;
		
		multa = new Multa( "abc123", IdZonaDeEstacionamiento,  LocalDate.now(), LocalTime.now(), IdInspector);


	}
	
	@Test
	void constructorTest()
	{
		setUp();
		assertEquals(1, multa.getIdMulta());
		assertEquals("abc123", multa.getPatente());
		assertEquals(IdZonaDeEstacionamiento, multa.getIdZonaDeEstacionamiento());
		assertEquals(hoy, multa.getFecha());
		assertEquals(tiempo, multa.getHora());
		assertEquals(IdInspector, multa.getIdInspector());
		assertEquals(true, multa.getVigencia());
		
	}
	 
	@Test
	void cancelarMultaTest()
	{
		setUp();
		assertEquals(true, multa.getVigencia());
		
		multa.cancelarMulta();
		assertEquals(false, multa.getVigencia());
	}
	
	@Test
	void generarIdTest()
	{
		Multa multa2 = new Multa( "abc100", IdZonaDeEstacionamiento,  LocalDate.now(), LocalTime.now(), IdInspector);
		Multa multa3 = new Multa( "abc111", IdZonaDeEstacionamiento,  LocalDate.now(), LocalTime.now(), IdInspector);
		Multa multa4 = new Multa( "abc101", IdZonaDeEstacionamiento,  LocalDate.now(), LocalTime.now(), IdInspector);
		
		assertEquals(2, multa2.getIdMulta());
		assertEquals(3, multa3.getIdMulta());
		assertEquals(4, multa4.getIdMulta());
	}

}
