package appCelularEstacionamiento;

public class Automatico extends ModoApp{


	@Override
	public void alertaDeInicioDeEstacionamiento(AppCelularEstacionamiento app) {
		
		app.iniciarEstacionamiento();
	}

	@Override
	public void alertaDeFinDeEstacionamiento(AppCelularEstacionamiento app) {
		app.finalizarEstacionamiento();
	}

}
