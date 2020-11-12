package appCelularEstacionamiento;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem_usuario.IUsuarioApp;
import sem_usuario.IUsuarioSEM;
import sem_usuario.IZonaDeEstacionamiento;

class testCaseAppCelular {

	AppCelularEstacionamiento app;
	IUsuarioApp iUsuarioApp;
	IUsuarioSEM iUsuarioSEM;
	IZonaDeEstacionamiento iZonaEstacionamiento;
	
	
	@BeforeEach
	void setUp() throws Exception
	{
		int numeroDeCelular = 1566770500; 
		iUsuarioApp = mock(IUsuarioApp.class);  
		iUsuarioSEM = mock(IUsuarioSEM.class);
		when(iUsuarioSEM.getIUsuario(numeroDeCelular)).thenReturn(iUsuarioApp);
		iZonaEstacionamiento = mock(IZonaDeEstacionamiento.class);
		when(iZonaEstacionamiento.buscarIdZona(100)).thenReturn(100);
		app = new AppCelularEstacionamiento(numeroDeCelular, iUsuarioSEM, iZonaEstacionamiento);
		
	} 
	   


	
	@Test
	public void testIniciarEstacionamiento()
	{
		String patente = "abc123";
		int zonaDeEstacionamiento = 101;
		
		app.iniciarEstacionamiento(patente, zonaDeEstacionamiento);
		
		verify(iUsuarioApp).iniciarEstacionamiento(patente, zonaDeEstacionamiento);
	}
	
	@Test
	public void testFinalizarEstacionamiento() 
	{
		app.finalizarEstacionamiento();
		
		verify(iUsuarioApp).finalizarEstacionamiento();
	}
	 
	@Test
	public void testConsultarSaldo()
	{
		when(iUsuarioApp.consultarSaldo()).thenReturn("100"); 
		app.consultarSaldo();
		
		verify(iUsuarioApp).consultarSaldo();
	}
	

	 
	
	@Test
	public void testIniciarEstaiconamientoOverLoad()
	{
		app.setModoAutomatico("abc100");
		app.iniciarEstacionamiento();
		
		verify(iUsuarioApp).iniciarEstacionamiento("abc100", 100);
	}
	

	@Test
	public void testalertarInicioDeEstacionamiento()
	{
		assertEquals("Alerta. Recuerde iniciar estacionamiento",app.alertarInicioDeEstacionamiento());
	}
	
	@Test
	public void tesalertarFinalizacionDeEstacionamiento()
	{
		assertEquals("Alerta. Recuerde finalizar estacionamiento",app.alertarFinalizacionDeEstacionamiento());
	}
	

	//INTERFACE MOVENTSENSOR???
	
	//state
	/*
	@Test
	public void testAlertaDeInicioDeEstacionamientoEnModoManual()
	{
		app.setModoManual();
		app.driving();
		app.walking();
		
		verify(app).alertarInicioDeEstacionamiento();
		
	}
	
	@Test
	public void testAlertaDeFinDeEstacionamientoEnModoManual()
	{
		app.setModoManual();
		app.walking();
		app.driving();
		
		
		
		
	}
	
	@Test
	public void testAlertaDeInicioDeEstacionamientoEnModoAutomatico()
	{
		app.setModoAutomatico("abc100");;
		app.driving();
		app.walking();
		
		assertEquals();
		
	}
	
	@Test
	public void testAlertaDeFinDeEstacionamientoEnModoAutomatico()
	{
		app.setModoAutomatico("abc100");
		app.walking();
		app.driving();
		
		verify(app).finalizarEstacionamiento();
		
	}
*/
}
