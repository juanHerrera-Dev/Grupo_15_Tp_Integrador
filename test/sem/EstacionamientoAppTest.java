package sem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import java.time.LocalTime;


public class EstacionamientoAppTest {
	
	EstacionamientoApp estacionamiento;
	
	public LocalTime horaActual()
	{
		return LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
	}
	
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
		LocalTime tiempo = horaActual();
		estacionamiento.establecerHoraDeFinDeEstacionamiento();
		assertEquals(tiempo, estacionamiento.getHoraDeFinalizacion());
	}
	
	@Test
	public void EstacionamientoVigenteTest()
	{
		LocalTime horaFin = LocalTime.of(LocalTime.now().getHour() + 1, LocalTime.now().getMinute());
		estacionamiento.setHorarioMaximo(horaFin);
		
		assertEquals(true, estacionamiento.estacionamientoVigente());
		
		horaFin = LocalTime.of(LocalTime.now().getHour() - 1, LocalTime.now().getMinute());
		estacionamiento.setHorarioMaximo(horaFin);
		
		assertEquals(false, estacionamiento.estacionamientoVigente());
	}
	
	@Test
	public void finalizarEstacionamientoTest()
	{
		estacionamiento.finalizarEstacionamiento();
		LocalTime horaFin = horaActual();
		
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
