package sem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import java.time.LocalTime;


public class EstacionamientoAppTest {
	
	EstacionamientoApp estacionamiento;
	
	
	
	@BeforeEach
	public void setUp()
	{
		estacionamiento = new EstacionamientoApp("aBc123", 100, 1566770101);

		
	}
	
	
	@Test
	public void numeroDeCelularTest()
	{
		assertEquals(1566770101, estacionamiento.getNumeroDeCelular());
	}
	
	@Test
	public void establecerHoraDeFinDeEstacionamientoTest() 
	{
		LocalTime tiempo = LocalTime.now();
		estacionamiento.establecerHoraDeFinDeEstacionamiento();
		assertEquals(tiempo, estacionamiento.getHoraDeFinalizacion());
	}
	
	@Test
	public void EstacionamientoVigenteTest()
	{
		
		//Comprueba que el estacionamiento esta vigente
		LocalTime horaFin = LocalTime.of(LocalTime.now().getHour() + 1, LocalTime.now().getMinute());
		estacionamiento.setHorarioMaximo(horaFin);
		
		assertEquals(true, estacionamiento.estacionamientoVigente(LocalTime.now()));
		
		//Comprueba que el estacionamiento no esta vigente
		horaFin = LocalTime.of(LocalTime.now().getHour() - 1, LocalTime.now().getMinute());
		estacionamiento.setHorarioMaximo(horaFin);
		
		assertEquals(false, estacionamiento.estacionamientoVigente(LocalTime.now()));
	}
	
	@Test
	//Este test verifica la hora de estaciomiento podria fallar si justo cambia la hora cuando se ejecutan los metodos.
	public void finalizarEstacionamientoTest()
	{
		estacionamiento.finalizarEstacionamiento();
		LocalTime horaFin = LocalTime.now();
		
		assertEquals(horaFin, estacionamiento.getHoraDeFinalizacion());
	}
	
	@Test
	public void patenteTest()
	{
		assertEquals("abc123", estacionamiento.getPatente());
	}
	
	@Test
	//como pruebo esto?
	public void patenteVacioTest()
	{
		EstacionamientoApp est = new EstacionamientoApp("", 100, 1566770101);
		
	}
}
