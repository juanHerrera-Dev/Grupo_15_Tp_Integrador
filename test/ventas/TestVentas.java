package ventas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.*;

class TestVentas {
	
	//Venta ventaN ;
	VentaPuntual ventaPuntual;
 	VentaRecarga ventaRecarga;
	@BeforeEach
	void setUp() throws Exception {
		
	 //ventaN= new Venta(12,134,LocalDate.of(2020,9,10),LocalTime.of(12,30));
	 ventaPuntual = new VentaPuntual(12,134,LocalDate.of(2020,9,10),LocalTime.of(12,30),LocalTime.of(6,30));
	 ventaRecarga = new VentaRecarga(12,134,LocalDate.of(2020,9,10),LocalTime.of(12,30),1564958641,130.5);
	}

	/*@Test
	void testConstructorVenta() {
		
		assertEquals(ventaN.getNmrControl(),12);
		assertEquals(ventaN.getIdPVenta(),134);
		assertEquals(ventaN.getFecha(),LocalDate.of(2020,9,10));
		assertEquals(ventaN.getHora(),LocalTime.of(12,30));
	}*/
	@Test
	void testConstructorVentaPuntual() {
		
		assertEquals(ventaPuntual.getHorasEstacionamiento(),LocalTime.of(6,30));
		
	
	}
	@Test
	void testConstructorVentaRecarga() {
		
		assertEquals(ventaRecarga.getMonto(),130.5);
		assertEquals(ventaRecarga.getCelular(),1564958641);
		
	}
}
