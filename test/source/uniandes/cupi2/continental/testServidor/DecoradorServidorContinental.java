package uniandes.cupi2.continental.testServidor;

import java.io.*;
import java.net.*;
import java.sql.*;

import uniandes.cupi2.continental.servidor.mundo.*;

/**
 * Esta clase sirve para enriquecer el comportamiento de la clase ServidorContinental para así facilitar la construcción de las pruebas automáticas.
 */
public class DecoradorServidorContinental extends Continental
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Indica si ya se inició un encuentro
	 */
	private boolean encuentroIniciado;

	/**
	 * El tiempo máximo (en milisegundos) que se va a esperar para que se cumplan las diferentes fases de la inicialización de un encuentro
	 */
	private long timeout;

	/**
	 * En caso de falla, acá se almacena un mensaje que explica el error
	 */
	private String mensajeFalla;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor del decorador
	 * @param archivo El archivo de configuración del servidor 
	 * @param tiempoTimeout Tiempo de espera de las distintas fases de inicialización
	 * @throws Exception Se lanza esta excepción si hay problemas con el archivo de propiedades o hay problemas en la conexión a la base de datos
	 * @throws SQLException Se lanza esta excepción si hay problemas conectando el almacén a la base de datos.
	 */
	public DecoradorServidorContinental( String archivo, long tiempoTimeout ) throws SQLException, Exception
	{
		super( archivo );

		encuentroIniciado = false;
		timeout = tiempoTimeout;
		mensajeFalla = null;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Inicia un encuentro usando una instancia de la clase DecoradorEncuentro. <br>
	 * Después de que se intenta iniciar el encuentro, se espera hasta que el encuentro esté iniciado efectivamente, o hasta que haya un timeout. <br>
	 * Si el timeout se cumple, entonces en el atributo falla queda un mensaje que explica el error.
	 * @param nuevoEncuentro El nuevo encuentro a ser iniciado 
	 */
	protected void iniciarEncuentro( Encuentro nuevoEncuentro )
	{
		try
		{
			Socket sJugador1 = nuevoEncuentro.darSocketJugador1( );
			Socket sJugador2 = nuevoEncuentro.darSocketJugador2( );
			AdministradorResultados adminResultados = nuevoEncuentro.darAdministradorResultados( );

			DecoradorEncuentro encuentroDecorado = new DecoradorEncuentro( sJugador1, sJugador2, adminResultados );

			super.iniciarEncuentro( encuentroDecorado );

			long tInicio = System.currentTimeMillis( );
			long tFinal = tInicio + timeout;

			while( !encuentroDecorado.estaIniciado( ) && System.currentTimeMillis( ) < tFinal )
			{
				try
				{
					Thread.sleep( 100 );
				}
				catch( InterruptedException e1 )
				{
				}
			}

			if( !encuentroDecorado.estaIniciado( ) )
				mensajeFalla = "El encuentro no se inició en el tiempo disponible";
			else
				encuentroIniciado = true;
		}
		catch( IOException e )
		{
			mensajeFalla = "Hubo un error iniciando el encuentro: " + e.getMessage( );
		}
	}

	/**
	 * Este método sirve para saber si ya se inició un encuentro. <br>
	 * Este método espera hasta que el encuentro se inicie o hasta que se cumpla el timeout establecido.
	 * @return Retorna true si se inició un encuentro. Retorna false en caso contrario.
	 */
	public boolean seInicioEncuentro( )
	{
		long tInicio = System.currentTimeMillis( );
		long tFinal = tInicio + timeout;

		while( !encuentroIniciado && mensajeFalla == null && System.currentTimeMillis( ) < tFinal )
		{
			try
			{
				Thread.sleep( 100 );
			}
			catch( InterruptedException e )
			{
				e.printStackTrace( );
			}
		}

		return encuentroIniciado;
	}

	/**
	 * Retorna el mensaje que explica porqué hubo un fallo
	 * @return Se retornó el mensaje con la cuasa de la falla. Si no ha habido ninguna falla, se retorno null
	 */
	public String darFallo( )
	{
		return mensajeFalla;
	}
}
