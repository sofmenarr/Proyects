package sacar_valores;

import java.awt.Component;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class GestorDatosRegistro {

	// Obtener el tipo de cama seleccionado
	public String getSelectedTipoCama(ButtonGroup grupo) {
		for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText(); // Retorna el texto del botón seleccionado
			}
		}
		return null; // Si no hay botón seleccionado
	}

	// Obtener el tipo de vista seleccionado
	public String getSelectedTipoVista(ButtonGroup grupo) {
		for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText(); // Retorna el texto del botón seleccionado
			}
		}
		return null; // Si no hay botón seleccionado
	}

	public boolean esCheckBoxSeleccionado(JPanel panel, String textoCheckBox) {
		for (Component component : panel.getComponents()) {
			if (component instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) component;
				String texto = checkBox.getText().split("\\(")[0].trim(); // quitar el precio
				if (texto.equalsIgnoreCase(textoCheckBox)) {
					return checkBox.isSelected();
				}
			}
		}
		return false;
	}

	// Obtener las mejoras de mobiliario seleccionadas
	public String getMejorasMobiliario(List<JCheckBox> mobiliarioSelec) {
		StringBuilder mobiliario = new StringBuilder();
		for (JCheckBox checkBox : mobiliarioSelec) {
			if (checkBox.isSelected()) {
				if (mobiliario.length() > 0) {
					mobiliario.append(", ");
				}
				mobiliario.append(checkBox.getText());
			}
		}
		return mobiliario.toString();
	}

	// Método para obtener las amenidades seleccionadas
	public String getAmenidades(List<JCheckBox> amenidadesSelec) {
		StringBuilder amenidades = new StringBuilder();
		for (JCheckBox checkBox : amenidadesSelec) {
			if (checkBox.isSelected()) {
				if (amenidades.length() > 0) {
					amenidades.append(", ");
				}
				amenidades.append(checkBox.getText());
			}
		}
		return amenidades.toString();
	}
}
