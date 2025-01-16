package ventanas;

import javax.swing.*;

import base_datos.GestionHotelBBDD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import estilos.*;
import sacar_valores.Reserva;

public class Home extends JFrame {
	private static final long serialVersionUID = 1L;
	private static int idiomaActual = 1;
	private static boolean inicializado = false;
	private Map<JComponent, String> mapaComponentes = new HashMap<>();

	public static int getIdiomaActual() {
		return idiomaActual; // Retorna el idioma actual en formato String
	}

	public static void setIdiomaActual(int idioma) {
		idiomaActual = idioma; // Método para establecer el idioma actual
	}

	public Home() {
		iniciarBBDD();

		// Crear el JFrame
		setTitle("HOTEL CALIFORNIA");
		Image icon = Toolkit.getDefaultToolkit().getImage("resources/images/hotel_icono.png");
		setIconImage(icon);
		setSize(600, 700);
		centrarVentana(this);
		setResizable(false); // Bloquea el poder redimensionar el frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Crear imagen logotipo
		ImageIcon imagenLogo = new ImageIcon("resources/images/logo_empresa.png");
		Image imagen = imagenLogo.getImage();
		Image escalarImagen = imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		imagenLogo = new ImageIcon(escalarImagen);
		JLabel logotipo = new JLabel(imagenLogo);

		// Crear botón idioma
		JButton botonIdioma = BotonIdioma();

		// Crear componentes
		EstiloTextos Titulo = new EstiloTextos("TituloHome", "titulo");
		EstiloTextos credenciales = new EstiloTextos("Credenciales", "credenciales");

		// Crear botones
		EstiloBotones boton1 = BotonReserva();
		EstiloBotones boton2 = BotonSalida();
		EstiloBotones boton3 = BotonServicios();
		EstiloBotones boton4 = BotonActividades();

		// Crear un panel y añadir componentes al panel superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout()); // Layout de tipo BorderLayout
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelSuperior.setBackground(Color.decode("#C0C0C0")); // Color del fondo

		panelSuperior.add(botonIdioma, BorderLayout.WEST);
		panelSuperior.add(Titulo, BorderLayout.CENTER);
		panelSuperior.add(logotipo, BorderLayout.EAST);

		// Crear segundo panel y añadir componentes
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panelCentral.setBackground(Color.decode("#C0C0C0"));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		panelCentral.add(boton1);
		panelCentral.add(boton2);
		panelCentral.add(boton3);
		panelCentral.add(boton4);

		// Crear tercer panel
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new FlowLayout());
		panelInferior.setBackground(Color.decode("#C0C0C0"));

		panelInferior.add(credenciales);

		mapaComponentes.put(Titulo, Titulo.getUsoTexto());
		mapaComponentes.put(boton1, boton1.getTipoBoton());
		mapaComponentes.put(boton2, boton2.getTipoBoton());
		mapaComponentes.put(boton3, boton3.getTipoBoton());
		mapaComponentes.put(boton4, boton4.getTipoBoton());
		mapaComponentes.put(credenciales, credenciales.getUsoTexto());

		add(panelSuperior, BorderLayout.NORTH);
		add(panelCentral, BorderLayout.CENTER);
		add(panelInferior, BorderLayout.SOUTH);
		setVisible(true);

	}

	public static void centrarVentana(JFrame ventana) {
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension dimensionPantalla = pantalla.getScreenSize();
		int anchoPantalla = dimensionPantalla.width;
		int alturaPantalla = dimensionPantalla.height;

		ventana.setLocationRelativeTo(null);
	}

	private JButton BotonIdioma() {
		ImageIcon iconoIdioma = new ImageIcon("resources/images/icono_idiomas.png");
		Image imagen = iconoIdioma.getImage();
		Image escalarImagen = imagen.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		iconoIdioma = new ImageIcon(escalarImagen);

		JButton botonIdioma = new JButton(iconoIdioma);
		botonIdioma.setBorderPainted(false); // Quitar el borde
		botonIdioma.setContentAreaFilled(false); // Quitar el fondo
		botonIdioma.setFocusPainted(false); // Quitar el enfoque

		botonIdioma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idiomaActual == 1) {
					setIdiomaActual(2);
				} else {
					setIdiomaActual(1);
				}
				ActualizarIdiomaPagina();
			}
		});
		return botonIdioma;
	}

	private EstiloBotones BotonReserva() {
		EstiloBotones boton1 = new EstiloBotones("Check-In", "Principal", 20);
		Reserva reserva = new Reserva();
		// Añadir imagen al botón
		ImageIcon iconoReserva = new ImageIcon("resources/images/icono_reserva.png");
		Image imagenReserva = iconoReserva.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		boton1.setIcon(new ImageIcon(imagenReserva)); // Asignar el icono al botón
		boton1.setHorizontalTextPosition(SwingConstants.LEFT); // Texto a la derecha del icono
		boton1.setVerticalTextPosition(SwingConstants.CENTER); // Alinear texto verticalmente al centro
		boton1.setIconTextGap(20); // Espacio entre la imagen y el texto

		boton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Upgrades.setSeUsaronUpgrades(false);
				Home.this.dispose(); // Cerrar ventana Home
				reserva.reset();
				new Registro(); // Abrir ventana Registro
			}
		});

		return boton1;
	}

	private EstiloBotones BotonSalida() {
		EstiloBotones boton2 = new EstiloBotones("Check-Out", "Principal", 20);

		// Añadir imagen al botón
		ImageIcon iconoReserva = new ImageIcon("resources/images/icono_salir.png");
		Image imagenReserva = iconoReserva.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		boton2.setIcon(new ImageIcon(imagenReserva)); // Asignar el icono al botón
		boton2.setHorizontalTextPosition(SwingConstants.LEFT); // Texto a la derecha del icono
		boton2.setVerticalTextPosition(SwingConstants.CENTER); // Alinear texto verticalmente al centro
		boton2.setIconTextGap(20); // Espacio entre la imagen y el texto

		boton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home.this.dispose(); // Cerrar ventana Home
				CheckOut checkoutFrame = new CheckOut();
				checkoutFrame.setVisible(true);
			}
		});

		return boton2;
	}

	private EstiloBotones BotonServicios() {
		EstiloBotones boton3 = new EstiloBotones("Servicios", "Principal", 20);

		// Añadir imagen al botón
		ImageIcon iconoReserva = new ImageIcon("resources/images/icono_servicios.png");
		Image imagenReserva = iconoReserva.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		boton3.setIcon(new ImageIcon(imagenReserva)); // Asignar el icono al botón
		boton3.setHorizontalTextPosition(SwingConstants.LEFT); // Texto a la derecha del icono
		boton3.setVerticalTextPosition(SwingConstants.CENTER); // Alinear texto verticalmente al centro
		boton3.setIconTextGap(20); // Espacio entre la imagen y el texto

		boton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home.this.dispose(); // Cerrar ventana Home
				ServiciosExtras serviciosContratadosFrame = new ServiciosExtras();
				serviciosContratadosFrame.setVisible(true);
			}
		});

		return boton3;
	}

	private EstiloBotones BotonActividades() {
		EstiloBotones boton4 = new EstiloBotones("Información", "Principal", 20);

		// Añadir imagen al botón
		ImageIcon iconoReserva = new ImageIcon("resources/images/icono_actividades.png");
		Image imagenReserva = iconoReserva.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		boton4.setIcon(new ImageIcon(imagenReserva)); // Asignar el icono al botón
		boton4.setHorizontalTextPosition(SwingConstants.LEFT); // Texto a la derecha del icono
		boton4.setVerticalTextPosition(SwingConstants.CENTER); // Alinear texto verticalmente al centro
		boton4.setIconTextGap(20); // Espacio entre la imagen y el texto

		boton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home.this.dispose(); // Cerrar ventana Home
				InfoActividades actividades = new InfoActividades();
				actividades.setVisible(true);
			}
		});

		return boton4;
	}

	private void ActualizarIdiomaPagina() {
		MultiIdioma.setIdiomaActual(idiomaActual);
		for (Map.Entry<JComponent, String> entry : mapaComponentes.entrySet()) {
			JComponent componente = entry.getKey();
			if (componente instanceof EstiloBotones) {
				((EstiloBotones) componente).actualizarTexto();
			} else if (componente instanceof EstiloTextos) {
				((EstiloTextos) componente).actualizarTexto();
			}
		}
		revalidate();
		repaint();
	}

	public static void main(String[] args) {
		// Crear una instancia de la ventana
		setIdiomaActual(1);
		new Home();

	}

	public void iniciarBBDD() {
		if (!inicializado) {
			GestionHotelBBDD gestorBBDD = new GestionHotelBBDD();
			gestorBBDD.crearBaseDatos();
			gestorBBDD.crearTablaHabitaciones();
			gestorBBDD.crearTablaClientes();
			gestorBBDD.crearTablaReservas();
			gestorBBDD.crearTablaServicios();

			gestorBBDD.insertarHabitaciones();
			inicializado = true;
		}
	}

}
