package semPrincipal;

import estacionamiento.SemEstacionamiento;
import sEM_Multa.SemMultas;
import semAlertas.SemAlertas;
import semZonas.SemZonasEstacionamiento;
import sem_usuario.SemUsuarios;

public interface ISemPrincipal {
	
	public SemEstacionamiento getSemEstacionamiento();
	
	public SemZonasEstacionamiento getSemZonas();
	
	public SemUsuarios getSemUsuarios();
	
	public SemAlertas getSemAlertas();
	
	public SemMultas getSemMultas();
	
}
