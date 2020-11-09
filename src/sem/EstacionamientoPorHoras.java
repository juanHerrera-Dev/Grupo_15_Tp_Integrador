package sem;

import java.time.LocalTime;

public class EstacionamientoPorHoras extends Estacionamiento {
	


	int cantidadDeHoras;
	
	public EstacionamientoPorHoras(String unaPatente, int IDZonaDeEstacionamiento, int unaCantidadDeHoras) {
		super(unaPatente, IDZonaDeEstacionamiento);
		this.setCantidadDeHoras(unaCantidadDeHoras);
		this.establecerHoraDeFinDeEstacionamiento();
		
	}

	private void setCantidadDeHoras(int unaCantidadDeHoras)
	{
		this.cantidadDeHoras = unaCantidadDeHoras;
	}

	private  int  getCantidadDeHoras()
	{
		return cantidadDeHoras;
	}
	
	
	@Override
	public void establecerHoraDeFinDeEstacionamiento() {
		this.setHoraDeFinalizacion(LocalTime.now().plusHours(this.getCantidadDeHoras()));
		
	}

	@Override
	public boolean estacionamientoVigente(LocalTime horaActual) {
		
		boolean vigencia = this.getHoraDeFinalizacion().isAfter(horaActual);
		
		return vigencia;
	}
}
