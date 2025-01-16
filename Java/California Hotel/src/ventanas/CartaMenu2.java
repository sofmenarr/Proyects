package ventanas;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import estilos.EstiloBotonRetroceso;
import estilos.EstiloTextos;

public class CartaMenu2 extends JFrame {
	private static final long serialVersionUID = 1L;

	public CartaMenu2() {
		setTitle("HOTEL CALIFORNIA");
		setSize(800, 800);
		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);

		Image icon = Toolkit.getDefaultToolkit().getImage("resources/images/hotel_icono.png");
		setIconImage(icon);

		iniciarComponentes();
	}

	public void iniciarComponentes() {
		// Panel Norte
		JPanel panelNorte = crearPanelNorte(this);

		// Panel Central
		JPanel panelCentral = crearPanelCentral(this);

		// Añadir los paneles al marco
		add(panelNorte, BorderLayout.NORTH);
		add(panelCentral, BorderLayout.CENTER);
	}

	private static JPanel crearPanelNorte(JFrame ventanaActual) {
		// Panel Norte
		JPanel panelNorte = new JPanel(new BorderLayout());
		panelNorte.setBackground(Color.decode("#C0C0C0"));

		// Boton atras
		EstiloBotonRetroceso botonFlecha = new EstiloBotonRetroceso(ventanaActual, CartaMenu1.class, -50, 10, 0, 0);
		panelNorte.add(botonFlecha, BorderLayout.WEST);

		// Título "Menu" (al centro)
		EstiloTextos tituloLabel = new EstiloTextos("TituloMenu", "titulo", "resources/images/icono_menu.png", 30, 80,
				0, 10, 0, 130, 0, 50);
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
		JPanel panelCentral = new JPanel(new GridLayout(10, 3, 2, 2));
		panelCentral.setBackground(Color.decode("#C0C0C0"));

		// Rellenar celdas
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Guarniciones", "encabezado3"), true, null); // Celda 1
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 2
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 3

		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Guarnicion1", "texto"), false, null); // Celda 4
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/patatas.jpg")); // Celda 5 (Imagen)
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Precio6", "texto"), true, null); // Celda 6 (Precio)

		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Guarnicion2", "texto"), false, null); // Celda // 7
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/arroz.jpg")); // Celda 8 (Imagen)
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Precio7", "texto"), true, null); // Celda 9 (Precio)

		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Guarnicion3", "texto"), false, null); // Celda 10
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/ensalada_mixta.jpg")); // Celda 11 (Imagen)
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Precio8", "texto"), true, null); // Celda 12 (Precio)

		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Postres", "encabezado3"), true, null); // Celda 13
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 14
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 15

		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Postre1", "texto"), false, null); // Celda 16
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/tarta_queso.jpg")); // Celda 17 (Imagen)
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Precio9", "texto"), true, null); // Celda 18 (Precio)

		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Postre2", "texto"), false, null); // Celda 19
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/brownie.jpg")); // Celda 20 (Imagen)
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Precio10", "texto"), true, null); // Celda 21 (Precio)

		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Bebidas", "encabezado3"), true, null); // Celda 22
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 23
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true, null); // Celda 24

		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Bebida1", "texto"), false, null); // Celda 25
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/vino.jpg")); // Celda 26 (Imagen)
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Precio11", "texto"), true, null); // Celda 27 (Precio)

		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Bebida2", "texto"), false, null); // Celda 28
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Nada", "texto"), true,
				new ImageIcon("resources/images/coctel.jpg")); // Celda 29 (Imagen)
		CartaMenu1.agregarCelda(panelCentral, new EstiloTextos("Precio12", "texto"), true, null); // Celda 30 (Precio)

		return panelCentral;
	}
}
