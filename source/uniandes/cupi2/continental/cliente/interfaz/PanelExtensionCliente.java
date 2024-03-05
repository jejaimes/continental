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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones.
 */
public class PanelExtensionCliente extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n.
     */
    private static final long serialVersionUID = -2368091935222716496L;

    /**
     * Comando opci�n 1.
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando opci�n 2.
     */
    private static final String OPCION_2 = "OPCION_2";

    /**
     * Comando terminar turno.
     */
    private static final String JUGAR = "Jugar";

    /**
     * Comando victoria.
     */
    private static final String VICTORIA = "Victoria";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n.
     */
    private InterfazContinental principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n opci�n 1.
     */
    private JButton btnOpcion1;

    /**
     * Bot�n opci�n 2.
     */
    private JButton btnOpcion2;

    /**
     * Bot�n Opci�n 3
     */
    private JButton btnTerminarTurno;

    /**
     * Bot�n Opci�n 4
     */
    private JButton btnVictoria;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param pVentanaPrincipal Ventana principal.
     */
    public PanelExtensionCliente( InterfazContinental pVentanaPrincipal )
    {
        principal = pVentanaPrincipal;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 4 ) );

        // Bot�n opci�n 1
        btnOpcion1 = new JButton( "Opci�n 1" );
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add( btnOpcion1 );

        // Bot�n opci�n 2
        btnOpcion2 = new JButton( "Opci�n 2" );
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add( btnOpcion2 );

        // Bot�n terminar turno
        btnTerminarTurno = new JButton( "Terminar turno" );
        btnTerminarTurno.setActionCommand( JUGAR );
        btnTerminarTurno.addActionListener( this );
        add( btnTerminarTurno );

        // Bot�n cantar victoria
        btnVictoria = new JButton( "Cantar victoria" );
        btnVictoria.setActionCommand( VICTORIA );
        btnVictoria.addActionListener( this );
        add( btnVictoria );

        desactivar( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        if( OPCION_1.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( pEvento.getActionCommand( ) ) )
        {
            principal.reqFuncOpcion2( );
        }
        else if( JUGAR.equals( pEvento.getActionCommand( ) ) )
        {
            principal.realizarJugada( );
        }
        else if( VICTORIA.equals( pEvento.getActionCommand( ) ) )
        {
            principal.validarVictoria( );
        }
    }

    /**
     * Activa el bot�n de terminar turno y desactiva el bot�n de victoria.
     */
    public void activarTerminarTurno( )
    {
        btnTerminarTurno.setEnabled( true );
        btnVictoria.setEnabled( false );
    }

    /**
     * Desactiva el bot�n de terminar turno y activa el bot�n de victoria.
     */
    public void desactivarTerminarTurno( )
    {
        btnTerminarTurno.setEnabled( false );
        btnVictoria.setEnabled( true );
    }

    /**
     * Desactiva el bot�n de terminar turno y de victoria.
     */
    public void desactivar( )
    {
        btnTerminarTurno.setEnabled( false );
        btnVictoria.setEnabled( false );
    }

}
