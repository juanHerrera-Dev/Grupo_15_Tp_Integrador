package ventas;

import java.time.LocalDate;
import java.time.LocalTime;

public class VentaPuntual extends Venta {
	
	LocalTime horasE;//horas de estacionamiento vendidas
	
	public VentaPuntual(int nmrControl, int id, LocalDate fecha, LocalTime hora, LocalTime horasE) {
		super(nmrControl,id,fecha,hora);
		this.horasE=horasE;
	}

	public LocalTime getHorasEstacionamiento() {
		
		return horasE;
	}

}
