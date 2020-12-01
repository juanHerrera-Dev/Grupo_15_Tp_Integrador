package appCelularEstacionamiento;

public class Automatico extends ModoApp{


	@Override
	public String alertaDeInicioDeEstacionamiento(AppCelularEstacionamiento app) {
		
		String patente   = app.getPatente();
		int idZonaEst = app.getIdZonaDeEstacionamiento();
		
		return app.iniciarEstacionamiento(patente, idZonaEst);
	}

	

	@Override
	public String alertaDeFinDeEstacionamiento(AppCelularEstacionamiento app) {
		
		return app.finalizarEstacionamiento();
	}

}
