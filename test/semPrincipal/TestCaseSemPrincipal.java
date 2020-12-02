package semPrincipal;

import static org.junit.Assert.assertNotNull;




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
		
		
		semPrincipal = new SemPrincipal();
		
	}

	/**
	 * testeamos el constructor y a la vez se testea toda la funcionalidad requerida por la sem principal
	 * simplemente es una clase que tiene la funcion de contener a las sem individuales y proveerselas entre si
	 */
	@Test
	void testConstructorSemPrincipal() {
		assertNotNull(semPrincipal.getSemEstacionamiento());
		assertNotNull(semPrincipal.getSemAlertas());
		assertNotNull(semPrincipal.getSemZonas());
		assertNotNull(semPrincipal.getSemUsuarios());
		assertNotNull(semPrincipal.getSemMultas());
	}

}
