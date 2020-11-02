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
		this.setHoraDeFinalizacion(LocalTime.of(LocalTime.now().getHour() + this.getCantidadDeHoras(), LocalTime.now().getMinute()));
		
	}

	@Override
	public boolean estacionamientoVigente() {
		
		boolean vigencia = this.getHoraDeFinalizacion().isAfter(this.horaActual());
		
		return vigencia;
	}
}
