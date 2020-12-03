package semPrincipal;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import estacionamiento.ISemEstacionamiento;
import estacionamiento.SemEstacionamiento;
import sEM_Multa.ISemMulta;
import sEM_Multa.SemMultas;
import semAlertas.ISemAlertas;
import semAlertas.SemAlertas;
import semZonas.ISemZonaDeEstacionamiento;
import semZonas.SemZonasEstacionamiento;
import sem_usuario.ISemUsuarios;
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
		
		
		semPrincipal = new SemPrincipal();
		semAlertas= mock (SemAlertas.class);
		semUsuarios= mock(SemUsuarios.class);
		semZonas= mock(SemZonasEstacionamiento.class);
		semMultas= mock(SemMultas.class);
		semEstacionamiento= mock(SemEstacionamiento.class);
		
	}

	/**
	 * testeamos el constructor y a la vez se testea toda la funcionalidad requerida por la sem principal
	 * simplemente es una clase que tiene la funcion de contener a las sem individuales y proveerselas entre si
	 */
	@Test
	void testConstructorSemPrincipal() {
		
		assertEquals(semAlertas.getClass(),semPrincipal.getSemAlertas().getClass());
		assertEquals(semEstacionamiento.getClass(),semPrincipal.getSemEstacionamiento().getClass());
		assertEquals(semMultas.getClass(),semPrincipal.getSemMultas().getClass());
		assertEquals(semZonas.getClass(),semPrincipal.getSemZonas().getClass());
		assertEquals(semUsuarios.getClass(),semPrincipal.getSemUsuarios().getClass());
		
	}

}
