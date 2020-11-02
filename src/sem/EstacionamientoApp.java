package sem;

import java.time.LocalTime;

public class EstacionamientoApp extends Estacionamiento {
	



	int numeroDeCelular;
	LocalTime horarioMaximo;
	
	public EstacionamientoApp(String unaPatente, int IDZonaDeEstacionamiento,int unNumeroDeCelular) {
		super(unaPatente, IDZonaDeEstacionamiento);
		this.setNumeroDeCelular(unNumeroDeCelular);
	}
	
	



	public void setNumeroDeCelular(int unNumeroDeCelular)
	{
		this.numeroDeCelular = unNumeroDeCelular;
	}

	
	public int getNumeroDeCelular()
	{
		return this.numeroDeCelular;
	}
	
	
	@Override
	public void establecerHoraDeFinDeEstacionamiento()
	{
		this.setHoraDeFinalizacion(this.horaActual());
	}


	@Override
	public boolean estacionamientoVigente() {
		
		return horarioMaximo.isAfter(this.horaActual());
	}
	
	
	public void finalizarEstacionamiento()
	{
		this.setHorarioMaximo(this.horaActual());
		this.establecerHoraDeFinDeEstacionamiento();
	}
	
	//tener en cuenta que el usuario puede recargar credito.
	public void setHorarioMaximo(LocalTime unHorario)
	{
		this.horarioMaximo = unHorario;
	}

}
