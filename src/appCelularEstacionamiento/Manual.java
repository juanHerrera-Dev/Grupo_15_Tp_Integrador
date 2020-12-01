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




}
