package sem_usuario;



import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;


import semPrincipal.ISemPrincipal;

import usuarios.Usuario;


public class SEMUsuarioTest {
	

	
	ISemPrincipal semPrincipal;
	SemUsuarios semUsuarios;
	
	@BeforeEach
	void setUp()
	{
		semPrincipal= mock(ISemPrincipal.class);
		semUsuarios = new SemUsuarios(semPrincipal);
		
	}
	
	
	@Test
	
	void testGenerarUsuario() 
	{
		int numeroDeCelular = 1566770520;
		int monto = 100;
		
		Usuario usuarioGenerado= semUsuarios.generarUsuario(numeroDeCelular, monto);
		
		semUsuarios.guardarUsuario(usuarioGenerado);

		
		
		assertEquals("Deberia haber agregado un usuario", 1, semUsuarios.cantidadDeUsuarios());
	}
	
	@Test
	public void recargarCreditoTest()
	{
		Usuario usuario1 = mock(Usuario.class);
		Usuario usuario2 = mock(Usuario.class);
		Usuario usuario3 = mock(Usuario.class);
		when(usuario1.getNumeroDeCelular()).thenReturn(1566770500);
		when(usuario2.getNumeroDeCelular()).thenReturn(1566770501);
		when(usuario3.getNumeroDeCelular()).thenReturn(1566770502);
		semUsuarios.guardarUsuario(usuario1);
		semUsuarios.guardarUsuario(usuario2);
		semUsuarios.guardarUsuario(usuario3);

		semUsuarios.recargarCredito(1566770502, 100);
		
		verify(usuario3).getNumeroDeCelular();
		verify(usuario3).recargarCredito(100);
	}
	
	
	//comprobacion de que al no encontrar el usuario genera uno.
	@Test
	void testRecargarCreditoAUsuarioInexistente()
	{
		Usuario usuario1 = mock(Usuario.class);
		Usuario usuario2 = mock(Usuario.class);
		Usuario usuario3 = mock(Usuario.class);
		when(usuario1.getNumeroDeCelular()).thenReturn(1566770500);
		when(usuario2.getNumeroDeCelular()).thenReturn(1566770501);
		when(usuario3.getNumeroDeCelular()).thenReturn(1566770502);
		
		semUsuarios.guardarUsuario(usuario1);
		semUsuarios.guardarUsuario(usuario2);
		semUsuarios.guardarUsuario(usuario3);
		
		assertEquals(3, semUsuarios.cantidadDeUsuarios());
		
		semUsuarios.recargarCredito(1566770503, 100);
		
		assertEquals(4, semUsuarios.cantidadDeUsuarios());
	}
	
	
	@Test
	void testBuscarUsuario()
	{
		Usuario usuario1 = mock(Usuario.class);
		Usuario usuario2 = mock(Usuario.class);
		Usuario usuario3 = mock(Usuario.class);
		when(usuario1.getNumeroDeCelular()).thenReturn(1566770500);
		when(usuario2.getNumeroDeCelular()).thenReturn(1566770501);
		when(usuario3.getNumeroDeCelular()).thenReturn(1566770502);
		
		semUsuarios.guardarUsuario(usuario1);
		semUsuarios.guardarUsuario(usuario2);
		semUsuarios.guardarUsuario(usuario3);
		
		assertSame(usuario3, semUsuarios.getIUsuario(1566770502));
	}
	
	
	
	@Test
	void testBuscarUsuarioInexistente()
	{
		Usuario usuario1 = mock(Usuario.class);
		Usuario usuario2 = mock(Usuario.class);
		Usuario usuario3 = mock(Usuario.class);
		when(usuario1.getNumeroDeCelular()).thenReturn(1566770500);
		when(usuario2.getNumeroDeCelular()).thenReturn(1566770501);
		when(usuario3.getNumeroDeCelular()).thenReturn(1566770502);
		
		semUsuarios.guardarUsuario(usuario1);
		semUsuarios.guardarUsuario(usuario2);
		semUsuarios.guardarUsuario(usuario3);
		
		
		assertThrows(IllegalArgumentException.class, 
				() -> semUsuarios.getIUsuario(1511223344));
              
						
	}
	
	
	@Test
	void testCantidadDeUsuarios()
	{
		Usuario usuario1 = mock(Usuario.class);
		Usuario usuario2 = mock(Usuario.class);
		Usuario usuario3 = mock(Usuario.class);
		
		semUsuarios.guardarUsuario(usuario1);
		assertEquals(1, semUsuarios.cantidadDeUsuarios());
		
		semUsuarios.guardarUsuario(usuario2);
		assertEquals(2, semUsuarios.cantidadDeUsuarios());
		
		semUsuarios.guardarUsuario(usuario3);
		assertEquals(3, semUsuarios.cantidadDeUsuarios());
	}
	
	@Test
	void testCreditoTotalDeUsuarios()
	{
		Usuario usuario1 = mock(Usuario.class);
		Usuario usuario2 = mock(Usuario.class);
		Usuario usuario3 = mock(Usuario.class);
		when(usuario1.getCredito()).thenReturn(100.0);
		when(usuario2.getCredito()).thenReturn(150.0);
		when(usuario3.getCredito()).thenReturn(1000.0);
		
		semUsuarios.guardarUsuario(usuario1);
		semUsuarios.guardarUsuario(usuario2);
		semUsuarios.guardarUsuario(usuario3);
		
		assertEquals(1250, semUsuarios.creditoTotalDeUsuarios());
	}
}

