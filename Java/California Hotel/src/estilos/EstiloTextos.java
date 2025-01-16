package estilos;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class EstiloTextos extends JLabel {
	private static final long serialVersionUID = 1L;
	private String usoTexto;
	private String tipoTexto;
	private String rutaImagen;

	private Font gravitasFont;
	private Font openSansFont;

	private int margenSuperiorImagen;
	private int margenIzquierdoImagen;
	private int margenInferiorImagen;
	private int margenDerechoImagen;
	private int margenSuperiorTexto;
	private int margenIzquierdoTexto;
	private int margenInferiorTexto;
	private int margenDerechoTexto;

	public EstiloTextos(String uso, String tipo) {
		this.usoTexto = uso;
		this.tipoTexto = tipo;

		actualizarTexto();
		inicializarFuentes();

		switch (tipo) {
		case "titulo":
			estiloTitulo();
			break;
		case "encabezado1":
			estiloEncabezado1();
			break;
		case "encabezado2":
			estiloEncabezado2();
			break;
		case "encabezado3":
			estiloEncabezado3();
			break;
		case "texto":
			estiloTextoNormal();
			break;
		case "credenciales":
			estiloTextoCredenciales();
		}

	}

	// Segundo constructor para que meta por parametro una ruta
	public EstiloTextos(String uso, String tipo, String rutaImagen, int margenSuperiorImagen, int margenIzquierdoImagen,
			int margenInferiorImagen, int margenDerechoImagen, int margenSuperiorTexto, int margenIzquierdoTexto,
			int margenInferiorTexto, int margenDerechoTexto) {
		this.usoTexto = uso;
		this.tipoTexto = tipo;
		this.rutaImagen = rutaImagen;
		this.margenSuperiorImagen = margenSuperiorImagen;
		this.margenIzquierdoImagen = margenIzquierdoImagen;
		this.margenInferiorImagen = margenInferiorImagen;
		this.margenDerechoImagen = margenDerechoImagen;
		this.margenSuperiorTexto = margenSuperiorTexto;
		this.margenIzquierdoTexto = margenIzquierdoTexto;
		this.margenInferiorTexto = margenInferiorTexto;
		this.margenDerechoTexto = margenDerechoTexto;

		actualizarTexto();
		inicializarFuentes();

		switch (tipo) {
		case "titulo":
			imagenTitulo(rutaImagen, margenSuperiorImagen, margenIzquierdoImagen, margenInferiorImagen,
					margenDerechoImagen, margenSuperiorTexto, margenIzquierdoTexto, margenInferiorTexto,
					margenDerechoTexto);
			break;
		case "encabezado1":
			estiloEncabezado1();
			break;
		case "encabezado2":
			estiloEncabezado2();
			break;
		case "encabezado3":
			estiloEncabezado3();
			break;
		case "encabezado4":
			estiloEncabezado4();
			break;
		case "texto":
			estiloTextoNormal();
			break;
		case "credenciales":
			estiloTextoCredenciales();
			break;
		}
	}

	public void actualizarTexto() {
		String texto = MultiIdioma.IdiomaTexto(usoTexto);
		setText(texto);
	}

	public void estiloTitulo() {
		Font boldFont = gravitasFont.deriveFont(Font.BOLD, 30f);
		setFont(boldFont);
		setForeground(Color.BLACK);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void estiloEncabezado1() {
		Font boldFont = openSansFont.deriveFont(Font.BOLD, 22f);
		setFont(boldFont);
		setForeground(Color.BLACK);
	}

	public void estiloEncabezado2() {
		Font boldFont = openSansFont.deriveFont(Font.PLAIN, 20f);
		setFont(boldFont);
		setForeground(Color.BLACK);
	}

	public void estiloEncabezado3() {
		Font boldFont = openSansFont.deriveFont(Font.BOLD, 17f);
		setFont(boldFont);
		setForeground(Color.BLACK);
	}

	public void estiloEncabezado4() {
		Font boldFont = openSansFont.deriveFont(Font.BOLD, 13f);
		setFont(boldFont);
		setForeground(Color.BLACK);
	}

	public void estiloTextoNormal() {
		Font boldFont = openSansFont.deriveFont(Font.PLAIN, 12f);
		setFont(boldFont);
		setForeground(Color.BLACK);
	}

	public void estiloTextoCredenciales() {
		Font boldFont = openSansFont.deriveFont(Font.PLAIN, 10f);
		setFont(boldFont);
		setForeground(Color.BLACK);
	}

	public void inicializarFuentes() {
		// Cargar las fuentes desde el classpath
		InputStream gravitasStream = getClass().getClassLoader().getResourceAsStream("fonts/GravitasOne-Regular.ttf");
		InputStream openSansStream = getClass().getClassLoader().getResourceAsStream("fonts/OpenSans-Regular.ttf");
		try {
			gravitasFont = Font.createFont(Font.TRUETYPE_FONT, gravitasStream);
			openSansFont = Font.createFont(Font.TRUETYPE_FONT, openSansStream);

			// Registrar las fuentes para uso posterior
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(gravitasFont);
			ge.registerFont(openSansFont);

		} catch (FontFormatException e) {
			System.err.println("Error de formato de fuente: " + e.getMessage());
			gravitasFont = new Font("Arial", Font.PLAIN, 10);
			openSansFont = new Font("Arial", Font.PLAIN, 10);
		} catch (IOException e) {
			System.err.println("Error al leer los datos de la fuente: " + e.getMessage());
			gravitasFont = new Font("Arial", Font.PLAIN, 10);
			openSansFont = new Font("Arial", Font.PLAIN, 10);
		}

	}

	public void imagenTitulo(String rutaImagen, int margenSuperiorImagen, int margenIzquierdoImagen,
			int margenInferiorImagen, int margenDerechoImagen, int margenSuperiorTexto, int margenIzquierdoTexto,
			int margenInferiorTexto, int margenDerechoTexto) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// Crear el JLabel para la imagen
		ImageIcon imagenIcono = new ImageIcon(rutaImagen);
		Image imagen = imagenIcono.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		imagenIcono = new ImageIcon(imagenRedimensionada);

		JLabel labelImagen = new JLabel(imagenIcono);
		labelImagen.setHorizontalAlignment(SwingConstants.CENTER);

		// Colocar la imagen en la fila superior
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new java.awt.Insets(margenSuperiorImagen, margenIzquierdoImagen, margenInferiorImagen,
				margenDerechoImagen); // Márgenes para ajustar posición
		this.add(labelImagen, gbc);

		// Crear el JLabel para el texto (título)
		JLabel labelTexto = new JLabel(getText());
		labelTexto.setFont(gravitasFont.deriveFont(Font.BOLD, 36f));
		labelTexto.setForeground(Color.BLACK);
		labelTexto.setHorizontalAlignment(SwingConstants.CENTER);

		setText("");

		// Colocar el título en la fila inferior
		gbc.gridy = 2;
		gbc.insets = new java.awt.Insets(margenSuperiorTexto, margenIzquierdoTexto, margenInferiorTexto,
				margenDerechoTexto); // Márgenes para ajustar posición
		this.add(labelTexto, gbc);
	}

	public String getUsoTexto() {
		return usoTexto;
	}

	public String getTipoTexto() {
		return tipoTexto;
	}

	@Override
	public void setText(String text) {
		// Personalización adicional si es necesario antes de establecer el texto
		super.setText(text);
	}

	public void actualizarTextoDinamico(String texto) {
		this.usoTexto = texto;
		String nuevoTexto = MultiIdioma.IdiomaTexto(this.usoTexto);
		setText(nuevoTexto);
	}

}
