package sEM_Multa;

import java.time.LocalDate;
import java.time.LocalTime;

public class Multa {
	
	private static int id;
	
	private int idMulta;
	private String patente;
	private int IdZonaDeEstacionamiento;
	private LocalDate fecha;
	private LocalTime hora;
	private int IdInspector;
	private boolean vigencia;
	
	
	/**
	 * 
	 * @param patente
	 * @param idZonaDeEstacionamiento
	 * @param fecha
	 * @param hora
	 * @param idInspector
	 */
	public Multa( String patente, int idZonaDeEstacionamiento, LocalDate fecha, LocalTime hora, int idInspector) {
		super();
		this.idMulta = Multa.generarId();
		this.patente = patente;
		IdZonaDeEstacionamiento = idZonaDeEstacionamiento;
		this.fecha = fecha;
		this.hora = hora;
		IdInspector = idInspector;
		this.vigencia = true;
	}


	public int getIdMulta() {
		return idMulta;
	}


	public String getPatente() {
		return patente;
	}


	public int getIdZonaDeEstacionamiento() {
		return IdZonaDeEstacionamiento;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public LocalTime getHora() {
		return hora;
	}


	public int getIdInspector() {
		return IdInspector;
	}


	public boolean getVigencia() {
		return vigencia;
	}
	
	
	public void cancelarMulta()
	{
		this.vigencia = false;
	}
	
	public static int generarId()
	{
		id += 1;
		return id;
	}

}
