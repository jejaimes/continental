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

package uniandes.cupi2.continental.cliente.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.continental.cliente.mundo.Carta;
import uniandes.cupi2.continental.cliente.mundo.Juego;

/**
 * Panel que contiene el juego.
 */
public class PanelJuego extends JPanel implements MouseListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = 902864214018299624L;

    /**
     * Constante con la ruta a las im�genes de las cartas.
     */
    private final static String RUTA = ".//data//cartas//";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Juego de cartas.
     */
    private Juego juego;

    /**
     * Indice de la carta seleccionada actualmente.
     */
    private int seleccionado;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta carta 1.
     */
    private JLabel lblCarta1;

    /**
     * Etiqueta carta 2.
     */
    private JLabel lblCarta2;

    /**
     * Etiqueta carta 3.
     */
    private JLabel lblCarta3;

    /**
     * Etiqueta carta 4.
     */
    private JLabel lblCarta4;

    /**
     * Etiqueta carta 5.
     */
    private JLabel lblCarta5;

    /**
     * Etiqueta carta 6.
     */
    private JLabel lblCarta6;

    /**
     * Etiqueta carta 7.
     */
    private JLabel lblCarta7;

    /**
     * Etiqueta carta a descartar por el jugador.
     */
    private JLabel lblCartaTemporal;

    /**
     * Panel con las cartas del juego.
     */
    private JPanel panelCartas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     */
    public PanelJuego( )
    {
        seleccionado = -1;
        GridLayout layoutPrincipal = new GridLayout( 2, 1 );
        layoutPrincipal.setHgap( 10 );
        layoutPrincipal.setVgap( 10 );
        this.setLayout( layoutPrincipal );

        this.setBackground( new Color( 0, 128, 0 ) );

        lblCartaTemporal = new JLabel( "" );
        lblCartaTemporal.setPreferredSize( new Dimension( 100, 150 ) );
        lblCartaTemporal.setHorizontalAlignment( JLabel.CENTER );
        lblCartaTemporal.setVerticalAlignment( JLabel.BOTTOM );
        lblCartaTemporal.addMouseListener( this );
        add( lblCartaTemporal );

        GridLayout layout = new GridLayout( 1, 7 );
        panelCartas = new JPanel( layout );
        panelCartas.setBackground( new Color( 0, 128, 0 ) );

        lblCarta1 = new JLabel( "" );
        lblCarta1.setPreferredSize( new Dimension( 100, 150 ) );
        lblCarta1.setHorizontalAlignment( JLabel.CENTER );
        lblCarta1.setVerticalAlignment( JLabel.CENTER );
        lblCarta1.addMouseListener( this );
        panelCartas.add( lblCarta1 );

        lblCarta2 = new JLabel( "" );
        lblCarta2.setPreferredSize( new Dimension( 100, 150 ) );
        lblCarta2.setHorizontalAlignment( JLabel.CENTER );
        lblCarta2.setVerticalAlignment( JLabel.CENTER );
        lblCarta2.addMouseListener( this );
        panelCartas.add( lblCarta2 );

        lblCarta3 = new JLabel( "" );
        lblCarta3.setPreferredSize( new Dimension( 100, 150 ) );
        lblCarta3.setHorizontalAlignment( JLabel.CENTER );
        lblCarta3.setVerticalAlignment( JLabel.CENTER );
        lblCarta3.addMouseListener( this );
        panelCartas.add( lblCarta3 );

        lblCarta4 = new JLabel( "" );
        lblCarta4.setPreferredSize( new Dimension( 100, 150 ) );
        lblCarta4.setHorizontalAlignment( JLabel.CENTER );
        lblCarta4.setVerticalAlignment( JLabel.CENTER );
        lblCarta4.addMouseListener( this );
        panelCartas.add( lblCarta4 );

        lblCarta5 = new JLabel( "" );
        lblCarta5.setPreferredSize( new Dimension( 100, 150 ) );
        lblCarta5.setHorizontalAlignment( JLabel.CENTER );
        lblCarta5.setVerticalAlignment( JLabel.CENTER );
        lblCarta5.addMouseListener( this );
        panelCartas.add( lblCarta5 );

        lblCarta6 = new JLabel( "" );
        lblCarta6.setPreferredSize( new Dimension( 100, 150 ) );
        lblCarta6.setHorizontalAlignment( JLabel.CENTER );
        lblCarta6.setVerticalAlignment( JLabel.CENTER );
        lblCarta6.addMouseListener( this );
        panelCartas.add( lblCarta6 );

        lblCarta7 = new JLabel( "" );
        lblCarta7.setPreferredSize( new Dimension( 100, 150 ) );
        lblCarta7.setHorizontalAlignment( JLabel.CENTER );
        lblCarta7.setVerticalAlignment( JLabel.CENTER );
        lblCarta7.addMouseListener( this );
        panelCartas.add( lblCarta7 );

        add( panelCartas );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Modifica el juego que se visualiza actualmente.
     * @param pNJuego Nuevo juego.
     */
    public void modificarJuego( Juego pNJuego )
    {

        juego = pNJuego;
        ArrayList<Carta> cartas = juego.darJuego( );

        Carta carta = null;

        try
        {
            BufferedImage bImagen;
            if( cartas.size( ) > 0 )
            {

                carta = ( Carta )cartas.get( 0 );
                bImagen = ImageIO.read( new File(  carta.darImagen( ) ) );
                lblCarta1.setIcon( new ImageIcon( bImagen ) );

                carta = ( Carta )cartas.get( 1 );
                bImagen = ImageIO.read( new File( carta.darImagen( ) ) );
                lblCarta2.setIcon( new ImageIcon( bImagen ) );

                carta = ( Carta )cartas.get( 2 );
                bImagen = ImageIO.read( new File( carta.darImagen( ) ) );
                lblCarta3.setIcon( new ImageIcon( bImagen ) );

                carta = ( Carta )cartas.get( 3 );
                bImagen = ImageIO.read( new File( carta.darImagen( ) ) );
                lblCarta4.setIcon( new ImageIcon( bImagen ) );

                carta = ( Carta )cartas.get( 4 );
                bImagen = ImageIO.read( new File(  carta.darImagen( ) ) );
                lblCarta5.setIcon( new ImageIcon( bImagen ) );

                carta = ( Carta )cartas.get( 5 );
                bImagen = ImageIO.read( new File(carta.darImagen( ) ) );
                lblCarta6.setIcon( new ImageIcon( bImagen ) );

                carta = ( Carta )cartas.get( 6 );
                bImagen = ImageIO.read( new File( carta.darImagen( ) ) );
                lblCarta7.setIcon( new ImageIcon( bImagen ) );
            }

            if( juego.darCartaTemporal( ) != null )
            {
                bImagen = ImageIO.read( new File(  juego.darCartaTemporal( ).darImagen( ) ) );
                lblCartaTemporal.setIcon( new ImageIcon( bImagen ) );
            }
            else
            {
                bImagen = ImageIO.read( new File( "./data/cartas/no-cover.png" ) );
                lblCartaTemporal.setIcon( new ImageIcon( bImagen ) );
            }

        }
        catch( IOException e )
        {
        	e.printStackTrace();
            JOptionPane.showMessageDialog( this, "Error al cargar la imagen de la carta " + carta.darRepresentacion( ) + carta.darPalo( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

    }

    /**
     * M�todo para manejar los clicks del mouse.
     * @param pEvento Evento del mouse.
     */
    public void mouseClicked( MouseEvent pEvento )
    {
        ArrayList<Carta> cartas = juego.darJuego( );

        if( pEvento.getButton( ) == MouseEvent.BUTTON1 )
        {
            int indice = 0;
            Object o = pEvento.getSource( );
            JLabel cartaRef = null;
            if( o.equals( lblCarta1 ) )
            {
                cartaRef = lblCarta1;
                indice = 0;
            }
            else if( o.equals( lblCarta2 ) )
            {
                cartaRef = lblCarta2;
                indice = 1;
            }
            else if( o.equals( lblCarta3 ) )
            {
                cartaRef = lblCarta3;
                indice = 2;
            }
            else if( o.equals( lblCarta4 ) )
            {
                cartaRef = lblCarta4;
                indice = 3;
            }
            else if( o.equals( lblCarta5 ) )
            {
                cartaRef = lblCarta5;
                indice = 4;
            }
            else if( o.equals( lblCarta6 ) )
            {
                cartaRef = lblCarta6;
                indice = 5;
            }
            else if( o.equals( lblCarta7 ) )
            {
                cartaRef = lblCarta7;
                indice = 6;
            }
            else if( o.equals( lblCartaTemporal ) )
            {
                if( juego.darCartaTemporal( ) == null )
                    return;
                cartaRef = lblCartaTemporal;
                indice = 7;
            }
            BufferedImage bImagen;
            Carta carta;
            if( indice == 7 )
                carta = juego.darCartaTemporal( );
            else
                carta = ( Carta )cartas.get( indice );

            try
            {
                if( seleccionado == -1 )
                {
                	String ruta = carta.darImagen();
                	String[] partes = ruta.split("/");
                	ruta = ruta.replace(partes[partes.length-1], "inv-"+partes[partes.length-1]);
                    bImagen = ImageIO.read( new File(  ruta ) );
                    cartaRef.setIcon( new ImageIcon( bImagen ) );
                    seleccionado = indice;
                }
                else if( seleccionado == indice )
                {
                    bImagen = ImageIO.read( new File( carta.darImagen( ) ) );
                    cartaRef.setIcon( new ImageIcon( bImagen ) );
                    seleccionado = -1;
                }
                else
                {
                    if( indice == 7 )
                    {
                        Carta c1 = juego.darCartaTemporal( );
                        Carta c2 = ( Carta )cartas.get( seleccionado );

                        juego.modificarCartaTemporal( c2 );
                        cartas.set( seleccionado, c1 );
                        seleccionado = -1;
                        modificarJuego( juego );
                    }
                    else if( seleccionado == 7 )
                    {
                        Carta c1 = ( Carta )cartas.get( indice );
                        Carta c2 = juego.darCartaTemporal( );

                        cartas.set( indice, c2 );
                        juego.modificarCartaTemporal( c1 );
                        seleccionado = -1;
                        modificarJuego( juego );

                    }
                    else
                    {
                        Carta c1 = ( Carta )cartas.get( indice );
                        Carta c2 = ( Carta )cartas.get( seleccionado );

                        cartas.set( indice, c2 );
                        cartas.set( seleccionado, c1 );
                        seleccionado = -1;
                        modificarJuego( juego );
                    }
                }

            }
            catch( IOException e1 )
            {
            	e1.printStackTrace();
                JOptionPane.showMessageDialog( this, e1.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * M�todo para manejar una acci�n cuando el mouse entre a una zona especifica.
     * @param pEvento Evento del mouse.
     */
    public void mouseEntered( MouseEvent pEvento )
    {
    	// No se implementa.
    }

    /**
     * M�todo para manejar una acci�n cuando el mouse salga de una zona especifica.
     * @param pEvento Evento del mouse.
     */
    public void mouseExited( MouseEvent pEvento )
    {
    	// No se implementa.
    }

    /**
     * M�todo para manejar una acci�n cuando el mouse ha sido presionado
     * @param pEvento Evento del mouse.
     */
    public void mousePressed( MouseEvent pEvento )
    {
    	// No se implementa.
    }

    /**
     * M�todo para manejar una acci�n cuando el mouse ha sido soltado.
     * @param pEvento Evento del mouse.
     */
    public void mouseReleased( MouseEvent pEvento )
    {
    	// No se implementa.
    }
}
