package uniandes.cupi2.continental.testServidor;

import java.io.*;
import java.net.*;
import java.sql.*;

import uniandes.cupi2.continental.cliente.mundo.ContinentalException;
import uniandes.cupi2.continental.servidor.mundo.*;

/**
 * Esta clase extiende el comportamiento de la clase encuentro para facilitar las pruebas de la clase ServidorContinental
 */
public class DecoradorEncuentro extends Encuentro
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Indica si el encuentro ya se inició
	 */
	private boolean encuentroIniciado;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea un nuevo encuentro
	 * @param cliente1 El socket que permite la comunicación con el cliente 1
	 * @param cliente2 El socket que permite la comunicación con el cliente 2
	 * @param administrador El administrador de resultados usado
	 * @throws IOException Se lanza esta excepción si hay problemas en la comunicación
	 */
	public DecoradorEncuentro( Socket cliente1, Socket cliente2, AdministradorResultados administrador ) throws IOException
	{
		super( cliente1, cliente2, administrador );
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Inicia un encuentro y cambia el valor del atributo encuentroIniciado
	 * @throws IOException Se lanza esta excepción si hay problemas en la comunicación
	 * @throws BatallaNavalException Se lanza esta excepción si hay problemas en la comunicación
	 */
	protected void iniciarEncuentro( ) throws IOException, ContinentalException
	{
		super.iniciarEncuentro( );
		encuentroIniciado = true;
	}

	/**
	 * Indica si el encuentro ya se inició
	 * @return encuentroIniciado
	 */
	public boolean estaIniciado( )
	{
		return encuentroIniciado;
	}

	/**
	 * Retorna el nombre del jugador 1
	 * @return nombre del jugador 1
	 */
	public String darNombreJugador1( )
	{
		return jugador1.darRegistroJugador( ).darNombreJugador( );
	}

	/**
	 * Retorna el nombre del jugador 2
	 * @return nombre del jugador 2
	 */
	public String darNombreJugador2( )
	{
		return jugador2.darRegistroJugador( ).darNombreJugador( );
	}

}
