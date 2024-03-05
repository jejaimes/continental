package uniandes.cupi2.continental.servidor.interfaz;

import uniandes.cupi2.continental.servidor.mundo.Continental;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Esta es la ventana principal del servidor de continental
 */
public class InterfazServidor extends JFrame
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Clase principal del servidor
	 */
	private Continental servidorContinental;

	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------

	/**
	 * Es el panel donde se muestran los registros de los jugadores
	 */
	private PanelJugadores panelJugadores;

	/**
	 * Es el panel donde se muestran los encuentros actuales
	 */
	private PanelEncuentros panelEncuentros;

	/**
	 * Panel con las extensiones
	 */
	private PanelExtension panelExtension;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye la ventana principal de la aplicación<br>
	 * @param servidor Es una referencia al servidor sobre el que funciona esta interfaz
	 */
	public InterfazServidor( Continental servidor )
	{
		servidorContinental = servidor;
		inicializarVentana( );
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Inicializa los elementos de la ventana principal
	 */
	private void inicializarVentana( )
	{
		// Construye la forma
		setLayout( new GridBagLayout( ) );
		setSize( 531, 534 );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Servidor Continental" );
		setLocationRelativeTo(null);

		// Creación de los paneles aquí
		GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 1, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		panelEncuentros = new PanelEncuentros( this );
		add( panelEncuentros, gbc );

		gbc = new GridBagConstraints( 0, 1, 1, 1, 1, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		panelJugadores = new PanelJugadores( this );
		add( panelJugadores, gbc );

		panelExtension = new PanelExtension( this );
		gbc = new GridBagConstraints( 0, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
		add( panelExtension, gbc );
	}

	/**
	 * Actualiza la lista de encuentros mostrada en el panelEncuentros
	 */
	public void actualizarEncuentros( )
	{
		Collection encuentros = servidorContinental.darListaActualizadaEncuentros( );
		panelEncuentros.actualizarEncuentros( encuentros );
	}

	/**
	 * Actualiza la lista de jugadores mostrada en el panelJugadores
	 */
	public void actualizarJugadores( )
	{
		try
		{
			Collection jugadores = servidorContinental.darAdministradorResultados( ).consultarRegistrosJugadores( );
			panelJugadores.actualizarJugadores( jugadores );
		}
		catch( SQLException e )
		{
			JOptionPane.showMessageDialog( this, "Hubo un error consultando la lista de jugadores:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
		}
	}

	/**
	 * Cierra la ventana y la aplicación
	 */
	public void dispose( )
	{
		super.dispose( );
		try
		{
			servidorContinental.darAdministradorResultados( ).desconectarBD( );
		}
		catch( SQLException e )
		{
			e.printStackTrace( );
		}
		System.exit( 0 );
	}

	// -----------------------------------------------------------------
	// Puntos de Extensión
	// -----------------------------------------------------------------

	/**
	 * Método para la extensión 1
	 */
	public void reqFuncOpcion1( )
	{
		String resultado = servidorContinental.metodo1( );
		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}

	/**
	 * Método para la extensión 2
	 */
	public void reqFuncOpcion2( )
	{
		String resultado = servidorContinental.metodo2( );
		JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}

	// -----------------------------------------------------------------
	// Main
	// -----------------------------------------------------------------

	/**
	 * Este métoodo ejecuta la aplicación, creando una nueva interfaz
	 * @param args Parámetros de ejecución que no son usados
	 */
	public static void main( String[] args )
	{
		try
		{
			String archivoPropiedades = "./data/servidor.properties";
			Continental servidorBatallaNaval = new Continental( archivoPropiedades );

			InterfazServidor interfaz = new InterfazServidor( servidorBatallaNaval );
			interfaz.setVisible( true );

			servidorBatallaNaval.recibirConexiones( );
		}
		catch( Exception e )
		{            
			e.printStackTrace( );
		}
	}

}