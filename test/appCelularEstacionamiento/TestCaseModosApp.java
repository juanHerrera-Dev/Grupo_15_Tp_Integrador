package appCelularEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem_usuario.IUsuarioApp;

class TestCaseModosApp {
	
	ModoApp modoManual;
	ModoApp modoAutomatico;
	AppCelularEstacionamiento app;
	String patente;
	Integer idZona;
	IUsuarioApp usuarioX;
	
	@BeforeEach
	void setUp() throws Exception {
		
		modoManual= new Manual();
		modoAutomatico= new Automatico();
		app= mock(AppCelularEstacionamiento.class);
		
		usuarioX=mock(IUsuarioApp.class);
	    patente= "Say291";
	    idZona= 123;
	}
	//test modoManual
	@Test
	void testModoManualalertaDeInicioDeEstacionamiento() {
		
		String respuestaEsperada="Alerta. Recuerde iniciar estacionamiento";
		
		assertEquals(respuestaEsperada,modoManual.alertaDeInicioDeEstacionamiento( app));
	}
	@Test
	void testModoManualalertaDeFinDeEstacionamiento() {
		
		String respuestaEsperada="Alerta. Recuerde finalizar estacionamiento";
		
		assertEquals(respuestaEsperada,modoManual.alertaDeFinDeEstacionamiento( app));
	}
	@Test
	void testModoManualInicioDeEstacionamiento() {
		
		when(app.getUsuario()).thenReturn(usuarioX);
		modoManual.iniciarEstacionamiento( patente,idZona, app);
		
		verify(usuarioX).iniciarEstacionamiento(patente,idZona);
	}
	@Test
	void testModoManualFinDeEstacionamiento() {
		
		when(app.getUsuario()).thenReturn(usuarioX);
		modoManual.finalizarEstacionamiento(app);
		
		verify(usuarioX).finalizarEstacionamiento();
	}
	//Modo automatico
	
	@Test
	void testModoAutomaticoalertaDeInicioDeEstacionamiento() {
		
		
		when(app.getUsuario()).thenReturn(usuarioX);
		when(app.getPatente()).thenReturn(patente);
		when(app.getIdZonaDeEstacionamiento()).thenReturn(idZona);
		
		modoAutomatico.alertaDeInicioDeEstacionamiento(app);
		
		verify(usuarioX).iniciarEstacionamiento(patente,idZona);
		
	}
	@Test
	void testModoAutomaticoalertaDeFinDeEstacionamiento() {
		
		when(app.getUsuario()).thenReturn(usuarioX);
	
		modoAutomatico.alertaDeFinDeEstacionamiento(app);
		
		verify(usuarioX).finalizarEstacionamiento();
		
	}
	@Test
	void testModoAutomaticoInicioDeEstacionamiento() {
		
		String respuestaEsperada= "No es posible iniciar estacionamiento de forma manual en modo automatico.Cambia de modo";
		
		
		
		
		assertEquals(respuestaEsperada,modoAutomatico.iniciarEstacionamiento( patente,idZona, app));
	}
	@Test
	void testModoAutomaticoFinDeEstacionamiento() {
		String respuestaEsperada= "No es posible finalizar estacionamiento de forma manual en modo automatico.Cambia de modo";
		
		
		assertEquals(respuestaEsperada,modoAutomatico.finalizarEstacionamiento(app));
	}
	
}
