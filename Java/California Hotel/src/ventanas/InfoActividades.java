package ventanas;

import javax.swing.*;
import javax.swing.border.Border;

import estilos.EstiloBotonRetroceso;
import estilos.EstiloBotones;
import estilos.EstiloTextos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class InfoActividades extends JFrame {
	private static final long serialVersionUID = 1L;

	public InfoActividades() {
		// Crear el JFrame
		Image icon = Toolkit.getDefaultToolkit().getImage("resources/images/hotel_icono.png");
		setTitle("HOTEL CALIFORNIA");
		setResizable(false); // Bloquea el poder redimensionarlo
		setIconImage(icon);
		setSize(600, 950);
		centrarVentana(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		iniciarComponentesActividades();
		setVisible(true);

	}

	private void iniciarComponentesActividades() {
		// Crear panel de todo el frame
		JPanel panelActividades = new JPanel();
		panelActividades.setLayout(new BoxLayout(panelActividades, BoxLayout.Y_AXIS)); // Orientado verticalmente
		panelActividades.setPreferredSize(new Dimension(900, 850));
		Dimension dimensionFrame = panelActividades.getPreferredSize();

		JPanel panelSuperior = crearPanelSuperior(this);
		JPanel panelCentral = crearPanelCentral(dimensionFrame);
		JPanel panelInferior = crearPanelInferior(dimensionFrame);

		panelActividades.add(panelSuperior);
		panelActividades.add(panelCentral);
		panelActividades.add(panelInferior);
		add(panelActividades);

	}

	private JPanel crearPanelSuperior(JFrame ventanaActual) {
		// Crear panel superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout()); 
		panelSuperior.setBackground(Color.decode("#C0C0C0")); 

		panelSuperior.setPreferredSize(new Dimension(ventanaActual.getWidth(), 150)); 
		panelSuperior.setMinimumSize(new Dimension(ventanaActual.getWidth(), 150));
		panelSuperior.setMaximumSize(new Dimension(ventanaActual.getWidth(), 150));

		// Crear botón volver
		EstiloBotonRetroceso botonRetroceso = new EstiloBotonRetroceso(ventanaActual, Home.class, -50, 10, 0, 0);

		// Crear Titulo de la ventana
		EstiloTextos TituloActividades = new EstiloTextos("TituloActividades", "titulo",
				"resources/images/icono_actividades.png", 30, 50, 0, 10, 0, 90, 0, 50);

		// Crear imagen logotipo
		ImageIcon imagenLogo = new ImageIcon("resources/images/logo_empresa.png");
		Image imagen = imagenLogo.getImage();
		Image escalarImagen = imagen.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		imagenLogo = new ImageIcon(escalarImagen);
		JLabel logotipo = new JLabel(imagenLogo);

		panelSuperior.add(botonRetroceso, BorderLayout.WEST);
		panelSuperior.add(TituloActividades, BorderLayout.CENTER);
		panelSuperior.add(logotipo, BorderLayout.EAST);

		return panelSuperior;
	}

	private JPanel crearPanelCentral(Dimension dimensionFrame) {
		// Crear panel central
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS)); // Layout en vertical
		panelCentral.setBackground(Color.decode("#C0C0C0"));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// BLOQUE ACTIVIDADES
		JPanel panelEncabezado1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelEncabezado1.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos EncabezadoActiv = new EstiloTextos("EncabezadoActividades", "encabezado1");
		panelEncabezado1.add(EncabezadoActiv);

		JPanel panelBloque1 = new JPanel();
		panelBloque1.setLayout(new BoxLayout(panelBloque1, BoxLayout.Y_AXIS));
		panelBloque1.setPreferredSize(new Dimension(dimensionFrame.width, 100));
		panelBloque1.add(actividadesBloque1());

		JPanel panelBloque2 = new JPanel();
		panelBloque2.setLayout(new BoxLayout(panelBloque2, BoxLayout.Y_AXIS));
		panelBloque2.setPreferredSize(new Dimension(dimensionFrame.width, 100));
		panelBloque2.add(actividadesBloque2());

		JPanel panelBloque3 = new JPanel();
		panelBloque3.setLayout(new BoxLayout(panelBloque3, BoxLayout.Y_AXIS));
		panelBloque3.setPreferredSize(new Dimension(dimensionFrame.width, 100));
		panelBloque3.add(actividadesBloque3());

		panelCentral.add(panelEncabezado1);
		panelCentral.add(panelBloque1);
		panelCentral.add(panelBloque2);
		panelCentral.add(panelBloque3);

		return panelCentral;
	}

	private JPanel actividadesBloque1() {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout()); 
		panel1.setPreferredSize(new Dimension(500, 70));
		panel1.setBackground(Color.decode("#C0C0C0"));
		GridBagConstraints c = new GridBagConstraints();

		JPanel panelIzquierdo = crearPanelActividad1();
		c.gridx = 0; 
		c.gridy = 0; 
		c.weightx = 0.5; 
		c.weighty = 1.0; 
		c.fill = GridBagConstraints.BOTH; 
		panel1.add(panelIzquierdo, c);

		JPanel panelDerecho = crearPanelActividad2();
		c.gridx = 1; 
		c.gridy = 0;
		c.weightx = 0.5; 
		c.weighty = 1.0; 
		c.fill = GridBagConstraints.BOTH; 
		panel1.add(panelDerecho, c);

		return panel1;
	}

	private JPanel actividadesBloque2() {
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout()); 
		panel2.setPreferredSize(new Dimension(500, 70));
		panel2.setBackground(Color.decode("#C0C0C0"));
		GridBagConstraints c = new GridBagConstraints();

		JPanel panelIzquierdo = crearPanelActividad3();
		c.gridx = 0;
		c.gridy = 0; 
		c.weightx = 0.5; 
		c.weighty = 1.0; 
		c.fill = GridBagConstraints.BOTH; 
		panel2.add(panelIzquierdo, c);

		JPanel panelDerecho = crearPanelActividad4();
		c.gridx = 1; 
		c.gridy = 0; 
		c.weightx = 0.5; 
		c.weighty = 1.0; 
		c.fill = GridBagConstraints.BOTH;
		panel2.add(panelDerecho, c);

		return panel2;
	}

	private JPanel actividadesBloque3() {
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridBagLayout()); 
		panel3.setPreferredSize(new Dimension(500, 70));
		panel3.setBackground(Color.decode("#C0C0C0"));
		GridBagConstraints c = new GridBagConstraints();

		JPanel panelIzquierdo = crearPanelActividad5();
		c.gridx = 0; 
		c.gridy = 0; 
		c.weightx = 0.5; 
		c.weighty = 1.0; 
		c.fill = GridBagConstraints.BOTH; 
		panel3.add(panelIzquierdo, c);

		JPanel panelDerecho = crearPanelActividad6();
		c.gridx = 1; 
		c.gridy = 0; 
		c.weightx = 0.5; 
		c.weighty = 1.0; 
		c.fill = GridBagConstraints.BOTH; 
		panel3.add(panelDerecho, c);

		return panel3;
	}

	private JPanel crearPanelActividad1() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelActividad1 = new JPanel(new GridBagLayout());
		panelActividad1.setBackground(Color.decode("#C0C0C0"));

		// Título con imagen
		EstiloTextos titulo = new EstiloTextos("TituloActividad1", "encabezado4");
		ImageIcon icono = new ImageIcon("resources/images/icono_titulo_izquierdo.png");
		titulo.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		c.gridx = 0; // Columna 0
		c.gridy = 0; // Fila 0
		c.weightx = 0.3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		panelActividad1.add(titulo, c);

		// Botón que abre URL
		EstiloBotones boton = new EstiloBotones("Abrir", "Tercero", 10);
		boton.addActionListener(e -> {
			try {
				Desktop.getDesktop()
						.browse(new URI("https://www.theprincipalmadridhotel.com/es/actividades/hotel-wellness/"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		panelActividad1.add(boton, c);

		EstiloTextos texto = new EstiloTextos("TextoActividad1", "texto");
		texto.setBackground(Color.LIGHT_GRAY);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weightx = 0.7;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelActividad1.add(texto, c);

		// Imagen al lado del texto
		JLabel imagen = new JLabel(new ImageIcon(new ImageIcon("resources/images/wellbeing.jpg").getImage()
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.3;
		c.anchor = GridBagConstraints.EAST;
		panelActividad1.add(imagen, c);

		return panelActividad1;
	}

	private JPanel crearPanelActividad2() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelActividad2 = new JPanel(new GridBagLayout());
		panelActividad2.setBackground(Color.decode("#C0C0C0"));

		// Título con imagen
		EstiloTextos titulo = new EstiloTextos("TituloActividad2", "encabezado4");
		ImageIcon icono = new ImageIcon("resources/images/icono_titulo_izquierdo.png");
		titulo.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		c.gridx = 0; // Columna 0
		c.gridy = 0; // Fila 0
		c.weightx = 0.3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		panelActividad2.add(titulo, c);

		// Botón que abre URL
		EstiloBotones boton = new EstiloBotones("Abrir", "Tercero", 10);
		boton.addActionListener(e -> {
			try {
				Desktop.getDesktop()
						.browse(new URI("https://www.theprincipalmadridhotel.com/es/actividades/hotel-wellness/"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		panelActividad2.add(boton, c);

		EstiloTextos texto = new EstiloTextos("TextoActividad2", "texto");
		texto.setBackground(Color.LIGHT_GRAY);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weightx = 0.7;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelActividad2.add(texto, c);

		// Imagen al lado del texto
		JLabel imagen = new JLabel(new ImageIcon(
				new ImageIcon("resources/images/yoga.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.3;
		c.anchor = GridBagConstraints.EAST;
		panelActividad2.add(imagen, c);

		return panelActividad2;
	}

	private JPanel crearPanelActividad3() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelActividad3 = new JPanel(new GridBagLayout());
		panelActividad3.setBackground(Color.decode("#C0C0C0"));

		// Título con imagen
		EstiloTextos titulo = new EstiloTextos("TituloActividad3", "encabezado4");
		ImageIcon icono = new ImageIcon("resources/images/icono_titulo_izquierdo.png");
		titulo.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		c.gridx = 0; // Columna 0
		c.gridy = 0; // Fila 0
		c.weightx = 0.3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		panelActividad3.add(titulo, c);

		// Botón que abre URL
		EstiloBotones boton = new EstiloBotones("Abrir", "Tercero", 10);
		boton.addActionListener(e -> {
			try {
				Desktop.getDesktop().browse(
						new URI("https://www.theprincipalmadridhotel.com/es/actividades/talleres-de-cocteleria/"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		panelActividad3.add(boton, c);

		EstiloTextos texto = new EstiloTextos("TextoActividad3", "texto");
		texto.setBackground(Color.LIGHT_GRAY);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weightx = 0.7;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelActividad3.add(texto, c);

		// Imagen al lado del texto
		JLabel imagen = new JLabel(new ImageIcon(new ImageIcon("resources/images/taller.jpg").getImage()
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.3;
		c.anchor = GridBagConstraints.EAST;
		panelActividad3.add(imagen, c);

		return panelActividad3;
	}

	private JPanel crearPanelActividad4() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelActividad4 = new JPanel(new GridBagLayout());
		panelActividad4.setBackground(Color.decode("#C0C0C0"));

		// Título con imagen
		EstiloTextos titulo = new EstiloTextos("TituloActividad4", "encabezado4");
		ImageIcon icono = new ImageIcon("resources/images/icono_titulo_izquierdo.png");
		titulo.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		c.gridx = 0; // Columna 0
		c.gridy = 0; // Fila 0
		c.weightx = 0.3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		panelActividad4.add(titulo, c);

		// Botón que abre URL
		EstiloBotones boton = new EstiloBotones("Abrir", "Tercero", 10);
		boton.addActionListener(e -> {
			try {
				Desktop.getDesktop().browse(new URI("https://madridsecreto.co/cines-verano-madrid/"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		panelActividad4.add(boton, c);

		EstiloTextos texto = new EstiloTextos("TextoActividad4", "texto");
		texto.setBackground(Color.LIGHT_GRAY);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weightx = 0.7;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelActividad4.add(texto, c);

		// Imagen al lado del texto
		JLabel imagen = new JLabel(new ImageIcon(
				new ImageIcon("resources/images/cine.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.3;
		c.anchor = GridBagConstraints.EAST;
		panelActividad4.add(imagen, c);

		return panelActividad4;
	}

	private JPanel crearPanelActividad5() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelActividad5 = new JPanel(new GridBagLayout());
		panelActividad5.setBackground(Color.decode("#C0C0C0"));

		// Título con imagen
		EstiloTextos titulo = new EstiloTextos("TituloActividad5", "encabezado4");
		ImageIcon icono = new ImageIcon("resources/images/icono_titulo_izquierdo.png");
		titulo.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		c.gridx = 0; // Columna 0
		c.gridy = 0; // Fila 0
		c.weightx = 0.3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		panelActividad5.add(titulo, c);

		// Botón que abre URL
		EstiloBotones boton = new EstiloBotones("Abrir", "Tercero", 10);
		boton.addActionListener(e -> {
			try {
				Desktop.getDesktop().browse(new URI(
						"https://www.atrapalo.com/actividades/cata-gourmet-5-vinos-5-pintxos-experiencia-en-bodega-de-los-reyes_e4803570/"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		panelActividad5.add(boton, c);

		EstiloTextos texto = new EstiloTextos("TextoActividad5", "texto");
		texto.setBackground(Color.LIGHT_GRAY);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weightx = 0.7;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelActividad5.add(texto, c);

		// Imagen al lado del texto
		JLabel imagen = new JLabel(new ImageIcon(
				new ImageIcon("resources/images/cata.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.3;
		c.anchor = GridBagConstraints.EAST;
		panelActividad5.add(imagen, c);

		return panelActividad5;
	}

	private JPanel crearPanelActividad6() {
		GridBagConstraints c = new GridBagConstraints();
		JPanel panelActividad6 = new JPanel(new GridBagLayout());
		panelActividad6.setBackground(Color.decode("#C0C0C0"));

		// Título con imagen
		EstiloTextos titulo = new EstiloTextos("TituloActividad6", "encabezado4");
		ImageIcon icono = new ImageIcon("resources/images/icono_titulo_izquierdo.png");
		titulo.setIcon(new ImageIcon(icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		c.gridx = 0; // Columna 0
		c.gridy = 0; // Fila 0
		c.weightx = 0.3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		panelActividad6.add(titulo, c);

		// Botón que abre URL
		EstiloBotones boton = new EstiloBotones("Abrir", "Tercero", 10);
		boton.addActionListener(e -> {
			try {
				Desktop.getDesktop()
						.browse(new URI("https://www.theprincipalmadridhotel.com/es/actividades/concierge/"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		panelActividad6.add(boton, c);

		EstiloTextos texto = new EstiloTextos("TextoActividad6", "texto");
		texto.setBackground(Color.LIGHT_GRAY);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weightx = 0.7;
		c.fill = GridBagConstraints.HORIZONTAL;
		panelActividad6.add(texto, c);

		// Imagen al lado del texto
		JLabel imagen = new JLabel(new ImageIcon(new ImageIcon("resources/images/conserjeria.jpg").getImage()
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.3;
		c.anchor = GridBagConstraints.EAST;
		panelActividad6.add(imagen, c);

		return panelActividad6;
	}

	private JPanel crearPanelInferior(Dimension dimensionFrame) {
		// Crear panel inferior
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new BorderLayout());
		panelInferior.setBackground(Color.decode("#C0C0C0"));
		panelInferior.setPreferredSize(new Dimension(dimensionFrame.width, 50));
		panelInferior.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel panelEncabezado1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelEncabezado1.setBackground(Color.decode("#C0C0C0"));
		EstiloTextos EncabezadoTransp = new EstiloTextos("EncabezadoTransporte", "encabezado1");
		panelEncabezado1.add(EncabezadoTransp);
		
		JPanel panelBloque1 = new JPanel();
		panelBloque1.setLayout(new BoxLayout(panelBloque1, BoxLayout.Y_AXIS));
		panelBloque1.setPreferredSize(new Dimension(dimensionFrame.width, 50));
		panelBloque1.add(crearPanelTransporte());

		panelInferior.add(panelEncabezado1);
		panelInferior.add(panelBloque1);

		return panelInferior;
	}

	private JPanel crearPanelTransporte() {
		JPanel panelTransporte = new JPanel();
		panelTransporte.setLayout(new GridBagLayout());
		panelTransporte.setBackground(Color.decode("#C0C0C0"));

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		
		EstiloTextos encabezado1 = new EstiloTextos("EncBus", "encabezado3");
		encabezado1.setHorizontalAlignment(SwingConstants.CENTER); 
	    encabezado1.setVerticalAlignment(SwingConstants.CENTER);
		c.gridx = 0; // Columna 0
		c.gridy = 0; // Fila 0
		c.weightx = 0.3;
		c.weighty = 0.1; // Fila más estrecha
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 10, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		panelTransporte.add(encabezado1, c);

		EstiloTextos encabezado2 = new EstiloTextos("EncMetro", "encabezado3");
		encabezado2.setHorizontalAlignment(SwingConstants.CENTER); 
		encabezado2.setVerticalAlignment(SwingConstants.CENTER);
	    c.gridx = 1; // Columna 1
		c.gridy = 0; // Fila 0
		panelTransporte.add(encabezado2, c);

		EstiloTextos encabezado3 = new EstiloTextos("EncTren", "encabezado3");
		encabezado3.setHorizontalAlignment(SwingConstants.CENTER); 
		encabezado3.setVerticalAlignment(SwingConstants.CENTER);		
		c.gridx = 2; // Columna 2
		c.gridy = 0; // Fila 0
		panelTransporte.add(encabezado3, c);

		// Fila del medio con imágenes   
		JLabel imagenBus = new JLabel(new ImageIcon(new ImageIcon("resources/images/autobus.png").getImage()
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		c.gridx = 0; // Columna 0
		c.gridy = 1; // Fila 1 (centro)
		c.weightx = 0.3;
		c.weighty = 0.5; // Fila más ancha
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);
		c.anchor = GridBagConstraints.CENTER;
		panelTransporte.add(imagenBus, c);

		JLabel imagenMetro = new JLabel(new ImageIcon(new ImageIcon("resources/images/metro.png").getImage()
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		c.gridx = 1; // Columna 1
		c.gridy = 1; // Fila 1 (centro)
		panelTransporte.add(imagenMetro, c);

		JLabel imagenTren = new JLabel(new ImageIcon(new ImageIcon("resources/images/tren.png").getImage()
				.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		c.gridx = 2; // Columna 2
		c.gridy = 1; // Fila 1 (centro)
		panelTransporte.add(imagenTren, c);

		// TERCERA FILA
		EstiloTextos texto1 = new EstiloTextos("TextoBus", "texto");
		texto1.setHorizontalAlignment(SwingConstants.CENTER); 
		texto1.setVerticalAlignment(SwingConstants.CENTER);
		c.gridx = 0; // Columna 0
		c.gridy = 2; // Fila 2 (inferior)
		c.weightx = 0.3;
		c.weighty = 0.1; // Fila más estrecha
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 10);
		panelTransporte.add(texto1, c);

		EstiloTextos texto2 = new EstiloTextos("TextoMetro", "texto");
		texto2.setHorizontalAlignment(SwingConstants.CENTER); 
		texto2.setVerticalAlignment(SwingConstants.CENTER);
		c.gridx = 1; // Columna 1
		c.gridy = 2; // Fila 2 (inferior)
		panelTransporte.add(texto2, c);

		EstiloTextos texto3 = new EstiloTextos("TextoTren", "texto");
		texto3.setHorizontalAlignment(SwingConstants.CENTER); 
		texto3.setVerticalAlignment(SwingConstants.CENTER);
		c.gridx = 2; // Columna 2
		c.gridy = 2; // Fila 2 (inferior)
		panelTransporte.add(texto3, c);

		return panelTransporte;
	}

	private void centrarVentana(JFrame ventana) {
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension dimensionPantalla = pantalla.getScreenSize();
		int anchoPantalla = dimensionPantalla.width;
		int alturaPantalla = dimensionPantalla.height;

		ventana.setLocationRelativeTo(null);
	}

}
