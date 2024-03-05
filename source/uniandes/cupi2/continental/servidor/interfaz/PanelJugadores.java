package uniandes.cupi2.continental.servidor.interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Es el panel donde se muestran los jugadores registrados en la base de datos
 */
public class PanelJugadores extends JPanel implements ActionListener
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para el bot�n Actualizar
	 */
	private static final String ACTUALIZAR = "Actualizar";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Es una referencia a la ventana principal de la aplicaci�n del servidor
	 */
	private InterfazServidor principal;

	// -----------------------------------------------------------------
	// Atributos de la Interfaz
	// -----------------------------------------------------------------

	/**
	 * Es la lista donde se muestran los jugadores
	 */
	private JList listaJugadores;

	/**
	 * Es el bot�n que se usa para refrescar la lista de jugadores
	 */
	private JButton botonActualizar;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicializa el panel
	 * @param ventanaPrincipal Es una referencia a la ventana principal del servidor
	 */
	public PanelJugadores( InterfazServidor ventanaPrincipal )
	{
		principal = ventanaPrincipal;
		inicializarPanel( );
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Inicializa los elementos dentro del panel
	 */
	private void inicializarPanel( )
	{
		setLayout( new BorderLayout( ) );

		JScrollPane scroll = new JScrollPane( );
		scroll.setPreferredSize(new Dimension(500, 150));
		listaJugadores = new JList( );
		scroll.getViewport( ).add( listaJugadores );
		add(scroll, BorderLayout.CENTER);

		botonActualizar = new JButton( "Actualizar" );
		botonActualizar.addActionListener( this );
		botonActualizar.setActionCommand( ACTUALIZAR );

		add(botonActualizar, BorderLayout.SOUTH); 
		setBorder( new TitledBorder( "Estad�sticas jugadores" ) );
	}

	/**
	 * Actualiza la lista mostrada de jugadores
	 * @param jugadores Es una colecci�n con la informaci�n de los jugadores que hay actualmente en la bd
	 */
	public void actualizarJugadores( Collection jugadores )
	{
		listaJugadores.setListData( jugadores.toArray( ) );
	}

	/**
	 * Es el m�todo llamado cuando se hace click sobre el bot�n refrescar
	 * @param evento Es el evento de click sobre el bot�n
	 */
	public void actionPerformed( ActionEvent evento )
	{
		String comando = evento.getActionCommand( );

		if( ACTUALIZAR.equals( comando ) )
		{
			principal.actualizarJugadores( );
		}
	}

}
