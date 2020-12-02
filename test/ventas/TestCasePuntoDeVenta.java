package ventas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estacionamiento.Estacionamiento;
import estacionamiento.SemEstacionamiento;
import semPrincipal.ISemPrincipal;
import sem_usuario.IUsuarioSEM;
import sem_usuario.SemUsuarios;

import static org.mockito.Mockito.*;

import java.time.LocalTime;

class TestCasePuntoDeVenta {
	
	PuntoDeVenta puntoDeVentaX;
	
	ISemPrincipal semPrincipal;
	@BeforeEach
	void setUp() throws Exception {
		semPrincipal=mock(ISemPrincipal.class);
		
		puntoDeVentaX = new PuntoDeVenta(1,
										"Maxiquiosco X",
										"123 bamba",
										semPrincipal);
	}

	@Test
	void testConstructorPuntoDeVenta() {
		assertEquals(puntoDeVentaX.getID(),1);
		assertEquals(puntoDeVentaX.getNombre(),"Maxiquiosco X");
		assertEquals(puntoDeVentaX.getDireccion(),"123 bamba");
		assertEquals(puntoDeVentaX.getSemPrincipal(),semPrincipal);
		
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
		SemEstacionamiento semEstacionamiento= mock(SemEstacionamiento.class);
		
		when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
		
		puntoDeVentaX.venderHoras("sau 703",LocalTime.of(5,0));
		
		assertEquals(1,puntoDeVentaX.getVentas().size());
		verify(semEstacionamiento).guardarEstacionamiento(any(Estacionamiento.class));
		
	}
	@Test
	void testCuandoUnPuntoDeVentaVendeUnaRecargaGeneraUnaRecargaDelCreditoDelUsuarioYUnaVenta(){
		
		SemUsuarios semUsuarios= mock(SemUsuarios.class);
		
		int idZona =12; 
		puntoDeVentaX.setIdZona(idZona);
		
		int nmrCelular= 1564986425;
		double montoARecargar= 200.0;
		
		when(semPrincipal.getSemUsuarios()).thenReturn(semUsuarios);//se hace esto porque se necesita a la semUsuarios para realizar la recarga
		
		puntoDeVentaX.hacerRecarga(nmrCelular,montoARecargar);
		
		assertEquals(1,puntoDeVentaX.getVentas().size());
		verify(semUsuarios).recargarCredito(nmrCelular,montoARecargar);
		
	}
	

}
