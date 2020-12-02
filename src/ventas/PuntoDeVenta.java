package ventas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import estacionamiento.EstacionamientoPorHoras;
import estacionamiento.ISemEstacionamiento;
import semPrincipal.ISemPrincipal;
import sem_usuario.IUsuarioSEM;



public class PuntoDeVenta {

	private int id;
	private String nombre;
	private String dir;
	private ISemPrincipal semPrincipal;
	private int id_zona;
	private ArrayList<Venta> ventas;
	private IUsuarioSEM semUsuarios;
	
	public PuntoDeVenta(int id, String nombre, String dir, ISemPrincipal semPrincipal) {
		
		this.id=id;
		this.nombre=nombre;
		this.dir=dir;
		this.semPrincipal=semPrincipal;
		this.ventas= new ArrayList<Venta>();
	}

	public int getID() {
		
		return this.id;
	}

	public String getDireccion() {
		
		return this.dir;
	}

	public String getNombre() {
		
		return this.nombre;
	}


	public IUsuarioSEM getIRecarga() {
		
		semUsuarios=this.semPrincipal.getSemUsuarios();
	
		return semUsuarios;
	}

	public ISemEstacionamiento getIEstacionamiento() {
		
		return this.semPrincipal.getSemEstacionamiento();
	}

	public void setIdZona(int idZona) {
		
		 this.id_zona=idZona;
	}

	public int getIdZona() {
		// TODO Auto-generated method stub
		return this.id_zona;
	}

	public ArrayList<Venta> getVentas() {
		
		return this.ventas;
	}

	public void venderHoras(String patente, LocalTime cantHoras) {
		
		this.ventas.add(new	VentaPuntual(generarNmrControl(), this.id, LocalDate.now(), LocalTime.now(), cantHoras));
		
		this.getIEstacionamiento().guardarEstacionamiento(new EstacionamientoPorHoras(patente,this.id_zona, cantHoras));
		//this.iSemEstacionamiento.guardarEstacionamiento();
	}

	private int generarNmrControl() {
		/**
		 * este metodo junta el id de zona con el id de puntoDeVenta para generar un nmrDeControl
		 * */
		
		return Integer.parseInt(String.valueOf(this.id_zona)+String.valueOf(this.id));
	}

	public void hacerRecarga(int nmrCelular, double montoARecargar) {
		
		this.ventas.add(new	VentaRecarga(generarNmrControl(), this.id, LocalDate.now(), LocalTime.now(),nmrCelular,montoARecargar));
		this.getIRecarga().recargarCredito(nmrCelular,montoARecargar);
	}

	public ISemPrincipal getSemPrincipal() {
		
		return this.semPrincipal;
	}

	

}
