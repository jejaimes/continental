package uniandes.cupi2.continental.servidor.mundo;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

/**
 * El Servidor Continental es el que se encarga de recibir conexiones de los clientes y organizar los encuentros. <br>
 * <b>inv:</b><br>
 * receptor!= null <br>
 * config!=null <br>
 * adminResultados!=null <br>
 * encuentros!=null <br>
 */
public class Continental
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Es el punto por el cual los clientes solicitan conexiones
	 */
	private ServerSocket receptor;

	/**
	 * Es el conjunto de propiedades que contienen la configuración de la aplicación
	 */
	private Properties config;

	/**
	 * Es el administrador que permite registrar los resultados sobre la base de datos
	 */
	private AdministradorResultados adminResultados;

	/**
	 * Este es el canal utilizado para establecer una comunicación con un jugador que está en espera de un oponente. <br>
	 * Si no hay jugadores en espera, este canal debe ser null.
	 */
	private Socket socketJugadorEnEspera;

	/**
	 * Es una colección con los encuentros que se están llevando a cabo en este momento
	 */
	protected Collection encuentros;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicia el servidor y deja listo el administrador de resultados
	 * @param archivo El archivo de propiedades que tiene la configuración del servidor - archivo != null
	 * @throws Exception Se lanza esta excepción si hay problemas con el archivo de propiedades o hay problemas en la conexión a la base de datos
	 * @throws SQLException Se lanza esta excepción si hay problemas conectando el almacén a la base de datos.
	 */
	public Continental( String archivo ) throws SQLException, Exception
	{
		encuentros = new Vector( );

		cargarConfiguracion( archivo );

		adminResultados = new AdministradorResultados( config );
		adminResultados.conectarABD( );
		adminResultados.inicializarTabla( );
		verificarInvariante(); 
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Carga la configuración a partir de un archivo de propiedades
	 * @param archivo El archivo de propiedades que contiene la configuración que requiere el servidor - archivo != null y el archivo debe contener la propiedad
	 *        "servidor.puerto" y las propiedades que requiere el administrador de resultados
	 * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
	 */
	private void cargarConfiguracion( String archivo ) throws Exception
	{
		FileInputStream fis = new FileInputStream( archivo );
		config = new Properties( );
		config.load( fis );
		fis.close( );
	}

	/**
	 * Retorna al administrador de resultados
	 * @return adminResultados;
	 */
	public AdministradorResultados darAdministradorResultados( )
	{
		return adminResultados;
	}

	/**
	 * Retorna una colección actualizada con los encuentros que se están desarrollando actualmente y no han terminado.<br>
	 * Si había encuentros en la lista que ya habían terminado deben ser eliminados.
	 * @return colección de encuentros
	 */
	public Collection darListaActualizadaEncuentros( )
	{
		Collection listaActualizada = new Vector( );

		// Armar la lista actualizada con los encuentros que no han terminado
		Iterator iter = encuentros.iterator( );
		while( iter.hasNext( ) )
		{
			Encuentro e = ( Encuentro )iter.next( );
			if( !e.encuentroTerminado( ) )
				listaActualizada.add( e );
		}

		// Reemplazar la lista antigua con la lista actualizada
		encuentros = listaActualizada;

		return encuentros;
	}

	/**
	 * Este método se encarga de recibir todas las conexiones entrantes y crear los encuentros cuando fuera necesario.
	 */
	public void recibirConexiones( )
	{
		String aux = config.getProperty( "servidor.puerto" );
		int puerto = Integer.parseInt( aux );
		try
		{
			receptor = new ServerSocket( puerto );

			while( true )
			{
				// Esperar una nueva conexión
				Socket socketNuevoCliente = receptor.accept( );

				// Intentar iniciar un encuentro con el nuevo cliente
				crearEncuentro( socketNuevoCliente );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
		finally
		{
			try
			{
				receptor.close( );
			}
			catch( IOException e )
			{
				e.printStackTrace( );
			}
		}
	}

	/**
	 * Intenta crear e iniciar un nuevo encuentro con el jugador que se acaba de conectar. <br>
	 * Si no se tiene ya un oponente, entonces el jugador queda en espera de que otra persona se conecte.
	 * @param socketNuevoCliente El canal que permite la comunicación con el nuevo cliente - socket != null
	 * @throws IOException Se lanza esta excepción si se presentan problemas de comunicación
	 */
	synchronized private void crearEncuentro( Socket socketNuevoCliente ) throws IOException
	{
		if( socketJugadorEnEspera == null )
		{
			// No hay un oponente aún, así que hay que dejarlo en espera
			socketJugadorEnEspera = socketNuevoCliente;
		}
		else
		{
			// Ya se tiene un oponente así que se puede empezar una partida
			Socket jug1 = socketJugadorEnEspera;
			Socket jug2 = socketNuevoCliente;

			socketJugadorEnEspera = null;

			try
			{
				Encuentro nuevo = new Encuentro( jug1, jug2, adminResultados );
				iniciarEncuentro( nuevo );
			}
			catch( IOException e )
			{
				try
				{
					jug1.close( );
				}
				catch( IOException e1 )
				{
					e.printStackTrace( );
				}
				try
				{
					jug2.close( );
				}
				catch( IOException e2 )
				{
					e.printStackTrace( );
				}

				// Mostrar la excepción original
				e.printStackTrace( );
			}
		}

		verificarInvariante();
	}

	/**
	 * Agrega el encuentro a la lista de encuentros en curso y lo inicia
	 * @param nuevoEncuentro Un encuentro que no ha sido inicializado ni agregado a la lista de encuentros - nuevoEncuentro != null
	 */
	protected void iniciarEncuentro( Encuentro nuevoEncuentro )
	{
		encuentros.add( nuevoEncuentro );
		nuevoEncuentro.start( );
	}

	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase <br>
	 * <b>inv:</b><br>
	 * receptor!= null <br>
	 * config!=null <br>
	 * adminResultados!=null <br>
	 * encuentros!=null <br>
	 */
	private void verificarInvariante( )
	{        
		assert receptor != null : "Canal inválido";
		assert config != null : "Conjunto de propiedades inválidas";
		assert adminResultados != null : "El administrador de resultados no debería ser null";
		assert encuentros != null : "La lista de encuentros no debería ser null";            
	}
	// -----------------------------------------------------------------
	// Puntos de Extensión
	// -----------------------------------------------------------------

	/**
	 * Método para la extensión 1
	 * @return respuesta1
	 */
	public String metodo1( )
	{
		return "Respuesta 1";
	}

	/**
	 * Método para la extensión2
	 * @return respuesta2
	 */
	public String metodo2( )
	{
		return "Respuesta 2";
	}
}
