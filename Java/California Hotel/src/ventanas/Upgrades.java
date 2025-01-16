package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import estilos.EstiloBotonRetroceso;
import estilos.EstiloBotones;
import estilos.EstiloCheckBox;
import estilos.EstiloTextos;
import sacar_valores.GestorDatosRegistro;
import sacar_valores.Reserva;

public class Upgrades extends JFrame {
	private static final long serialVersionUID = 1L;
	public static boolean seUsaronUpgrades = false;

	public static EstiloCheckBox cuna;
	public static EstiloCheckBox supletoria;
	public static EstiloCheckBox ropa;

	public static EstiloCheckBox maquina;
	public static EstiloCheckBox caja;
	public static EstiloCheckBox router;
	public static EstiloCheckBox minibar;
	
	public static boolean isSeUsaronUpgrades() {
		return seUsaronUpgrades;
	}

	public static void setSeUsaronUpgrades(boolean seUsaronUpgrades) {
		Upgrades.seUsaronUpgrades = seUsaronUpgrades;
	}

	public Upgrades() {
		// Crear el JFrame
		Image icon = Toolkit.getDefaultToolkit().getImage("resources/images/hotel_icono.png");
		setTitle("HOTEL CALIFORNIA");
		setSize(600, 400);
		setResizable(false); // Bloquea el poder redimensionarlo
		setIconImage(icon);
		centrarVentana(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		iniciarComponentesUpgrade();

		seUsaronUpgrades = true;
		setVisible(true);

	}

	private void iniciarComponentesUpgrade() {
		// Crear panel de todo el frame
		JPanel panelRegistro = new JPanel();
		panelRegistro.setLayout(new BoxLayout(panelRegistro, BoxLayout.Y_AXIS)); // Orientado verticalmente
		panelRegistro.setPreferredSize(new Dimension(600, 700));
		Dimension dimensionFrame = panelRegistro.getPreferredSize();

		JPanel panelSuperior = crearPanelSuperior(this, dimensionFrame);
		JPanel panelCentral = crearPanelCentral(dimensionFrame);
		JPanel panelInferior = crearPanelInferior(dimensionFrame);

		panelRegistro.add(panelSuperior);
		panelRegistro.add(panelCentral);
		panelRegistro.add(panelInferior);
		add(panelRegistro);
	}

	private JPanel crearPanelSuperior(JFrame ventanaActual, Dimension dimensionFrame) {
		// Crear panel superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout()); // Layout de tipo BorderLayout
		panelSuperior.setPreferredSize(new Dimension(dimensionFrame.width, 80));
		panelSuperior.setMaximumSize(new Dimension(dimensionFrame.width, 100));
		panelSuperior.setBackground(Color.decode("#C0C0C0"));

		// Crear imagen logotipo
		ImageIcon imagenLogo = new ImageIcon("resources/images/logo_empresa.png");
		Image imagen = imagenLogo.getImage();
		Image escalarImagen = imagen.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		imagenLogo = new ImageIcon(escalarImagen);
		JLabel logotipo = new JLabel(imagenLogo);

		// Crear botón volver
		EstiloBotonRetroceso botonRetroceso = new EstiloBotonRetroceso(this, -40, 10, 0, 0);

		// Crear componentes
		EstiloTextos TituloUpgrades = new EstiloTextos("TituloUpgrade", "titulo");

		panelSuperior.add(botonRetroceso, BorderLayout.WEST);
		panelSuperior.add(TituloUpgrades, BorderLayout.CENTER);
		panelSuperior.add(logotipo, BorderLayout.EAST);

		return panelSuperior;
	}

	private JPanel crearPanelCentral(Dimension dimensionFrame) {
		// Crear panel central
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS)); // Layout en vertical
		panelCentral.setBackground(Color.decode("#C0C0C0")); // Color del fondo
		panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel panelBloque1 = new JPanel();
		panelBloque1.setLayout(new BoxLayout(panelBloque1, BoxLayout.Y_AXIS));
		panelBloque1.setBackground(Color.decode("#C0C0C0"));
		panelBloque1.setPreferredSize(new Dimension(dimensionFrame.width, 150));
		panelBloque1.setMaximumSize(new Dimension(dimensionFrame.width, 200));
		panelBloque1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panelBloque1.add(SeleccionMejorasMobiliario());
		panelBloque1.add(Box.createRigidArea(new Dimension(0, 10)));
		panelBloque1.add(SeleccionMejorasAmenidades());

		panelCentral.add(panelBloque1);

		return panelCentral;
	}

	private JPanel crearPanelInferior(Dimension dimensionFrame) {
		// Crear panel inferior
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
		panelInferior.setBackground(Color.decode("#C0C0C0"));
		panelInferior.setPreferredSize(new Dimension(dimensionFrame.width, 80));
		panelInferior.setMaximumSize(new Dimension(dimensionFrame.width, 100));
		panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		EstiloBotones botonConfirmar = Confirmar();
		JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botonPanel.setOpaque(false);
		botonPanel.add(botonConfirmar);

		panelInferior.add(botonPanel);
		
		return panelInferior;
	}

	
	public static void centrarVentana(JFrame ventana) {
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension dimensionPantalla = pantalla.getScreenSize();
		int anchoPantalla = dimensionPantalla.width;
		int alturaPantalla = dimensionPantalla.height;

		ventana.setLocationRelativeTo(null);
	}

	private JPanel SeleccionMejorasMobiliario() {
		JPanel panelMejorasMobiliario = new JPanel();
		panelMejorasMobiliario.setLayout(new GridBagLayout()); // Layout que permite posicion vertical/horizontal
		panelMejorasMobiliario.setBackground(Color.WHITE);
		Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border bordeVacio = BorderFactory.createEmptyBorder(5, 20, 5, 20);
		panelMejorasMobiliario.setBorder(BorderFactory.createCompoundBorder(bordeNegro, bordeVacio));
		panelMejorasMobiliario.setPreferredSize(new Dimension(100, 70));
		
		GridBagConstraints c = new GridBagConstraints();
		EstiloTextos EncabezadoMejoras = new EstiloTextos("EncabezadoMejoras", "encabezado3");
		c.gridx = 0; // Columna
		c.gridy = 0; // Fila
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.LINE_START;
		panelMejorasMobiliario.add(EncabezadoMejoras, c);

		// Crear un margen adicional para los textos a la izquierda
		Insets leftInset = new Insets(2, 2, 2, 2);

		cuna = new EstiloCheckBox("Cuna");
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = leftInset;
		c.fill = GridBagConstraints.HORIZONTAL;
		cuna.setBorderPainted(false);
		panelMejorasMobiliario.add(cuna, c);

		supletoria = new EstiloCheckBox("Supletoria");
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = leftInset;
		c.fill = GridBagConstraints.HORIZONTAL;
		supletoria.setBorderPainted(false);
		panelMejorasMobiliario.add(supletoria, c);

		ropa = new EstiloCheckBox("Vestimenta");
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = leftInset;
		c.fill = GridBagConstraints.HORIZONTAL;
		ropa.setBorderPainted(false);
		panelMejorasMobiliario.add(ropa, c);
		
		return panelMejorasMobiliario;

	}
	
	private JPanel SeleccionMejorasAmenidades() {
		JPanel panelAmenidades = new JPanel();
		panelAmenidades.setLayout(new GridBagLayout()); // Layout que permite posicion vertical/horizontal
		panelAmenidades.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border bordeVacio = BorderFactory.createEmptyBorder(5, 20, 5, 20);
		panelAmenidades.setBorder(BorderFactory.createCompoundBorder(bordeNegro, bordeVacio));
		panelAmenidades.setPreferredSize(new Dimension(100, 70));

		EstiloTextos EncabezadoAmenidades = new EstiloTextos("EncabezadoAmenidades", "encabezado3");
		c.gridx = 0; // Columna
		c.gridy = 0; // Fila
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.insets = new Insets(2, 2, 2, 2);
		c.anchor = GridBagConstraints.LINE_START;
		panelAmenidades.add(EncabezadoAmenidades, c);

		// Crear un margen adicional para los textos a la izquierda
		Insets leftInset = new Insets(2, 2, 2, 2);

		maquina = new EstiloCheckBox("Cafe");
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = leftInset;
		maquina.setBorderPainted(false);
		panelAmenidades.add(maquina, c);

		caja = new EstiloCheckBox("Caja");
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = leftInset;
		c.fill = GridBagConstraints.HORIZONTAL;
		caja.setBorderPainted(false);
		panelAmenidades.add(caja, c);

		router = new EstiloCheckBox("Router");
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = leftInset;
		c.fill = GridBagConstraints.HORIZONTAL;
		router.setBorderPainted(false);
		panelAmenidades.add(router, c);

		minibar = new EstiloCheckBox("Nevera");
		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.insets = leftInset;
		c.fill = GridBagConstraints.HORIZONTAL;
		minibar.setBorderPainted(false);
		panelAmenidades.add(minibar, c);
		
		return panelAmenidades;
	}
	
	private EstiloBotones Confirmar() {
		EstiloBotones botonConfirmar = new EstiloBotones("Terminar", "Secundario", 10);
		botonConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GestorDatosRegistro seleccionDatos = new GestorDatosRegistro();
				Reserva reserva = new Reserva();

				List<JCheckBox> listaMobiliario = Arrays.asList(cuna, supletoria, ropa);
				List<JCheckBox> listaAmenidades = Arrays.asList(maquina, caja, router, minibar);
				String mejoras = seleccionDatos.getMejorasMobiliario(listaMobiliario);
				String amenidades = seleccionDatos.getAmenidades(listaAmenidades);

				reserva.setMejoras(mejoras, amenidades);

				 // Verificar selección de cada checkbox
				boolean cuna_bebe = cuna.isSelected();
				boolean cama_extra = supletoria.isSelected();
				boolean pijamas = ropa.isSelected();
	            boolean maquinaSeleccionada = maquina.isSelected();
	            boolean cajaSeleccionada = caja.isSelected();
	            boolean routerSeleccionado = router.isSelected();
	            boolean minibarSeleccionado = minibar.isSelected();
				
	            reserva.setCuna(cuna_bebe);
	            reserva.setCamaSupletoria(cama_extra);
	            reserva.setPijamasAlbornoces(pijamas);
	            reserva.setMaquinaCafe(maquinaSeleccionada);
	            reserva.setCajaFuerte(cajaSeleccionada);
	            reserva.setRouter(routerSeleccionado);
				reserva.setMinibar(minibarSeleccionado);

				Upgrades.this.dispose();
			}
		});
		return botonConfirmar;
	}
	
}
