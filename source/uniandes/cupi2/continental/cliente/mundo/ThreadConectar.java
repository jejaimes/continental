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
 * Hilo de ejecuci�n cuando para conectar al cliente con el servidor.
 */
public class ThreadConectar extends Thread
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

    /**
     * Nombre que utilizar� el jugador.
     */
    private String nombre;

    /**
     * Direcci�n para localizar al servidor.
     */
    private String servidor;

    /**
     * Puerto a trav�s del cual se realizar� la conexi�n con el servidor.
     */
    private int puerto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el nuevo hilo para conectarse al servidor.
     * @param pJuego Referencia al juego. juego != null.
     * @param pInterfaz Referencia a la ventana principal de la aplicaci�n. interfaz != null.
     * @param pNombreJugador Nombre que utilizar� el jugador. nombreJugador != null.
     * @param pDireccionServidor Direcci�n para localizar al servidor. direccionServidor != null.
     * @param pPuertoServidor Puerto a trav�s del cual se realizar� la conexi�n con el servidor. puertoServidor != null.
     */
    public ThreadConectar( JugadorContinental pJuego, InterfazContinental pInterfaz, String pNombreJugador, String pDireccionServidor, int pPuertoServidor )
    {
        jugador = pJuego;
        principal = pInterfaz;
        nombre = pNombreJugador;
        servidor = pDireccionServidor;
        puerto = pPuertoServidor;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicia la ejecuci�n del hilo que realiza la conexi�n con el servidor.<br>
     */
    public void run( )
    {

        try
        {
            jugador.conectar( nombre, servidor, puerto );
            principal.actualizarInterfaz( );
            principal.actualizarDatosOponente( );
            principal.activarBarajas( );
            if( jugador.darEstado( ).equals( JugadorContinental.ESPERANDO_JUGADA ) )
                principal.esperarJugada( );
            else
                principal.desactivarBotonJugar( );
        }
        catch( ContinentalException e )
        {
            principal.mostrarError( e );
        }

    }
}
