package estilos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

public class EstiloBotones extends JButton {
	private static final long serialVersionUID = 1L;
	private int radio;
	private String tipoBoton;
	private String categoriaBoton;
	private JButton boton;

	public EstiloBotones(String tipo, String categoria, int radio) {
		this.tipoBoton = tipo;
		this.categoriaBoton = categoria;
		this.radio = radio;
		setOpaque(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorderPainted(false);

		actualizarTexto();

		if (categoriaBoton.equalsIgnoreCase("Principal")) {
			setBackground(Color.decode("#F5F5F5"));
			setForeground(Color.BLACK);

		}
		if (categoriaBoton.equalsIgnoreCase("Secundario")) {
			setBackground(Color.decode("#2F4F4F"));
			setForeground(Color.WHITE);
		}
		if (categoriaBoton.equalsIgnoreCase("Tercero")) {
			setBackground(Color.decode("#2F4F4F"));
			setForeground(Color.WHITE);
		}

	}

	public void actualizarTexto() {
		String texto = MultiIdioma.IdiomaBoton(tipoBoton);
		setText(texto);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Establecer el color del fondo del botón
		g.setColor(getBackground());
		// Dibujar un rectángulo redondeado
		g.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);

		super.paintComponent(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		// Establecer el color del borde en función de la categoría
		if (categoriaBoton.equalsIgnoreCase("Secundario")|| categoriaBoton.equalsIgnoreCase("Tercero")) {
			g.setColor(Color.BLACK); // Borde negro para los botones secundarios
		} else {
			g.setColor(getForeground()); // Usar el color del texto para otros bordes
		}
		// Dibujar un borde redondeado
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension dimension = null;
		if (categoriaBoton.equalsIgnoreCase("Principal")) {
			dimension = new Dimension(200, 70);
		}
		if (categoriaBoton.equalsIgnoreCase("Secundario")) {
			dimension = new Dimension(100, 20);
		}
		// Para el boton finalizar del check-out (necesitaba que fuera mas grade)
		if (categoriaBoton.equalsIgnoreCase("Tercero")) {
			dimension = super.getPreferredSize();
		}
		return dimension;
	}

	public int getRadio() {
		return radio;
	}

	public String getTipoBoton() {
		return tipoBoton;
	}

	public JButton getBoton() {
		return boton;
	}

}
