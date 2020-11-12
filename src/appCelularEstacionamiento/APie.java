package appCelularEstacionamiento;

public class APie extends EstadoApp {
	
	
	
	public void caminando(AppCelularEstacionamiento app) {}
	
	public void enAuto(AppCelularEstacionamiento app)
	{
		app.setEstadoEnAuto();
		app.alertaDeFinDeEstacionamiento();
	}
}
