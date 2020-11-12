package appCelularEstacionamiento;

import sem_usuario.IUsuarioApp;
import sem_usuario.IUsuarioSEM;
import sem_usuario.IZonaDeEstacionamiento;


public class AppCelularEstacionamiento implements MovementSensor{
	
	
	private int numeroDeCelular;
	private String patente;
	private IUsuarioApp iUsuarioApp;
	private IUsuarioSEM iUsuarioSEM;
	private IZonaDeEstacionamiento iZona;
	//Tipos de modo: automatico / manual
	private ModoApp modoApp;
	//Tipos de estado: APie o EnAuto 
	private EstadoApp estado; 
	
	/**
	 * Precondicion el usuario ya existe en la sem antes de instanciar la app.
	 * @param unNumeroDeCelular
	 * @param unIUsuarioApp
	 * @param unIUSuarioSEM
	 * @param unaIZona
	 */
	public AppCelularEstacionamiento(int unNumeroDeCelular, IUsuarioSEM unIUSuarioSEM, IZonaDeEstacionamiento unaIZona)//,IUsuarioApp unUsuario )
	{
		this.numeroDeCelular = unNumeroDeCelular;
		this.iUsuarioSEM = unIUSuarioSEM;
		this.iZona = unaIZona;
		this.modoApp = new Manual();
		this.estado = new APie();
		this.iUsuarioApp = this.iUsuarioSEM.getIUsuario(unNumeroDeCelular);
	}
	
	

	
	public String iniciarEstacionamiento(String patente, int idZonaDeEstacionamiento) {
		
		return iUsuarioApp.iniciarEstacionamiento(patente, idZonaDeEstacionamiento);
		
	}
	

	
	public String finalizarEstacionamiento() {
		
		return iUsuarioApp.finalizarEstacionamiento();	
	}
	
	public String consultarSaldo()  
	{
		return iUsuarioApp.consultarSaldo();
	}
	
	//
	private void setPatente(String unaPatente) {
		
		this.patente = unaPatente;
	}
	
	//Solo se usa para averiguar el idZonaDeEstacionamiento
	private int consultarCoordenadasAlGPS()
	{
		return 100;
	}

 //FALTAN EN EL UML
	
	public int getNumeroDeCelular()
	{
		return this.numeroDeCelular;
	}
	

	
	
	
	
	
	/*
	 * inicioDe
	 * fin
	 * consulta
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	//*******************************************************************
	// MODOS DE LA APP, AUTO/MANUAL, ALERTAS, E IINTERFACE MOVEMENTSENSOR
	//*******************************************************************
	
	/**
	 * Cambia el estado para poder recibir o no alertas de estacionamiento
	 */
	
	
	
	
	//ESTADOS: APIE O EN AUTO
	public void setEstadoAPie()
	{
		this.estado = new APie();
	}
	
	public void setEstadoEnAuto()
	{
		this.estado = new EnAuto();
	}
	
	
	
	//MODOS: AUTOMATICO O MANUAL
	
	public void setModoManual()
	{
		this.modoApp = new Manual();
	}
	
	
	public void setModoAutomatico(String unaPatente)
	{
		this.setPatente(unaPatente);
		this.modoApp = new Automatico();
	}
	
	//Uso exclusivo del modo Automatico
	public String iniciarEstacionamiento()
	{
		int idZonaDeEstacionamiento = this.iZona.buscarIdZona(this.consultarCoordenadasAlGPS());
		
		return this.iniciarEstacionamiento(this.patente, idZonaDeEstacionamiento );
	}
	
	//El mensaje lo manda el estado  EnAuto
	public void alertaDeInicioDeEstacionamiento()
	{
		modoApp.alertaDeInicioDeEstacionamiento(this);
	}
	
	//El mensaje lo manda el estado  APie
	public void alertaDeFinDeEstacionamiento() 
	{
		modoApp.alertaDeFinDeEstacionamiento(this);
	}
	

	//El mensaje lo manda el modo  Manual.
	public String alertarInicioDeEstacionamiento()
	{
		return "Alerta. Recuerde iniciar estacionamiento";
	}
	
	
	//El mensaje lo manda el modo  Manual.
	public String alertarFinalizacionDeEstacionamiento()
	{
		return "Alerta. Recuerde finalizar estacionamiento";
	}
	
	
	//INTERFACE MOVENTSENSOR
	@Override
	public void driving() {
		
		 this.estado.enAuto(this);
	}


	@Override
	public void walking() {
		
		this.estado.caminando(this);
		
	}

	// Estos dos metos harian que los mensaje de la inteface MoveSensor lleguen o dejen de llegar
	public void activarMoveSensor() {}
	
	public void desactivarMoveSensor() {}

	

}
