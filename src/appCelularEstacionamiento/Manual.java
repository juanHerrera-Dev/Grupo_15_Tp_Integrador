package appCelularEstacionamiento;

public class Manual extends ModoApp{

	
	@Override
	public void alertaDeInicioDeEstacionamiento(AppCelularEstacionamiento app) {
		
		app.alertarInicioDeEstacionamiento();
	}
	
	
	
	@Override
	public void alertaDeFinDeEstacionamiento(AppCelularEstacionamiento app) {
		
		app.alertarFinalizacionDeEstacionamiento();
	}



}
