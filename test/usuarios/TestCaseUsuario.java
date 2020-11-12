package usuarios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import estacionamiento.EstacionamientoApp;

import static org.mockito.Mockito.*;

import java.time.LocalTime;

import semAlertas.ISemAlertas;
import semEstacionamientos.IsemEstacionamiento;

class TestCaseUsuario {
	
	Usuario usuarioX;
	ISemAlertas semAlertas;
	IsemEstacionamiento semEstacionamiento;
	
	
	@BeforeEach
	void setUp() throws Exception {
		
		semAlertas=mock(ISemAlertas.class);
		semEstacionamiento=mock(IsemEstacionamiento.class);
		usuarioX=new Usuario(semAlertas,semEstacionamiento,200.0,1531489563);
	}

	@Test
	void testCuandoUnUsuarioSeCreaNoTieneNingunEstacionamientoVigente() {
		
		assertEquals(null,usuarioX.getEstacionamientoVigente());
	}
	@Test
	void testCuandoUnUsuarioIniciaUnEstacionamientoLoSeteaComoColaboradorInterno() {
		int zonaId=123;
		String patente= "sau 231";
		
		usuarioX.iniciarEstacionamiento(patente,zonaId);
		
		assertNotEquals(null,usuarioX.getEstacionamientoVigente());
		
		
		/*4 funcionalidades: almacenar el estacionamiento en colaborador v
							 enviar alerta al sem alertas 
							 almacenar el estacionamiento en la sem estacionamientos
							 retornar un string
		*/					 
	}
	@Test 
	void testCuandoUnUsuarioIniciaUnEstacionamientoEnviarUnaAlertaALaSemAlertas() {
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			
			verify(semAlertas).alertaInicioDeEstacionamiento(1531489563,200.0, zonaId,LocalTime.now());
		} 
				
		//verify(el mock).elMensaje(any(cualquierClaseOInterfaz.class));
		/*4 funcionalidades: almacenar el estacionamiento en colaborador v
							 enviar alerta al sem alertas v 
							 almacenar el estacionamiento en la sem estacionamientos
							 retornar un string
		*/					 
	}
	@Test
	void testCuandoUnUsuarioIniciaUnEstacionamientoGuardaEseEstacionamientoEnLaSemEstacionamientos() {
		int zonaId=123;
		String patente= "sau 231";
		
		usuarioX.iniciarEstacionamiento(patente,zonaId);
		
		verify(semEstacionamiento).guardarEstacionamiento(any(EstacionamientoApp.class));
		
		
		/*4 funcionalidades: almacenar el estacionamiento en colaborador v
							 enviar alerta al sem alertas f
							 almacenar el estacionamiento en la sem estacionamientos v
							 retornar un string 
		*/					 
	}
	@Test // va  afuncionar cuando pueda mockear la hora y haga bien la diferencia de horario
	void testCuandoUnUsuarioIniciaUnEstacionamientoRetornaElStringEsperado() {
		
		LocalTime horaActual = LocalTime.of(13,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			
			
			String respuestaEsperada= "horaInicio=13:0"+" "+"horaMaxima=18:0"; 
			
			assertEquals(respuestaEsperada,usuarioX.iniciarEstacionamiento(patente,zonaId));
		} 
	}
	@Test 
	void testCuandoUnUsuarioIniciaUnEstacionamientoPeroNoPoseeSaldoSuficienteSeDevuelveUnaNotificacion() {
		
		int zonaId=123;
		String patente= "sau 231";
		Usuario usuarioY=new Usuario(semAlertas,semEstacionamiento,0.0,1531489563);
		
		
		String respuestaEsperada= "Saldo insuficiente. Estacionamiento no permitido."; 
		
		assertEquals(respuestaEsperada,usuarioY.iniciarEstacionamiento(patente,zonaId));
		
	}
	
	@Test 
	void testCuandoUnUsuarioFinalizaUnEstacionamientoFinalizaSuEstacionamiento() {
		
		int zonaId=123;
		String patente= "sau 231";
		usuarioX.iniciarEstacionamiento(patente,zonaId);
		
		usuarioX.finalizarEstacionamiento();
		
		assertEquals(false,usuarioX.getEstacionamientoVigente().estacionamientoVigente(LocalTime.now()));
		
	}
	@Test 
	void testCuandoUnUsuarioFinalizaUnEstacionamientoEnviaUnaAlertaALaSemAlertas() {
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			
			usuarioX.finalizarEstacionamiento();
			
			verify(semAlertas).alertaFinDeEstacionamiento(1531489563,200.0,zonaId, LocalTime.now());
		} 
	}
	@Test //va  afuncionar cuando pueda mockear la hora y haga bien la diferencia de horario
	void testCuandoUnUsuarioFinalizaUnEstacionamientoRetornaUnaNotificacion() {
		
		int zonaId=123;
		String patente= "sau 231";
		usuarioX.iniciarEstacionamiento(patente,zonaId);
		
		
		
		String respuestaEsperada= "horaInicio=13:0"+" "+"horaFin=18:0"+" "+"duracion=5horas"+" "+"coste=200.0"; 
		
		assertEquals(respuestaEsperada,usuarioX.finalizarEstacionamiento());
		 
	}
	@Test
	void testUnUsuarioNoPuedeFinalizarUnEstacionamientoSiEsteYaFinalizo()
	{
		int zonaId=123;
		String patente= "sau 231";
		usuarioX.iniciarEstacionamiento(patente,zonaId);
		
		usuarioX.finalizarEstacionamiento();
		
		
		
		assertThrows(IllegalArgumentException.class, 
            	() -> usuarioX.finalizarEstacionamiento());
		
		
		//hacer cuando tenga la clase estacionamiento que pueda responder el mensaje estacionamientoVigente()
	}
	@Test
	void testCuandoAUnUsuarioLeConsultanSuSaldoRetornaUnaNotificacion()
	{
		
		String respuestaEsperada= "Saldo:200.0"; 
		
		assertEquals(respuestaEsperada,usuarioX.consultarSaldo());
		
		
		//hacer cuando tenga la clase estacionamiento que pueda responder el mensaje estacionamientoVigente()
	}
	@Test
	void testCuandoUnUsuarioRecargaCreditoSuMontoPasaAActualizarse()
	{
		int zonaId=123;
		String patente= "sau 231";
		double creditoEsperado= 400.0;
		usuarioX.iniciarEstacionamiento(patente,zonaId);
		
		usuarioX.recargarCredito(200.0);
		
		assertEquals(creditoEsperado,usuarioX.getCredito());
	}
	@Test // no funciona hasta hacer el mock LocalTime.now()
	void testCuandoUnUsuarioRecargaCreditoEnviaUnaAlertaALaSemAlertas()
	{
	
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			
			usuarioX.recargarCredito(200.0);
			
			
			verify(semAlertas).alertaRecargaDeSaldo(1531489563,200.0,LocalTime.now());
		} 
	}
	@Test
	void testCuandoUnUsuarioRecargaCreditoActualizaLaHoraMaximaDeSuEstacionamientoVigente()
	{
		int zonaId=123;
		String patente= "sau 231";
		
		LocalTime horaActual = LocalTime.of(13,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			usuarioX.recargarCredito(80.0);
			
			
			assertEquals(LocalTime.of(20,00),usuarioX.getEstacionamientoVigente().getHoraMaxima());
		} 
	}
	
	@Test
	void testCuandoUnUsuarioRecargaCreditoPeroNoPoseeUnEstacionamientoVigenteNoActualizaSuHoraMaxima()
	{
		
		usuarioX.recargarCredito(1000.0);
		
		assertEquals(null,usuarioX.getEstacionamientoVigente());
	}
	/*
	 * 1.finaliza el estacionamiento que contiene
	 * 2.envia una alaerta
	 * 3.retorna un string
	 *  
	 *  la hora exacta de comienzo del estacionamiento 
	  la hora exacta de fin del estacionamiento. 
	  la duración en horas del estacionamiento 
	 el costo del estacionamiento (ya debitado de su crédito)
	 */ 
	
}
