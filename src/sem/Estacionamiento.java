package sem;

import java.time.LocalTime;


//si bien la implementacion de localTime esta expuesta a las clases hijas solo interactuan por medio de los metodos de esta clase.
public abstract class Estacionamiento {
	
	private String patente;
	private LocalTime horaDeInicio;
	private LocalTime horaDeFinalizacion;
	private int zonaDeEstacionamiento;
	
	
	
	// ORDENADO DE LA SIGUIENTE MANERA ABSTRACT, PRIVATE, PROTECTED, PUBLIC
	
	//ABSTRACT
	
	abstract public void establecerHoraDeFinDeEstacionamiento();
	
	abstract public boolean estacionamientoVigente(LocalTime horaActual);
	
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
			System.out.println("La patente no puede estar vacia");
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
		this.zonaDeEstacionamiento = unId;
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
	
	//CONSTRUCTORES
	public Estacionamiento(String unaPatente, int IDZonaDeEstacionamiento)
	{
		this.setHoraDeInicio();;
		this.setPatente(unaPatente);
		this.setZonaDeEstacionamiento(IDZonaDeEstacionamiento);
	}
}
