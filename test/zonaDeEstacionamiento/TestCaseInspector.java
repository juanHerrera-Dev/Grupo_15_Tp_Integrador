package zonaDeEstacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCaseInspector {

	Inspector insp;
	int dni = 30111000;
	String nombre = "Luis";
	@BeforeEach
	void setUp() throws Exception {
		insp = new Inspector(dni, nombre);
	}

	@Test
	void testSGetDni() {
		assertEquals(dni, insp.getDni() );
	}
	
	@Test
	void testSetDni() {
		insp.setDni(11888333);
		assertEquals(11888333, insp.getDni());
	}

	@Test
	void testSGetNombre() {
		assertEquals(nombre, insp.getNombre() );
	}
	
	@Test
	void testSetNombre() {
		insp.setNombre("Juan");
		assertEquals("Juan", insp.getNombre());
	}
}
