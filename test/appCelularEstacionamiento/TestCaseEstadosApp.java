package appCelularEstacionamiento;



import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCaseEstadosApp {
	
	EstadoApp estadoEnAuto;
	EstadoApp estadoAPie;
	AppCelularEstacionamiento app;
	
	@BeforeEach
	void setUp() throws Exception {
		
		estadoEnAuto= new EnAuto();
		estadoAPie = new APie();
		app= mock(AppCelularEstacionamiento.class);
	}

	@Test
	void testEstadoEnAutoRecibeMensajeCaminando() {
		
		estadoEnAuto.caminando(app);
		
		verify(app).setEstadoAPie();
		verify(app).alertaDeInicioDeEstacionamiento();
		
	}
	@SuppressWarnings("deprecation")
	@Test
	void testEstadoEnAutoRecibeMensajeEnAuto() {
		
		estadoEnAuto.enAuto(app);
		verifyZeroInteractions(app);
	}
	@SuppressWarnings("deprecation")
	@Test
	void testEstadoAPieRecibeMensajeCaminando() {
		
		estadoAPie.caminando(app);
		verifyZeroInteractions(app);
		
	}
	@Test
	void testEstadoAPieRecibeMensajeEnAuto() {
		
		estadoAPie.enAuto(app);
		
		verify(app).setEstadoEnAuto();
		verify(app).alertaDeFinDeEstacionamiento();
		
	}
}
