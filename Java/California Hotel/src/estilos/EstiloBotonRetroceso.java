package estilos;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class EstiloBotonRetroceso extends JButton {

	private static final long serialVersionUID = 1L;
	// Atributos
	private JFrame ventanaActual;
	private Class<? extends JFrame> ventanaDestino;

	public EstiloBotonRetroceso(JFrame ventanaActual, Class<? extends JFrame> ventanaDestino, int margenSuperior,
			int margenIzquierdo, int margenInferior, int margenDerecho) {
		this.ventanaActual = ventanaActual;
		this.ventanaDestino = ventanaDestino;
		
		// Configuración del icono
		ImageIcon iconoRetroceso = new ImageIcon("resources/images/icono_volver.png");
		Image imagen = iconoRetroceso.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(imagen));

		// Configuración de los márgenes
		setBorder(BorderFactory.createEmptyBorder(margenSuperior, margenIzquierdo, margenInferior, margenDerecho));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);

		// Acción del botón para abrir la ventana destino
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose(); // Cierra la ventana actual
				try {
					// Crear la ventana de destino usando reflexión
					JFrame nuevaVentana = ventanaDestino.getDeclaredConstructor().newInstance();
					nuevaVentana.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace(); // Muestra errores si la creación de la ventana falla
				}
			}
		});
	}

	public EstiloBotonRetroceso(JFrame ventanaActual, int margenSuperior, int margenIzquierdo, int margenInferior,
			int margenDerecho) {
		this.ventanaActual = ventanaActual;

		// Configuración del icono
		ImageIcon iconoRetroceso = new ImageIcon("resources/images/icono_volver.png");
		Image imagen = iconoRetroceso.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(imagen));

		// Configuración de los márgenes
		setBorder(BorderFactory.createEmptyBorder(margenSuperior, margenIzquierdo, margenInferior, margenDerecho));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);

		// Acción del botón para abrir la ventana destino
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose(); // Cierra la ventana actual
			}
		});
	}

}
