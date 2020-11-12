package ventas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estacionamiento.Estacionamiento;

import static org.mockito.Mockito.*;

import java.time.LocalTime;

class TestCasePuntoDeVenta {
	
	PuntoDeVenta puntoDeVentaX;
	
	ISemUsuario iSemUsuario;
	ISemEstacionamiento iSemEstacionamiento;
	
	@BeforeEach
	void setUp() throws Exception {
		
		iSemUsuario= mock(ISemUsuario.class);
		iSemEstacionamiento=mock(ISemEstacionamiento.class);
		puntoDeVentaX = new PuntoDeVenta(1,
										"Maxiquiosco X",
										"123 bamba",
										iSemUsuario,
										iSemEstacionamiento);
	}

	@Test
	void testConstructorPuntoDeVenta() {
		assertEquals(puntoDeVentaX.getID(),1);
		assertEquals(puntoDeVentaX.getNombre(),"Maxiquiosco X");
		assertEquals(puntoDeVentaX.getDireccion(),"123 bamba");
		assertEquals(puntoDeVentaX.getIRecarga(),iSemUsuario);
		assertEquals(puntoDeVentaX.getIEstacionamiento(),iSemEstacionamiento);
	}
	@Test 
	void testCuandoSeCreaUnPuntoDeVentaSuCantidadDeVentasEs0(){
	
		assertEquals(0,puntoDeVentaX.getVentas().size());
	}
	@Test 
	void testUnPuntoDeVentaPuedeSetearElIdDeZonaQueSeLeIndique(){
		/**
		 * este metodo solo se invocaria cuando se registra el puntoDeVenta en una zona
		 * */
		
		int idZona = 12;
		
		puntoDeVentaX.setIdZona(idZona);
		
		assertEquals(idZona,puntoDeVentaX.getIdZona());
	}
	@Test 
	void testCuandoUnPuntoDeVentaVendeUnEstacionamientoPuntualGeneraUnEstacionamientoYUnaVentaPuntual(){
		
		int idZona =12; 
		puntoDeVentaX.setIdZona(idZona);
		
		
		puntoDeVentaX.venderHoras("sau 703",LocalTime.of(5,0));
		
		assertEquals(1,puntoDeVentaX.getVentas().size());
		verify(iSemEstacionamiento).guardarEstacionamiento(any(Estacionamiento.class));
	}
	@Test
	void testCuandoUnPuntoDeVentaVendeUnaRecargaGeneraUnaRecargaDelCreditoDelUsuarioYUnaVenta(){
		
		int idZona =12; 
		puntoDeVentaX.setIdZona(idZona);
		
		int nmrCelular= 1564986425;
		double montoARecargar= 200.0;
		puntoDeVentaX.hacerRecarga(nmrCelular,montoARecargar);
		
		assertEquals(1,puntoDeVentaX.getVentas().size());
		verify(iSemUsuario).recargarCredito(nmrCelular,montoARecargar);
	}
	@Test
	void testCuandoUnPuntoDeVentaSolicitaVenderUnEstacionamientoPuntualFueraDeHorarioNoPuede() {
		
	}

}
