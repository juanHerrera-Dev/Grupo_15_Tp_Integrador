package zonaDeEstacionamiento;

public class Inspector {
	int dni;
	String nombre;
	
	public Inspector(int dni, String nombre)
	{
		this.setDni(dni);
		this.setNombre(nombre);
	}
	
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
