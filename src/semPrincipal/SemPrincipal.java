package semPrincipal;

import estacionamiento.ISemEstacionamiento;
import estacionamiento.SemEstacionamiento;
import sEM_Multa.ISemMulta;
import sEM_Multa.SemMultas;
import semAlertas.ISemAlertas;
import semAlertas.SemAlertas;
import semZonas.ISemZonaDeEstacionamiento;
import semZonas.SemZonasEstacionamiento;
import sem_usuario.ISemUsuarios;
import sem_usuario.SemUsuarios;

public class SemPrincipal implements ISemPrincipal{

	private ISemEstacionamiento semEstacionamiento;
	private ISemZonaDeEstacionamiento semZonas;
	private ISemUsuarios semUsuarios;
	private ISemAlertas semAlertas;
	private ISemMulta semMultas;

	public SemPrincipal() {
		this.semEstacionamiento=new SemEstacionamiento();
		this.semAlertas=new SemAlertas();
		this.semZonas=new SemZonasEstacionamiento();
		this.semUsuarios= new SemUsuarios(this);
		this.semMultas= new SemMultas(this);
	}

	public ISemEstacionamiento getSemEstacionamiento() {
		return semEstacionamiento;
	}

	public ISemZonaDeEstacionamiento getSemZonas() {
		return semZonas;
	}

	public ISemUsuarios getSemUsuarios() {
		return semUsuarios;
	}

	public ISemAlertas getSemAlertas() {
		return semAlertas;
	}

	public ISemMulta getSemMultas() {
		return semMultas;
	}

}
