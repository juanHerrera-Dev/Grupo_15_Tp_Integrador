package semPrincipal;

import estacionamiento.SemEstacionamiento;
import sEM_Multa.SemMultas;
import semAlertas.SemAlertas;
import semZonas.SemZonasEstacionamiento;
import sem_usuario.SemUsuarios;

public class SemPrincipal implements ISemPrincipal{

	private SemEstacionamiento semEstacionamiento;
	private SemZonasEstacionamiento semZonas;
	private SemUsuarios semUsuarios;
	private SemAlertas semAlertas;
	private SemMultas semMultas;

	public SemPrincipal() {
		this.semEstacionamiento=new SemEstacionamiento();
		this.semAlertas=new SemAlertas();
		this.semZonas=new SemZonasEstacionamiento();
		this.semUsuarios= new SemUsuarios(this);
		this.semMultas= new SemMultas(this);
	}

	public SemEstacionamiento getSemEstacionamiento() {
		return semEstacionamiento;
	}

	public SemZonasEstacionamiento getSemZonas() {
		return semZonas;
	}

	public SemUsuarios getSemUsuarios() {
		return semUsuarios;
	}

	public SemAlertas getSemAlertas() {
		return semAlertas;
	}

	public SemMultas getSemMultas() {
		return semMultas;
	}

}
