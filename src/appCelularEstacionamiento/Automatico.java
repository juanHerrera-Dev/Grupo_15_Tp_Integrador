package appCelularEstacionamiento;

public class Automatico extends ModoApp{


	@Override
	public String alertaDeInicioDeEstacionamiento(AppCelularEstacionamiento app) {
		
		
		
		return app.getUsuario().iniciarEstacionamiento(app.getPatente(),app.getIdZonaDeEstacionamiento());
	}

	

	@Override
	public String alertaDeFinDeEstacionamiento(AppCelularEstacionamiento app) {
		
		return app.getUsuario().finalizarEstacionamiento();
	}



	@Override
	public String iniciarEstacionamiento(String patente, int idZonaDeEstacionamiento,AppCelularEstacionamiento appCelularEstacionamiento) {
		
		return "No es posible iniciar estacionamiento de forma manual en modo automatico.Cambia de modo";
	}



	@Override
	public String finalizarEstacionamiento(AppCelularEstacionamiento app) {
		
		return "No es posible finalizar estacionamiento de forma manual en modo automatico.Cambia de modo";
	}

}
