package estilos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

public class EstiloRadioBotones extends JRadioButton {
	private static final long serialVersionUID = 1L;
	private String tipoBoton;

	public EstiloRadioBotones(String tipo) {
		this.tipoBoton = tipo;
		setOpaque(false);
		setFocusPainted(false);

		actualizarTexto();

		setBackground(Color.decode("#F5F5DC"));
		setForeground(Color.BLACK);
	}

	public void actualizarTexto() {
		String texto = MultiIdioma.IdiomaBoton(tipoBoton);
		setText(texto);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground()); // Establece el color del fondo
		super.paintComponent(g);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(30, 70);
	}

	public String getTipoBoton() {
		return tipoBoton;
	}

}
