 package estacionamiento;

import java.time.LocalTime;

public class EstacionamientoApp extends Estacionamiento {
	



	int numeroDeCelular; 
	LocalTime horarioMaximo;
	double costoPorHora;
																									
	public  EstacionamientoApp(String unaPatente, int IDZonaDeEstacionamiento,int unNumeroDeCelular, int minutos)
	{
		super(unaPatente, IDZonaDeEstacionamiento);
		this.setNumeroDeCelular(unNumeroDeCelular);
		this.setHorarioMaximo(LocalTime.now());
		this.actualizarHorarioMaximo(minutos);
	}
	
	

	
	public void actualizarHorarioMaximo(int plusMinutos)
	{
		//Si no esta en el horario de funcionamieno no hace falta actualizar nada, ademas previene de posibles bug si se llama al metodo fuera del horario de funcionamiento
		if(super.dentroDelHorarioDeFuncionamiento(LocalTime.now()))
		{
			int minutosMaximosDeEstacionamiento = (super.getFinDeJornada().getHour() - this.getHorarioMaximo().getHour()) * 60 + (super.getFinDeJornada().getMinute() - this.getHorarioMaximo().getMinute());
			
			if(plusMinutos > minutosMaximosDeEstacionamiento)
			{
				this.setHorarioMaximo(super.getFinDeJornada());
			}
			else
			{
				this.setHorarioMaximo(this.getHorarioMaximo().plusMinutes(plusMinutos));
			}
		}
		
		
		if(this.getHorarioMaximo().isBefore(LocalTime.of(20, 00)))
		{
			this.getHorarioMaximo().plusMinutes(plusMinutos);
		}
				
	}
	
	
	public LocalTime getHorarioMaximo()
	{
		return this.horarioMaximo;
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
		this.setHoraDeFinalizacion(LocalTime.now());
	}


	@Override
	public boolean estacionamientoVigente(LocalTime horaActual) {
		
		return horarioMaximo.isAfter(horaActual);
	}
	
	
	
	@Override
	public void finalizarEstacionamiento()
	{
		this.setHorarioMaximo(LocalTime.now());
		this.establecerHoraDeFinDeEstacionamiento();
	}
	
	//tener en cuenta que el usuario puede recargar credito.
	public void setHorarioMaximo(LocalTime unHorario)
	{
		this.horarioMaximo = unHorario;
	}

	public void setCostorPoHora(double unCostoPorHora)
	{
		this.costoPorHora = unCostoPorHora;
	}
	
	public double getCostoPorHora()
	{
		return this.costoPorHora;
	}
	
	public double costo ()
	{
		int minutosEstacionados =  (LocalTime.now().getHour() - super.getHoraDeInicio().getHour()) * 60;
		
		minutosEstacionados    +=  (LocalTime.now().getMinute() - super.getHoraDeInicio().getMinute());
		
		return (this.costoPorHora/60)*minutosEstacionados;

	}




	public int getDuracion() {
		
		int duracionHoras=(super.getHoraDeFinalizacion().getHour() - super.getHoraDeInicio().getHour());
		return duracionHoras;
	}




	
}
