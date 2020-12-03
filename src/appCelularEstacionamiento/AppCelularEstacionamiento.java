package appCelularEstacionamiento;

import semPrincipal.ISemPrincipal;
import semZonas.ISemZonaDeEstacionamiento;

import sem_usuario.IUsuarioApp;
import sem_usuario.ISemUsuarios;



public class AppCelularEstacionamiento implements MovementSensor{
	
	
	private int numeroDeCelular;
	private String patente;
	private IUsuarioApp iUsuarioApp;
	private ISemPrincipal semPrincipal;
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
	public AppCelularEstacionamiento(int unNumeroDeCelular, ISemPrincipal semPrincipal,ModoApp modoapp,EstadoApp estadoApp)//,IUsuarioApp unUsuario )
	{
		this.numeroDeCelular = unNumeroDeCelular;
		this.semPrincipal=semPrincipal;
		this.modoApp = modoapp;
		this.estado = estadoApp;
		this.iUsuarioApp = getSemUsuarios().getIUsuario(unNumeroDeCelular);
	}


	

	private ISemUsuarios getSemUsuarios() {
		return this.semPrincipal.getSemUsuarios();
	}
	
	

	
	public String iniciarEstacionamiento(String patente) {
		/**
		 * este mensaje solo puede ser enviado en modo manual por lo que no chequeamos con si esta en estado manual o automatico
		 * lo que desencadena los estacionamientos en modo automatico son las alertas , y este mensaje seria el de confirmacion
		 * despues de recibir la alertas.
		 * 
		 * */
		//return iUsuarioApp.iniciarEstacionamiento(patente, idZonaDeEstacionamiento);
		return modoApp.iniciarEstacionamiento(patente, this.getIdZonaDeEstacionamiento(),this);
	}
	

	
	public String finalizarEstacionamiento() {
		
		return modoApp.finalizarEstacionamiento(this);
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


	
	public int getNumeroDeCelular()
	{
		return this.numeroDeCelular;
	}
	
	public String getPatente() {
		return patente;
	}


	
	
	
	
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

	
	public int getIdZonaDeEstacionamiento()
	{
		return getZonaDeEstacionamiento().buscarIdZona(this.consultarCoordenadasAlGPS());
	}




	private ISemZonaDeEstacionamiento getZonaDeEstacionamiento() {
		return this.semPrincipal.getSemZonas();
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




	public IUsuarioApp getUsuario() {
		
		return this.iUsuarioApp;
	}




	public EstadoApp getEstado() {
		
		return this.estado;
	}

	

}
