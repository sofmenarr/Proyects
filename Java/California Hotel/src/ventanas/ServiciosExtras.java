package ventanas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import base_datos.GestionHotelBBDD;
import estilos.EstiloBotonRetroceso;
import estilos.EstiloBotones;
import estilos.EstiloCheckBox;
import estilos.EstiloRadioBotones;
import estilos.EstiloTextos;
import estilos.MultiIdioma;

public class ServiciosExtras extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTextField txtHabitacion;
	private JTextField txtTitular;

	private EstiloRadioBotones radioButtonSi;
	private EstiloCheckBox checkBoxMasajeSueco;
	private EstiloCheckBox checkBoxMasajeDeportivo;
	private EstiloCheckBox checkBoxMasajeTailandes;
	private EstiloCheckBox checkBoxSpaMedio;
	private EstiloCheckBox checkBoxSpaCompleto;
	private EstiloCheckBox checkBoxBaños;
	private EstiloCheckBox checkBoxMesasEstandar;
	private EstiloCheckBox checkBoxMesasPrivadas;
	private EstiloCheckBox checkBoxMenusEspeciales;
	private EstiloCheckBox checkBoxEventosyCelebraciones;
	private EstiloCheckBox checkBoxExperienciaGastronomica;
	private EstiloCheckBox checkBoxAireLibre;

	public ServiciosExtras() {
		// Configuración de la ventana
		setTitle("Hotel California");
		setSize(850, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());

		Image icon = Toolkit.getDefaultToolkit().getImage("resources/images/hotel_icono.png");
		setIconImage(icon);

		GestionHotelBBDD baseDatos = new GestionHotelBBDD();

		// Configuración de los componentes
		iniciarComponentes(baseDatos);
	}

	private void iniciarComponentes(GestionHotelBBDD baseDatos) {
		// Panel Norte
		JPanel panelNorte = crearPanelNorte();
		add(panelNorte, BorderLayout.NORTH);

		// Panel central
		JPanel panelCentral = crearPanelCentral(this, baseDatos);
		add(panelCentral, BorderLayout.CENTER);

		// Panel Sur
		JPanel panelSur = crearPanelSur(this, baseDatos);
		add(panelSur, BorderLayout.SOUTH);
	}

	private JPanel crearPanelNorte() {
		// Panel Norte
		JPanel panelNorte = new JPanel(new BorderLayout());
		panelNorte.setPreferredSize(new Dimension(0, 150));
		panelNorte.setBackground(Color.decode("#C0C0C0"));

		// Boton atras (a la izquierda)
		EstiloBotonRetroceso botonFlecha = new EstiloBotonRetroceso(this, Home.class, -50, 10, 0, 0);
		panelNorte.add(botonFlecha, BorderLayout.WEST);

		// Título "Check-Out" (al centro)
		EstiloTextos tituloLabel = new EstiloTextos("TituloServicios", "titulo", "resources/images/icono_salir.png", 30,
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

	private JPanel crearPanelCentral(JFrame ventanaActual, GestionHotelBBDD baseDatos) {
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panelCentral.setBackground(Color.decode("#C0C0C0"));

		// Añadir cada sección con sus componentes
		panelCentral.add(crearPanelHabitacionTitular(ventanaActual, baseDatos));
		panelCentral.add(crearSeccion("TituloAparcamiento", crearPanelParking()));
		panelCentral.add(crearSeccion("TituloMasajes", crearPanelMasajes()));
		panelCentral.add(crearSeccion("TituloSpa", crearPanelSpa()));
		panelCentral.add(crearSeccion("TituloRestaurante", crearPanelRestaurante()));
		panelCentral.add(crearSeccion("TituloServicioHabitaciones", crearPanelServicioHabitaciones()));

		return panelCentral;
	}

	private JPanel crearSeccion(String titulo, JPanel contenido) {
		JPanel panelSeccion = new JPanel();
		panelSeccion.setLayout(new BorderLayout());
		panelSeccion.setBackground(Color.decode("#C0C0C0"));
		panelSeccion.setBorder(new EmptyBorder(5, 20, 0, 20));

		EstiloTextos encabezado = new EstiloTextos(titulo, "encabezado4");
		encabezado.setHorizontalAlignment(SwingConstants.LEFT);

		panelSeccion.add(encabezado, BorderLayout.NORTH);
		panelSeccion.add(contenido, BorderLayout.CENTER);

		return panelSeccion;
	}

	private JPanel crearPanelHabitacionTitular(JFrame ventanaActual, GestionHotelBBDD baseDatos) {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new EmptyBorder(20, 10, 10, 20));

		JPanel panelOeste = new JPanel();
		panelOeste.setOpaque(false);
		EstiloTextos numHabitacion = new EstiloTextos("NumHabitacion", "texto");
		panelOeste.add(numHabitacion);
		txtHabitacion = new JTextField(10);
		txtHabitacion.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Limitar solo a numeros en el campo numeroHabitacion
		txtHabitacion.setDocument(new PlainDocument() {
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

		panelOeste.add(txtHabitacion);

		JLabel blanco = new JLabel();
		blanco.setPreferredSize(new Dimension(30, 0));

		JPanel panelCentral = new JPanel();
		panelCentral.setOpaque(false);
		EstiloTextos numDni = new EstiloTextos("NumeroDNI", "texto");
		panelCentral.add(numDni);
		txtTitular = new JTextField(20);
		txtTitular.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// Limitar solo a 9 caracteres en el campo titular
		txtTitular.setDocument(new PlainDocument() {
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
		panelCentral.add(txtTitular);

		JLabel iconoLimpiar = new JLabel();
		iconoLimpiar.setPreferredSize(new Dimension(90, 70));

		ImageIcon icono = new ImageIcon("resources/images/icono_limpiar.png");
		Image imagen = icono.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

		iconoLimpiar.setIcon(iconoRedimensionado);
		iconoLimpiar.setHorizontalAlignment(SwingConstants.CENTER);

		// Limpia los campos de texto, los checkboxes y los radioButtons
		iconoLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiar();
			}
		});

		JPanel panelEste = new JPanel();
		panelEste.setOpaque(false);
		EstiloBotones btnActualizar = new EstiloBotones("Actualizar", "Tercero", 20);

		//
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(verificarDatosFormulario(ventanaActual)) {
					// Llamamos al método verificarReserva
					baseDatos.verificarReserva(ventanaActual, txtHabitacion, txtTitular, radioButtonSi,
							checkBoxMasajeSueco, checkBoxMasajeDeportivo, checkBoxMasajeTailandes, checkBoxSpaMedio,
							checkBoxSpaCompleto, checkBoxBaños, checkBoxMesasEstandar, checkBoxMesasPrivadas,
							checkBoxMenusEspeciales, checkBoxEventosyCelebraciones, checkBoxExperienciaGastronomica,
							checkBoxAireLibre);
				};
			}
		});

		panelEste.add(btnActualizar);

		panel.add(panelOeste);
		panel.add(blanco);
		panel.add(panelCentral);
		panel.add(iconoLimpiar);
		panel.add(panelEste);

		return panel;
	}

	private JPanel crearPanelParking() {
		JPanel panel = new JPanel(new GridLayout(1, 4, 1, 1));
		panel.setBackground(Color.decode("#C0C0C0"));
		panel.setBorder(new EmptyBorder(10, 40, 20, 0));

		radioButtonSi = new EstiloRadioBotones("RadioButtonSi");

		panel.add(radioButtonSi);
		return panel;
	}

	private JPanel crearPanelMasajes() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 2, 2));
		panel.setBorder(new EmptyBorder(10, 40, 20, 0));
		panel.setBackground(Color.decode("#C0C0C0"));

		checkBoxMasajeSueco = new EstiloCheckBox("CheckBoxMasajeSueco");
		checkBoxMasajeDeportivo = new EstiloCheckBox("CheckBoxMasajeDeportivo");
		checkBoxMasajeTailandes = new EstiloCheckBox("CheckBoxMasajeTailandes");

		panel.add(checkBoxMasajeSueco);
		panel.add(checkBoxMasajeDeportivo);
		panel.add(checkBoxMasajeTailandes);

		return panel;
	}

	private JPanel crearPanelSpa() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 2, 2));
		panel.setBorder(new EmptyBorder(10, 40, 20, 0));
		panel.setBackground(Color.decode("#C0C0C0"));

		checkBoxSpaMedio = new EstiloCheckBox("CheckBoxSpaMedio");
		checkBoxSpaCompleto = new EstiloCheckBox("CheckBoxSpaCompleto");
		checkBoxBaños = new EstiloCheckBox("CheckBoxBaños");

		panel.add(checkBoxSpaMedio);
		panel.add(checkBoxSpaCompleto);
		panel.add(checkBoxBaños);

		return panel;
	}

	private JPanel crearPanelRestaurante() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 2, 2));
		panel.setBorder(new EmptyBorder(10, 40, 20, 0));
		panel.setBackground(Color.decode("#C0C0C0"));

		checkBoxMesasEstandar = new EstiloCheckBox("CheckBoxMesasEstandar");
		checkBoxMesasPrivadas = new EstiloCheckBox("CheckBoxMesasPrivadas");
		checkBoxMenusEspeciales = new EstiloCheckBox("CheckBoxMenusEspeciales");
		checkBoxEventosyCelebraciones = new EstiloCheckBox("CheckBoxEventosyCelebraciones");
		checkBoxExperienciaGastronomica = new EstiloCheckBox("CheckBoxExperienciaGastronomica");
		checkBoxAireLibre = new EstiloCheckBox("CheckBoxAireLibre");

		panel.add(checkBoxMesasEstandar);
		panel.add(checkBoxMesasPrivadas);
		panel.add(checkBoxMenusEspeciales);
		panel.add(checkBoxEventosyCelebraciones);
		panel.add(checkBoxExperienciaGastronomica);
		panel.add(checkBoxAireLibre);

		return panel;
	}

	private JPanel crearPanelServicioHabitaciones() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 40, 20, 0));
		panel.setBackground(Color.decode("#C0C0C0"));

		JPanel panelInfoMenu = new JPanel();
		panelInfoMenu.setBackground(Color.WHITE);
		panelInfoMenu.setPreferredSize(new Dimension(550, 20));

		EstiloTextos infoMenu = new EstiloTextos("InfoMenu", "texto");
		panelInfoMenu.add(infoMenu);
		panel.add(panelInfoMenu, BorderLayout.WEST);

		EstiloBotones botonMenu = new EstiloBotones("Menu", "Secundario", 20);
		panel.add(botonMenu, BorderLayout.EAST);

		botonMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Cuando se hace clic, se abre una nueva ventana
				abrirNuevaVentana();
			}
		});

		return panel;
	}

	private void abrirNuevaVentana() {
		// Crear una nueva ventana (JFrame) encima de la ventana actual
		CartaMenu1 menuVentana = new CartaMenu1();
		menuVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuVentana.setVisible(true);
	}

	private JPanel crearPanelSur(JFrame ventanaActual, GestionHotelBBDD baseDatos) {
		JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelSur.setBackground(Color.decode("#C0C0C0"));

		EstiloBotones botonConfirmar = new EstiloBotones("Confirmar", "Secundario", 20);

		botonConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(verificarDatosFormulario(ventanaActual)) {
					baseDatos.confirmarServicios(ventanaActual, txtHabitacion, txtTitular, radioButtonSi,
							checkBoxMasajeSueco, checkBoxMasajeDeportivo, checkBoxMasajeTailandes, checkBoxSpaMedio,
							checkBoxSpaCompleto, checkBoxBaños, checkBoxMesasEstandar, checkBoxMesasPrivadas,
							checkBoxMenusEspeciales, checkBoxEventosyCelebraciones, checkBoxExperienciaGastronomica,
							checkBoxAireLibre);
					limpiar();
					habilitarComponentes();
				}
			}
		});

		panelSur.add(botonConfirmar);

		return panelSur;
	}

	// Limpiar todos los campos
	private void limpiar() {
		// Limpiar los campos de texto
		txtHabitacion.setText("");
		txtTitular.setText("");

		// Desmarcar los checkboxes
		checkBoxMasajeSueco.setSelected(false);
		checkBoxMasajeDeportivo.setSelected(false);
		checkBoxMasajeTailandes.setSelected(false);
		checkBoxSpaMedio.setSelected(false);
		checkBoxSpaCompleto.setSelected(false);
		checkBoxBaños.setSelected(false);
		checkBoxMesasEstandar.setSelected(false);
		checkBoxMesasPrivadas.setSelected(false);
		checkBoxMenusEspeciales.setSelected(false);
		checkBoxEventosyCelebraciones.setSelected(false);
		checkBoxExperienciaGastronomica.setSelected(false);
		checkBoxAireLibre.setSelected(false);

		// Desmarcar los radioButton
		radioButtonSi.setSelected(false);
	}

	// Habilitar todos los campos
	private void habilitarComponentes() {
		radioButtonSi.setEnabled(true);
		checkBoxMasajeSueco.setEnabled(true);
		checkBoxMasajeDeportivo.setEnabled(true);
		checkBoxMasajeTailandes.setEnabled(true);
		checkBoxSpaMedio.setEnabled(true);
		checkBoxSpaCompleto.setEnabled(true);
		checkBoxBaños.setEnabled(true);
		checkBoxMesasEstandar.setEnabled(true);
		checkBoxMesasPrivadas.setEnabled(true);
		checkBoxMenusEspeciales.setEnabled(true);
		checkBoxEventosyCelebraciones.setEnabled(true);
		checkBoxExperienciaGastronomica.setEnabled(true);
		checkBoxAireLibre.setEnabled(true);
	}
	
	private boolean verificarDatosFormulario(JFrame ventanaActual) {
		boolean verdad = false;
		if (txtHabitacion.getText().isEmpty() && txtTitular.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("CamposVacios"), MultiIdioma.IdiomaTexto("Informacion"),
					JOptionPane.INFORMATION_MESSAGE);
		} else if (!txtHabitacion.getText().isEmpty() && txtTitular.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventanaActual,  MultiIdioma.IdiomaTexto("CampoDniVacio"), MultiIdioma.IdiomaTexto("Informacion"),
					JOptionPane.INFORMATION_MESSAGE);
		} else if (txtHabitacion.getText().isEmpty() && !txtTitular.getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("CampoHabitacionVacio"), MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
		} else {
			verdad = true;
		}
		return verdad;
	}
}

