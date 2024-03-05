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
 * Hilo de ejecuci�n para enviar una jugada.
 */
public class ThreadEnviarJugada extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Referencia al juego.
     */
    private JugadorContinental jugador;

    /**
     * Referencia a la ventana principal de la aplicaci�n.
     */
    private InterfazContinental principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo y lo deja listo para enviar la jugada.
     * @param pJuego Referencia al juego. juego != null.
     * @param pInterfaz Referencia a la ventana principal de la aplicaci�n. interfaz != null.
     */
    public ThreadEnviarJugada( JugadorContinental pJuego, InterfazContinental pInterfaz )
    {
        super( );

        jugador = pJuego;
        principal = pInterfaz;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecuci�n del hilo que realiza el env�o del mensaje y espera la respuesta. <br>
     * Cuando se tiene informaci�n sobre el resultado de la jugada entonces se actualiza la interfaz.<br>
     * Si el juego debe terminar entonces muestra quien fue el ganador y termina el encuentro y la conexi�n al servidor.
     */
    public void run( )
    {

        jugador.realizarJugada( );
        principal.actualizarInterfaz( );
        principal.esperarJugada( );

    }
}
