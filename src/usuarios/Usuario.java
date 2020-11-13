package usuarios;




import java.time.LocalTime;
import estacionamiento.EstacionamientoApp;
import semAlertas.ISemAlertas;
import semEstacionamientos.IsemEstacionamiento;

public class Usuario {
	
	private double monto;
	private int nmrCelular;
	private ISemAlertas semAlertas;
	private IsemEstacionamiento semEstacionamiento;
	private EstacionamientoApp estacionamientoReciente;
	
	
	public Usuario(ISemAlertas semAlertas, IsemEstacionamiento semEstacionamiento, double monto, int nmrCelular) {
		
		this.monto=monto;
		this.nmrCelular=nmrCelular;
		this.semAlertas=semAlertas;
		this.semEstacionamiento=semEstacionamiento;
	}

	
	public EstacionamientoApp getEstacionamientoVigente() {
		// TODO Auto-generated method stub
		return this.estacionamientoReciente;
	}
	
	public int getNumeroDeCelular() {
		// TODO Auto-generated method stub
		return this.nmrCelular;
	}
	
	public double getCredito() {
		// TODO Auto-generated method stub
		return this.monto;
	}

	public String iniciarEstacionamiento(String patente, int zonaId) {
		/** coloque un if porque no veo posibles cambios con respecto al calculo de si posee saldo positivo o no*/
		if(this.monto>0) {
			//almacenamiento del estacionamiento
			this.estacionamientoReciente= new EstacionamientoApp(patente,zonaId,this.nmrCelular,this.minutosDeEstacionamiento(this.monto));
			//this.horaDeFinDeEstacionamiento()
			//envio de alerta a la semAlertas
			this.semAlertas.alertaInicioDeEstacionamiento(this.nmrCelular,this.monto, zonaId,LocalTime.now());
		
			//almacenamiento del estacionamiento en la semEstacionamiento
			this.semEstacionamiento.guardarEstacionamiento(this.estacionamientoReciente);
		
		
			//notificacion de inicioDeEstacionamiento para la app
			return notificacionDeInicioDeEstacionamiento();
		}
		else {return "Saldo insuficiente. Estacionamiento no permitido.";}
	}


	private int minutosDeEstacionamiento(double montoACargar) {
		
		int minutos=(int)(montoACargar*60)/40;
		
		return minutos;
	}


	private String notificacionDeInicioDeEstacionamiento() {
		
		LocalTime horaInicio= this.estacionamientoReciente.getHoraDeInicio();
		LocalTime horaMaxima= this.estacionamientoReciente.getHorarioMaximo();
		
		return "horaInicio="+horaInicio.getHour()+":"+horaInicio.getMinute()+" "+"horaMaxima="+horaMaxima.getHour()+":"+horaMaxima.getMinute();
	}			
	

	/*private LocalTime horaDeFinDeEstacionamiento() {
		
		LocalTime horaDeFinDeEstacionamiento;
		LocalTime horaActual= LocalTime.now();
		LocalTime horaMaxima= LocalTime.of(20,00);
		
		LocalTime horasRestantes= (horaMaxima.minusHours(horaActual.getHour())).minusMinutes(horaActual.getMinute());
		
		horaDeFinDeEstacionamiento= horaActual.plusHours(horasRestantes.getHour()).minusMinutes(horasRestantes.getMinute());
		//tengo que arreglar el metodo para devolver la hora final.
		return horaDeFinDeEstacionamiento;
	}*/


	
	
	public String finalizarEstacionamiento() {
		
		if(this.estacionamientoReciente.estacionamientoVigente(LocalTime.now())) {	
				this.estacionamientoReciente.finalizarEstacionamiento();
		
				this.semAlertas.alertaFinDeEstacionamiento(this.nmrCelular,this.monto,this.estacionamientoReciente.getZonaId(), LocalTime.now());
		
				return notificacionDeFinDeEstacionamiento();
		}
		else {throw new IllegalArgumentException("el estacionamiento ya fue finalizado");}
	}


	private String notificacionDeFinDeEstacionamiento() {
		
		LocalTime horaInicio= this.estacionamientoReciente.getHoraDeInicio();
		LocalTime horaMaxima= this.estacionamientoReciente.getHorarioMaximo();
		return "horaInicio="+horaInicio.getHour()+":"+horaInicio.getMinute()+" "+"horaFin="+horaMaxima.getHour()+":"+horaMaxima.getMinute()+" "+"duracion="+this.estacionamientoReciente.getDuracion()+"horas"+" "+"coste="+estacionamientoReciente.costo();
	}


	public String consultarSaldo() {
		// TODO Auto-generated method stub
		
		 if(this.estacionamientoReciente != null) {
			 double saldoActual=this.getCredito() - this.estacionamientoReciente.costo();
			 return "Saldo:"+(saldoActual);
		 }
				  
		 else {
			 	 double saldoActual=this.getCredito();
				 return "Saldo:"+(saldoActual);
		 }
		
		
	}


	public void recargarCredito(double montoACargar) {
		
		this.monto=this.monto+montoACargar;
		this.semAlertas.alertaRecargaDeSaldo(this.nmrCelular,montoACargar,LocalTime.now());
		
		if(this.estacionamientoReciente != null) {this.estacionamientoReciente.actualizarHorarioMaximo(this.minutosDeEstacionamiento(montoACargar));}
		;
	}
}
