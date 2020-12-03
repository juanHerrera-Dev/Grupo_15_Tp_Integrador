package estacionamiento;


import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;



import java.time.LocalTime;


public class EstacionamientoAppTest {
	
	EstacionamientoApp estacionamiento;
	
	
	
	@BeforeEach
	public void setUp()
	{
		estacionamiento = new EstacionamientoApp("aBc123", 100, 1566770101, 60);

	}
	
	
	@Test
	public void numeroDeCelularTest()
	{
		assertEquals(1566770101, estacionamiento.getNumeroDeCelular());
	}
	
	@Test
	public void establecerHoraDeFinDeEstacionamientoTest() 
	{
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			LocalTime tiempo = horaActual;
			estacionamiento.establecerHoraDeFinDeEstacionamiento();
			assertEquals(tiempo, estacionamiento.getHoraDeFinalizacion());
		}
	}
	
	@Test
	public void EstacionamientoVigenteTest()
	{
		
		 LocalTime horaActual = LocalTime.of(12,00);
			
			try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
				localTimeMock.when(LocalTime::now).thenReturn(horaActual);
				
				//Comprueba que el estacionamiento esta vigente
				
				LocalTime horaFin = LocalTime.of(13,00);
				estacionamiento.setHorarioMaximo(horaFin);
				
				assertEquals(true, estacionamiento.estacionamientoVigente(LocalTime.now()));
				
				//Comprueba que el estacionamiento no esta vigente
				horaFin = LocalTime.of(11, 00);
				estacionamiento.setHorarioMaximo(horaFin);
				
				assertEquals(false, estacionamiento.estacionamientoVigente(LocalTime.now()));
				
				
			} 

	}
	
	@Test
	//Este test verifica la hora de estaciomiento podria fallar si justo cambia la hora cuando se ejecutan los metodos.
	public void finalizarEstacionamientoTest()
	{
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
		
		
			estacionamiento.finalizarEstacionamiento();
			LocalTime horaFin = LocalTime.now();
		
			assertEquals(horaFin, estacionamiento.getHoraDeFinalizacion());
		}
	}
	
	@Test
	public void patenteTest()
	{
		
		
		
		 LocalTime horaActual = LocalTime.of(12,00);
			
			try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
				localTimeMock.when(LocalTime::now).thenReturn(horaActual);
				
				estacionamiento = new EstacionamientoApp("aBc123", 100, 1566770101, 60);
				
				assertEquals("abc123", estacionamiento.getPatente());

				
				
			} 
	}
	
	@Test
	void testSetPatenteVacia()
	{
		assertThrows(IllegalArgumentException.class, 
                () -> estacionamiento.setPatente(""));
	}
	
	@Test
	public void testCosto()
	{
		estacionamiento.setCostorPoHora(100);
		assertEquals(100, estacionamiento.getCostoPorHora());
	}
	
	@Test
	public void testSetInicioDeJornada()
	{
		LocalTime unaHora = LocalTime.of(12, 00);
		estacionamiento.setInicioDeJornada(unaHora);
		assertEquals(unaHora, estacionamiento.getInicioDeJornada());
	}
	
	@Test
	public void testSetFinDeJornada()
	{
		
		LocalTime unaHora = LocalTime.of(12, 00);
		estacionamiento.setFinDeJornada(unaHora);
		assertEquals(unaHora, estacionamiento.getFinDeJornada());
	}
	
	@Test
	public void dentroDelHorarioDeFuncionamiento()
	{
		
		 LocalTime horaActual = LocalTime.of(12,00);
			
			try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
				localTimeMock.when(LocalTime::now).thenReturn(horaActual);
				
				//Comprueba que esta dentro del horario
				assertTrue(estacionamiento.dentroDelHorarioDeFuncionamiento(horaActual));

				
				
			} 
			
			


	}
	
	@Test
	public void fueraDelHorarioDeFuncionamiento()
	{
		
		 LocalTime horaActual = LocalTime.of(21,00);
			
			try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
				localTimeMock.when(LocalTime::now).thenReturn(horaActual);
				
				
				assertEquals(false, estacionamiento.dentroDelHorarioDeFuncionamiento(horaActual));

			
			} 
			
	}
	
	@Test
	public void fueraDelHorarioDeFuncionamientoCaso2()
	{
		
		 LocalTime horaActual = LocalTime.of(6,00);
			
			try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
				localTimeMock.when(LocalTime::now).thenReturn(horaActual);
				
				
				assertEquals(false, estacionamiento.dentroDelHorarioDeFuncionamiento(horaActual));

			
			} 
			
	}
	
	@Test
	public void getHoraDeInicio()
	{
		
		 LocalTime horaActual = LocalTime.of(12,00);
			
			try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
				localTimeMock.when(LocalTime::now).thenReturn(horaActual);
				estacionamiento = new EstacionamientoApp("aBc123", 100, 1566770101, 60);
				
				assertEquals(horaActual, estacionamiento.getHoraDeInicio());

			
			} 
			
	}
	
	@Test
	public void actualizarHorarioMaximo()
	{
		
		 LocalTime horaActual = LocalTime.of(12,00);
			
			try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS))
			{
				localTimeMock.when(LocalTime::now).thenReturn(horaActual);
				
			
				estacionamiento = new EstacionamientoApp("aBc123", 100, 1566770101, 60);
				
				estacionamiento.actualizarHorarioMaximo(60);
				
				assertEquals(LocalTime.of(14, 00), estacionamiento.getHorarioMaximo());
				

			
			} 
			
	}

}
