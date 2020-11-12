package semAlertas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usuarios.Usuario;

class TestCaseSemAlertas {
	
	SemAlertas semAlertas;
	Suscriptor suscriptorX;
	Usuario usuarioX;
	@BeforeEach
	void setUp() throws Exception {
		semAlertas= new SemAlertas();
		suscriptorX= mock(Suscriptor.class);
		usuarioX= mock(Usuario.class);
	}

	@Test
	void testCuandoLaSemDeAlertasSeCreaNoPoseeNingunSuscriptor() {
		
		
		assertEquals(0,semAlertas.getSuscriptores().size());
	}
	@Test
	void LaSemDeAlertasPuedeSuscribirSuscriptores() {
		
		semAlertas.agregarSuscriptor(suscriptorX);
		
		assertEquals(1,semAlertas.getSuscriptores().size());
	}
	@Test
	void LaSemDeAlertasNoPuedeSuscribirAUnMismoSuscriptor2Veces() {
		
		semAlertas.agregarSuscriptor(suscriptorX);
		
		assertThrows(IllegalArgumentException.class, 
            	() -> semAlertas.agregarSuscriptor(suscriptorX));
	}
	@Test
	void LaSemDeAlertasPuedeDesuscribirSuscriptores() {
		
		semAlertas.agregarSuscriptor(suscriptorX);
		semAlertas.quitarSuscriptor(suscriptorX);
		
		assertEquals(0,semAlertas.getSuscriptores().size());
	}
	@Test
	void cuandoSeQuiereDesuscribirUnSubscriptorNoExistenteLosSubscriptoresQuedanIgual() {
		
	
		semAlertas.quitarSuscriptor(suscriptorX);
		
		assertEquals(0,semAlertas.getSuscriptores().size());
	}
	@Test
	void cuandoLaSemAlertasRecibeUnaAlertaDeInicioDeEstacionamientoNotificaASusSubscriptores() {
		
		/**
		 * en estas funciones de alerta paso estos parametros(celular,monto,zonaId Y la hora) 
		 * por hacer algo general que pueden necesitar los suscriptores,pero al no estar especificado siempre 
		 * paso por parametro estos 4.
		 * */
		
		int nmrCelular=1596346894;
		double monto=40.0;
		int zonaId=123;
		LocalTime horaEstacionamiento= LocalTime.of(11,30);
		semAlertas.agregarSuscriptor(suscriptorX);
		
		semAlertas.alertaInicioDeEstacionamiento(nmrCelular,monto,zonaId,horaEstacionamiento);
		
		verify(suscriptorX).recibirAlertaInicioE(nmrCelular,monto,zonaId,horaEstacionamiento);
	}
	@Test
	void cuandoLaSemAlertasRecibeUnaAlertaDeFinDeEstacionamientoNotificaASusSubscriptores() {
		
		/**
		 * en estas funciones de alerta paso estos parametros(celular,monto,zonaId Y la hora) 
		 * por hacer algo general que pueden necesitar los suscriptores,pero al no estar especificado siempre 
		 * paso por parametro estos 4.
		 * */
		
		int nmrCelular=1596346894;
		double monto=0.0;
		int zonaId=123;
		LocalTime horaFinEstacionamiento= LocalTime.of(12,30);
		semAlertas.agregarSuscriptor(suscriptorX);
		
		semAlertas.alertaFinDeEstacionamiento(nmrCelular,monto,zonaId,horaFinEstacionamiento);
		
		verify(suscriptorX).recibirAlertaFinE(nmrCelular,monto,zonaId,horaFinEstacionamiento);
	}
	@Test
	void cuandoLaSemAlertasRecibeUnaAlertaDeRecargaNotificaASusSubscriptores() {
		
		/**
		 * en estas funciones de alerta paso estos parametros(celular,monto,zonaId Y la hora) 
		 * por hacer algo general que pueden necesitar los suscriptores,pero al no estar especificado siempre 
		 * paso por parametro estos 4.
		 * */
		
		int nmrCelular=1596346894;
		double monto=0.0;
		LocalTime horaRecarga= LocalTime.of(20,30);
		
		semAlertas.agregarSuscriptor(suscriptorX);
		
		semAlertas.alertaRecargaDeSaldo(nmrCelular,monto,horaRecarga);
		
		verify(suscriptorX).recibirAlertaRecarga(nmrCelular,monto,horaRecarga);
	}
}
