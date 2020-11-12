package sEM_Multa;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import estacionamiento.IEstacionamiento;

public class SEM_Multa implements IMulta{
	
	protected ArrayList<Multa> multas;
	//private static final SEM_Multa SEM_MULTA;
	private final IEstacionamiento SEM_Estacionamiento;
	
	
	
	public  SEM_Multa(IEstacionamiento iEstacionamiento)
	{
		this.multas = new ArrayList<Multa>();
		this.SEM_Estacionamiento = iEstacionamiento;
	}
	
	
	
	private ArrayList<Multa> getMultas()
	{
		return this.multas;
	}
	
	public void agregarMulta(Multa unaMulta)
	{
		this.getMultas().add(unaMulta);
	}
	 
	
	public ArrayList<Multa> buscarMultas(String unaPatente)
	{
		ArrayList<Multa> multasCoincidentes = new ArrayList<Multa>();
		
		for(Multa multa:this.getMultas())
		{
			if(multa.getPatente() == unaPatente)
			{
				multasCoincidentes.add(multa);
			}
		}
		
		return multasCoincidentes;
	}
	
	
	public Multa buscarMulta(int unIdMulta) //throws IOException
	{
	
		Multa multaCoincidente = null;
		
		{
			for(Multa multa:this.getMultas())
			{
				if(multa.getIdMulta() == unIdMulta)
				{
					multaCoincidente = multa;
				}
			}			
		}
		
		return multaCoincidente;
	}
	
	
	public void cancelarMulta(int unIdMulta)
	{

		Multa multa = buscarMulta(unIdMulta);
		
		if (multa != null)
		{
			multa.cancelarMulta();
		}
		else
		{
			System.out.println("IdMulta no existe");
		}
	}
	
	
	public boolean multaVigente(int unIdMulta) throws NullPointerException
	{
		Multa multa = this.buscarMulta(unIdMulta);
		boolean vigencia = false;
		
		try 
		{
			vigencia = multa.getVigencia();	
		}
		catch(NullPointerException ex)
		{
			System.out.println("IdMulta no existe");
		}
		
		return vigencia;
	}
	
	
	public int cantidadDeMultas()
	{
		return this.getMultas().size();
	}

	@Override
	public void registrarMulta(String patente, int idZonaDeEstacionamiento, int idInspector) 
	{
		if(!SEM_Estacionamiento.consultarEstacionamiento(patente, LocalTime.now()))
		{
			Multa unaMulta = this.generarMulta(patente, idZonaDeEstacionamiento, LocalDate.now(), LocalTime.now(), idInspector);
			this.agregarMulta(unaMulta);
		}
		else
		{
			System.out.println("La multa no se puede realizar por que el estacionamiento es valido.");
		}
		
	}
	
	private Multa  generarMulta( String patente, int idZonaDeEstacionamiento, LocalDate fecha, LocalTime hora, int idInspector)
	{
		return new Multa(patente, idZonaDeEstacionamiento, fecha, hora, idInspector);
	}
	
}
