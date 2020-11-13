package estacionamiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.LocalTime;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import estacionamiento.EstacionamientoPorHoras;




public class EstacionamientoPorHorasTest {
	


	EstacionamientoPorHoras estacionamiento;
	LocalTime tiempo;
	
	
	
	@BeforeEach
	public void setUp()
	{
		String patente = "abc123";
		LocalTime cantidadDeHorasCompradas = LocalTime.of(1,00);
		int IDPuntoDeVenta = 101;
		
		estacionamiento = new EstacionamientoPorHoras(patente, IDPuntoDeVenta, cantidadDeHorasCompradas);
		tiempo = LocalTime.now().plusHours(cantidadDeHorasCompradas.getHour());
	}
	
	@Test
	public void horaDeFinDeEstacionamientoTest()
	{
	
		assertEquals(tiempo, estacionamiento.getHoraDeFinalizacion());
		
	}
	
	@Test
	public void estacionamientoVigenteTest()
	{
		
		String patente = "abc123";
		LocalTime cantidadDeHorasCompradas = LocalTime.of(2,00);
		int IDPuntoDeVenta = 101;
		
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		LocalTime horaCuandoEstaVigente= LocalTime.of(13,00);
		
		LocalTime horaCuandoYaNoEstaVigente= LocalTime.of(18,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			estacionamiento = new EstacionamientoPorHoras(patente, IDPuntoDeVenta, cantidadDeHorasCompradas);
			
			//Estacionamiento  vigente
			assertEquals(true, estacionamiento.estacionamientoVigente(horaCuandoEstaVigente));
			
			//Estacionamiento vencido
			
			assertEquals(false, estacionamiento.estacionamientoVigente(horaCuandoYaNoEstaVigente));
			
		
		} 
		
		
	}

}