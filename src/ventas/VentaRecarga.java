package ventas;

import java.time.LocalDate;
import java.time.LocalTime;

public class VentaRecarga extends Venta {
	
	int numeroCelular;
	double monto;
	
	
	public VentaRecarga(int nmrControl, int id, LocalDate fecha, LocalTime hora, int nmrCel, double monto) {
		
		super(nmrControl,id,fecha,hora);
		this.numeroCelular= nmrCel;
		this.monto=monto;
	}


	public double getMonto() {
		// TODO Auto-generated method stub
		return monto;
	}


	public int getCelular() {
		// TODO Auto-generated method stub
		return numeroCelular;
	}
	
	
}
