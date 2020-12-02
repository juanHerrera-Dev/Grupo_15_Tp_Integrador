package usuarios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import estacionamiento.EstacionamientoApp;

import estacionamiento.SemEstacionamiento;

import static org.mockito.Mockito.*;

import java.time.LocalTime;


import semAlertas.SemAlertas;
import semPrincipal.ISemPrincipal;


class TestCaseUsuario {
	
	Usuario usuarioX;
	
	ISemPrincipal semPrincipal;
	SemAlertas semAlertas;
	SemEstacionamiento semEstacionamiento;
	@BeforeEach
	void setUp() throws Exception {
		semPrincipal= mock(ISemPrincipal.class);
		
		semAlertas=mock(SemAlertas.class);
		semEstacionamiento= mock(SemEstacionamiento.class);
		
		usuarioX=new Usuario(semPrincipal,200.0,1531489563);
	}

	@Test
	void testCuandoUnUsuarioSeCreaNoTieneNingunEstacionamientoVigente() {
		
		assertEquals(null,usuarioX.getEstacionamientoVigente());
	}
	@Test
	void testCuandoUnUsuarioIniciaUnEstacionamientoLoSeteaComoColaboradorInterno() {
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			
			assertNotEquals(null,usuarioX.getEstacionamientoVigente());
		} 
		
		/*4 funcionalidades: almacenar el estacionamiento en colaborador v
							 enviar alerta al sem alertas 
							 almacenar el estacionamiento en la sem estacionamientos
							 retornar un string
		*/					 
	}
	@Test 
	void testCuandoUnUsuarioIniciaUnEstacionamientoEnviarUnaAlertaALaSemAlertas() {
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		/**
		 *mockeamos a las sem porque son necesarias para el funcionamiento del usuario, lo que cambio es que ahora el sem
		 *principal se las probee al usuario en vez de tenerlas como colaborador interno 
		 */
		
		SemAlertas semAlertas=mock(SemAlertas.class);
		SemEstacionamiento semEstacionamiento= mock(SemEstacionamiento.class);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
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
		
		
		
		LocalTime horaActual = LocalTime.of(13,00);
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			
			
			
			verify(semEstacionamiento).guardarEstacionamiento(any(EstacionamientoApp.class));
		} 
						 
	}
	@Test 
	void testCuandoUnUsuarioIniciaUnEstacionamientoRetornaElStringEsperado() {
		
		LocalTime horaActual = LocalTime.of(13,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
			String respuestaEsperada= "horaInicio=13:0"+" "+"horaMaxima=18:0"; 
			
			assertEquals(respuestaEsperada,usuarioX.iniciarEstacionamiento(patente,zonaId));
		} 
	}
	@Test 
	void testCuandoUnUsuarioIniciaUnEstacionamientoPeroNoPoseeSaldoSuficienteSeDevuelveUnaNotificacion() {
		
		int zonaId=123;
		String patente= "sau 231";
		Usuario usuarioY=new Usuario(semPrincipal,0.0,1531489563);
		
		when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
		when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
		
		String respuestaEsperada= "Saldo insuficiente. Estacionamiento no permitido."; 
		
		assertEquals(respuestaEsperada,usuarioY.iniciarEstacionamiento(patente,zonaId));
		
	}
	
	@Test 
	void testCuandoUnUsuarioFinalizaUnEstacionamientoFinalizaSuEstacionamiento() {
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			
			usuarioX.finalizarEstacionamiento();
			
			assertEquals(false,usuarioX.getEstacionamientoVigente().estacionamientoVigente(LocalTime.now()));
		} 
	}
	@Test 
	void testCuandoUnUsuarioFinalizaUnEstacionamientoEnviaUnaAlertaALaSemAlertas() {
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			
			usuarioX.finalizarEstacionamiento();
			
			verify(semAlertas).alertaFinDeEstacionamiento(1531489563,200.0,zonaId, LocalTime.now());
		} 
	}
	@Test 
	void testCuandoUnUsuarioFinalizaUnEstacionamientoRetornaUnaNotificacion() {
		
		LocalTime horaInicio = LocalTime.of(13,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaInicio);
			
			int zonaId=123;
			String patente= "sau 231";
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			
			}
		LocalTime horaFin = LocalTime.of(17,00);
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaFin);
			
			
			String respuestaEsperada= "horaInicio=13:0"+" "+"horaFin=17:0"+" "+"duracion=4horas"+" "+"coste=0.0"; 
			
			assertEquals(respuestaEsperada,usuarioX.finalizarEstacionamiento());
			
			
			}
		
	}
	@Test
	void testUnUsuarioNoPuedeFinalizarUnEstacionamientoSiEsteYaFinalizo()
	{
		
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			int zonaId=123;
			String patente= "sau 231";
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			
			usuarioX.finalizarEstacionamiento();
			
			
			assertThrows(IllegalArgumentException.class, 
	            	() -> usuarioX.finalizarEstacionamiento());
		}
		
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
		
		
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			
			usuarioX.recargarCredito(200.0);
			
			
			assertEquals(creditoEsperado,usuarioX.getCredito());
		}
		
	}
	@Test 
	void testCuandoUnUsuarioRecargaCreditoEnviaUnaAlertaALaSemAlertas()
	{
	
		LocalTime horaActual = LocalTime.of(12,00);
		
		try(MockedStatic<LocalTime> localTimeMock= Mockito.mockStatic(LocalTime.class, Mockito.CALLS_REAL_METHODS)){
			localTimeMock.when(LocalTime::now).thenReturn(horaActual);
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
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
			
			when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
			when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
			
			usuarioX.iniciarEstacionamiento(patente,zonaId);
			usuarioX.recargarCredito(80.0);
			
			
			assertEquals(LocalTime.of(20,00),usuarioX.getEstacionamientoVigente().getHorarioMaximo());
		} 
	}
	
	@Test
	void testCuandoUnUsuarioRecargaCreditoPeroNoPoseeUnEstacionamientoVigenteNoActualizaSuHoraMaxima()
	{
		when(semPrincipal.getSemAlertas()).thenReturn(semAlertas);
		when(semPrincipal.getSemEstacionamiento()).thenReturn(semEstacionamiento);
		
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
