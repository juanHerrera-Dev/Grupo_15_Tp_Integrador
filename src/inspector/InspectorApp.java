package inspector;

import java.time.LocalTime;

import estacionamiento.IEstacionamiento;
import sEM_Multa.IMulta;

public class InspectorApp {
	private int idInspector;
	private int idZonaDeEstacionamiento;
	private IEstacionamiento iestacionamiento;
	private IMulta imulta;
	
	public InspectorApp(int unIdInspector, int unIdZonaDeEstacionamiento, IEstacionamiento unaIestacionamiento, IMulta unaIMulta)
	{
		this.idInspector = unIdInspector;
		this.idZonaDeEstacionamiento = unIdZonaDeEstacionamiento;
		this.iestacionamiento = unaIestacionamiento;
		this.imulta =unaIMulta;
	}
	
	
	
	public int getIdInspector() {
		return idInspector;
	}



	public int getIdZonaDeEstacionamiento() {
		return idZonaDeEstacionamiento;
	}



	public void consultarEstacionamiento(String patente, LocalTime ahora)
	{
		iestacionamiento.consultarEstacionamiento(patente, ahora);
	}
	
	
	
	public void registrarMulta(String patente, int idZonaDeEstacionamiento, int idInspector)
	{
		imulta.registrarMulta(patente, idZonaDeEstacionamiento, idInspector);
	}
}
