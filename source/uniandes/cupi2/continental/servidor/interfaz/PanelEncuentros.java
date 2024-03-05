package uniandes.cupi2.continental.servidor.interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Es el panel donde se muestran los encuentros que hay actualmente en el servidor
 */
public class PanelEncuentros extends JPanel implements ActionListener
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Comando para el botón Actualizar
	 */
	private static final String ACTUALIZAR = "Actualizar";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Es una referencia a la ventana principal de la aplicación del servidor
	 */
	private InterfazServidor principal;

	// -----------------------------------------------------------------
	// Atributos de la Interfaz
	// -----------------------------------------------------------------

	/**
	 * Es la lista donde se muestran los encuentros
	 */
	private JList listaEncuentros;

	/**
	 * Es el botón que se usa para Actualizar la lista de encuentros
	 */
	private JButton botonActualizar;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Inicializa el panel
	 * @param ventanaPrincipal Es una referencia a la ventana principal del servidor
	 */
	public PanelEncuentros( InterfazServidor ventanaPrincipal )
	{
		principal = ventanaPrincipal;
		inicializarPanel( );
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Inicializa los elementos dentro del panel
	 */
	private void inicializarPanel( )
	{
		setLayout( new BorderLayout( ) );
		setSize(new Dimension(500,200));

		JScrollPane scroll = new JScrollPane( );
		scroll.setPreferredSize(new Dimension(500, 150));
		listaEncuentros = new JList( );
		scroll.getViewport( ).add( listaEncuentros );
		add(scroll, BorderLayout.CENTER);  

		botonActualizar = new JButton( "Actualizar" );
		botonActualizar.addActionListener( this );
		botonActualizar.setActionCommand( ACTUALIZAR );

		add(botonActualizar, BorderLayout.SOUTH);
		setBorder( new TitledBorder( "Juegos actuales" ) );
	}

	/**
	 * Actualiza la lista mostrada de encuentros
	 * @param encuentros Es una colección con los encuentros que hay actualmente en curso
	 */
	public void actualizarEncuentros( Collection encuentros )
	{
		listaEncuentros.setListData( encuentros.toArray( ) );
	}

	/**
	 * Es el método llamado cuando se hace click sobre el botón refrescar
	 * @param evento Es el evento de click sobre el botón
	 */
	public void actionPerformed( ActionEvent evento )
	{
		String comando = evento.getActionCommand( );

		if( ACTUALIZAR.equals( comando ) )
		{
			principal.actualizarEncuentros( );
		}
	}

}
