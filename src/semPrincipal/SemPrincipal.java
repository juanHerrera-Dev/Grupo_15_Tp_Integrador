package semPrincipal;

import estacionamiento.SemEstacionamiento;
import sEM_Multa.SemMultas;
import semAlertas.SemAlertas;
import semZonas.SemZonasEstacionamiento;
import sem_usuario.SemUsuarios;

public class SemPrincipal {

	private SemEstacionamiento semEstacionamiento;
	private SemZonasEstacionamiento semZonas;
	private SemUsuarios semUsuarios;
	private SemAlertas semAlertas;
	private SemMultas semMultas;

	public SemPrincipal(SemEstacionamiento semEstacionamiento, SemAlertas semAlertas, SemZonasEstacionamiento semZonas,
			SemUsuarios semUsuarios, SemMultas semMultas) {
		this.semEstacionamiento=semEstacionamiento;
		this.semAlertas=semAlertas;
		this.semZonas=semZonas;
		this.semUsuarios=semUsuarios;
		this.semMultas=semMultas;
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
