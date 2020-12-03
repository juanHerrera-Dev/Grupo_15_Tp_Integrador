package semZonas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estacionamiento.Estacionamiento;
import zonaDeEstacionamiento.ZonaDeEstacionamiento;

class TestCaseSemZonas {
	
	SemZonasEstacionamiento semZonas;
	ZonaDeEstacionamiento zonaX;
	Estacionamiento estacionamiento;
	
	@BeforeEach
	void setUp() throws Exception {
		semZonas= new SemZonasEstacionamiento();
		zonaX= mock(ZonaDeEstacionamiento.class);
		
		
	}
	
	
	
	@Test
	void testCuandoLaSemDeZonasSeCreaNoPoseeNingunaZonaRegistrada() {
		
		
		assertEquals(0,semZonas.getZonas().size());
	}
	@Test
	void testLaSemDeZonasPuedeRegistrarUnaZona() {
		
		semZonas.registrarZona(zonaX);
		
		assertEquals(1,semZonas.getZonas().size());
	}
	@Test
	void testLaSemDeZonasNoPuedeRegistrar2VecesLaMismaZona() {
		
		semZonas.registrarZona(zonaX);
		
		assertThrows(IllegalArgumentException.class, 
            	() -> semZonas.registrarZona(zonaX));
	}
	@Test
	void testLaSemDeZonasPuedeChequearSiEstaUnaZonaDadaPorSuIdYDevolverloEnCasoDeQueEste() {
		
		int idZona=123;
		
		when(zonaX.getId()).thenReturn(123);
		
		semZonas.registrarZona(zonaX);
		
		
		assertEquals(123,semZonas.buscarIdZona(idZona));
	}
	
	@Test
	void testLaSemDeZonasCuandoLePidenBuscarUnIdDeZonaQueNoExisteDevuelveUnError() {
		
		int idZona=123;
		
		when(zonaX.getId()).thenReturn(84);
		
		semZonas.registrarZona(zonaX);
		
		assertThrows(IllegalArgumentException.class, 
            	() -> semZonas.buscarIdZona(idZona));
		
	}
}
