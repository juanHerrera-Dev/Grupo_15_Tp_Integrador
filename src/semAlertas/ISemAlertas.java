package semAlertas;

import java.time.LocalTime;

public interface ISemAlertas {

	void alertaInicioDeEstacionamiento(int nmrCelular, double monto, int zonaId, LocalTime horaInicio);

	void alertaFinDeEstacionamiento(int nmrCelular, double monto, int zonaId, LocalTime horaFinEstacionamiento);

	void alertaRecargaDeSaldo(int nmrCelular, double monto, LocalTime horaRecarga);

}
