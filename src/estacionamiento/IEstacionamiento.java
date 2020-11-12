package estacionamiento;

import java.time.LocalTime;

public interface IEstacionamiento {
	
	public boolean consultarEstacionamiento(String patente, LocalTime ahora);
	public void guardarEstacionamiento(Estacionamiento unEstacionamiento);

}
