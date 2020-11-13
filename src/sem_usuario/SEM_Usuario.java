package sem_usuario;
 
import java.util.ArrayList;

import semAlertas.ISemAlertas;
import semEstacionamientos.IsemEstacionamiento;
import usuarios.Usuario;

public class SEM_Usuario {
	
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ISemAlertas semAlertas;
	private IsemEstacionamiento semEstacionamiento;
	
	public SEM_Usuario(ISemAlertas semAlertas, IsemEstacionamiento semEstacionamiento) {
		
		this.semAlertas=semAlertas;
		this.semEstacionamiento=semEstacionamiento;
		
	}

	private ArrayList<Usuario> getUsuarios()
	{
		return this.usuarios;
	}
	
	public Usuario generarUsuario(int numeroDeCelular, int monto)
	{
		return new Usuario(semAlertas,semEstacionamiento,numeroDeCelular, monto);
	}
	public void recargarCredito(int unNumeroDeCelular, int unMonto)
	{
		
		for(Usuario usuario:this.getUsuarios())
		{
			if(usuario.getNumeroDeCelular() == unNumeroDeCelular)
			{
				usuario.recargarCredito(unMonto);
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
			montoTotal += usuario. getCredito();
		}
		
		return montoTotal;
	}
	
	public void guardarUsuario(Usuario unUsuario)
	{
		this.getUsuarios().add(unUsuario);
	}
}
