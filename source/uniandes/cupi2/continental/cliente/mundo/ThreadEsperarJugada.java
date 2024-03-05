/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_continental
 * Autor: Equipo Cupi2 2018-2
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.continental.cliente.mundo;

import uniandes.cupi2.continental.cliente.interfaz.InterfazContinental;

/**
 * Hilo de ejecuci�n que espera la jugada del oponente.
 */
public class ThreadEsperarJugada extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia al juego
     */
    private JugadorContinental jugador;

    /**
     * Referencia a la ventana principal de la aplicaci�n
     */
    private InterfazContinental principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para esperar la jugada.
     * @param pJuego Referencia al juego. juego != null.
     * @param pInterfaz Referencia a la ventana principal de la aplicaci�n. interfaz != null.
     */
    public ThreadEsperarJugada( JugadorContinental pJuego, InterfazContinental pInterfaz )
    {
        super( );
        jugador = pJuego;
        principal = pInterfaz;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecuci�n del hilo que espera la jugada del oponente. <br>
     * Cuando se tiene informaci�n sobre la jugada del oponente entonces se actualiza la interfaz.<br>
     * Si el juego debe terminar entonces muestra quien fue el ganador y termina el encuentro y la conexi�n al servidor.
     */
    public void run( )
    {
        try
        {
            jugador.esperarJugada( );
            if( jugador.juegoTerminado( ) )
            {
                principal.mostrarInformacionGanador( jugador.darVictoriaValidaOponente( ) );
                principal.actualizarInterfaz( );
            }
            else
            {
                principal.desactivarBotonJugar( );
                principal.actualizarInterfaz( );
            }
        }
        catch( ContinentalException e )
        {
            principal.mostrarError( e );
            principal.desactivarBarajas( );
        }

    }
}
