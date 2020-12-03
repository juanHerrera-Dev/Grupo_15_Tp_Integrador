package sem_usuario;
 
import java.util.ArrayList;
import java.util.List;

import semPrincipal.ISemPrincipal;
import usuarios.Usuario;

public class SemUsuarios implements ISemUsuarios{
	
	private List<Usuario> usuarios ;
	
	private ISemPrincipal semPrincipal;
	
	public SemUsuarios(ISemPrincipal semPrincipal) {
		
		this.semPrincipal=semPrincipal;
		this.usuarios=new ArrayList<Usuario>();
	
	}

	private List<Usuario> getUsuarios()
	{
		return this.usuarios;  
	}
	
	public Usuario generarUsuario(int numeroDeCelular, double monto)
	{
		return new Usuario(semPrincipal,monto, numeroDeCelular);
	}
	public void recargarCredito(int unNumeroDeCelular, double unMonto)
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
	
	
	public IUsuarioApp getIUsuario(int numeroDeCelular)
	{
		for(Usuario usuario:this.getUsuarios())
		{
			if(usuario.getNumeroDeCelular() == numeroDeCelular)
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
