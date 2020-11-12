package sEM_Multa;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

import estacionamiento.IEstacionamiento;
import sEM_Multa.Multa;
import sEM_Multa.SEM_Multa;


public class SEM_MultaTest {

	
	SEM_Multa semM;
	
	
	@BeforeEach
	void setUp()
	{
		IEstacionamiento estacionamiento = mock(IEstacionamiento.class);
		semM = new SEM_Multa(estacionamiento);
	}
	
	

	@Test 
	void agregarMultaTest ()
	{
		assertEquals(0, semM.cantidadDeMultas());
			

		Multa multa1 = mock(Multa.class);
		semM.agregarMulta(multa1);
		assertEquals(1, semM.cantidadDeMultas());
		
		Multa multa2 = mock(Multa.class);
		semM.agregarMulta(multa2);
		assertEquals(2, semM.cantidadDeMultas());
		
	}

	
	
	@Test
	void buscarMulta() 
	{
		
		
		//assertNull("La multa  no esta contenida", semM.buscarMulta(3));
		
		//Se agragan 3 multas para poder buscar alguna de ellas.
		Multa multa1 = mock(Multa.class);
		Multa multa2 = mock(Multa.class);
		Multa multa3 = mock(Multa.class);
		
		when(multa1.getIdMulta()).thenReturn(1);
		when(multa2.getIdMulta()).thenReturn(2);
		when(multa3.getIdMulta()).thenReturn(3);
		
		semM.agregarMulta(multa1);
		semM.agregarMulta(multa2);
		semM.agregarMulta(multa3);
		
		assertSame(multa3, semM.buscarMulta(3));
		
	}
	
	
	@Test
	public void buscarMultasTest()
	{
		
		
		Multa multa1 = mock(Multa.class);
		Multa multa2 = mock(Multa.class);
		Multa multa3 = mock(Multa.class);
		Multa multa4 = mock(Multa.class);
		Multa multa5 = mock(Multa.class);
		
		
		assertEquals(0, semM.buscarMultas("abc123").size());
		
		semM.agregarMulta(multa1);
		semM.agregarMulta(multa2);
		semM.agregarMulta(multa3);
		semM.agregarMulta(multa4);
		semM.agregarMulta(multa5);
		
		when(multa1.getPatente()).thenReturn("dfs120");
		when(multa2.getPatente()).thenReturn("fsh561");
		when(multa3.getPatente()).thenReturn("adf828");
		when(multa4.getPatente()).thenReturn("abc123");
		when(multa5.getPatente()).thenReturn("abc123");
		
		assertEquals(2, semM.buscarMultas("abc123").size());
				
	}
	
	@Test
	void cancelarMultaTest()
	{
		Multa multa1 = mock(Multa.class);
		Multa multa2 = mock(Multa.class);
		Multa multa3 = mock(Multa.class);
		 
		when(multa1.getIdMulta()).thenReturn(1);
		when(multa2.getIdMulta()).thenReturn(2);
		when(multa3.getIdMulta()).thenReturn(3);
		
		semM.agregarMulta(multa1);
		semM.agregarMulta(multa2);
		semM.agregarMulta(multa3);
		
		//Se cancela 1 de las mulas
		semM.cancelarMulta(3);
		
		verify(multa1, never()).cancelarMulta();
		verify(multa2, never()).cancelarMulta();
		verify(multa3).cancelarMulta();
		

		
		
		
	}
	
	@Test
	void cancelarMultaInexistenteTest()
	{
		Multa multa1 = mock(Multa.class);
		Multa multa2 = mock(Multa.class);
		Multa multa3 = mock(Multa.class);
		 
		when(multa1.getIdMulta()).thenReturn(1);
		when(multa2.getIdMulta()).thenReturn(2);
		when(multa3.getIdMulta()).thenReturn(3);
		
		semM.agregarMulta(multa1);
		semM.agregarMulta(multa2);
		semM.agregarMulta(multa3);
		
		//Se cancela una multa inexistente
		semM.cancelarMulta(4);
		
		assertNull(semM.buscarMulta(4));
		verify(multa1, never()).cancelarMulta();
		verify(multa2, never()).cancelarMulta();
		verify(multa3, never()).cancelarMulta();
	}
	
	@Test
	void multaVigenteTest()
	{
		Multa multa1 = mock(Multa.class);
		Multa multa2 = mock(Multa.class);

		when(multa1.getIdMulta()).thenReturn(1);
		when(multa2.getIdMulta()).thenReturn(2);
		 
		
		semM.agregarMulta(multa1);
		semM.agregarMulta(multa2);
		
		semM.multaVigente(2);
		
		verify(multa1, never()).getVigencia();
		verify(multa2).getVigencia();
		
	
	}
	
	
 

	
	@Test
	void cantidadDeMultasTest()
	{
		Multa multa1 = mock(Multa.class);
		Multa multa2 = mock(Multa.class);
		
		assertEquals(0, semM.cantidadDeMultas());
		
		semM.agregarMulta(multa1);
		assertEquals(1, semM.cantidadDeMultas());
		
		semM.agregarMulta(multa2);
		assertEquals(2, semM.cantidadDeMultas());
		
	}
	
	//*completar
	@Test
	void registrarMultaTest()
	{
		int idZonaEstacionamiento = 100;
		int idInspector = 7;
		
		semM.registrarMulta("abc123", idZonaEstacionamiento, idInspector);
	}
	
	
}
