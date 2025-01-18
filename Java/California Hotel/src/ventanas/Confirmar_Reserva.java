package ventanas;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import base_datos.GestionHotelBBDD;
import estilos.EstiloBotonRetroceso;
import estilos.EstiloBotones;
import estilos.EstiloTextos;
import estilos.MultiIdioma;
import sacar_valores.Reserva;

public class Confirmar_Reserva extends JFrame {
	private static final long serialVersionUID = 1L;

//	private static JPanel panelEspecificaciones = new JPanel();
//	private static JPanel panelPrecio = new JPanel();

	private static EstiloTextos tipoCamaLabel;
	private static EstiloTextos tipoVistasLabel;
	private static EstiloTextos mejorasMobiliarioLabel;
	private static EstiloTextos mejorasAmenidadesLabel;
	private static JLabel precioLabel;

	public Confirmar_Reserva() {
		// Crear el JFrame
		Image icon = Toolkit.getDefaultToolkit().getImage("resources/images/hotel_icono.png");
		setTitle("HOTEL CALIFORNIA");
		setResizable(false); // Bloquea el poder redimensionarlo
		setIconImage(icon);
		setSize(600, 500);
		centrarVentana(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		iniciarComponentesReserva();
		
		setVisible(true);
	}

	private void iniciarComponentesReserva() {
		// Crear panel de todo el frame
		JPanel panelPrecioHabitacion = new JPanel();
		panelPrecioHabitacion.setLayout(new BoxLayout(panelPrecioHabitacion, BoxLayout.Y_AXIS));
		panelPrecioHabitacion.setPreferredSize(new Dimension(600, 600));
		Dimension dimensionFrame = panelPrecioHabitacion.getPreferredSize();

		JPanel panelSuperior = crearPanelSuperior(this);
		JPanel panelCentral = crearPanelCentral(dimensionFrame);
		JPanel panelInferior = crearPanelInferior(dimensionFrame);

		panelPrecioHabitacion.add(panelSuperior);
		panelPrecioHabitacion.add(panelCentral);
		panelPrecioHabitacion.add(panelInferior);
		add(panelPrecioHabitacion);

	}

	private JPanel crearPanelSuperior(JFrame ventanaActual) {
		// Crear panel superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout()); // Layout de tipo BorderLayout
		panelSuperior.setBackground(Color.decode("#C0C0C0")); // Color del fondo

		// Crear imagen logotipo
		ImageIcon imagenLogo = new ImageIcon("resources/images/logo_empresa.png");
		Image imagen = imagenLogo.getImage();
		Image escalarImagen = imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		imagenLogo = new ImageIcon(escalarImagen);
		JLabel logotipo = new JLabel(imagenLogo);

		// Crear botón volver
		EstiloBotonRetroceso botonRetroceso = new EstiloBotonRetroceso(this, Registro.class, -50, 10, 0, 0);

		// Crear componentes
		EstiloTextos TituloReserva = new EstiloTextos("TituloPrecio", "titulo");

		panelSuperior.add(botonRetroceso, BorderLayout.WEST);
		panelSuperior.add(TituloReserva, BorderLayout.CENTER);
		panelSuperior.add(logotipo, BorderLayout.EAST);

		return panelSuperior;

	}

	private JPanel crearPanelCentral(Dimension dimensionFrame) {
		// Crear panel central
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS)); // Layout en vertical
		panelCentral.setBackground(Color.decode("#C0C0C0"));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 20));

		JPanel panelEncabezado1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelEncabezado1.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos detalle = new EstiloTextos("Detalle", "texto");
		detalle.setHorizontalAlignment(SwingConstants.LEFT);
		panelEncabezado1.add(detalle);

		panelCentral.add(panelEncabezado1);
		panelCentral.add(EspecificacionesSeleccionadas());
		panelCentral.add(Box.createRigidArea(new Dimension(0, 30)));
		panelCentral.add(PrecioTotal());

		return panelCentral;
	}

	private JPanel crearPanelInferior(Dimension dimensionFrame) {
		// Crear panel inferior
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new BorderLayout());
		panelInferior.setBackground(Color.decode("#C0C0C0"));
		panelInferior.setPreferredSize(new Dimension(dimensionFrame.width, 20));
		panelInferior.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));

		EstiloBotones botonContinuar = AcceptarReserva();
		botonContinuar.setHorizontalAlignment(SwingConstants.CENTER);

		panelInferior.add(botonContinuar);
		
		return panelInferior;
	}

	private JPanel EspecificacionesSeleccionadas() {
		JPanel panelEspecificaciones = new JPanel();
		panelEspecificaciones.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelEspecificaciones.setBackground(Color.WHITE);
		Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border bordeVacio = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panelEspecificaciones.setBorder(BorderFactory.createCompoundBorder(bordeNegro, bordeVacio));
		panelEspecificaciones.setPreferredSize(new Dimension(200, 100));

		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		panelDatos.setBackground(Color.WHITE);

		Reserva estado = new Reserva();
		estado.getInstance();

		tipoCamaLabel = new EstiloTextos("CamaSelec", "texto");
		tipoVistasLabel = new EstiloTextos("VistasSelec", "texto");
		mejorasMobiliarioLabel = new EstiloTextos("MejorasMobiliario", "texto");
		mejorasAmenidadesLabel = new EstiloTextos("MejorasAmenidades", "texto");

		panelDatos.add(tipoCamaLabel);
		panelDatos.add(tipoVistasLabel);
		panelDatos.add(mejorasMobiliarioLabel);
		panelDatos.add(mejorasAmenidadesLabel);

		panelEspecificaciones.add(panelDatos);
		
		return panelEspecificaciones;

	}

	private JPanel PrecioTotal() {
		JPanel panelPrecio = new JPanel();
		panelPrecio.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelPrecio.setBackground(Color.WHITE);

		// Crear borde redondeado
		int radio = 15; // Radio del borde redondeado
		Border bordeNegro = new LineBorder(Color.BLACK, 2) {
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(2));
				g2d.drawRoundRect(x, y, width - 1, height - 1, radio, radio);
			}
		};

		// Establecer borde redondeado al panel
		panelPrecio.setBorder(bordeNegro);
		panelPrecio.setPreferredSize(new Dimension(50, 30));
		panelPrecio.setMaximumSize(new Dimension(70, 40));

		// Obtener el número de habitación y los días de la reserva
		GestionHotelBBDD bbdd = new GestionHotelBBDD();
		Reserva reserva = new Reserva();
		int numHabitacion = reserva.getNumeroHabitacion();
		int dias = reserva.getNumeroDias();

		// Llama al método con el booleano de si se usaron upgrades
		boolean upgradesSeleccionados = Upgrades.seUsaronUpgrades;
		double precioTotal = bbdd.calcularPrecioTotal(numHabitacion, upgradesSeleccionados, dias);
		reserva.setPrecioTotal(precioTotal);

		precioLabel = new JLabel(String.valueOf(precioTotal) + "€");
		precioLabel.setFont(new Font("Arial", Font.BOLD, 20));
		precioLabel.setForeground(Color.BLACK);

		panelPrecio.add(precioLabel);
		
		return panelPrecio;
	}

	// Verificar si se han seleccionado mejoras o no
	private boolean containsMejora(String mejoras, String mejora) {
		return mejoras != null && mejoras.contains(mejora);
	}

	public static void centrarVentana(JFrame ventana) {
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension dimensionPantalla = pantalla.getScreenSize();
		int anchoPantalla = dimensionPantalla.width;
		int alturaPantalla = dimensionPantalla.height;

		ventana.setLocationRelativeTo(null);
	}
	
	public void actualizarVista() {
		// Actualiza los textos dinámicamente usando las nuevas claves o datos
		tipoCamaLabel.actualizarTextoDinamico("CamaSelec");
		tipoVistasLabel.actualizarTextoDinamico("VistasSelec");
		mejorasMobiliarioLabel.actualizarTextoDinamico("MejorasMobiliario");
		mejorasAmenidadesLabel.actualizarTextoDinamico("MejorasAmenidades");

		// Actualiza el precio dinámicamente
		Reserva reserva = new Reserva();
		int numHabitacion = reserva.getNumeroHabitacion();
		int dias = reserva.getNumeroDias();
		boolean upgradesSeleccionados = Upgrades.seUsaronUpgrades;
		double precioTotal = new GestionHotelBBDD().calcularPrecioTotal(numHabitacion, upgradesSeleccionados, dias);
		precioLabel.setText(precioTotal + "€");

		// Revalida y redibuja los paneles
		EspecificacionesSeleccionadas().revalidate();
		EspecificacionesSeleccionadas().repaint();
		PrecioTotal().revalidate();
		PrecioTotal().repaint();
	}
	
	private EstiloBotones AcceptarReserva() {
		EstiloBotones botonContinuar = new EstiloBotones("Acceptar", "Secundario", 10);
		botonContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reserva reserva = new Reserva();
				String dni = reserva.getDocumentoIdentificacion(); // Documento de identificación
				String nombre = reserva.getNombreCliente(); // Nombre del cliente
				String apellidos = reserva.getApellidosCliente(); // Apellidos del cliente

				int numHabitacion = reserva.getNumeroHabitacion();

				// Número de días de la reserva
				int diasReserva = reserva.getNumeroDias();
				double precioTotal = reserva.getPrecioTotal();

				// Transformar mejoras y amenidades en valores booleanos
				boolean cuna = reserva.isCuna();
				boolean camaSupletoria = reserva.isCamaSupletoria();
				boolean pijamasAlbornoces = reserva.isPijamasAlbornoces();
				boolean maquinaCafe = reserva.isMaquinaCafe();
				boolean cajaFuerte = reserva.isCajaFuerte();
				boolean router = reserva.isRouter();
				boolean minibar = reserva.isMinibar();

				// Insertar el cliente en la base de datos si no existe
				GestionHotelBBDD bbdd = new GestionHotelBBDD();
				bbdd.insertarCliente(dni, nombre, apellidos);

				// Insertar la reserva en la base de datos
				bbdd.insertarReserva(dni, numHabitacion, diasReserva, cuna, camaSupletoria, pijamasAlbornoces,
						maquinaCafe, cajaFuerte, router, minibar, precioTotal);

				JOptionPane.showMessageDialog(null, MultiIdioma.IdiomaTexto("MessageReservada") + reserva.getNumeroHabitacion());

				Confirmar_Reserva.this.dispose();
				Home ventanaPrincipal = new Home();
				ventanaPrincipal.setVisible(true);
			}
		});
		return botonContinuar;
	}
}
