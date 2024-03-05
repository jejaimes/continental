package uniandes.cupi2.continental.servidor.mundo;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Esta es la clase que se encarga de registrar los resultados de los encuentros sobre la base de datos. <br>
 * <b>inv:</b><br>
 * config!=null <br>
 */
public class AdministradorResultados
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Conexión a la base de datos
	 */
	private Connection conexion;

	/**
	 * Conjunto de propiedades que contienen la configuración de la aplicación
	 */
	private Properties config;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el administrador de resultados y lo deja listo para conectarse a la base de datos
	 * @param propiedades Las propiedades para la configuración del administrador - Debe tener las propiedades "admin.db.path", "admin.db.driver", "admin.db.url" y
	 *        "admin.db.shutdown"
	 */
	public AdministradorResultados( Properties propiedades )
	{
		config = propiedades;

		// Establecer la ruta donde va a estar la base de datos.
		// Derby utiliza la propiedad del sistema derby.system.home para saber donde están los datos
		File data = new File( config.getProperty( "admin.db.path" ) );
		System.setProperty( "derby.system.home", data.getAbsolutePath( ) );

	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Conecta el administrador a la base de datos
	 * @throws SQLException Se lanza esta excepción si hay problemas realizando la operación
	 * @throws Exception Se lanza esta excepción si hay problemas con los controladores
	 */
	public void conectarABD( ) throws SQLException, Exception
	{
		String driver = config.getProperty( "admin.db.driver" );
		Class.forName( driver ).newInstance( );

		String url = config.getProperty( "admin.db.url" );
		conexion = DriverManager.getConnection( url );
		verificarInvariante();
	}

	/**
	 * Desconecta el administrador de la base de datos y la detiene
	 * @throws SQLException Se lanza esta excepción si hay problemas realizando la operación
	 */
	public void desconectarBD( ) throws SQLException
	{ 
		conexion.close( );
		String down = config.getProperty( "admin.db.shutdown" );
		try
		{
			DriverManager.getConnection( down );
		}
		catch( SQLException e )
		{
			// Al bajar la base de datos se produce siempre una excepción
		}
		verificarInvariante();
	}

	/**
	 * Crea la tabla necesaria para guardar los resultados. Si la tabla ya estaba creada entonces no hace nada. <br>
	 * @throws SQLException Se lanza esta excepción si hay problemas creando la tabla
	 */
	public void inicializarTabla( ) throws SQLException
	{
		Statement s = conexion.createStatement( );

		boolean crearTabla = false;
		try
		{
			// Verificar si ya existe la tabla resultados
			s.executeQuery( "SELECT * FROM resultados WHERE 1=2" );
		}
		catch( SQLException se )
		{
			// La excepción se lanza si la tabla resultados no existe
			crearTabla = true;
		}

		// Se crea una nueva tabla vacía
		if( crearTabla )
		{
			s.execute( "CREATE TABLE resultados (nombre varchar(32), ganados int, perdidos int, PRIMARY KEY (nombre))" );
		}

		s.close( );
		verificarInvariante();
	}

	/**
	 * Este método se encarga de consultar la información de un jugador (encuentros ganados y encuentros perdidos). <br>
	 * Si no se encuentra un registro del jugador en la base de datos, entonces se crea uno nuevo.
	 * @param nombre El nombre del jugador del cual se está buscando información - nombre != null
	 * @return Retorna el registro de victorias y derrotas del jugador
	 * @throws SQLException Se lanza esta excepción si hay problemas en la comunicación con la base de datos
	 */
	public RegistroJugador consultarRegistroJugador( String nombre ) throws SQLException
	{
		RegistroJugador registro = null;

		String sql = "SELECT ganados, perdidos FROM resultados WHERE nombre ='" + nombre + "'";

		Statement st = conexion.createStatement( );
		ResultSet resultado = st.executeQuery( sql );

		if( resultado.next( ) ) // Se encontró el jugador
		{
			int ganados = resultado.getInt( 1 );
			int perdidos = resultado.getInt( 2 );

			registro = new RegistroJugador( nombre, ganados, perdidos );

			resultado.close( );
		}
		else
			// Hay que crear un nuevo registro porque es un jugador nuevo
		{
			resultado.close( );

			String insert = "INSERT INTO resultados (nombre, ganados, perdidos) VALUES ('" + nombre + "', 0,0)";
			st.execute( insert );

			registro = new RegistroJugador( nombre, 0, 0 );
		}
		st.close( );
		verificarInvariante();
		return registro;
	}

	/**
	 * Este método se encarga de consultar la información de todos los jugadores (encuentros ganados y encuentros perdidos).
	 * @return Retorna una colección de los registros (RegistroJugador) de victorias y derrotas
	 * @throws SQLException Se lanza esta excepción si hay problemas en la comunicación con la base de datos
	 */
	public Collection consultarRegistrosJugadores( ) throws SQLException
	{
		Collection registros = new LinkedList( );

		String sql = "SELECT nombre, ganados, perdidos FROM resultados";

		Statement st = conexion.createStatement( );
		ResultSet resultado = st.executeQuery( sql );

		while( resultado.next( ) )
		{
			String nombre = resultado.getString( 1 );
			int ganados = resultado.getInt( 2 );
			int perdidos = resultado.getInt( 3 );

			RegistroJugador registro = new RegistroJugador( nombre, ganados, perdidos );
			registros.add( registro );
		}

		resultado.close( );
		st.close( );

		return registros;
	}

	/**
	 * Este método se encarga de registrar una victoria más a un jugador
	 * @param nombre El nombre del jugador al cual se le va a registrar la victoria - nombre != null && corresponde a un registro en la base de datos
	 * @throws SQLException Se lanza esta excepción si hay problemas en la comunicación con la base de datos
	 */
	public void registrarVictoria( String nombre ) throws SQLException
	{
		String sql = "UPDATE resultados SET ganados = ganados+1 WHERE nombre ='" + nombre + "'";

		Statement st = conexion.createStatement( );
		st.executeUpdate( sql );
		st.close( );
		verificarInvariante();
	}

	/**
	 * Este método se encarga de registrar una derrota más a un jugador
	 * @param nombre El nombre del jugador al cual se le va a registrar la derrota - nombre != null && corresponde a un registro en la base de datos
	 * @throws SQLException Se lanza esta excepción si hay problemas en la comunicación con la base de datos
	 */
	public void registrarDerrota( String nombre ) throws SQLException
	{
		String sql = "UPDATE resultados SET perdidos = perdidos+1 WHERE nombre ='" + nombre + "'";

		Statement st = conexion.createStatement( );
		st.executeUpdate( sql );
		st.close( );
		verificarInvariante();
	}

	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------
	/**
	 * Verifica el invariante de la clase <br>
	 * <b>inv:</b><br>    
	 * config!=null <br>
	 */
	private void verificarInvariante( )
	{                
		assert config != null : "Conjunto de propiedades inválidas";                           
	}

}
