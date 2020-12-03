package sem_usuario;

public interface ISemUsuarios {

	public IUsuarioApp getIUsuario(int numeroDeCelular);
	void recargarCredito(int nmrCelular, double montoARecargar);
}
