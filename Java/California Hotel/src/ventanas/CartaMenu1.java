package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import estilos.EstiloTextos;

public class CartaMenu1 extends JFrame {
	private static final long serialVersionUID = 1L;

	public CartaMenu1() {
		setTitle("HOTEL CALIFORNIA");
		setSize(650, 670);
		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);

		Image icon = Toolkit.getDefaultToolkit().getImage("resources/images/hotel_icono.png");
		setIconImage(icon);

		iniciarComponentes();

	}

	private void iniciarComponentes() {

		// Panel Norte
		JPanel panelNorte = crearPanelNorte(this);

		// Panel Central
		JPanel panelCentral = crearPanelCentral(this);

		// Panel Sur
		JPanel panelSur = crearPanelSur(this);

		// Añadir los paneles al marco
		add(panelNorte, BorderLayout.NORTH);
		add(panelCentral, BorderLayout.CENTER);
		add(panelSur, BorderLayout.SOUTH);
	}

	private static JPanel crearPanelNorte(JFrame ventanaActual) {
		// Panel Norte
		JPanel panelNorte = new JPanel(new BorderLayout());
		panelNorte.setBackground(Color.decode("#C0C0C0"));

		// Título "Menu" (al centro)
		EstiloTextos tituloLabel = new EstiloTextos("TituloMenu", "titulo", "resources/images/icono_menu.png", 30, 100,
				0, 10, 0, 150, 0, 50);
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

	private static JPanel crearPanelCentral(JFrame ventanaActual) {
		// Panel Central (Menú)
		JPanel panelCentral = new JPanel(new GridLayout(7, 3, 2, 2));
		panelCentral.setBackground(Color.decode("#C0C0C0"));

		// Rellenar celdas
		agregarCelda(panelCentral, new EstiloTextos("Entrantes", "encabezado3"), true, null); // Celda 1
		agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 2
		agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 3

		agregarCelda(panelCentral, new EstiloTextos("Entrante1", "texto"), false, null); // Celda 4
		agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/ensalada_caprese.jpg")); // Celda 5 (Imagen)
		agregarCelda(panelCentral, new EstiloTextos("Precio1", "texto"), true, null); // Celda 6 (Precio)

		agregarCelda(panelCentral, new EstiloTextos("Entrante2", "texto"), false, null); // Celda // 7
		agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/sopa_calabaza.jpg")); // Celda 8 (Imagen)
		agregarCelda(panelCentral, new EstiloTextos("Precio2", "texto"), true, null); // Celda 9 (Precio)

		agregarCelda(panelCentral, new EstiloTextos("Entrante3", "texto"), false, null); // Celda 10
		agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/tartar_atun.jpg")); // Celda 11 (Imagen)
		agregarCelda(panelCentral, new EstiloTextos("Precio3", "texto"), true, null); // Celda 12 (Precio)

		agregarCelda(panelCentral, new EstiloTextos("Principales", "encabezado3"), true, null); // Celda 13
		agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 14
		agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 15

		agregarCelda(panelCentral, new EstiloTextos("Principal1", "texto"), false, null); // Celda 16
		agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/solomillo.jpg")); // Celda 17 (Imagen)
		agregarCelda(panelCentral, new EstiloTextos("Precio4", "texto"), true, null); // Celda 18 (Precio)

		agregarCelda(panelCentral, new EstiloTextos("Principal2", "texto"), false, null); // Celda 19
		agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/paella.jpg")); // Celda 20 (Imagen)
		agregarCelda(panelCentral, new EstiloTextos("Precio5", "texto"), true, null); // Celda 21 (Precio)

		return panelCentral;
	}

	private static JPanel crearPanelSur(JFrame ventanaActual) {
		// Panel Sur
		JPanel panelSur = new JPanel(new BorderLayout());
		panelSur.setBackground(Color.decode("#C0C0C0"));

		// Crear el botón con la imagen redimensionada
		ImageIcon iconoAdelante = new ImageIcon("resources/images/adelante.png");
		Image imagen = iconoAdelante.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		JButton botonAdelante = new JButton(new ImageIcon(imagen));

		botonAdelante.setOpaque(false);
		botonAdelante.setContentAreaFilled(false);
		botonAdelante.setBorderPainted(false);
		botonAdelante.setFocusPainted(false);

		// Acción del botón (cuando se hace clic)
		botonAdelante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CartaMenu2 menuVentana2 = new CartaMenu2();
					menuVentana2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					menuVentana2.setVisible(true);

					// Cerrar la ventana actual
					ventanaActual.setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace(); // Muestra errores si la creación de la ventana falla
				}
			}
		});
		panelSur.add(botonAdelante, BorderLayout.EAST);
		return panelSur;
	}

	public static void agregarCelda(JPanel panel, EstiloTextos estilo, boolean centrado, ImageIcon imagen) {
		JPanel subPanel = new JPanel(new BorderLayout());
		subPanel.setBackground(Color.decode("#C0C0C0"));
		subPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0)); // Crear un margen inferior

		if (imagen != null) {
			// Redimensionar imagen
			Image img = imagen.getImage();
			ImageIcon imagenEscalada = new ImageIcon(img.getScaledInstance(150, 70, Image.SCALE_SMOOTH));
			JLabel labelImagen = new JLabel(imagenEscalada);
			labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
			subPanel.add(labelImagen, BorderLayout.CENTER);
		} else {
			if (centrado) {
				estilo.setHorizontalAlignment(SwingConstants.CENTER);
			} else {
				estilo.setHorizontalAlignment(SwingConstants.LEFT);
			}
			subPanel.add(estilo, BorderLayout.CENTER);
		}
		panel.add(subPanel);
	}

}
