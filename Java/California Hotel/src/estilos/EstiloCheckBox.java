package estilos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JCheckBox;

public class EstiloCheckBox extends JCheckBox {
	private static final long serialVersionUID = 1L;
	private String tipoBoton;

	public EstiloCheckBox(String tipo) {
		this.tipoBoton = tipo;
		setOpaque(false); // Para que el fondo sea transparente
		setFocusPainted(false); // No pintar el borde cuando se selecciona

		actualizarTexto(); 

		setBackground(Color.decode("#F5F5DC")); // Color de fondo del checkbox
		setForeground(Color.BLACK); // Color del texto
	}

	public void actualizarTexto() {
		String texto = MultiIdioma.IdiomaBoton(tipoBoton); // Método para obtener el texto en el idioma correspondiente
		setText(texto);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground()); // Establece el color del fondo
		super.paintComponent(g); // Dibuja el checkbox normalmente
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 30); // Dimensiones preferidas del checkbox
	}

	public String getTipoBoton() {
		return tipoBoton; // Método para obtener el tipo de botón
	}
}
