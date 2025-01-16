package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import base_datos.GestionHotelBBDD;
import estilos.*;
import sacar_valores.GestorDatosRegistro;
import sacar_valores.Reserva;

public class Registro extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField insertarNombre;
	private JTextField insertarApellidos;
	private JTextField insertarIdentificacion;
	private JSpinner insertarDias;

	private ButtonGroup grupoEstiloCamas;
	private ButtonGroup grupoTipoVistas;

	public EstiloCheckBox aceptarCondiciones;

	public Registro() {
		// Crear el JFrame
		Image icon = Toolkit.getDefaultToolkit().getImage("resources/images/hotel_icono.png");
		setTitle("HOTEL CALIFORNIA");
		setResizable(false); // Bloquea el poder redimensionarlo
		setIconImage(icon);
		setSize(600, 850);
		centrarVentana(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		iniciarComponentesRegistro();
		setVisible(true);

	}

	private void iniciarComponentesRegistro() {
		// Crear panel de todo el frame
		JPanel panelRegistro = new JPanel();
		panelRegistro.setLayout(new BoxLayout(panelRegistro, BoxLayout.Y_AXIS)); // Orientado verticalmente
		panelRegistro.setPreferredSize(new Dimension(600, 700));
		Dimension dimensionFrame = panelRegistro.getPreferredSize();

		JPanel panelSuperior = crearPanelSuperior(this);
		JPanel panelCentral = crearPanelCentral(dimensionFrame);
		JPanel panelInferior = crearPanelInferior(dimensionFrame);

		panelRegistro.add(panelSuperior);
		panelRegistro.add(panelCentral);
		panelRegistro.add(panelInferior);
		add(panelRegistro);
	}

	private JPanel crearPanelSuperior(JFrame ventanaActual) {
		// Crear panel superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout()); // Layout de tipo BorderLayout
		panelSuperior.setBackground(Color.decode("#C0C0C0")); // Color del fondo

		// Crear botón volver
		EstiloBotonRetroceso botonRetroceso = new EstiloBotonRetroceso(ventanaActual, Home.class, -50, 10, 0, 0);

		// Crear Titulo de la ventana
		EstiloTextos TituloReserva = new EstiloTextos("TituloReserva", "titulo", "resources/images/icono_reserva.png",
				30, 50, 0, 10, 0, 90, 0, 50);

		// Crear imagen logotipo
		ImageIcon imagenLogo = new ImageIcon("resources/images/logo_empresa.png");
		Image imagen = imagenLogo.getImage();
		Image escalarImagen = imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		imagenLogo = new ImageIcon(escalarImagen);
		JLabel logotipo = new JLabel(imagenLogo);

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
		panelCentral.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

		// BLOQUE IDENTIFICACION
		JPanel panelEncabezado1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelEncabezado1.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos EncabezadoIdentif = new EstiloTextos("EncabezadoIdentificación", "encabezado1");
		panelEncabezado1.add(EncabezadoIdentif);

		JPanel panelBloque1 = new JPanel();
		panelBloque1.setLayout(new BoxLayout(panelBloque1, BoxLayout.Y_AXIS));
		panelBloque1.setBackground(Color.decode("#C0C0C0"));
		panelBloque1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panelBloque1.setPreferredSize(new Dimension(dimensionFrame.width, 150));
		panelBloque1.add(InsertarIdentificacion());

		// BLOQUE HABITACIONES
		JPanel panelEncabezado2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelEncabezado2.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos EncabezadoHabitacion = new EstiloTextos("SelecHabitaciones", "encabezado1");
		panelEncabezado2.add(EncabezadoHabitacion);

		JPanel panelBloque2 = new JPanel();
		panelBloque2.setLayout(new BoxLayout(panelBloque2, BoxLayout.Y_AXIS));
		panelBloque2.setBackground(Color.decode("#C0C0C0"));
		panelBloque2.setPreferredSize(new Dimension(dimensionFrame.width, 150));
		panelBloque2.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panelBloque2.add(SeleccionHabitaciones());

		JPanel panelUpgrade = new JPanel();
		panelUpgrade.setLayout(new BorderLayout());
		panelUpgrade.setBackground(Color.decode("#C0C0C0"));
		panelUpgrade.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		EstiloTextos infoUpgrade = new EstiloTextos("Upgrade", "texto");
		infoUpgrade.setHorizontalAlignment(SwingConstants.LEFT);

		JButton upgrade = Upgrade();
		JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botonPanel.setOpaque(false);
		botonPanel.add(upgrade);
		panelUpgrade.add(infoUpgrade, BorderLayout.NORTH);
		panelUpgrade.add(botonPanel, BorderLayout.SOUTH);

		// BLOQUE POLITICAS
		JPanel panelEncabezado3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelEncabezado3.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos EncabezadoPoliticas = new EstiloTextos("PoliticaPrivacidad", "encabezado1");
		panelEncabezado3.add(EncabezadoPoliticas);

		JPanel panelBloque3 = new JPanel();
		panelBloque3.setLayout(new BoxLayout(panelBloque3, BoxLayout.Y_AXIS));
		panelBloque3.setBackground(Color.decode("#C0C0C0"));
		panelBloque3.setPreferredSize(new Dimension(dimensionFrame.width, 100));
		panelBloque3.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		JPanel panelTextoPoliticas = new JPanel();
		panelTextoPoliticas.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelTextoPoliticas.setBackground(Color.WHITE);
		Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border bordeVacio = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		panelTextoPoliticas.setBorder(BorderFactory.createCompoundBorder(bordeNegro, bordeVacio));
		panelTextoPoliticas.setPreferredSize(new Dimension(dimensionFrame.width, 60));

		EstiloTextos textoPoliticasPriv = new EstiloTextos("textoPoliticas", "texto");
		textoPoliticasPriv.setHorizontalAlignment(SwingConstants.LEFT);
		panelTextoPoliticas.add(textoPoliticasPriv);

		EstiloTextos masInfo = PoliticasPrivacidad();
		masInfo.setHorizontalAlignment(SwingConstants.RIGHT);

		aceptarCondiciones = new EstiloCheckBox("aceptar");
		aceptarCondiciones.setAlignmentY(Component.TOP_ALIGNMENT);

		JPanel panelCheckbox = new JPanel();
		panelCheckbox.setLayout(new BorderLayout());
		panelCheckbox.setPreferredSize(new Dimension(dimensionFrame.width, 10));
		panelCheckbox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panelCheckbox.setBackground(Color.decode("#C0C0C0"));
		panelCheckbox.add(aceptarCondiciones, BorderLayout.WEST);
		panelCheckbox.add(masInfo, BorderLayout.EAST);

		panelBloque3.add(panelTextoPoliticas);
		panelBloque3.add(panelCheckbox);

		panelCentral.add(panelEncabezado1);
		panelCentral.add(panelBloque1);
		panelCentral.add(panelEncabezado2);
		panelCentral.add(panelBloque2);
		panelCentral.add(panelUpgrade);
		panelCentral.add(panelEncabezado3);
		panelCentral.add(panelBloque3);

		return panelCentral;
	}

	private JPanel crearPanelInferior(Dimension dimensionFrame) {
		// Crear panel inferior
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new BorderLayout());
		panelInferior.setBackground(Color.decode("#C0C0C0"));
		panelInferior.setPreferredSize(new Dimension(dimensionFrame.width, 40));
		panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 200));

		EstiloBotones botonContinuar = ContinuarReserva();
		botonContinuar.setHorizontalAlignment(SwingConstants.CENTER);

		panelInferior.add(botonContinuar);

		return panelInferior;
	}

	private JPanel InsertarIdentificacion() {
		JPanel panelIdentificacion = new JPanel();
		panelIdentificacion.setLayout(new BoxLayout(panelIdentificacion, BoxLayout.Y_AXIS));
		panelIdentificacion.setBackground(Color.decode("#C0C0C0"));
		panelIdentificacion.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
		panelIdentificacion.setPreferredSize(new Dimension(700, 780));

		panelIdentificacion.add(crearPanelNombre());
		panelIdentificacion.add(crearPanelApellidos());
		panelIdentificacion.add(crearPanelDocumentacion());
		panelIdentificacion.add(crearPanelDias());

		return panelIdentificacion;
	}

	private JPanel crearPanelNombre() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelNombre = new JPanel(new GridBagLayout());
		panelNombre.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos textoNombre = new EstiloTextos("Nombre", "encabezado2");
		insertarNombre = new JTextField(20);
		// Limitar solo a letras en el campo "Nombre"
		insertarNombre.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str == null)
					return;
				// Solo permitir letras (mayúsculas y minúsculas)
				if (str.matches("[a-zA-Z\\s]+") && getLength() < 15) {
					super.insertString(offs, str, a);
				}
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.LINE_START;
		panelNombre.add(textoNombre, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelNombre.add(insertarNombre, c);

		return panelNombre;
	}

	private JPanel crearPanelApellidos() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelApellidos = new JPanel(new GridBagLayout());
		panelApellidos.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos textoApellidos = new EstiloTextos("Apellidos", "encabezado2");
		insertarApellidos = new JTextField(20);
		// Limitar solo a letras en el campo "Apellidos"
		insertarApellidos.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str == null)
					return;
				// Solo permitir letras (mayúsculas y minúsculas)
				if (str.matches("[a-zA-Z\\s]+") && getLength() < 20) {
					super.insertString(offs, str, a);
				}
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.LINE_START;
		panelApellidos.add(textoApellidos, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelApellidos.add(insertarApellidos, c);

		return panelApellidos;

	}

	private JPanel crearPanelDocumentacion() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelDocumento = new JPanel(new GridBagLayout());
		panelDocumento.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos textoDocumento = new EstiloTextos("DocumentoIdentificador", "encabezado2");
		insertarIdentificacion = new JTextField(20);
		// Limitar a 9 caracteres, solo números y una letra al final
		insertarIdentificacion.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str == null)
					return;
				// Solo permitir números o una letra al final
				if (str.matches("[0-9]+") && getLength() < 9 || (str.matches("[A-Za-z]") && getLength() < 9)) {
					super.insertString(offs, str, a);
				}
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.LINE_START;
		panelDocumento.add(textoDocumento, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelDocumento.add(insertarIdentificacion, c);

		return panelDocumento;
	}

	private JPanel crearPanelDias() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelDias = new JPanel(new GridBagLayout());
		panelDias.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos textoDias = new EstiloTextos("Dias", "encabezado2");
		SpinnerNumberModel modeloDias = new SpinnerNumberModel(1, 1, 15, 1); // Rango de 1 a 15 días
		insertarDias = new JSpinner(modeloDias);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.LINE_START;
		panelDias.add(textoDias, c);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelDias.add(insertarDias, c);

		return panelDias;
	}

	private JPanel SeleccionHabitaciones() {
		JPanel panelSeleccion = new JPanel();
		panelSeleccion.setLayout(new BoxLayout(panelSeleccion, BoxLayout.Y_AXIS));
		panelSeleccion.setBackground(Color.WHITE);
		Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border bordeVacio = BorderFactory.createEmptyBorder(5, 20, 5, 20);
		panelSeleccion.setBorder(BorderFactory.createCompoundBorder(bordeNegro, bordeVacio));
		panelSeleccion.setPreferredSize(new Dimension(100, 60));

		panelSeleccion.add(crearPanelCamas());
		panelSeleccion.add(crearPanelVistas());

		return panelSeleccion;
	}

	private JPanel crearPanelCamas() {
		JPanel panelCamas = new JPanel();
		panelCamas.setLayout(new GridBagLayout()); // Layout que permite posicion vertical/horizontal
		panelCamas.setPreferredSize(new Dimension(50, 60));
		panelCamas.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		EstiloTextos tiposCamas = new EstiloTextos("EncabezadoCamas", "encabezado3");
		c.gridx = 0; // Columna
		c.gridy = 0; // Fila
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.LINE_START;
		panelCamas.add(tiposCamas, c);

		// Crear un margen adicional para los textos a la izquierda
		Insets leftInset = new Insets(2, 2, 2, 2);

		grupoEstiloCamas = new ButtonGroup();
		EstiloRadioBotones matrimonial = new EstiloRadioBotones("Matrimonial");
		grupoEstiloCamas.add(matrimonial);
		c.gridx = 0; // Columa
		c.gridy = 1; // Fila
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = leftInset;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelCamas.add(matrimonial, c);

		EstiloRadioBotones dosCamas = new EstiloRadioBotones("CamasSeparadas");
		grupoEstiloCamas.add(dosCamas);
		c.gridx = 1;
		panelCamas.add(dosCamas, c);

		EstiloRadioBotones unaCama = new EstiloRadioBotones("CamaIndividual");
		grupoEstiloCamas.add(unaCama);
		c.gridx = 2;
		panelCamas.add(unaCama, c);

		return panelCamas;
	}

	private JPanel crearPanelVistas() {
		JPanel panelVistas = new JPanel();
		panelVistas.setLayout(new GridBagLayout()); // Layout que permite posicion vertical/horizontal
		panelVistas.setPreferredSize(new Dimension(50, 60));
		panelVistas.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		EstiloTextos tiposVistas = new EstiloTextos("EncabezadoVistas", "encabezado3");
		c.gridx = 0; // Columna
		c.gridy = 0; // Fila
		c.weightx = 0.1;
		c.gridwidth = 3;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.LINE_START;
		panelVistas.add(tiposVistas, c);

		// Crear un margen adicional para los textos a la izquierda
		Insets leftInset2 = new Insets(2, 2, 2, 2);

		grupoTipoVistas = new ButtonGroup();
		EstiloRadioBotones jardin = new EstiloRadioBotones("Jardin");
		grupoTipoVistas.add(jardin);
		c.gridx = 0; // Columa
		c.gridy = 1; // Fila
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = leftInset2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelVistas.add(jardin, c);

		EstiloRadioBotones paronamica = new EstiloRadioBotones("Panoramica");
		grupoTipoVistas.add(paronamica);
		c.gridx = 1;
		panelVistas.add(paronamica, c);

		EstiloRadioBotones patioInterior = new EstiloRadioBotones("Patio Interior");
		grupoTipoVistas.add(patioInterior);
		c.gridx = 2;
		panelVistas.add(patioInterior, c);

		return panelVistas;
	}

	private void centrarVentana(JFrame ventana) {
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension dimensionPantalla = pantalla.getScreenSize();
		int anchoPantalla = dimensionPantalla.width;
		int alturaPantalla = dimensionPantalla.height;

		ventana.setLocationRelativeTo(null);
	}

	private EstiloBotones Upgrade() {
		EstiloBotones botonUpgrade = new EstiloBotones("Upgrade", "Secundario", 10);
		botonUpgrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Upgrades();
			}
		});
		return botonUpgrade;
	}

	private EstiloTextos PoliticasPrivacidad() {
		EstiloTextos masInfo = new EstiloTextos("MasInfo", "texto");
		masInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		masInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Politicas_Privacidad();
			}
		});
		return masInfo;
	}

	private EstiloBotones ContinuarReserva() {
		EstiloBotones botonContinuar = new EstiloBotones("Continuar", "Secundario", 10);
		botonContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GestionHotelBBDD bbdd = new GestionHotelBBDD();
				GestorDatosRegistro seleccionDatos = new GestorDatosRegistro();
				Reserva reserva = new Reserva();

				// Obtener los valores de la identificación
				String nombre = insertarNombre.getText();
				String apellidos = insertarApellidos.getText();
				String documento = insertarIdentificacion.getText();
				int dias = (int) insertarDias.getValue();

				// Obtener los valores de selección de habitación y vista
				String tipoCamaSeleccionada = seleccionDatos.getSelectedTipoCama(grupoEstiloCamas);
				String tipoVistaSeleccionada = seleccionDatos.getSelectedTipoVista(grupoTipoVistas);

				// Cambiar el texto a español para comprobar con la bbdd
				String tipoCama = MultiIdioma.traducirTexto(tipoCamaSeleccionada, true);
				String tipoVista = MultiIdioma.traducirTexto(tipoVistaSeleccionada, true);
				int numHabitacion = bbdd.getNumeroHabitacion(tipoCama, tipoVista);

				boolean existeCliente = bbdd.existeDocumentoCliente(documento);
				if (existeCliente) {
					JOptionPane.showMessageDialog(null, "Cliente ya registrado en el sistema");
					return;
				}

				if (!nombre.isEmpty() && !apellidos.isEmpty() && !documento.isEmpty() && dias > 0
						&& tipoCamaSeleccionada != null && tipoVistaSeleccionada != null) {
					if (aceptarCondiciones.isSelected()) {
						// Establecer los datos del cliente
						reserva.setDatosCliente(nombre, apellidos, documento);
						reserva.setDiasReserva(dias);
						reserva.setDatosHabitacion(tipoCamaSeleccionada, tipoVistaSeleccionada);

						if (numHabitacion != -1) {
							reserva.setNumeroHabitacion(numHabitacion); // Guardarlo en la clase Reserva
							System.out.println("Número de habitación disponible: " + numHabitacion);

							Registro.this.dispose();
							new Confirmar_Reserva();

						} else {
							JOptionPane.showMessageDialog(null, MultiIdioma.IdiomaTexto("MessageHabitaciones"));
							return; // Salir del método si no hay habitación válida
						}
					} else {
						JOptionPane.showMessageDialog(null, MultiIdioma.IdiomaTexto("MessagePoliticas"));
					}

				} else {
					JOptionPane.showMessageDialog(null, MultiIdioma.IdiomaTexto("MessageCampos"));
				}
			}
		});
		return botonContinuar;
	}

}
