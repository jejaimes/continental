package uniandes.cupi2.continental.servidor.mundo;

/**
 * Esta clase representa a un jugador que se conectó al servidor para jugar Continental y está participando en un encuentro.<br>
 * <b>inv:</b><br>
 * registroJugador != null<br>
 * puntosEncuentro >= 0
 */
public class JugadorRemoto
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Registro con la información de los partidos del jugador antes de iniciar el encuentro
	 */
	private RegistroJugador registroJugador;

	/**
	 * El puntaje del jugador en el encuentro actual
	 */
	private int puntosEncuentro;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el objeto para mantener la información del jugador remoto. <br>
	 * Al iniciar el encuentro, el puntaje es 0.
	 * @param registro El registro del jugador antes de empezar el encuentro actual - registro != null
	 */
	public JugadorRemoto( RegistroJugador registro )
	{
		registroJugador = registro;
		puntosEncuentro = 0;
		verificarInvariante( );
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna el registro del jugador
	 * @return registroJugador
	 */
	public RegistroJugador darRegistroJugador( )
	{
		return registroJugador;
	}

	/**
	 * Retorna los puntos del jugador en el encuentro actual
	 * @return puntosEncuentro
	 */
	public int darPuntosPartida( )
	{
		return puntosEncuentro;
	}

	/**
	 * Aumenta en una determinada cantidad el número de puntos
	 * @param cantidad La cantidad que debe aumentarse el número de puntos - cantidad >= 0
	 */
	public void aumentarPuntosEncuentro( int cantidad )
	{
		puntosEncuentro += cantidad;
		verificarInvariante( );
	}

	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase<br>
	 * <b>inv</b><br>
	 * registroJugador != null<br>
	 * puntosEncuentro >= 0;
	 */
	private void verificarInvariante( )
	{
		assert ( registroJugador != null ) : "El registros de un jugador no puede ser null";
		assert ( puntosEncuentro >= 0 ) : "El número de puntos de un jugador debe ser mayor o igual a 0";
	}
}
