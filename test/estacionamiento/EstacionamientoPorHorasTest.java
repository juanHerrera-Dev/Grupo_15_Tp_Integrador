package estacionamiento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.*;

import estacionamiento.EstacionamientoPorHoras;




public class EstacionamientoPorHorasTest {
	


	EstacionamientoPorHoras estacionamiento;
	LocalTime tiempo;
	
	
	
	@BeforeEach
	public void setUp()
	{
		String patente = "abc123";
		int cantidadDeHorasCompradas = 1;
		int IDPuntoDeVenta = 101;
		
		estacionamiento = new EstacionamientoPorHoras(patente, IDPuntoDeVenta, cantidadDeHorasCompradas);
		tiempo = LocalTime.now().plusHours(cantidadDeHorasCompradas);
	}
	
	@Test
	public void horaDeFinDeEstacionamientoTest()
	{
	
		assertEquals(tiempo, estacionamiento.getHoraDeFinalizacion());
		
	}
	
	@Test
	public void estacionamientoVigenteTest()
	{
		//Estacionamiento  vigente
		assertEquals(true, estacionamiento.estacionamientoVigente(LocalTime.now()));
		
		//Estacionamiento vencido
		LocalTime hora = LocalTime.now().plusHours(1).plusMinutes(1);
		assertEquals(false, estacionamiento.estacionamientoVigente(hora));
		
	}

}