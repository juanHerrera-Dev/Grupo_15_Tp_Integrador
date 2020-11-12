package appCelularEstacionamiento;

public class EnAuto extends EstadoApp {
	
	public void caminando(AppCelularEstacionamiento app)
	{
		app.setEstadoAPie();
		app.alertaDeInicioDeEstacionamiento();
	}
	
	public void enAuto(AppCelularEstacionamiento app) {}

}
