package sem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.*;

public class EstacionamientoPorHorasTest {
	
	EstacionamientoPorHoras estacionamiento;
	LocalTime tiempo;
	
	@BeforeEach
	public void setUp()
	{
		
		estacionamiento = new EstacionamientoPorHoras("abc123", 101, 2);
		tiempo = LocalTime.of(LocalTime.now().getHour() + 2, LocalTime.now().getMinute());
	}
	
	@Test
	public void horaDeFinDeEstacionamientoTest()
	{
	
		assertEquals(tiempo, estacionamiento.getHoraDeFinalizacion());
		
	}
	
	@Test
	public void estacionamientoVigenteTest()
	{
		assertEquals(true, estacionamiento.estacionamientoVigente());
		
		estacionamiento = new EstacionamientoPorHoras("abc123", 101, -2);
		
		assertEquals(false, estacionamiento.estacionamientoVigente());
		
	}

}