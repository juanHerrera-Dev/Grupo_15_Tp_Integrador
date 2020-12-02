package semPrincipal;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estacionamiento.SemEstacionamiento;
import sEM_Multa.SemMultas;
import semAlertas.SemAlertas;
import semZonas.SemZonasEstacionamiento;
import sem_usuario.SemUsuarios;

class TestCaseSemPrincipal {
	
	SemPrincipal semPrincipal; 
	SemEstacionamiento semEstacionamiento;
	SemAlertas semAlertas;
	SemZonasEstacionamiento semZonas;
	SemUsuarios semUsuarios;
	SemMultas semMultas;
	
	@BeforeEach
	void setUp() throws Exception {
		
		semEstacionamiento = mock(SemEstacionamiento.class);
		semAlertas = mock(SemAlertas.class);
		semZonas = mock(SemZonasEstacionamiento.class);
		semUsuarios = mock(SemUsuarios.class);
		semMultas = mock(SemMultas.class);
		semPrincipal = new SemPrincipal(semEstacionamiento,semAlertas,semZonas,semUsuarios,semMultas);
		
	}

	/**
	 * testeamos el constructor y a la vez se testea toda la funcionalidad requerida por la sem principal
	 * simplemente es una clase que tiene la funcion de contener a las sem individuales y proveerselas entre si
	 */
	@Test
	void testConstructorSemPrincipal() {
		assertEquals(semPrincipal.getSemEstacionamiento(),semEstacionamiento);
		assertEquals(semPrincipal.getSemAlertas(),semAlertas);
		assertEquals(semPrincipal.getSemZonas(),semZonas);
		assertEquals(semPrincipal.getSemUsuarios(),semUsuarios);
		assertEquals(semPrincipal.getSemMultas(),semMultas);
	}

}
