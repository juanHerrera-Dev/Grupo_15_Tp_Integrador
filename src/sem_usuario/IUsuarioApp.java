package sem_usuario;

public interface IUsuarioApp {

	public String consultarSaldo();
	public String iniciarEstacionamiento(String patente, int idZonaDeEstacionamiento);
	public String finalizarEstacionamiento();
	
	
	
}
