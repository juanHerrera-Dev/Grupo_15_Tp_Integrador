package inspector;

import java.time.LocalTime;

import estacionamiento.ISemEstacionamiento;
import sEM_Multa.ISemMulta;
import semPrincipal.ISemPrincipal;

public class InspectorApp {
	private int idInspector;
	private int idZonaDeEstacionamiento;
	private ISemEstacionamiento iestacionamiento;
	private ISemMulta imulta;
	private ISemPrincipal semPrincipal;
	
	public InspectorApp(int unIdInspector, int unIdZonaDeEstacionamiento ,ISemPrincipal semPrincipal)
	{
		this.semPrincipal= semPrincipal;
		this.idInspector = unIdInspector;
		this.idZonaDeEstacionamiento = unIdZonaDeEstacionamiento;
		this.iestacionamiento = this.semPrincipal.getSemEstacionamiento();
		this.imulta =this.semPrincipal.getSemMultas();
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
