/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
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
 * Hilo de ejecución cuando se espera una carta del servidor.
 */
public class ThreadEsperarCarta extends Thread
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Representa a la baraja inicial.
     */
    public final static String INICIAL = "INICIAL";

    /**
     * Representa a la baraja jugada.
     */
    public final static String JUGADA = "JUGADA";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia al juego.
     */
    private JugadorContinental jugador;

    /**
     * Referencia a la ventana principal de la aplicación.
     */
    private InterfazContinental principal;

    /**
     * Baraja de la cual se pidió una carta.
     */
    private String baraja;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para recibir una carta.
     * @param pJuego Jugador de continental.
     * @param pInterfaz Ventana principal de la aplicación.
     * @param pNBaraja Baraja de la cual se pidió una carta.
     */
    public ThreadEsperarCarta( JugadorContinental pJuego, InterfazContinental pInterfaz, String pNBaraja )
    {
        super( );

        jugador = pJuego;
        principal = pInterfaz;
        baraja = pNBaraja;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecución del hilo que realiza el envío del mensaje para pedir una carta y espera la respuesta con la carta del servidor. <br>
     * Cuando se tiene información sobre el resultado de la carta entonces se actualiza la interfaz.<br>
     */
    public void run( )
    {

        try
        {
            if( baraja.equals( INICIAL ) )
                jugador.pedirCartaBarajaInicial( );
            else if( baraja.equals( JUGADA ) )
                jugador.pedirCartaBarajaJugada( );

            principal.actualizarInterfaz( );
        }
        catch( ContinentalException e )
        {
            principal.mostrarError( e );
        }

    }
}
