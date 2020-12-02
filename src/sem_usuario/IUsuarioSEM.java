package sem_usuario;

public interface IUsuarioSEM {

	public IUsuarioApp getIUsuario(int numeroDeCelular);
	void recargarCredito(int nmrCelular, double montoARecargar);
}
