package sem_usuario;
 
import java.util.ArrayList;

public class SEM_Usuario {
	
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	private ArrayList<Usuario> getUsuarios()
	{
		return this.usuarios;
	}
	
	public Usuario generarUsuario(int numeroDeCelular, int monto)
	{
		return new Usuario(numeroDeCelular, monto);
	}
	
	public void recargarCredito(int unNumeroDeCelular, int unMonto)
	{
		
		for(Usuario usuario:this.getUsuarios())
		{
			if(usuario.getNumeroDeCelular() == unNumeroDeCelular)
			{
				usuario.recargarMonto(unMonto);
				break;
			}
		}

		// Si no encuentra el usuario entonces crea uno
		 
		this.guardarUsuario(this.generarUsuario(unNumeroDeCelular, unMonto));

	} 
	
	
	public Usuario buscarUsuario(int unNumeroDeCelular)
	{
		for(Usuario usuario:this.getUsuarios())
		{
			if(usuario.getNumeroDeCelular() == unNumeroDeCelular)
			{
				return usuario;
			}
		}
	
		throw new IllegalArgumentException("no se encontro usuario con ese numero de celular");
		
	}
	
	public int cantidadDeUsuarios()
	{
		return this.getUsuarios().size();
	}
	
	public int creditoTotalDeUsuarios()
	{
		int montoTotal = 0;
		
		for(Usuario usuario:this.getUsuarios())
		{
			montoTotal += usuario.getMonto();
		}
		
		return montoTotal;
	}
	
	public void guardarUsuario(Usuario unUsuario)
	{
		this.getUsuarios().add(unUsuario);
	}
}
