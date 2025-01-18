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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import base_datos.GestionHotelBBDD;
import estilos.EstiloBotonRetroceso;
import estilos.EstiloBotones;
import estilos.EstiloCheckBox;
import estilos.EstiloTextos;
import estilos.MultiIdioma;

public class CheckOut extends JFrame {

	private static EstiloCheckBox mobilarioCheckBox;
	private static EstiloCheckBox amenidadesCheckBox;
	private static EstiloCheckBox restauranteCheckBox;
	private static EstiloCheckBox parkingCheckBox;
	private static EstiloCheckBox masajesCheckBox;
	private static EstiloCheckBox spaCheckBox;

	private static JTextField numeroHabitacionField;

	public static double precioTotalReserva;

	private static JLabel precioLabel;

	public CheckOut() {

		// Configuración del JFrame
		setTitle("HOTEL CALIFORNIA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());

		Image icon = Toolkit.getDefaultToolkit().getImage("resources/images/hotel_icono.png");
		setIconImage(icon);

		GestionHotelBBDD baseDatos = new GestionHotelBBDD();
		baseDatos.crearBaseDatos();
		baseDatos.crearTablaHabitaciones();
		baseDatos.insertarHabitaciones();
		
		iniciarComponentes(baseDatos);
	}

	private void iniciarComponentes(GestionHotelBBDD baseDatos) {
		// Panel Norte
		JPanel panelNorte = crearPanelNorte(this);

		// Panel Central
		JPanel panelCentral = crearPanelCentral(this, baseDatos);

		// Panel Sur
		JPanel panelSur = crearPanelSur(this, baseDatos);

		// Añadir los paneles al marco
		add(panelNorte, BorderLayout.NORTH);
		add(panelCentral, BorderLayout.CENTER);
		add(panelSur, BorderLayout.SOUTH);
		
		precioLabel.setText("0.0");
	}

	private static JPanel crearPanelNorte(JFrame ventanaActual) {
		// Panel superior
		JPanel panelNorte = new JPanel(new BorderLayout());
		panelNorte.setBackground(Color.decode("#C0C0C0"));

		// Boton atras (a la izquierda)
		EstiloBotonRetroceso botonFlecha = new EstiloBotonRetroceso(ventanaActual, Home.class, -50, 10, 0, 0);
		panelNorte.add(botonFlecha, BorderLayout.WEST);

		// Título "Check-Out" (al centro)
		EstiloTextos tituloLabel = new EstiloTextos("TituloSalida", "titulo", "resources/images/icono_salir.png", 30,
				50, 0, 10, 0, 90, 0, 50);
		panelNorte.add(tituloLabel, BorderLayout.CENTER);

		// Logo del hotel (a la derecha)
		ImageIcon imagenLogo = new ImageIcon("resources/images/logo_empresa.png");
		Image logo = imagenLogo.getImage();
		Image imgRedimensionada = logo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		imagenLogo = new ImageIcon(imgRedimensionada);
		JLabel logoLabel = new JLabel(imagenLogo);
		panelNorte.add(logoLabel, BorderLayout.EAST);

		return panelNorte;
	}

	private static JPanel crearPanelCentral(JFrame ventanaActual, GestionHotelBBDD baseDatos) {

		// Panel central
		JPanel panelCentral = new JPanel(new GridBagLayout());
		panelCentral.setBackground(Color.decode("#C0C0C0"));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(15, 10, 0, 15); // Espacio entre componentes

		// Etiqueta "Número de habitación"
		gbc.gridx = 0; // Columna 0
		gbc.gridy = 0; // Fila 0
		gbc.gridwidth = 1; // Ocupa 1 columna
		gbc.weightx = 0; // No expande horizontalmente
		EstiloTextos numeroHabitacionLabel = new EstiloTextos("NumHabitacion", "texto");
		panelCentral.add(numeroHabitacionLabel, gbc);

		// Campo de texto para "Número de habitación"
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = 1;
		numeroHabitacionField = new JTextField(5);
		numeroHabitacionField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Limitar solo a numeros en el campo numeroHabitacion
		numeroHabitacionField.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str == null)
					return;
				// Solo permitir letras (mayúsculas y minúsculas)
				if (str.matches("[0-9]") && getLength() < 10) {
					super.insertString(offs, str, a);
				}
			}
		});
		panelCentral.add(numeroHabitacionField, gbc);

		// Botón "Buscar"
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		EstiloBotones botonBuscar = new EstiloBotones("Buscar", "Secundario", 20);
		panelCentral.add(botonBuscar, gbc);

		botonBuscar.addActionListener(e -> {
			accionBotonBuscar(ventanaActual, numeroHabitacionField, baseDatos);
		});

		// KeyListener para activar el botón al presionar Enter
	    numeroHabitacionField.addKeyListener(new java.awt.event.KeyAdapter() {
	        public void keyPressed(java.awt.event.KeyEvent evt) {
	            // Si la tecla presionada es Enter
	            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
	                botonBuscar.doClick(); // Esto simula un clic en el botón Buscar
	            }
	        }
	    });

		// Texto "Detalle"
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		EstiloTextos detalleLabel = new EstiloTextos("Detalle", "texto");
		panelCentral.add(detalleLabel, gbc);

		// Panel para los checkboxes
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH; // Expande horizontal y verticalmente
		JPanel panelDetalles = new JPanel();
		panelDetalles.setBackground(Color.WHITE);
		panelDetalles.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));

		// Crear y añadir checkboxes
		mobilarioCheckBox = new EstiloCheckBox("CheckBoxMobilario");
		amenidadesCheckBox = new EstiloCheckBox("CheckBoxAmenidades");
		restauranteCheckBox = new EstiloCheckBox("CheckBoxRestaurante");
		parkingCheckBox = new EstiloCheckBox("CheckBoxParking");
		masajesCheckBox = new EstiloCheckBox("CheckBoxMasajes");
		spaCheckBox = new EstiloCheckBox("CheckBoxSpa");

		mobilarioCheckBox.setEnabled(false);
		amenidadesCheckBox.setEnabled(false);
		restauranteCheckBox.setEnabled(false);
		parkingCheckBox.setEnabled(false);
		masajesCheckBox.setEnabled(false);
		spaCheckBox.setEnabled(false);

		panelDetalles.add(mobilarioCheckBox);
		panelDetalles.add(amenidadesCheckBox);
		panelDetalles.add(parkingCheckBox);
		panelDetalles.add(restauranteCheckBox);
		panelDetalles.add(masajesCheckBox);
		panelDetalles.add(spaCheckBox);

		panelCentral.add(panelDetalles, gbc);

		return panelCentral;
	}

	private static JPanel crearPanelSur(JFrame ventanaActual, GestionHotelBBDD baseDatos) {

		// Panel sur
		JPanel panelSur = new JPanel(new BorderLayout());
		panelSur.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Margen inferior
		panelSur.setBackground(Color.decode("#C0C0C0"));

		// Panel en blanco
		JPanel panelEnBlanco = new JPanel();
		panelEnBlanco.setPreferredSize(new Dimension(150, panelSur.getHeight()));
		panelEnBlanco.setBackground(Color.decode("#C0C0C0"));
		panelSur.add(panelEnBlanco, BorderLayout.WEST);

		// Botón Dinero
		panelSur.add(PrecioTotal(), BorderLayout.CENTER);

		// Botón Finalizar
		JPanel subPanel = new JPanel(new BorderLayout());
		subPanel.setBackground(Color.decode("#C0C0C0"));

		// Panel en blanco 2
		JPanel panelEnBlanco2 = new JPanel();
		panelEnBlanco2.setBackground(Color.decode("#C0C0C0"));
		panelEnBlanco2.setPreferredSize(new Dimension(80, panelSur.getHeight()));

		EstiloBotones botonFinalizar = new EstiloBotones("Finalizar", "Tercero", 20);
		botonFinalizar.addActionListener(e -> {
			accionBotonFinalizar(ventanaActual, numeroHabitacionField, baseDatos);
		});

		// Panel en blanco 3
		JPanel panelEnBlanco3 = new JPanel();
		panelEnBlanco3.setBackground(Color.decode("#C0C0C0"));
		panelEnBlanco3.setPreferredSize(new Dimension(20, panelSur.getHeight()));

		subPanel.add(panelEnBlanco2, BorderLayout.WEST);
		subPanel.add(botonFinalizar, BorderLayout.CENTER);
		subPanel.add(panelEnBlanco3, BorderLayout.EAST);

		panelSur.add(subPanel, BorderLayout.EAST);

		return panelSur;
	}

	// Label precioTotal
	private static JPanel PrecioTotal() {
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

		precioLabel = new JLabel(String.valueOf(precioTotalReserva) + "€");
		precioLabel.setFont(new Font("Arial", Font.BOLD, 20));
		precioLabel.setForeground(Color.BLACK);

		panelPrecio.add(precioLabel);

		return panelPrecio;
	}

	// Acción del botón Buscar
	private static void accionBotonBuscar(JFrame ventanaActual, JTextField numeroHabitacionField,
			GestionHotelBBDD baseDatos) {
		String numeroHabitacion = numeroHabitacionField.getText().trim();
		if (numeroHabitacion.isEmpty()) {
			JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("Campos vacios"),
					MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		try {
			int numHabitacion = Integer.parseInt(numeroHabitacion);

			// Reiniciar precioTotalReserva a 0 para que no acumule valores previos
			precioTotalReserva = 0.0;

			// Verificar habitación en Reservas y actualizar checkboxes
			if (baseDatos.verificarYActualizarReservas(numHabitacion, mobilarioCheckBox, amenidadesCheckBox)) {
				System.out.println("Checkboxes de Reservas actualizados.");

				// Verificar habitación en Servicios y actualizar checkboxes
				if (baseDatos.verificarYActualizarServicios(numHabitacion, parkingCheckBox, masajesCheckBox,
						spaCheckBox, restauranteCheckBox)) {
					System.out.println("Checkboxes de Servicios actualizados.");
				} else {
					System.out.println("La habitación no tiene datos en la tabla Servicios.");
				}

				String cliente = baseDatos.obtenerClientePorNumeroHabitacion(numHabitacion);

				// Calcular precio de la reserva y servicios
				precioTotalReserva += baseDatos.obtenerPrecioDeReserva(numHabitacion, cliente);
				precioTotalReserva += baseDatos.obtenerPrecioDeServicios(numHabitacion, cliente);

				// Actualizar el JLabel con el precio total
				precioLabel.setText(String.valueOf(precioTotalReserva) + MultiIdioma.IdiomaTexto("Dinero"));

				JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("HabitacionEncontrada"),
						MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("HabitacionNoEncontrada"),
						MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (NumberFormatException ex) {
			ex.getMessage();
		}
	}

	// Acción del botón Finalizar
	private static void accionBotonFinalizar(JFrame ventanaActual, JTextField numeroHabitacionField,
			GestionHotelBBDD baseDatos) {
		String numeroHabitacion = numeroHabitacionField.getText().trim();
		if (numeroHabitacion.isEmpty()) {
			JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("CamposVacios"),
					MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try {
			int numHabitacion = Integer.parseInt(numeroHabitacion); // Convertir a entero

			// Confirmar la acción
			int confirmacion = JOptionPane.showConfirmDialog(ventanaActual,
					MultiIdioma.IdiomaTexto("estaSeguroReserva"), MultiIdioma.IdiomaTexto("Confirmacion"),
					JOptionPane.YES_NO_OPTION);

			if (confirmacion == JOptionPane.YES_OPTION) {
				// Obtener el cliente asociado a la habitación
				String clienteDNI = baseDatos.obtenerClientePorNumeroHabitacion(numHabitacion);

				if (clienteDNI != null) {
					// Eliminar la reserva
					baseDatos.eliminarReservaPorNumHabitacion(numHabitacion);

					// Eliminar los servicios
					baseDatos.eliminarServicios(numHabitacion, clienteDNI);

					// Eliminar el cliente
					baseDatos.eliminarClientePorDni(clienteDNI);

					// Liberar la habitación
					baseDatos.marcarHabitacionComoLibre(numHabitacion);

					JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("finalizarReserva"), MultiIdioma.IdiomaTexto("Exito"),
							JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("HabitacionNoEncontrada"), MultiIdioma.IdiomaTexto("Confirmacion"), JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (NumberFormatException ex) {
			ex.getMessage();
		}
		numeroHabitacionField.setText("");
		precioLabel.setText("");
	}
}