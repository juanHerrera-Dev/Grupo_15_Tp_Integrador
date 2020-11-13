package estacionamiento;

import java.time.LocalTime;


//si bien la implementacion de localTime esta expuesta a las clases hijas solo interactuan por medio de los metodos de esta clase.
public abstract class Estacionamiento {
	
	private String patente;
	private LocalTime horaDeInicio;
	private LocalTime horaDeFinalizacion;
	private int idZonaEstacionamiento;
	//InicioDeJornada es la hora a la que empieza a funcionar el estacionamiento (fuera de la jornada no se registraran estacionamientos)
	private LocalTime inicioDeJornada = LocalTime.of(7, 00);
	//FinDeJornada es la hora a la que empieza a funcionar el estacionamiento (fuera de la jornada no se registraran estacionamientos)
	private LocalTime FinDeJornada = LocalTime.of(20, 00);
	
	
	
	//CONSTRUCTOR/ES
	public Estacionamiento(String unaPatente, int IDZonaDeEstacionamiento)
	{
		if(this.dentroDelHorarioDeFuncionamiento(LocalTime.now()))
		{
			this.setHoraDeInicio();
			this.setPatente(unaPatente);
			this.setZonaDeEstacionamiento(IDZonaDeEstacionamiento);			
		}
	}
	
	
	//ABSTRACT 
	
	abstract public void establecerHoraDeFinDeEstacionamiento();
	
	abstract public boolean estacionamientoVigente(LocalTime horaActual);
	
	abstract public void finalizarEstacionamiento();
	
	//PRIVATE
	
	
	//PROTECTED
	
	protected void setPatente(String unaPatente)
	{
		if (!unaPatente.isEmpty())
		{
			this.patente = unaPatente.toLowerCase().replace(" ", "");
		}
		else
		{
			throw new IllegalArgumentException("La patente no puede estar vacia");
		}
	}
	
	
	protected void setHoraDeInicio()
	{
		this.horaDeInicio = LocalTime.now();
	}
	
	protected void setHoraDeFinalizacion(LocalTime unaHora)
	{
		this.horaDeFinalizacion = unaHora;
	}
	
	
	protected void setZonaDeEstacionamiento(int unId)
	{
		this.idZonaEstacionamiento = unId;
	}
	
	
	//No se me ocurre como no darle acceso a la variable, hasta ahora lo habia logrado pero
	//se va complicando
	
	protected LocalTime getHoraDeFinalizacion()
	{
		return horaDeFinalizacion;
	}
	
	 
    //PUBLIC
	 
	public String getPatente()
	{
		return patente;
	}
	 

	
	public boolean dentroDelHorarioDeFuncionamiento(LocalTime ahora)
	{
		return this.getInicioDeJornada().isBefore(ahora) && this.getFinDeJornada().isAfter(ahora);
	}
	
	public LocalTime getHoraDeInicio()
	{
		return this.horaDeInicio;
	}
	
	public LocalTime getInicioDeJornada()
	{
		return this.inicioDeJornada;
	}
	
	public LocalTime getFinDeJornada()
	{
		return this.FinDeJornada;
	}
	
	public void setInicioDeJornada(LocalTime horaDeInicio)
	{
		this.inicioDeJornada = horaDeInicio;
	}
	
	public void setFinDeJornada(LocalTime horaDeFinalizacion)
	{
		this.FinDeJornada = horaDeFinalizacion;
	}
	public int getZonaId() {
		
		return this.idZonaEstacionamiento;
	}
}
