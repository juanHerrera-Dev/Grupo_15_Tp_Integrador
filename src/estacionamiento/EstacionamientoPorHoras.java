package estacionamiento;

import java.time.LocalTime;

public class EstacionamientoPorHoras extends Estacionamiento {
	


	LocalTime cantidadDeHoras;
	
	public EstacionamientoPorHoras(String unaPatente, int IDZonaDeEstacionamiento, LocalTime unaCantidadDeHoras) {
		super(unaPatente, IDZonaDeEstacionamiento);
		this.setCantidadDeHoras(unaCantidadDeHoras);
		this.establecerHoraDeFinDeEstacionamiento();
		
	}

	private void setCantidadDeHoras(LocalTime unaCantidadDeHoras)
	{
		this.cantidadDeHoras = unaCantidadDeHoras;
	}

	private  LocalTime  getCantidadDeHoras()
	{
		return cantidadDeHoras;
	}
	
	
	@Override
	public void establecerHoraDeFinDeEstacionamiento() {
		this.setHoraDeFinalizacion(LocalTime.now().plusHours(this.getCantidadDeHoras().getHour()));
		
	}

	@Override
	public boolean estacionamientoVigente(LocalTime horaActual) {
		
		boolean vigencia = this.getHoraDeFinalizacion().isAfter(horaActual);
		
		return vigencia;
	}

	@Override
	public void finalizarEstacionamiento() {
		// TODO Auto-generated method stub
		
	}
}
