package appCelularEstacionamiento;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import semPrincipal.ISemPrincipal;
import semZonas.ISemZonaDeEstacionamiento;

import sem_usuario.ISemUsuarios;
import sem_usuario.IUsuarioApp;


class testCaseAppCelular {

	AppCelularEstacionamiento app;
	IUsuarioApp iUsuarioApp;
	ISemUsuarios iSemUsuarios;
	ISemZonaDeEstacionamiento iZonaEstacionamiento;
	ISemPrincipal semPrincipal;
	ModoApp modoApp;
	EstadoApp estadoApp;
	
	@BeforeEach
	void setUp() throws Exception
	{	
		semPrincipal= mock(ISemPrincipal.class);
		
		modoApp= mock(ModoApp.class);
		estadoApp= mock(EstadoApp.class);
		
		int numeroDeCelular = 1566770500; 
		iUsuarioApp = mock(IUsuarioApp.class);  
		iSemUsuarios = mock(ISemUsuarios.class);
		when(iSemUsuarios.getIUsuario(numeroDeCelular)).thenReturn(iUsuarioApp);
		iZonaEstacionamiento = mock(ISemZonaDeEstacionamiento.class);
		when(iZonaEstacionamiento.buscarIdZona(100)).thenReturn(100);
		
		when(semPrincipal.getSemUsuarios()).thenReturn(iSemUsuarios);
		
		
		app = new AppCelularEstacionamiento(numeroDeCelular,semPrincipal,modoApp,estadoApp );
		
	} 
	
	
	@Test
	public void testIniciarEstacionamiento()
	{
		String patente = "abc123";
		int idZonaDeEstacionamiento = 101;
		
		when(semPrincipal.getSemZonas()).thenReturn(iZonaEstacionamiento);
		when(iZonaEstacionamiento.buscarIdZona(100)).thenReturn(idZonaDeEstacionamiento);
		
		app.iniciarEstacionamiento(patente);
		
		
		verify(modoApp).iniciarEstacionamiento(patente, idZonaDeEstacionamiento,app);
	}
	
	@Test
	public void testFinalizarEstacionamiento() 
	{	
		
		
		app.finalizarEstacionamiento();
		
		verify(modoApp).finalizarEstacionamiento(app);
	}
	 
	@Test
	public void testConsultarSaldo()
	{
		when(iUsuarioApp.consultarSaldo()).thenReturn("100"); 
		app.consultarSaldo();
		
		verify(iUsuarioApp).consultarSaldo();
	}
	
	

	@Test
	public void testalertarInicioDeEstacionamiento()
	{	
		
		app.alertaDeInicioDeEstacionamiento();
		
		verify(modoApp).alertaDeInicioDeEstacionamiento(app);
	}
	
	@Test
	public void testalertarFinalizacionDeEstacionamiento()
	{	
		
		app.alertaDeFinDeEstacionamiento();
		
		verify(modoApp).alertaDeFinDeEstacionamiento(app);
	}
	

	
	
	// luis este lo hice para testear algunos de los que faltaban,
	//fijate de que mas se puede poner de esos metodos que estan. en 0% 
		@Test
		public void testConstructorApp() {
			int numeroDeCelular = 1566770500;
			
			assertEquals(numeroDeCelular,app.getNumeroDeCelular());
			
		}
		@Test
		public void testDriving() {
			
			app.driving();
			verify(estadoApp).enAuto(app);
		}
		@Test
		public void testWalking() {
			
			app.walking();
			verify(estadoApp).caminando(app);
		}
	
	/**
	 * esto lo pusimos nada mas para cubrir el coverage porque solo faltaban 
	 * set ,getter y estos metodos que debian estar pero que no hacen nada
	 * */
	@Test
	public void testActivarMoveSensor() {
		
		app.activarMoveSensor();
		
	}
	@Test
	public void testdesactivarMoveSensor() {
		
		app.desactivarMoveSensor();
		
	}
		
}
