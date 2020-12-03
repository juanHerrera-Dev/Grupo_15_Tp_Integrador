package inspector;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.*;

import estacionamiento.ISemEstacionamiento;
import inspector.InspectorApp;
import sEM_Multa.ISemMulta;
import semPrincipal.ISemPrincipal;

public class InspectorAppTest {

	InspectorApp inspector;
	ISemEstacionamiento iestacionamiento;
	ISemMulta imulta;
	int idInspector;
	int idZonaDeEstacionamiento;
	ISemPrincipal semPrincipal;
	
	@BeforeEach
	void setUp()
	{
		semPrincipal= mock(ISemPrincipal.class);
		iestacionamiento = mock(ISemEstacionamiento.class);
		imulta = mock(ISemMulta.class);
		idInspector = 007;
		idZonaDeEstacionamiento = 55;
		
		when(semPrincipal.getSemEstacionamiento()).thenReturn(iestacionamiento);
		when(semPrincipal.getSemMultas()).thenReturn(imulta);
		
		inspector = new InspectorApp(idInspector, idZonaDeEstacionamiento, semPrincipal);
	}
	
	@Test
	void testConsultarEstacionamiento()
	{
		LocalTime tiempo = LocalTime.of(12, 45);
		inspector.consultarEstacionamiento("abc123", tiempo);
		
		verify(iestacionamiento).consultarEstacionamiento("abc123", tiempo);
	}
	
	@Test
	void testRegistrarMulta()
	{
		inspector.registrarMulta("abc123", idZonaDeEstacionamiento,idInspector);
		
		verify(imulta).registrarMulta("abc123", idZonaDeEstacionamiento, idInspector);
	}
	
	@Test
	void testGetIdInspector()
	{
		assertEquals(007, inspector.getIdInspector());
	}
	
	@Test
	void testGetIdZonaDeEstacionamiento()
	{
		assertEquals(55, inspector.getIdZonaDeEstacionamiento());
	}
}
