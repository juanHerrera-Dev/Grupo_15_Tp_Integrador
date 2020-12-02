package semAlertas;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SemAlertas implements ISemAlertas {
	
	
	private List<Suscriptor> suscriptores;


	public SemAlertas() {
		this.suscriptores=new ArrayList<Suscriptor>();
	}
	
	
	public List<Suscriptor> getSuscriptores() {
		// TODO Auto-generated method stub
		return this.suscriptores;
	}


	public void agregarSuscriptor(Suscriptor suscriptor) {
		if(this.suscriptores.contains(suscriptor)) {
			throw new IllegalArgumentException("el suscriptor ya esta registrado");
		}
		else {
			this.suscriptores.add(suscriptor);
		}
	}


	public void quitarSuscriptor(Suscriptor suscriptor) {
		
		this.suscriptores.remove(suscriptor);
		
	}


	public void alertaInicioDeEstacionamiento(int nmrCelular, double monto, int zonaId, LocalTime horaEstacionamiento) {
		
		for(Suscriptor suscriptor:this.suscriptores) {
			suscriptor.recibirAlertaInicioE(nmrCelular,monto,zonaId,horaEstacionamiento);
		}
		
	}


	public void alertaFinDeEstacionamiento(int nmrCelular, double monto, int zonaId, LocalTime horaFinEstacionamiento) {
		
		for(Suscriptor suscriptor:this.suscriptores) {
			suscriptor.recibirAlertaFinE(nmrCelular,monto,zonaId,horaFinEstacionamiento);
		}
		
	}


	public void alertaRecargaDeSaldo(int nmrCelular, double monto, LocalTime horaRecarga) {
		
		for(Suscriptor suscriptor:this.suscriptores) {
			suscriptor.recibirAlertaRecarga(nmrCelular,monto,horaRecarga);
		}
		
	}
	
}
