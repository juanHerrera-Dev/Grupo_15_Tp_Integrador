package semZonas;

import java.util.ArrayList;
import java.util.List;

import zonaDeEstacionamiento.ZonaDeEstacionamiento;

public class SemZonasEstacionamiento {
	
	private List<ZonaDeEstacionamiento> zonas;
	
	public SemZonasEstacionamiento() {
		this.zonas= new ArrayList<ZonaDeEstacionamiento>();
	}
	
	

	public List<ZonaDeEstacionamiento> getZonas() {
		
		return this.zonas;
	}

	public void registrarZona(ZonaDeEstacionamiento zona) {
		/**
		 * use el if porque no veo posibilidad de crecimiento en este error de duplicidad de elementos en el array zonas.
		 * en el caso es que tendria que hacer otras acciones usaria el patron state (proveedor de estados para actuar adecuadamente)
		 * */
		if(this.zonas.contains(zona)) {
			throw new IllegalArgumentException("la zona ya fue registrada");
		}
		else {
				
			this.zonas.add(zona);
		}
	}

	public int buscarIdZona(int idZona) {
		
		boolean existeLaZona=false;
		for(ZonaDeEstacionamiento zona:this.zonas) {
			
			if(zona.getId()==idZona) {
				existeLaZona=true;
			}
		}
		if(existeLaZona=true) {
			return idZona;
		}
		else {throw new IllegalArgumentException("no existe zona con ese id");}
	}

}
