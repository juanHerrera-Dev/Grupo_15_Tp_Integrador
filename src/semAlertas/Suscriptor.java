package semAlertas;

import java.time.LocalTime;

public interface Suscriptor {

	void recibirAlertaInicioE(int nmrCelular, double monto, int zonaId, LocalTime horaEstacionamiento);

	void recibirAlertaFinE(int nmrCelular, double monto, int zonaId, LocalTime horaFinEstacionamiento);

	void recibirAlertaRecarga(int nmrCelular, double monto, LocalTime horaRecarga);

}
