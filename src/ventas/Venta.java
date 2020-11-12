package ventas;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Venta {
	
	private int nmrControl;
	private int idPVenta;
	private LocalDate fecha;
	private LocalTime hora;
	
	public Venta(int nmrControl, int idPVenta, LocalDate fecha, LocalTime hora) {
		
		this.nmrControl= nmrControl; this.idPVenta= idPVenta;
		this.fecha=fecha; this.hora=hora;
		
		
	}

	public int getNmrControl() {
		
		return this.nmrControl;
	}

	public int getIdPVenta() {
		
		return this.idPVenta;
	}

	public LocalTime getHora() {
		
		return this.hora;
	}

	public LocalDate getFecha() {
		
		return this.fecha;
	}
}
