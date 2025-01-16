package ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import estilos.EstiloTextos;

public class Politicas_Privacidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Politicas_Privacidad instance;
	
	public Politicas_Privacidad() {
		setTitle("HOTEL CALIFORNIA");
		setSize(700, 700);
		setResizable(false); // Bloquea el poder redimensionarlo
		centrarVentana(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		iniciarComponentesRegistro();
		 
		setVisible(true);
		
	}
	
	private void iniciarComponentesRegistro() {
		JPanel panelRegistro = new JPanel();
		panelRegistro.setLayout(new BoxLayout(panelRegistro, BoxLayout.Y_AXIS)); // Orientado verticalmente
		panelRegistro.setPreferredSize(new Dimension(900, 700));
		Dimension dimensionFrame = panelRegistro.getPreferredSize();
		
		JPanel panelSuperior = crearPanelSuperior(this, dimensionFrame);
		JPanel panelCentral = crearPanelCentral(dimensionFrame);
		
		panelRegistro.add(panelSuperior);
		panelRegistro.add(panelCentral);
		add(panelRegistro);
		
	}
	
	private JPanel crearPanelSuperior(JFrame ventanaActual, Dimension dimensionFrame) {
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout()); // Layout de tipo BorderLayout
		panelSuperior.setPreferredSize(new Dimension(dimensionFrame.width, 80));
		panelSuperior.setMaximumSize(new Dimension(dimensionFrame.width, 100));
		panelSuperior.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		EstiloTextos TituloPoliticas = new EstiloTextos("TituloPolitica", "titulo");
		
		panelSuperior.add(TituloPoliticas, BorderLayout.WEST);
		
		return panelSuperior;
	}
	
	private JPanel crearPanelCentral(Dimension dimensionFrame) {
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout()); // Layout de tipo BorderLayout
		panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel panelTexto = new JPanel();
		EstiloTextos textoPoliticas = new EstiloTextos("textoPoliticaCompleta", "texto");
		textoPoliticas.setHorizontalAlignment(SwingConstants.LEFT);
		panelTexto.add(textoPoliticas);
		
		panelCentral.add(panelTexto);
		
		return panelCentral;
	}
	
	public static void centrarVentana(JFrame ventana) {
		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension dimensionPantalla = pantalla.getScreenSize();
		int anchoPantalla = dimensionPantalla.width;
		int alturaPantalla = dimensionPantalla.height;

		ventana.setLocationRelativeTo(null);
	}
}
