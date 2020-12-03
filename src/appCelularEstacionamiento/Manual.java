package appCelularEstacionamiento;

public class Manual extends ModoApp{

	
	@Override
	public String alertaDeInicioDeEstacionamiento(AppCelularEstacionamiento app) {
		
		return "Alerta. Recuerde iniciar estacionamiento";
	}
	
	
	
	@Override
	public String alertaDeFinDeEstacionamiento(AppCelularEstacionamiento app) {
		
		return "Alerta. Recuerde finalizar estacionamiento";
	}



	@Override
	public String iniciarEstacionamiento(String patente, int idZonaDeEstacionamiento,AppCelularEstacionamiento app) {
		
		return app.getUsuario().iniciarEstacionamiento(patente,idZonaDeEstacionamiento);
	}



	@Override
	public String finalizarEstacionamiento(AppCelularEstacionamiento app) {
		
		return app.getUsuario().finalizarEstacionamiento();
	}




}
