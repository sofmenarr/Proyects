package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class InfoActividades extends JFrame{
	private static final long serialVersionUID = 1L;

	public InfoActividades() {
	        // Constructor de la ventana
	        setTitle("Información y Actividades");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(900, 850); // Tamaño de la ventana
	        setLayout(new BorderLayout());
	        setResizable(false);
	        setLocationRelativeTo(null);

	        // Panel principal que contiene el panel de retroceso y el panel de logo y título
	        JPanel northPanel = new JPanel();
	        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

	        // Panel de retroceso (ahora está en la parte superior izquierda)
	        JPanel retrocesoPanel = new JPanel();
	        retrocesoPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinear el botón a la izquierda

	        JButton retrocesoButton = new JButton();
	        ImageIcon icono = new ImageIcon("resources/images/icono_volver.png"); // Cargar la imagen
	        Image imagen = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
	        retrocesoButton.setIcon(new ImageIcon(imagen)); // Asignar la imagen redimensionada
	        retrocesoButton.setBorder(null); // Eliminar borde
	        retrocesoButton.setPreferredSize(new Dimension(40, 40)); // El tamaño del botón
	        retrocesoPanel.setBackground(new Color(238, 238, 238)); // Fondo del panel
	        retrocesoButton.setBackground(new Color(238, 238, 238)); // Coincidir con el fondo del panel

	        // Acción del botón de retroceso
	        retrocesoButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose(); // Cierra la ventana actual
	                Home home = new Home(); // Instancia de la ventana Home
	                home.setVisible(true); // Muestra la ventana Home
	            }
	        });

	        // Añadir el botón al panel de retroceso
	        retrocesoPanel.add(retrocesoButton);
	        northPanel.add(retrocesoPanel);

	        // Panel de logo e título
	        JPanel headerPanel = new JPanel();
	        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
	        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        headerPanel.setBackground(new Color(238, 238, 238)); // Fondo del panel

	        // Logo en la parte superior
	        ImageIcon logoIcon = new ImageIcon("resources/images/logo_empresa.png");
	        if (logoIcon != null) {
	            Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Reducimos el tamaño del logo
	            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
	            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	            headerPanel.add(logoLabel);
	        }

	        // Título debajo del logo
	        JLabel titleLabel = new JLabel("Actividades e Información", SwingConstants.CENTER);
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Reducimos el tamaño de la fuente
	        titleLabel.setForeground(Color.DARK_GRAY);
	        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        headerPanel.add(titleLabel);

	        northPanel.add(headerPanel);
	        add(northPanel, BorderLayout.NORTH);

	        // Panel principal de actividades
	        JPanel actividadesPanel = new JPanel(new GridLayout(2, 3, 8, 8)); // Reducimos el espacio entre los elementos
	        actividadesPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
	        actividadesPanel.setBackground(new Color(245, 245, 245)); // Fondo del panel principal

	        // Agregar las actividades al panel
	        actividadesPanel.add(crearPanelActividad(
	                "Wellbeing", "resources/images/wellbeing.jpg",
	                "El lujo de desconectar para conectar. Invitamos en exclusiva a nuestros clientes a explorar nuestra Wellbeing Area.",
	                "https://www.theprincipalmadridhotel.com/es/actividades/hotel-wellness/"
	        ));

	        actividadesPanel.add(crearPanelActividad(
	                "Yoga", "resources/images/yoga.jpg",
	                "Encuentra la mejor versión de ti. Sesiones gratuitas de Yoga para clientes alojados en el hotel.",
	                "https://www.theprincipalmadridhotel.com/es/actividades/yoga/"
	        ));

	        actividadesPanel.add(crearPanelActividad(
	                "Taller de Coctelería", "resources/images/taller.jpg",
	                "Descubra nuevos sabores. Nuestro barman enseñará a preparar cócteles clásicos.",
	                "https://www.theprincipalmadridhotel.com/es/actividades/talleres-de-cocteleria/"
	        ));

	        actividadesPanel.add(crearPanelActividad(
	                "Cine al Aire Libre", "resources/images/cine.jpg",
	                "Películas bajo las estrellas. Vive una experiencia cinematográfica única con nuestras proyecciones al aire libre.",
	                "https://madridsecreto.co/cines-verano-madrid/"
	        ));

	        actividadesPanel.add(crearPanelActividad(
	                "Cata de Vinos", "resources/images/cata.jpg",
	                "Una experiencia enológica exclusiva. Cata de vinos seleccionados de las mejores bodegas de la región.",
	                "https://www.atrapalo.com/actividades/cata-gourmet-5-vinos-5-pintxos-experiencia-en-bodega-de-los-reyes_e4803570/"
	        ));

	        actividadesPanel.add(crearPanelActividad(
	                "Conserjería", "resources/images/conserjeria.jpg",
	                "Un equipo a su disposición. Conozca Madrid como un auténtico local.",
	                "https://www.theprincipalmadridhotel.com/es/actividades/concierge/"
	        ));

	        add(actividadesPanel, BorderLayout.CENTER);

	        // Panel de transporte en la parte inferior
	        JPanel transportePanel = new JPanel(new GridLayout(1, 3, 20, 0)); // Reducimos el espacio entre los elementos
	        transportePanel.setBorder(BorderFactory.createEmptyBorder(15, 45, 15, 45));
	        transportePanel.setBackground(new Color(245, 245, 245)); // Fondo del panel de transporte

	        transportePanel.add(crearPanelTransporte("Autobús", "1, 2, 74, 146, N16, N18"));
	        transportePanel.add(crearPanelTransporte("Metro", "Líneas 1, 2 y 5"));
	        transportePanel.add(crearPanelTransporte("Tren", "C3 y C4"));

	        add(transportePanel, BorderLayout.SOUTH);

	        // Hacer visible la ventana
	        revalidate();
	        repaint();
	    }

	    // Método para crear panel de cada actividad
	    private static JPanel crearPanelActividad(String nombre, String rutaImagen, String descripcion, String url) {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        panel.setBackground(Color.WHITE);
	        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

	        panel.add(Box.createVerticalStrut(8)); // Espaciado arriba

	        ImageIcon icono = new ImageIcon(rutaImagen);
	        if (icono != null) {
	            // Cambiar tamaño de las imágenes según el nombre de la actividad
	            int ancho = 160;
	            if (nombre.equals("Wellbeing") || nombre.equals("Yoga") || nombre.equals("Cine al Aire Libre")) {
	                ancho = 160; // Aumentamos tamaño de las imágenes de esas actividades
	            }

	            JLabel labelIcono = new JLabel(new ImageIcon(icono.getImage().getScaledInstance(ancho, ancho, Image.SCALE_SMOOTH)));
	            labelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
	            panel.add(labelIcono);
	        }

	        panel.add(Box.createVerticalStrut(8)); // Espaciado entre la imagen y el texto

	        JLabel labelNombre = new JLabel(nombre);
	        labelNombre.setFont(new Font("Arial", Font.BOLD, 16)); // Título más grande
	        labelNombre.setForeground(Color.DARK_GRAY);
	        labelNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
	        panel.add(labelNombre);

	        JLabel labelDescripcion = new JLabel("<html><div style='width: 180px;'>" + descripcion + "</div></html>");
	        labelDescripcion.setFont(new Font("Arial", Font.PLAIN, 12)); // Tamaño de fuente más pequeño
	        labelDescripcion.setForeground(Color.GRAY);
	        labelDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
	        panel.add(labelDescripcion);

	        // Agregar acción al panel
	        panel.add(Box.createVerticalStrut(8)); // Espaciado entre la descripción y el botón
	        JButton boton = new JButton("Ver Más");
	        boton.setBackground(new Color(102, 204, 255));
	        boton.setForeground(Color.WHITE);
	        boton.setFocusPainted(false);
	        boton.setFont(new Font("Arial", Font.PLAIN, 12)); // Fuente más pequeña
	        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
	        boton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    Desktop.getDesktop().browse(new URI(url));
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	        panel.add(boton);

	        return panel;
	    }

	    // Método para crear panel de transporte
	    private static JPanel crearPanelTransporte(String tipo, String lineas) {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        panel.setBackground(Color.WHITE);
	        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

	        JLabel labelTipo = new JLabel(tipo);
	        labelTipo.setFont(new Font("Arial", Font.BOLD, 14));
	        labelTipo.setAlignmentX(Component.CENTER_ALIGNMENT);

	        JLabel labelLineas = new JLabel(lineas);
	        labelLineas.setFont(new Font("Arial", Font.PLAIN, 12));
	        labelLineas.setAlignmentX(Component.CENTER_ALIGNMENT);

	        panel.add(labelTipo);
	        panel.add(labelLineas);

	        return panel;
	    }
}
