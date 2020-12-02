package estacionamiento;

import java.time.LocalTime;
import java.util.ArrayList;

import semPrincipal.SemPrincipal;
 
public class SemEstacionamiento implements ISemEstacionamiento {

	ArrayList<Estacionamiento> estacionamientos = new ArrayList<Estacionamiento>();
	
	//falta constructor
	
	


	public boolean consultarEstacionamiento(String patente, LocalTime ahora) {
		
		boolean vigencia = false;
		
		for(Estacionamiento estacionamiento:this.getEstacionamientos())
		{
			if(estacionamiento.getPatente() == patente)
			{
				vigencia = estacionamiento.estacionamientoVigente(ahora);
			}
		}
		
		return vigencia;
	}

	
	private ArrayList<Estacionamiento> getEstacionamientos()
	{
		return this.estacionamientos;
	}
	
	@Override
	public void guardarEstacionamiento(Estacionamiento unEstacionamiento) {
		
		this.getEstacionamientos().add(unEstacionamiento);
	}
	
	public int cantidadDeEstacionamientos()
	{
		return this.getEstacionamientos().size();
	}

	public void finalizarEstacionamientos() {
		
		for(Estacionamiento estacionamiento:this.getEstacionamientos())
		{
			estacionamiento.finalizarEstacionamiento();
		}
		
	}
	

}
