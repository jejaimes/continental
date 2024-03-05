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

import java.util.ArrayList;

/**
 * Clase que representa el juego de cartas que posee un jugador 
 * <b>inv: </b><br>
 * cartasEnJuego contiene máximo 7 cartas.<br>
 */
public class Juego
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Cartas que posee el jugador.
     */
    private ArrayList<Carta> cartasEnJuego;

    /**
     * Carta que el jugador va a descartar.
     */
    private Carta temporal;

    /**
     * Carta que se encuentra en la baraja donde se descartan las cartas.
     */
    private Carta cartaBarajaJugada;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del juego <b> 
     * post: </b> Se inicializaron cartas en juego. cartasEnJuego != null. <br>
     * Se inicializa la carta temporal en null. temporal == null. <br>
     * Se inicializa la carta de la baraja jugada en null. cartaBarajaJugada == null.
     * 
     */
    public Juego( )
    {
        cartasEnJuego = new ArrayList<Carta>( );
        temporal = null;
        cartaBarajaJugada = null;
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Agrega una carta al juego. 
     * <b> pre: </b> La lista de cartas se encuentra inicializada. cartasEnJuego != null <br>
     * <b> post: </b> Se adiciona una nueva carta a la lista de cartas del jugador. <br>
     * @param pPalo Palo de la carta. palo != null.
     * @param pRepresentacion Representación de la carta. representacion != null.
     * @param pImagen Ruta de la imagen que representa a la carta. imagen != null.
     */
    public void agregarCarta( String pPalo, String pRepresentacion, String pImagen )
    {
        cartasEnJuego.add( new Carta( pPalo, pRepresentacion, pImagen ) );
        verificarInvariante( );
    }

    /**
     * Devuelve la lista que contiene las cartas del juego. <b> 
     * pre: </b> La lista de cartas se encuentra inicializada. cartasEnJuego != null. <br>
     * @return Cartas que posee el jugador.
     */
    public ArrayList<Carta> darJuego( )
    {
        return cartasEnJuego;
    }

    /**
     * Devuelve la carta temporal.
     * @return Carta carta que el jugador puede descartar.
     */
    public Carta darCartaTemporal( )
    {
        return temporal;
    }

    /**
     * Modificar la carta temporal.
     * @param pPalo Palo de la carta. palo != null.
     * @param pRepresentacion Representación de la carta. representacion != null.
     * @param pImagen Ruta de la imagen que representa a la carta. imagen != null.
     */
    public void modificarCartaTemporal( String pPalo, String pRepresentacion, String pImagen )
    {
        temporal = new Carta( pPalo, pRepresentacion, pImagen );
    }

    /**
     * Modifica la carta temporal.
     * @param pCarta Nueva carta para modificar la carta temporal.
     */
    public void modificarCartaTemporal( Carta pCarta )
    {
        temporal = pCarta;
    }

    /**
     * Devuelve la carta de la baraja jugada.
     * @return Carta de la baraja jugada.
     */
    public Carta darCartaBarajaJugada( )
    {
        return cartaBarajaJugada;
    }

    /**
     * Modifica la carta actual de la baraja jugada.
     * @param pPalo Palo de la carta. palo != null.
     * @param pRepresentacion Representación de la carta. representacion != null.
     * @param pImagen Ruta de la imagen que representa a la carta. imagen != null.
     */
    public void modificarCartaBarajaJugada( String pPalo, String pRepresentacion, String pImagen )
    {
        cartaBarajaJugada = new Carta( pPalo, pRepresentacion, pImagen );
    }

    /**
     * Modifica la carta actual de la baraja jugada.
     * @param pCarta Nueva carta para modificar la cata de la baraja jugada.
     */
    public void modificarCartaBarajaJugada( Carta pCarta )
    {
        cartaBarajaJugada = pCarta;

    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verificar el invariante de la clase Juego.<br>
     * <b>inv: </b><br>
     * cartasEnJuego contiene máximo 7 cartas. <br>
     */
    private void verificarInvariante( )
    {
        assert ( cartasEnJuego.size( ) <= 7 ) : "No puede contener más de siete cartas.";
    }

}
