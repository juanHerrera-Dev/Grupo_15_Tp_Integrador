package appCelularEstacionamiento;

public abstract class ModoApp {

	public abstract String alertaDeInicioDeEstacionamiento(AppCelularEstacionamiento app);
	public abstract String alertaDeFinDeEstacionamiento(AppCelularEstacionamiento app);
	public abstract String iniciarEstacionamiento(String patente, int idZonaDeEstacionamiento,AppCelularEstacionamiento app);
	public abstract String finalizarEstacionamiento(AppCelularEstacionamiento app);
	
}
