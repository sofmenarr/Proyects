package estilos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sacar_valores.Reserva;
import ventanas.Home;

public class MultiIdioma {

	private static int idiomaActual = Home.getIdiomaActual();
	private static List<Runnable> escuchadores = new ArrayList<>();
	private static Reserva reserva = new Reserva();
	private static Reserva estado = reserva.getInstance();

	public static void setIdiomaActual(int idiomaActualizado) {
		MultiIdioma.idiomaActual = idiomaActualizado;
		notificarCambioIdioma();
	}

	public static int getIdiomaActual() {
		return idiomaActual;
	}

	public static void registrarComponente(Runnable listener) {
		escuchadores.add(listener);
	}

	public static void notificarCambioIdioma() {
		for (Runnable listener : escuchadores) {
			listener.run(); // Llamar al método para actualizar el componente
		}
	}

	public static String IdiomaBoton(String boton) {
		String textoBoton = "";
		switch (idiomaActual) {
		case 1:
			// BOTONES EN LA VENTANA PRINCIPAL
			if (boton.equalsIgnoreCase("Check-In")) {
				textoBoton = "Reserva";
			}
			if (boton.equalsIgnoreCase("Check-Out")) {
				textoBoton = "Salida";
			}
			if (boton.equalsIgnoreCase("Servicios")) {
				textoBoton = "<html><center>Servicios<br>Contratados</center><html>";
			}
			if (boton.equalsIgnoreCase("Información")) {
				textoBoton = "<html><center>Informacíon<br>& Actividades</center><html>";
			}
			// RADIO BOTONES EN LA VENTANA REGISTRO
			if (boton.equalsIgnoreCase("Matrimonial")) {
				textoBoton = "Matrimonial";
			}
			if (boton.equalsIgnoreCase("CamasSeparadas")) {
				textoBoton = "Dos separadas";
			}
			if (boton.equalsIgnoreCase("CamaIndividual")) {
				textoBoton = "Individual";
			}
			if (boton.equalsIgnoreCase("Jardin")) {
				textoBoton = "Jardín";
			}
			if (boton.equalsIgnoreCase("Panoramica")) {
				textoBoton = "Panorámica";
			}
			if (boton.equalsIgnoreCase("Patio Interior")) {
				textoBoton = "Interior";
			}
			// BOTONES EN LA VENTANA REGISTRO
			if (boton.equalsIgnoreCase("Upgrade")) {
				textoBoton = "MEJORA";
			}
			if (boton.equalsIgnoreCase("aceptar")) {
				textoBoton = "Acepto las condiciones de privacidad y términos";
			}
			if (boton.equalsIgnoreCase("Continuar")) {
				textoBoton = "Continuar Reserva";
			}
			// BOTONES EN LA VENTANA UPGRADE
			if (boton.equalsIgnoreCase("Cuna")) {
				textoBoton = "Cuna bebé (+10€)";
			}
			if (boton.equalsIgnoreCase("Supletoria")) {
				textoBoton = "Cama supletoria (+20€)";
			}
			if (boton.equalsIgnoreCase("Vestimenta")) {
				textoBoton = "Pijamas y Albornoces (+15€)";
			}
			if (boton.equalsIgnoreCase("Cafe")) {
				textoBoton = "Máquina de café (+5€)";
			}
			if (boton.equalsIgnoreCase("Caja")) {
				textoBoton = "Caja Fuerte (+7€)";
			}
			if (boton.equalsIgnoreCase("Router")) {
				textoBoton = "Router (+8€)";
			}
			if (boton.equalsIgnoreCase("Nevera")) {
				textoBoton = "Minibar (+12€)";
			}
			if (boton.equalsIgnoreCase("Terminar")) {
				textoBoton = "INSERTAR";
			}
			// BOTONES EN LA VENTANA PRECIO HABITACION
			if (boton.equalsIgnoreCase("Acceptar")) {
				textoBoton = "Acceptar";
			}

			// BOTONES DE LA VENTANA CHECK-OUT
			if (boton.equalsIgnoreCase("Buscar")) {
				textoBoton = "BUSCAR";
			}
			if (boton.equalsIgnoreCase("Finalizar")) {
				textoBoton = "<html><center>Completar<br>Salida</center></html>";
			}
			if (boton.equalsIgnoreCase("CheckBoxHabtiacion")) {
				textoBoton = "Habitación";
			}
			if (boton.equalsIgnoreCase("CheckBoxMobilario")) {
				textoBoton = "Mejoras en el mobiliario";
			}
			if (boton.equalsIgnoreCase("CheckBoxAmenidades")) {
				textoBoton = "Amenidades de lujo";
			}
			if (boton.equalsIgnoreCase("CheckBoxParking")) {
				textoBoton = "Aparcamiento";
			}
			if (boton.equalsIgnoreCase("CheckBoxRestaurante")) {
				textoBoton = "Restaurante";
			}
			if (boton.equalsIgnoreCase("CheckBoxMasajes")) {
				textoBoton = "Masajes";
			}
			if (boton.equalsIgnoreCase("CheckBoxSpa")) {
				textoBoton = "Balneario";
			}
			if (boton.equalsIgnoreCase("Dinero")) {
				textoBoton = " €";
			}

			// BOTONES DE LA VENTANA SERVICIOS CONTRATADOS
			if (boton.equalsIgnoreCase("Actualizar")) {
				textoBoton = "ACTUALIZAR";
			}
			if (boton.equalsIgnoreCase("Menu")) {
				textoBoton = "MENÚ";
			}
			if (boton.equalsIgnoreCase("Confirmar")) {
				textoBoton = "Confirmar";
			}
			if (boton.equalsIgnoreCase("RadioButtonSi")) {
				textoBoton = "Sí";
			}
			if (boton.equalsIgnoreCase("RadioButtonNo")) {
				textoBoton = "No";
			}
			if (boton.equalsIgnoreCase("CheckBoxMasajeSueco")) {
				textoBoton = "Masaje Sueco (40€/Sesion)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMasajeDeportivo")) {
				textoBoton = "Masaje Deportivo (30€/Sesion)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMasajeTailandes")) {
				textoBoton = "Masaje Tailandés (45€/Sesión)";
			}
			if (boton.equalsIgnoreCase("CheckBoxSpaMedio")) {
				textoBoton = "Ritual spa medio día (35€/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxSpaCompleto")) {
				textoBoton = "Ritual spa día completo (70€/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxBaños")) {
				textoBoton = "Baños de inmersión (25€/D)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMesasEstandar")) {
				textoBoton = "Mesas estándar (10€/D)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMesasPrivadas")) {
				textoBoton = "Mesas privadas (30€/D)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMenusEspeciales")) {
				textoBoton = "Menús especiales (30€/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxEventosyCelebraciones")) {
				textoBoton = "Eventos y celebraciones (130€/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxExperienciaGastronomica")) {
				textoBoton = "Experiencia gastronómica (60€/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxAireLibre")) {
				textoBoton = "Al aire libre (20€/D)";
			}

			break;
		case 2:
			// BOTONES EN LA VENTANA PRINCIPAL
			if (boton.equalsIgnoreCase("Check-In")) {
				textoBoton = "Check-In";
			}
			if (boton.equalsIgnoreCase("Check-Out")) {
				textoBoton = "Check-Out";
			}
			if (boton.equalsIgnoreCase("Servicios")) {
				textoBoton = "<html><center>Contracted<br>Services</center><html>";
			}
			if (boton.equalsIgnoreCase("Información")) {
				textoBoton = "<html><center>Activities<br></center>& Information<html>";
			}
			// RADIO BOTONES EN LA VENTANA REGISTRO
			if (boton.equalsIgnoreCase("Matrimonial")) {
				textoBoton = "Matrimonial";
			}
			if (boton.equalsIgnoreCase("CamasSeparadas")) {
				textoBoton = "Two individual";
			}
			if (boton.equalsIgnoreCase("CamaIndividual")) {
				textoBoton = "One bed";
			}
			if (boton.equalsIgnoreCase("Jardin")) {
				textoBoton = "Garden";
			}
			if (boton.equalsIgnoreCase("Panoramica")) {
				textoBoton = "Panorama";
			}
			if (boton.equalsIgnoreCase("Patio Interior")) {
				textoBoton = "Courtyard";
			}
			// BOTONES EN LA VENTANA REGISTRO
			if (boton.equalsIgnoreCase("Upgrade")) {
				textoBoton = "UPGRADE";
			}
			if (boton.equalsIgnoreCase("aceptar")) {
				textoBoton = "I accept the conditions of privacy and terms";
			}
			if (boton.equalsIgnoreCase("Continuar")) {
				textoBoton = "Continue Check-In";
			}

			// BOTONES EN LA VENTANA UPGRADE
			if (boton.equalsIgnoreCase("Cuna")) {
				textoBoton = "Baby crib (+10€)";
			}
			if (boton.equalsIgnoreCase("Supletoria")) {
				textoBoton = "Extra bed (+20€)";
			}
			if (boton.equalsIgnoreCase("Vestimenta")) {
				textoBoton = "Pijamas & Bathrobes (+15€)";
			}
			if (boton.equalsIgnoreCase("Cafe")) {
				textoBoton = "Coffee machine (+5€)";
			}
			if (boton.equalsIgnoreCase("Caja")) {
				textoBoton = "Safe (+7€)";
			}
			if (boton.equalsIgnoreCase("Router")) {
				textoBoton = "Router (+8€)";
			}
			if (boton.equalsIgnoreCase("Nevera")) {
				textoBoton = "Minibar (+12€)";
			}
			if (boton.equalsIgnoreCase("Terminar")) {
				textoBoton = "ENTER";
			}
			// BOTONES EN LA VENTANA PRECIO HABITACION
			if (boton.equalsIgnoreCase("Acceptar")) {
				textoBoton = "Accept";
			}

			// BOTONES DE LA VENTANA CHECK-OUT
			if (boton.equalsIgnoreCase("Buscar")) {
				textoBoton = "SEARCH";
			}
			if (boton.equalsIgnoreCase("Finalizar")) {
				textoBoton = "<html>Complete<br>check-out<html>";
			}
			if (boton.equalsIgnoreCase("CheckBoxHabtiacion")) {
				textoBoton = "Room";
			}
			if (boton.equalsIgnoreCase("CheckBoxMobilario")) {
				textoBoton = "Improvements in furniture";
			}
			if (boton.equalsIgnoreCase("CheckBoxAmenidades")) {
				textoBoton = "Luxury amenities";
			}
			if (boton.equalsIgnoreCase("CheckBoxParking")) {
				textoBoton = "Parking";
			}
			if (boton.equalsIgnoreCase("CheckBoxRestaurante")) {
				textoBoton = "Restaurant";
			}
			if (boton.equalsIgnoreCase("CheckBoxMasajes")) {
				textoBoton = "Massages";
			}
			if (boton.equalsIgnoreCase("CheckBoxSpa")) {
				textoBoton = "Spa";
			}
			if (boton.equalsIgnoreCase("Dinero")) {
				textoBoton = "$ ";
			}

			// BOTONES DE LA VENTANA SERVICIOS CONTRATADOS
			if (boton.equalsIgnoreCase("Actualizar")) {
				textoBoton = "UPDATE";
			}
			if (boton.equalsIgnoreCase("Menu")) {
				textoBoton = "MENÚ";
			}
			if (boton.equalsIgnoreCase("Confirmar")) {
				textoBoton = "Confirm";
			}
			if (boton.equalsIgnoreCase("RadioButtonSi")) {
				textoBoton = "Yes";
			}
			if (boton.equalsIgnoreCase("RadioButtonNo")) {
				textoBoton = "No";
			}
			if (boton.equalsIgnoreCase("CheckBoxMasajeSueco")) {
				textoBoton = "Swedish Massage (40$/Session)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMasajeDeportivo")) {
				textoBoton = "Sports Massage (30$/Session)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMasajeTailandes")) {
				textoBoton = "Thai Massage (45$/Session)";
			}
			if (boton.equalsIgnoreCase("CheckBoxSpaMedio")) {
				textoBoton = "Half-day spa ritual (35$/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxSpaCompleto")) {
				textoBoton = "Full-day spa ritual (70$/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxBaños")) {
				textoBoton = "Immersion baths (25$/D)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMesasEstandar")) {
				textoBoton = "Standard tables (10$/D)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMesasPrivadas")) {
				textoBoton = "Private tables (30$/D)";
			}
			if (boton.equalsIgnoreCase("CheckBoxMenusEspeciales")) {
				textoBoton = "Special menus (30$/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxEventosyCelebraciones")) {
				textoBoton = "Events and celebrations (130$/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxExperienciaGastronomica")) {
				textoBoton = "Gastronomic experience (60$/P)";
			}
			if (boton.equalsIgnoreCase("CheckBoxAireLibre")) {
				textoBoton = "Outdoors (20$/D)";
			}
			break;
		}
		return textoBoton;

	}

	public static String IdiomaTexto(String tipo) {
		String texto = "";
		switch (idiomaActual) {
		case 1:
			// TEXTO EN LA VENTANA PRINCIPAL
			if (tipo.equalsIgnoreCase("TituloHome")) {
				texto = "<html><center>Hotel<br>California</center><html>";
			}
			if (tipo.equalsIgnoreCase("TituloCheck-In")) {
				texto = "Registro";
			}
			if (tipo.equalsIgnoreCase("Credenciales")) {
				texto = "© 2024 Hotel California. Todos los derechos reservados.";
			}

			// TEXTO EN LA VENTANA REGISTRO
			if (tipo.equalsIgnoreCase("TituloReserva")) {
				texto = "Registro";
			}
			if (tipo.equalsIgnoreCase("EncabezadoIdentificación")) {
				texto = "IDENTIFICACIÓN:";
			}
			if (tipo.equalsIgnoreCase("Nombre")) {
				texto = "Nombre:";
			}
			if (tipo.equalsIgnoreCase("Apellidos")) {
				texto = "Apellidos:";
			}
			if (tipo.equalsIgnoreCase("DocumentoIdentificador")) {
				texto = "DNI/Pasaporte:";
			}
			if (tipo.equalsIgnoreCase("Dias")) {
				texto = "Nº Días:";
			}
			if (tipo.equalsIgnoreCase("SelecHabitaciones")) {
				texto = "SELECCIÓN DE HABITACIONES:";
			}
			if (tipo.equalsIgnoreCase("EncabezadoCamas")) {
				texto = "Tipos de Camas:";
			}
			if (tipo.equalsIgnoreCase("EncabezadoVistas")) {
				texto = "Tipos de Vistas:";
			}
			if (tipo.equalsIgnoreCase("Upgrade")) {
				texto = "<html>Si desea tener alguna mejora en su habitación pulse en el botón de 'UPGRADE'<br>"
						+ "(Recuerde que por cada mejora se sumará a los costos adicionales).</html>";
			}
			if (tipo.equalsIgnoreCase("PoliticaPrivacidad")) {
				texto = "POLÍTICA DE PRIVACIDAD:";
			}
			if (tipo.equalsIgnoreCase("textoPoliticas")) {
				texto = "<html>Política de Privacidad:<br>"
						+ "haciendo clic en 'Aceptar', usted confirma que ha leído y acepta nuestros <br>"
						+ "[Términos de servicio] y [Política de privacidad].</html>";
			}
			if (tipo.equalsIgnoreCase("MasInfo")) {
				texto = "<html><u>Más Información</u></html>";
			}
			if (tipo.equalsIgnoreCase("MessageHabitaciones")) {
				texto = "No hay habitaciones disponibles con esas características. Por favor, elige otra combinación.";
			}
			if (tipo.equalsIgnoreCase("MessagePoliticas")) {
				texto = "Para poder continuar tiene que aceptar las politicas de privacidad.";
			}
			if (tipo.equalsIgnoreCase("MessageCampos")) {
				texto = "Por favor, rellena todos los campos.";
			}
			// TEXTO EN LA VENTANA UPGRADE
			if (tipo.equalsIgnoreCase("TituloUpgrade")) {
				texto = "Mejoras";
			}
			if (tipo.equalsIgnoreCase("EncabezadoMejoras")) {
				texto = "Mejoras en el Mobiliario:";
			}
			if (tipo.equalsIgnoreCase("EncabezadoAmenidades")) {
				texto = "Amenidades de Lujo:";
			}
			// TEXTO EN LA VENTANA DE POLITICAS Y PRIVACIDAD
			if (tipo.equalsIgnoreCase("TituloPolitica")) {
				texto = "Terminos y condiciones";
			}
			if (tipo.equalsIgnoreCase("textoPoliticaCompleta")) {
				texto = "<html><strong>Términos y Condiciones</strong> <br>" + "<u>Uso del Servicio:</u><br>"
						+ "Los usuarios deben utilizar el servicio de manera legal y conforme a las políticas establecidas.<br>"
						+ "Está prohibido el uso del servicio para actividades ilícitas o no autorizadas.<br>"
						+ "<u>Propiedad Intelectual:</u><br>"
						+ "Todos los contenidos, marcas y derechos de autor pertenecen al titular del servicio.<br>"
						+ "Los usuarios no pueden reproducir, distribuir ni modificar el contenido sin permiso.<br>"
						+ "<u>Limitación de Responsabilidad:</u><br>"
						+ "El titular del servicio no se hace responsable de daños directos o indirectos derivados del uso del servicio.<br>"
						+ "Se recomienda a los usuarios tomar precauciones al utilizar el servicio.<br>"
						+ "<u>Modificaciones:</u><br>"
						+ "El titular del servicio se reserva el derecho de modificar los términos y condiciones en cualquier momento.<br>"
						+ "Los usuarios serán notificados de los cambios y deberán aceptar los nuevos términos para continuar usando el servicio.<br>"
						+ "<br><strong>Política de Privacidad</strong><br>" + "<u>Recopilación de Datos:</u><br>"
						+ "Se recopilan datos personales como nombre, correo electrónico y dirección IP.<br>"
						+ "Los datos se utilizan para mejorar el servicio y personalizar la experiencia del usuario.<br>"
						+ "<u>Uso de los Datos:</u><br>"
						+ "Los datos se utilizan para comunicaciones, promociones y análisis internos.<br>"
						+ "No se comparten datos con terceros sin el consentimiento del usuario, salvo en casos legales.<br>"
						+ "<u>Derechos del Usuario:</u><br>"
						+ "Los usuarios tienen derecho a acceder, rectificar y eliminar sus datos personales.<br>"
						+ "Pueden retirar su consentimiento para el uso de sus datos en cualquier momento.<br>"
						+ "<u>Seguridad de los Datos:</u><br>"
						+ "Se implementan medidas de seguridad para proteger los datos personales contra accesos no autorizados.<br>"
						+ "Se realizan auditorías periódicas para garantizar la seguridad de la información.<br>"
						+ "<u>Cookies y Tecnologías Similares:</u><br>"
						+ "Se utilizan cookies para mejorar la funcionalidad del sitio y analizar el comportamiento del usuario.<br>"
						+ "Los usuarios pueden gestionar sus preferencias de cookies a través de la configuración del navegador. </html>";
			}
			// TEXTO EN LA VENTANA CONFIRMAR RESERVA
			if (tipo.equalsIgnoreCase("TituloPrecio")) {
				texto = "<html><center>Precio Habitacion</center></html>";
			}
			if (tipo.equalsIgnoreCase("Detalle")) {
				texto = "Detalle:";
			}
			if (tipo.equalsIgnoreCase("CamaSelec")) {
				texto = estado.getTipoCamaSeleccionada();
			}
			if (tipo.equalsIgnoreCase("VistasSelec")) {
				texto = estado.getTipoVistaSeleccionada();
			}
			if (tipo.equalsIgnoreCase("MejorasMobiliario")) {
				if (estado.getMejorasSeleccionadas() != null) {
					texto = estado.getMejorasSeleccionadas();
				}
			}
			if (tipo.equalsIgnoreCase("MejorasAmenidades")) {
				if (estado.getAmenidadesSeleccionadas() != null) {
					texto = estado.getAmenidadesSeleccionadas();
				}
			}
			if (tipo.equalsIgnoreCase("MessageReservada")) {
				texto = "Habitación reservada. Su número de habitacion es: ";
			}
			// TEXTO EN LA VENTANA CHECK-OUT
			if (tipo.equalsIgnoreCase("TituloSalida")) {
				texto = "Salida";
			}
			if (tipo.equalsIgnoreCase("NumeroHabitacion")) {
				texto = "Número de habitación:";
			}
			if (tipo.equalsIgnoreCase("Detalle")) {
				texto = "Detalle:";
			}

			// TEXTO EN LA VENTANA SERVICIOS CONTRATADOS
			if (tipo.equalsIgnoreCase("TituloServicios")) {
				texto = "<html><center>Servicios<br>Contratados</center><html>";
			}
			if (tipo.equalsIgnoreCase("NumHabitacion")) {
				texto = "Nº Habitación";
			}
			if (tipo.equalsIgnoreCase("NumeroDNI")) {
				texto = "Número del DNI";
			}
			if (tipo.equalsIgnoreCase("TituloAparcamiento")) {
				texto = "APARCAMIENTO (25€/Día)";
			}
			if (tipo.equalsIgnoreCase("TituloMasajes")) {
				texto = "MASAJES MARTES Y VIERNES (11:00 - 13:00)";
			}
			if (tipo.equalsIgnoreCase("TituloSpa")) {
				texto = "BALNERARIO";
			}
			if (tipo.equalsIgnoreCase("TituloRestaurante")) {
				texto = "RESTAURANTE";
			}
			if (tipo.equalsIgnoreCase("TituloServicioHabitaciones")) {
				texto = "SERVICIO DE HABITACIONES";
			}
			if (tipo.equalsIgnoreCase("InfoMenu")) {
				texto = "<html>Para poder ver el menú que se le puede ofrecer a sus habitaciones con los precios pulse MENÚ</html>";
			}

			// TEXTO EN LA VENTANA MENU
			if (tipo.equalsIgnoreCase("TituloMenu")) {
				texto = "Menú";
			}
			if (tipo.equalsIgnoreCase("Entrantes")) {
				texto = "Entrantes";
			}
			if (tipo.equalsIgnoreCase("Entrante1")) {
				texto = "<html><b>Ensalada Caprese:</b> <br> Tomates frescos, mozzarella,<br> albahaca y un toque de aceite <br> de oliva y balsámico.<html>";
			}
			if (tipo.equalsIgnoreCase("Entrante2")) {
				texto = "<html><b>Sopa de calabaza:</b> <br> Cremosa sopa de calabaza con <br> jengibre y una pizca de nuez <br> moscada.<html>";
			}
			if (tipo.equalsIgnoreCase("Entrante3")) {
				texto = "<html><b>Tartar de atún:</b><br> Atún rojo fresco con aguacate <br> y aderezo de soja y sésamo<html>";
			}
			if (tipo.equalsIgnoreCase("Principales")) {
				texto = "Platos Principales";
			}
			if (tipo.equalsIgnoreCase("Principal1")) {
				texto = "<html><b>Solomillo de ternera a la parrilla:</b> <br> Con reducción de vino tinto y <br> guarnición de papas al romero.<html>";
			}
			if (tipo.equalsIgnoreCase("Principal2")) {
				texto = "<html><b>Paella de mariscos:</b><br> Arroz con gambas, mejillones, <br> almejas y calamares.<html>";
			}
			if (tipo.equalsIgnoreCase("Precio1")) {
				texto = "7,50€";
			}
			if (tipo.equalsIgnoreCase("Precio2")) {
				texto = "6,50€";
			}
			if (tipo.equalsIgnoreCase("Precio3")) {
				texto = "15,00€";
			}
			if (tipo.equalsIgnoreCase("Precio4")) {
				texto = "27,00€";
			}
			if (tipo.equalsIgnoreCase("Precio5")) {
				texto = "25,50€";
			}
			if (tipo.equalsIgnoreCase("Nada")) {
				texto = "";
			}

			// TEXTO EN LA VENTANA MENU2
			if (tipo.equalsIgnoreCase("Guarniciones")) {
				texto = "<html>Guarniciones<html>";
			}
			if (tipo.equalsIgnoreCase("Guarnicion1")) {
				texto = "<html><b>Patatas gratinadas:</b><br> Finas láminas de patata al horno <br> con queso gratinado.<html>";
			}
			if (tipo.equalsIgnoreCase("Guarnicion2")) {
				texto = "<html><b>Arroz pilaf:</b><br> Arroz suave cocido con especias <br> suaves y frutos secos.<html>";
			}
			if (tipo.equalsIgnoreCase("Guarnicion3")) {
				texto = "<html><b>Ensalada mixta</b><br> Lechuga, tomate, pepino, <br> zanahoria y vinagreta ligera.<html>";
			}
			if (tipo.equalsIgnoreCase("Postres")) {
				texto = "Postres";
			}
			if (tipo.equalsIgnoreCase("Postre1")) {
				texto = "<html><b>Tarta de queso con frutos rojos:</b> <br> Cremosa tarta de queso con una capa <br> de mermelada de frutos rojos.<html>";
			}
			if (tipo.equalsIgnoreCase("Postre2")) {
				texto = "<html><b>Brownie con helado de vainilla:</b> <br> Brownie tibio servido con una <br> bola de helado de vainilla.<html>";
			}
			if (tipo.equalsIgnoreCase("Bebidas")) {
				texto = "Bebidas";
			}
			if (tipo.equalsIgnoreCase("Bebida1")) {
				texto = "<html><b>Vino tinto: </b><br> Selección de vinos tintos nacionales <br> e internacionales.<html>";
			}
			if (tipo.equalsIgnoreCase("Bebida2")) {
				texto = "<html><b>Cóctel de la casa:</b><br> Creación única con ingredientes frescos.<html>";
			}
			if (tipo.equalsIgnoreCase("Precio6")) {
				texto = "7,50€";
			}
			if (tipo.equalsIgnoreCase("Precio7")) {
				texto = "5,50€";
			}
			if (tipo.equalsIgnoreCase("Precio8")) {
				texto = "6,35€";
			}
			if (tipo.equalsIgnoreCase("Precio9")) {
				texto = "8,50€";
			}
			if (tipo.equalsIgnoreCase("Precio10")) {
				texto = "4,20€";
			}
			if (tipo.equalsIgnoreCase("Precio11")) {
				texto = "7,00€";
			}
			if (tipo.equalsIgnoreCase("Precio12")) {
				texto = "10,00€";
			}
			
			// TEXTO DE LOS JDIALOGS
			if (tipo.equalsIgnoreCase("Informacion")) {
				texto = "Información";
			}
			if (tipo.equalsIgnoreCase("Error")) {
				texto = "Error";
			}
			if (tipo.equalsIgnoreCase("Exito")) {
				texto = "Éxito";
			}
			if (tipo.equalsIgnoreCase("!DniYHabitacion")) {
				texto = "El número de habitación y el DNI no coinciden.";
			}
			if (tipo.equalsIgnoreCase("!Dni")) {
				texto = "El DNI no coincide.";
			}
			if (tipo.equalsIgnoreCase("!Habitacion")) {
				texto = "El número de habitación no coincide.";
			}
			if (tipo.equalsIgnoreCase("ServiciosActualizados")) {
				texto = "Servicios encontrados y actualizados.";
			}
			if (tipo.equalsIgnoreCase("PrimeraVezServicios")) {
				texto = "Es la primera vez que utilizas los servicios. Registro creado.";
			}
			if (tipo.equalsIgnoreCase("ErrorBaseDatos")) {
				texto = "Error al consultar la base de datos: ";
			}
			if (tipo.equalsIgnoreCase("CamposVacios")) {
				texto = "Debe rellenar todos los campos";
			}
			if (tipo.equalsIgnoreCase("HabitacionNoEncontrada")) {
				texto = "La habitacion no se encuntra reservada. Porfavor intentelo de nuevo";
			}
			if (tipo.equalsIgnoreCase("HabitacionEncontrada")) {
				texto = "Habitacion encontrada";
			}
			if (tipo.equalsIgnoreCase("finalizarReserva")) {
				texto = "Reserva finalizada con éxito.";
			}
			if (tipo.equalsIgnoreCase("estaSeguroReserva")) {
				texto = "¿Está seguro de que desea finalizar la reserva?";
			}
			if (tipo.equalsIgnoreCase("Confirmacion")) {
				texto = "Confirmación";
			}
			if (tipo.equalsIgnoreCase("CampoDniVacio")) {
				texto = "Campo DNI vacío";
			}
			if (tipo.equalsIgnoreCase("CampoHabitacionVacio")) {
				texto = "Campo numero de habitacion vacío";
			}
			if (tipo.equalsIgnoreCase("ReservaDias")) {
				texto = "¿Cuántos días quiere reservar?";
			}
			if (tipo.equalsIgnoreCase("ReservaPersonas")) {
				texto = "¿Cuántos días quiere reservar?";
			}
			if (tipo.equalsIgnoreCase("ReservaParkingPlazas")) {
				texto = "¿Cuántas plazas de aparcamiento quiere?";
			}
			if (tipo.equalsIgnoreCase("Dias")) {
				texto = "día(s)";
			}
			if (tipo.equalsIgnoreCase("Plazas")) {
				texto = "plaza(s)";
			}
			if (tipo.equalsIgnoreCase("Personas")) {
				texto = "persona(s)";
			}
			if (tipo.equalsIgnoreCase("MasajeSueco")) {
				texto = "Masaje Sueco";
			}
			if (tipo.equalsIgnoreCase("MasajeDeportivo")) {
				texto = "Masaje Deportivo";
			}
			if (tipo.equalsIgnoreCase("MasajeTailandes")) {
				texto = "Masaje Tailandés";
			}
			if (tipo.equalsIgnoreCase("SpaMedio")) {
				texto = "Ritual spa medio día";
			}
			if (tipo.equalsIgnoreCase("SpaCompleto")) {
				texto = "Ritual spa día completo";
			}
			if (tipo.equalsIgnoreCase("Baños")) {
				texto = "Baños de inmersión";
			}
			if (tipo.equalsIgnoreCase("MesasEstandar")) {
				texto = "Mesas estándar";
			}
			if (tipo.equalsIgnoreCase("MesasPrivadas")) {
				texto = "Mesas privadas";
			}
			if (tipo.equalsIgnoreCase("MenusEspeciales")) {
				texto = "Menús especiales";
			}
			if (tipo.equalsIgnoreCase("EventosyCelebraciones")) {
				texto = "Eventos y celebraciones";
			}
			if (tipo.equalsIgnoreCase("ExperienciaGastronomica")) {
				texto = "Experiencia gastronómica";
			}
			if (tipo.equalsIgnoreCase("AireLibre")) {
				texto = "Al aire libre";
			}
			if (tipo.equalsIgnoreCase("Aparcamiento")) {
				texto = "Aparcamiento";
			}
			if (tipo.equalsIgnoreCase("ConfirmarServicios")) {
				texto = "¿Desea confirmar estos servicios? Una vez confirmado, no podrá modificarlos.";
			}
			if (tipo.equalsIgnoreCase("Confirmar")) {
				texto = "Confirmar Servicios";
			}
			break;
		case 2:
			// TEXTO EN LA VENTANA PRINCIPAL
			if (tipo.equalsIgnoreCase("TituloHome")) {
				texto = "<html><center>California<br>Hotel</center><html>";
			}
			if (tipo.equalsIgnoreCase("TituloCheck-In")) {
				texto = "Check-In";
			}
			if (tipo.equalsIgnoreCase("Credenciales")) {
				texto = "© 2024 Hotel California. All rights reserved.";
			}
			// TEXTO EN LA VENTANA REGISTRO
			if (tipo.equalsIgnoreCase("TituloReserva")) {
				texto = "Check-In";
			}
			if (tipo.equalsIgnoreCase("EncabezadoIdentificación")) {
				texto = "IDENTIFICATION:";
			}
			if (tipo.equalsIgnoreCase("Nombre")) {
				texto = "Name:";
			}
			if (tipo.equalsIgnoreCase("Apellidos")) {
				texto = "Surename:";
			}
			if (tipo.equalsIgnoreCase("DocumentoIdentificador")) {
				texto = "DNI/Pasaport:";
			}
			if (tipo.equalsIgnoreCase("Dias")) {
				texto = "Nº Days:";
			}
			if (tipo.equalsIgnoreCase("SelecHabitaciones")) {
				texto = "ROOMS SELECTOR:";
			}
			if (tipo.equalsIgnoreCase("EncabezadoCamas")) {
				texto = "Types of Beds:";
			}
			if (tipo.equalsIgnoreCase("EncabezadoVistas")) {
				texto = "Types of Views:";
			}
			if (tipo.equalsIgnoreCase("Upgrade")) {
				texto = "<html>If you wish to upgrade your room, click on the 'UPGRADE' button<br>"
						+ "(Remember that for each upgrade, additional costs will be added).</html>";
			}
			if (tipo.equalsIgnoreCase("PoliticaPrivacidad")) {
				texto = "PRIVACY POLITICS:";
			}
			if (tipo.equalsIgnoreCase("textoPoliticas")) {
				texto = "<html>Privacy Policy:<br>"
						+ "By clicking 'Accept', you confirm that you have read and agree to our <br>"
						+ "[Terms of Service] and [Privacy Policy].</html>";
			}
			if (tipo.equalsIgnoreCase("MasInfo")) {
				texto = "<html><u>More Information</u></html>";
			}
			if (tipo.equalsIgnoreCase("MessageHabitaciones")) {
				texto = "There are no rooms available with those features. Please choose another combination.";
			}
			if (tipo.equalsIgnoreCase("MessagePoliticas")) {
				texto = "To proceed, you must accept the privacy policies.";
			}
			if (tipo.equalsIgnoreCase("MessageCampos")) {
				texto = "Please fill in all the fields.";
			}
			// TEXTO EN LA VENTANA UPGRADE
			if (tipo.equalsIgnoreCase("TituloUpgrade")) {
				texto = "Upgrades";
			}
			if (tipo.equalsIgnoreCase("EncabezadoMejoras")) {
				texto = "Improvements in Furnishings:";
			}
			if (tipo.equalsIgnoreCase("EncabezadoAmenidades")) {
				texto = "Luxury Amenities:";
			}
			// TEXTO EN LA VENTANA DE POLITICAS Y PRIVACIDAD
			if (tipo.equalsIgnoreCase("TituloPolitica")) {
				texto = "Terms and Conditions";
			}
			if (tipo.equalsIgnoreCase("textoPoliticaCompleta")) {
				texto = "<html><strong>Terms and Conditions</strong><br>" + "<u>Usage of the Service:</u><br>"
						+ "Users must use the service legally and in accordance with the established policies.<br>"
						+ "The use of the service for illegal or unauthorized activities is prohibited.<br>"
						+ "<u>Intellectual Property:</u><br>"
						+ "All content, trademarks, and copyrights belong to the service owner.<br>"
						+ "Users may not reproduce, distribute, or modify the content without permission.<br>"
						+ "<u>Limitation of Liability:</u><br>"
						+ "The service owner is not responsible for direct or indirect damages arising from the use of the service.<br>"
						+ "Users are advised to take precautions when using the service.<br>"
						+ "<u>Modifications:</u><br>"
						+ "The service owner reserves the right to modify the terms and conditions at any time.<br>"
						+ "Users will be notified of changes and must accept the new terms to continue using the service.<br>"
						+ "<br><strong>Privacy Policy</strong><br>" + "<u>Data Collection:</u><br>"
						+ "Personal data such as name, email address, and IP address are collected.<br>"
						+ "The data is used to improve the service and personalize the user experience.<br>"
						+ "<u>Use of the Data:</u><br>"
						+ "The data is used for communications, promotions, and internal analysis.<br>"
						+ "Data is not shared with third parties without the user's consent, except in legal cases.<br>"
						+ "<u>User Rights:</u><br>"
						+ "Users have the right to access, rectify, and delete their personal data.<br>"
						+ "They can withdraw their consent for the use of their data at any time.<br>"
						+ "<u>Data Security:</u><br>"
						+ "Security measures are implemented to protect personal data against unauthorized access.<br>"
						+ "Regular audits are conducted to ensure information security.<br>"
						+ "<u>Cookies and Similar Technologies:</u><br>"
						+ "Cookies are used to improve site functionality and analyze user behavior.<br>"
						+ "Users can manage their cookie preferences through their browser settings. </html>";
			}
			// TEXTO EN LA VENTANA CONFIRMAR RESERVA
			if (tipo.equalsIgnoreCase("TituloPrecio")) {
				texto = "Room Price";
			}
			if (tipo.equalsIgnoreCase("Detalle")) {
				texto = "Detail:";
			}
			if (tipo.equalsIgnoreCase("CamaSelec")) {
				texto = estado.getTipoCamaSeleccionada();
			}
			if (tipo.equalsIgnoreCase("VistasSelec")) {
				texto = estado.getTipoVistaSeleccionada();
			}
			if (tipo.equalsIgnoreCase("MejorasMobiliario")) {
				if (estado.getMejorasSeleccionadas() != null) {
					texto = estado.getMejorasSeleccionadas();
				}
			}
			if (tipo.equalsIgnoreCase("MejorasAmenidades")) {
				if (estado.getAmenidadesSeleccionadas() != null) {
					texto = estado.getAmenidadesSeleccionadas();
				}
			}
			if (tipo.equalsIgnoreCase("MessageReservada")) {
				texto = "Room reserved. Your room number is: ";
			}

			// TEXTO EN LA VENTANA CHECK-OUT
			if (tipo.equalsIgnoreCase("TituloSalida")) {
				texto = "Check-Out";
			}
			if (tipo.equalsIgnoreCase("NumHabitacion")) {
				texto = "Room's number:";
			}
			if (tipo.equalsIgnoreCase("Detalle")) {
				texto = "Detail:";
			}

			// TEXTO EN LA VENTANA SERVICIOS CONTRATADOS
			if (tipo.equalsIgnoreCase("TituloServicios")) {
				texto = "<html><center>Contracted<br>Services</center><html>";
			}
			if (tipo.equalsIgnoreCase("NumeroDNI")) {
				texto = "Titular's DNI";
			}
			if (tipo.equalsIgnoreCase("TituloAparcamiento")) {
				texto = "PARKING ($25/day)";
			}
			if (tipo.equalsIgnoreCase("TituloMasajes")) {
				texto = "MASSAGES TUESDAY AND FRIDAY (11:00 AM - 1:00 PM)";
			}
			if (tipo.equalsIgnoreCase("TituloSpa")) {
				texto = "SPA";
			}
			if (tipo.equalsIgnoreCase("TituloRestaurante")) {
				texto = "RESTAURANT";
			}
			if (tipo.equalsIgnoreCase("TituloServicioHabitaciones")) {
				texto = "ROOM SERVICE";
			}
			if (tipo.equalsIgnoreCase("InfoMenu")) {
				texto = "To view the menu that can be offered to your rooms with prices, click 'MENU'";
			}

			// TEXTO EN LA VENTANA MENU
			if (tipo.equalsIgnoreCase("TituloMenu")) {
				texto = "Menu";
			}
			if (tipo.equalsIgnoreCase("Entrantes")) {
				texto = "Appetizers";
			}
			if (tipo.equalsIgnoreCase("Entrante1")) {
				texto = "<html><b>Caprese Salad:</b> <br> Fresh tomatoes, mozzarella, <br> basil, and a touch of olive oil <br> and balsamic vinegar.</html>\r\n";
			}
			if (tipo.equalsIgnoreCase("Entrante2")) {
				texto = "<html><b>Pumpkin Soup:</b> <br> Creamy pumpkin soup with <br> ginger and a pinch of nutmeg.</html>";
			}
			if (tipo.equalsIgnoreCase("Entrante3")) {
				texto = "<html><b>Tuna Tartare:</b><br> Fresh tuna with avocado <br> and soy sesame dressing.</html>";
			}
			if (tipo.equalsIgnoreCase("Principales")) {
				texto = "Main Courses";
			}
			if (tipo.equalsIgnoreCase("Principal1")) {
				texto = "<html><b>Grilled Beef Tenderloin:</b> <br> With red wine reduction and <br> rosemary potatoes as a side dish.</html>";
			}
			if (tipo.equalsIgnoreCase("Principal2")) {
				texto = "<html><b>Seafood Paella:</b><br> Rice with shrimp, mussels, <br> clams, and squid.</html>";
			}
			if (tipo.equalsIgnoreCase("Precio1")) {
				texto = "7.50$";
			}
			if (tipo.equalsIgnoreCase("Precio2")) {
				texto = "6.50$";
			}
			if (tipo.equalsIgnoreCase("Precio3")) {
				texto = "15.00$";
			}
			if (tipo.equalsIgnoreCase("Precio4")) {
				texto = "27.00$";
			}
			if (tipo.equalsIgnoreCase("Precio5")) {
				texto = "25.50$";
			}
			if (tipo.equalsIgnoreCase("Nada")) {
				texto = "";
			}

			// TEXTO EN LA VENTANA MENU2
			if (tipo.equalsIgnoreCase("Guarniciones")) {
				texto = "<html>Side Dishes<html>";
			}
			if (tipo.equalsIgnoreCase("Guarnicion1")) {
				texto = "<html><b>Gratinated Potatoes:</b><br> Thin slices of potato baked <br> with melted cheese.</html>";
			}
			if (tipo.equalsIgnoreCase("Guarnicion2")) {
				texto = "<html><b>Pilaf Rice:</b><br> Soft rice cooked with mild spices <br> and nuts.</html>";
			}
			if (tipo.equalsIgnoreCase("Guarnicion3")) {
				texto = "<html><b>Mixed Salad:</b><br> Lettuce, tomato, cucumber, <br> carrot, and light vinaigrette.</html>";
			}
			if (tipo.equalsIgnoreCase("Postres")) {
				texto = "Desserts";
			}
			if (tipo.equalsIgnoreCase("Postre1")) {
				texto = "<html><b>Cheesecake with Red Berries:</b> <br> Creamy cheesecake with a layer <br> of red berry jam.</html>";
			}
			if (tipo.equalsIgnoreCase("Postre2")) {
				texto = "<html><b>Brownie with Vanilla Ice Cream:</b> <br> Warm brownie served with a <br> scoop of vanilla ice cream.</html>";
			}
			if (tipo.equalsIgnoreCase("Bebidas")) {
				texto = "Drinks";
			}
			if (tipo.equalsIgnoreCase("Bebida1")) {
				texto = "<html><b>Red Wine:</b><br> Selection of national and international red wines.</html>";
			}
			if (tipo.equalsIgnoreCase("Bebida2")) {
				texto = "<html><b>House's Cocktail:</b><br> Unique creation with fresh ingredients.</html>";
			}
			if (tipo.equalsIgnoreCase("Precio6")) {
				texto = "7.50$";
			}
			if (tipo.equalsIgnoreCase("Precio7")) {
				texto = "5.50$";
			}
			if (tipo.equalsIgnoreCase("Precio8")) {
				texto = "6.35$";
			}
			if (tipo.equalsIgnoreCase("Precio9")) {
				texto = "8.50$";
			}
			if (tipo.equalsIgnoreCase("Precio10")) {
				texto = "4.20$";
			}
			if (tipo.equalsIgnoreCase("Precio11")) {
				texto = "7.00$";
			}
			if (tipo.equalsIgnoreCase("Precio12")) {
				texto = "10.00$";
			}
			
			
			// TEXTOS DE LOS JDIALOGS
			if (tipo.equalsIgnoreCase("Informacion")) {
			    texto = "Information";
			}
			if (tipo.equalsIgnoreCase("Error")) {
			    texto = "Error";
			}
			if (tipo.equalsIgnoreCase("Exito")) {
			    texto = "Success";
			}
			if (tipo.equalsIgnoreCase("!DniYHabitacion")) {
			    texto = "The room number and ID do not match.";
			}
			if (tipo.equalsIgnoreCase("!Dni")) {
			    texto = "The ID does not match.";
			}
			if (tipo.equalsIgnoreCase("!Habitacion")) {
			    texto = "The room number does not match.";
			}
			if (tipo.equalsIgnoreCase("ServiciosActualizados")) {
			    texto = "Services found and updated.";
			}
			if (tipo.equalsIgnoreCase("PrimeraVezServicios")) {
			    texto = "This is the first time you use the services. Record created.";
			}
			if (tipo.equalsIgnoreCase("ErrorBaseDatos")) {
			    texto = "Error querying the database: ";
			}
			if (tipo.equalsIgnoreCase("CamposVacios")) {
			    texto = "All fields must be filled out.";
			}
			if (tipo.equalsIgnoreCase("HabitacionNoEncontrada")) {
			    texto = "The room is not reserved. Please try again.";
			}
			if (tipo.equalsIgnoreCase("HabitacionEncontrada")) {
			    texto = "Room found.";
			}
			if (tipo.equalsIgnoreCase("finalizarReserva")) {
			    texto = "Reservation successfully completed.";
			}
			if (tipo.equalsIgnoreCase("estaSeguroReserva")) {
			    texto = "Are you sure you want to complete the reservation?";
			}
			if (tipo.equalsIgnoreCase("Confirmacion")) {
			    texto = "Confirmation";
			}
			if (tipo.equalsIgnoreCase("CampoDniVacio")) {
			    texto = "ID field is empty.";
			}
			if (tipo.equalsIgnoreCase("CampoHabitacionVacio")) {
			    texto = "Room number field is empty.";
			}
			if (tipo.equalsIgnoreCase("ReservaDias")) {
			    texto = "How many days would you like to reserve?";
			}
			if (tipo.equalsIgnoreCase("ReservaPersonas")) {
			    texto = "How many people are you reserving for?";
			}
			if (tipo.equalsIgnoreCase("ReservaParkingPlazas")) {
			    texto = "How many parking spaces would you like?";
			}
			if (tipo.equalsIgnoreCase("Dias")) {
			    texto = "day(s)";
			}
			if (tipo.equalsIgnoreCase("Plazas")) {
			    texto = "space(s)";
			}
			if (tipo.equalsIgnoreCase("Personas")) {
			    texto = "person(s)";
			}
			if (tipo.equalsIgnoreCase("MasajeSueco")) {
			    texto = "Swedish Massage";
			}
			if (tipo.equalsIgnoreCase("MasajeDeportivo")) {
			    texto = "Sports Massage";
			}
			if (tipo.equalsIgnoreCase("MasajeTailandes")) {
			    texto = "Thai Massage";
			}
			if (tipo.equalsIgnoreCase("SpaMedio")) {
			    texto = "Half-day spa ritual";
			}
			if (tipo.equalsIgnoreCase("SpaCompleto")) {
			    texto = "Full-day spa ritual";
			}
			if (tipo.equalsIgnoreCase("Baños")) {
			    texto = "Immersion baths";
			}
			if (tipo.equalsIgnoreCase("MesasEstandar")) {
			    texto = "Standard tables";
			}
			if (tipo.equalsIgnoreCase("MesasPrivadas")) {
			    texto = "Private tables";
			}
			if (tipo.equalsIgnoreCase("MenusEspeciales")) {
			    texto = "Special menus";
			}
			if (tipo.equalsIgnoreCase("EventosyCelebraciones")) {
			    texto = "Events and celebrations";
			}
			if (tipo.equalsIgnoreCase("ExperienciaGastronomica")) {
			    texto = "Gastronomic experience";
			}
			if (tipo.equalsIgnoreCase("AireLibre")) {
			    texto = "Outdoor";
			}
			if (tipo.equalsIgnoreCase("Aparcamiento")) {
			    texto = "Parking";
			}
			if (tipo.equalsIgnoreCase("Aparcamiento")) {
			    texto = "Parking";
			}
			if (tipo.equalsIgnoreCase("ConfirmarServicios")) {
			    texto = "Do you want to confirm these services? Once confirmed, you will not be able to modify them.";
			}
			if (tipo.equalsIgnoreCase("Confirmar")) {
			    texto = "Confirm Services";
			}
			break;
		}
		return texto;
	}

	// Para poder comprobar luego los datos seleccionados con la bbdd
	public static String traducirTexto(String texto, boolean deInglesAEspanol) {
		Map<String, String> traducciones = new HashMap<>();

		// Traducción de camas
		traducciones.put("Matrimonial", "Matrimonial");
		traducciones.put("Two individual", "Dos separadas");
		traducciones.put("One bed", "Individual");

		// Traducción de vistas
		traducciones.put("Garden", "Jardín");
		traducciones.put("Panorama", "Panorámica");
		traducciones.put("Courtyard", "Interior");

		if (!deInglesAEspanol) {
			Map<String, String> invertido = new HashMap<>();
			for (Map.Entry<String, String> entrada : traducciones.entrySet()) {
				invertido.put(entrada.getValue(), entrada.getKey());
			}
			traducciones = invertido;
		}

		return traducciones.getOrDefault(texto, texto);
	}

}