package semPrincipal;

import estacionamiento.ISemEstacionamiento;

import sEM_Multa.ISemMulta;

import semAlertas.ISemAlertas;

import semZonas.ISemZonaDeEstacionamiento;

import sem_usuario.ISemUsuarios;


public interface ISemPrincipal {
	
	public ISemEstacionamiento getSemEstacionamiento();
	
	public ISemZonaDeEstacionamiento getSemZonas();
	
	public ISemUsuarios getSemUsuarios();
	
	public ISemAlertas getSemAlertas();
	
	public ISemMulta getSemMultas();
	
}
