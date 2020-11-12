package sem_usuario;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoApp;
import estacionamiento.EstacionamientoPorHoras;
import estacionamiento.SEM_Estacionamiento;

import java.time.LocalTime;

public class SEM_EstacionamientoTest {
	
	SEM_Estacionamiento semEst;
	
	@BeforeEach
	void setUp()
	{
		semEst = new SEM_Estacionamiento();
	}
	
	
	@Test
	void testGuardarEstacionamientoYCantidadDeEstacionamientos()
	{
		Estacionamiento est1 = mock(Estacionamiento.class);
		Estacionamiento est2 = mock(Estacionamiento.class);
		Estacionamiento est3 = mock(Estacionamiento.class);
		
		assertEquals(0, semEst.cantidadDeEstacionamientos());
		
		semEst.guardarEstacionamiento(est1);
		assertEquals(1, semEst.cantidadDeEstacionamientos());
		
		semEst.guardarEstacionamiento(est2);
		assertEquals(2, semEst.cantidadDeEstacionamientos());
		
		semEst.guardarEstacionamiento(est3);
		assertEquals(3, semEst.cantidadDeEstacionamientos());
	}
	
	@Test
	void testFinalizarEstacionamientos()
	{
		EstacionamientoApp est1 = mock(EstacionamientoApp.class);
		EstacionamientoApp est2 = mock(EstacionamientoApp.class);
		EstacionamientoPorHoras est3 = mock(EstacionamientoPorHoras.class);
		EstacionamientoPorHoras est4 = mock(EstacionamientoPorHoras.class);
		
		semEst.guardarEstacionamiento(est1);
		semEst.guardarEstacionamiento(est2);
		semEst.guardarEstacionamiento(est3);
		semEst.guardarEstacionamiento(est4);
		
		semEst.finalizarEstacionamientos();
		
		verify(est1).finalizarEstacionamiento();
		verify(est2).finalizarEstacionamiento();
		verify(est3).finalizarEstacionamiento();
		verify(est4).finalizarEstacionamiento();
	}

	@Test
	void testConsultarEstacionamiento()
	{
		EstacionamientoApp est1 = mock(EstacionamientoApp.class);
		EstacionamientoApp est2 = mock(EstacionamientoApp.class);
		EstacionamientoPorHoras est3 = mock(EstacionamientoPorHoras.class);
		EstacionamientoPorHoras est4 = mock(EstacionamientoPorHoras.class);
		LocalTime time = LocalTime.of(12,45);
		
		when(est1.getPatente()).thenReturn("abc001");
		when(est2.getPatente()).thenReturn("abc002");
		when(est3.getPatente()).thenReturn("abc003");
		when(est4.getPatente()).thenReturn("abc004");
		
		semEst.consultarEstacionamiento("abc004", time);
		
		verify(est4).estacionamientoVigente(time);
	}
	
	

}
