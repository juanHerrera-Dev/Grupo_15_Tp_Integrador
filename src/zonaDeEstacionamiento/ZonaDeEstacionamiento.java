package zonaDeEstacionamiento;

import java.util.ArrayList;

import ventas.PuntoDeVenta;

public class ZonaDeEstacionamiento {
	
	private int id;
	private Inspector inspector;
	private ArrayList<PuntoDeVenta> puntosDeVenta;
	
	public ZonaDeEstacionamiento(int id, Inspector inspector) {
		this.id=id;
		this.inspector=inspector;
		this.puntosDeVenta= new ArrayList<PuntoDeVenta>();
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
 
	public void registrarPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		/**
		 *en este metodo de setea el id De Zona por decision de diseño 
		 * */
		if(this.puntosDeVenta.contains(puntoDeVenta)) {
			throw new IllegalArgumentException("el punto de venta ya esta registrado");
		}
		else {
				puntoDeVenta.setIdZona(this.id);
				this.puntosDeVenta.add(puntoDeVenta);
		}
	}

	public Inspector getInspector() {
		// TODO Auto-generated method stub
		return this.inspector;
	}

	public ArrayList<PuntoDeVenta> getPuntosDeVentas() {
		// TODO Auto-generated method stub
		return this.puntosDeVenta;
	}

}
