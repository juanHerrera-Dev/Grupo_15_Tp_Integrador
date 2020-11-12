package appCelularEstacionamiento;

public abstract class EstadoApp {
	

	//public abstract void cambiarEstado(AppCelularEstacionamiento app);

	protected abstract void enAuto(AppCelularEstacionamiento appCelularEstacionamiento);

	protected abstract void caminando(AppCelularEstacionamiento appCelularEstacionamiento);

}
