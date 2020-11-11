package zonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import ventas.PuntoDeVenta;

class TestCaseZonaDeEstacionamiento {
	
	ZonaDeEstacionamiento zonaX;
	Inspector inspectorDeZonaX;
	PuntoDeVenta puntoDeVentaX;
	@BeforeEach
	void setUp() throws Exception {
		inspectorDeZonaX=mock(Inspector.class);
		puntoDeVentaX=mock(PuntoDeVenta.class);
		
		zonaX= new ZonaDeEstacionamiento(1,inspectorDeZonaX);
	}

	@Test
	void testCuandoUnaZonaDeEstacionamientoEsCreadaNoTieneNingunPuntoDeVentaPeroSiTieneAsignadoUnInspector() {
		
		assertEquals(1,zonaX.getId());
		assertEquals(inspectorDeZonaX,zonaX.getInspector());
		assertEquals(0,zonaX.getPuntosDeVentas().size());
	}
	@Test
	void testUnaZonaDeEstacionamientoPuedeRegistrarUnPuntoDeVenta() {
		
		zonaX.registrarPuntoDeVenta(puntoDeVentaX);
		
		assertEquals(1,zonaX.getPuntosDeVentas().size());
	}
	@Test
	void testUnaZonaDeEstacionamientoNoPuedeRegistrarUnPuntoDeVentaSiEsteExistePreviamente() {
		
		zonaX.registrarPuntoDeVenta(puntoDeVentaX);
		
		
		assertThrows(IllegalArgumentException.class, 
	            	() -> zonaX.registrarPuntoDeVenta(puntoDeVentaX));
		
	}
}
