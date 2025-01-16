package base_datos;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import estilos.EstiloCheckBox;
import estilos.EstiloRadioBotones;
import estilos.MultiIdioma;
import ventanas.Upgrades;

public class GestionHotelBBDD {
	private static Connection conexion;

	public void crearBaseDatos() {
		Statement declaracion = null;
		try {
			// Conexion inicial
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			declaracion = conexion.createStatement();

			// Buscar si existe la base de datos y si no, la crea
			String nombreBaseDatos = "hotel_california";
			declaracion.executeUpdate("CREATE DATABASE IF NOT EXISTS " + nombreBaseDatos);

			declaracion.close();
			conexion.close();

			// Conectar a la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nombreBaseDatos, "root", "");
			System.out.println("Conectado a la base de datos " + nombreBaseDatos);
		} catch (SQLException e) {
			System.out.println("ERROR BBDD: " + e.getMessage());
		}
	}

	// Crear la tabla clientes
	public void crearTablaClientes() {
		try (Statement declaracion = conexion.createStatement()) {
			String sql = """
					CREATE TABLE IF NOT EXISTS clientes (
					documento_legal VARCHAR(20) NOT NULL,
					nombre VARCHAR(50) NOT NULL,
					apellidos VARCHAR(100) NOT NULL,
					CONSTRAINT pk_cliente PRIMARY KEY (documento_legal)
					);""";
			declaracion.execute(sql);
			System.out.println("Tabla clientes creada");
		} catch (SQLException e) {
			System.out.println("ERROR BBDD: " + e.getMessage());
		}
	}

	// Crear la tabla Habitaciones
	public void crearTablaHabitaciones() {
		try (Statement declaracion = conexion.createStatement()) {
			String sql = """
					CREATE TABLE IF NOT EXISTS habitaciones (
					num_habitacion INTEGER,
					tipo_cama VARCHAR(50) NOT NULL,
					tipo_vista VARCHAR(50) NOT NULL,
					precio DECIMAL(10, 2) NOT NULL,
					disponibilidad BOOLEAN DEFAULT TRUE,
					CONSTRAINT pk_habitacion PRIMARY KEY (num_habitacion)
					);""";
			declaracion.execute(sql);
			System.out.println("Tabla habitaciones creada");
		} catch (SQLException e) {
			System.out.println("ERROR BBDD: " + e.getMessage());
		}
	}

	// Crear la tabla Reservas
	public void crearTablaReservas() {
		try (Statement declaracion = conexion.createStatement()) {
			String sql = """
					CREATE TABLE IF NOT EXISTS reservas (
					numero_habitacion INTEGER,
					documento_cliente VARCHAR(20),
					dias_reserva INTEGER NOT NULL,
					cuna BOOLEAN,
					cama_supletoria BOOLEAN,
					pijamas_albornoces BOOLEAN,
					maquina_cafe BOOLEAN,
					caja_fuerte BOOLEAN,
					router BOOLEAN,
					minibar BOOLEAN,
					precio_total DOUBLE(10,2) NOT NULL,
					CONSTRAINT pk_reservas PRIMARY KEY (numero_habitacion, documento_cliente),
					CONSTRAINT fk_habitacion FOREIGN KEY (numero_habitacion) REFERENCES habitaciones(num_habitacion) ON DELETE CASCADE,
					CONSTRAINT fk_cliente FOREIGN KEY (documento_cliente) REFERENCES clientes(documento_legal) ON DELETE CASCADE
					);""";
			declaracion.execute(sql);
			System.out.println("Tabla reservas creada");
		} catch (SQLException e) {
			System.out.println("ERROR BBDD: " + e.getMessage());
		}
	}

	// Crear la tabla Servicios
	public void crearTablaServicios() {
		try (Statement declaracion = conexion.createStatement()) {
			String sql = """
					CREATE TABLE IF NOT EXISTS servicios (
					numero_habitacion INTEGER NOT NULL,
					dni_cliente VARCHAR(9) NOT NULL,
					parking BOOLEAN,
					masaje_sueco BOOLEAN,
					masaje_deportivo BOOLEAN,
					masaje_tailandes BOOLEAN,
					spa_medio_dia BOOLEAN,
					spa_dia_entero BOOLEAN,
					baños_innmersion BOOLEAN,
					mesas_estandar BOOLEAN,
					mesas_privadas BOOLEAN,
					menus_especiales BOOLEAN,
					eventos_y_celebraciones BOOLEAN,
					experiencia_gastronomica BOOLEAN,
					al_aire_libre BOOLEAN,
					precio DOUBLE,
					CONSTRAINT pk_servicios PRIMARY KEY (numero_habitacion, dni_cliente),
					CONSTRAINT fk_servicios_habitacion FOREIGN KEY (numero_habitacion) REFERENCES habitaciones(num_habitacion) ON DELETE CASCADE,
					CONSTRAINT fk_servicios_cliente FOREIGN KEY (dni_cliente) REFERENCES clientes(documento_legal) ON DELETE CASCADE
					);""";
			declaracion.execute(sql);
			System.out.println("Tabla servicios creada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Instertar los datos iniciales de la tabla
	public void insertarHabitaciones() {
		try (Statement declaracion = conexion.createStatement()) {
			String secuenciaScript = new String(Files.readAllBytes(Paths.get("./insertar_habitaciones.sql")));
			declaracion.execute(secuenciaScript);
			System.out.println("Habitaciones insertadas");
		} catch (Exception e) {
			if (e.getMessage().contains("Duplicate entry")) {
				System.out.println("Error: Los datos ya están insertados en la tabla.");
			} else {
				e.printStackTrace();
			}
		}
	}

	// Insertar en la tabla clientes los clientes
	public void insertarCliente(String dni, String nombre, String apellidos) {
		String sql = "INSERT INTO clientes (documento_legal, nombre, apellidos) VALUES (?, ?, ?)";
		try (PreparedStatement declaracion = conexion.prepareStatement(sql)) {
			declaracion.setString(1, dni);
			declaracion.setString(2, nombre);
			declaracion.setString(3, apellidos);
			declaracion.executeUpdate();
			System.out.println("Cliente insertado");
		} catch (SQLException e) {
			System.out.println("ERROR BBDD: " + e.getMessage());
		}
	}

	// Insertar en la tabla reservas la reserva realizada
	public void insertarReserva(String dni, int numHabitacion, int dias, boolean cuna, boolean camaSupletoria,
			boolean pijamasAlbornoces, boolean maquinaCafe, boolean cajaFuerte, boolean router, boolean minibar,
			double precioTotal) {
		String sql = "INSERT INTO reservas (numero_habitacion, documento_cliente, dias_reserva, cuna, cama_supletoria, pijamas_albornoces, maquina_cafe, caja_fuerte, router, minibar, precio_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement declaracion = conexion.prepareStatement(sql)) {
			// Buscar el número de habitación correspondiente basado en el tipo de cama y
			// vista
			declaracion.setInt(1, numHabitacion);
			declaracion.setString(2, dni);
			declaracion.setInt(3, dias);
			declaracion.setInt(4, cuna ? 1 : 0);
			declaracion.setInt(5, camaSupletoria ? 1 : 0);
			declaracion.setInt(6, pijamasAlbornoces ? 1 : 0);
			declaracion.setInt(7, maquinaCafe ? 1 : 0);
			declaracion.setInt(8, cajaFuerte ? 1 : 0);
			declaracion.setInt(9, router ? 1 : 0);
			declaracion.setInt(10, minibar ? 1 : 0);
			declaracion.setDouble(11, precioTotal);
			int filasInsertadas = declaracion.executeUpdate();
			if (filasInsertadas > 0) {
				System.out.println("Reserva insertada correctamente para la habitación " + numHabitacion);
				marcarHabitacionComoOcupada(numHabitacion);
			} else {
				System.out.println("Error: No se pudo insertar la reserva.");
			}

			// Marcar la habitación como ocupada
			marcarHabitacionComoOcupada(numHabitacion);

		} catch (SQLException e) {
			System.out.println("ERROR BBDD: " + e.getMessage());
		}
	}

	public boolean existeDocumentoCliente(String documento) {
		boolean existe = false;
		String query = "SELECT COUNT(*) FROM clientes WHERE documento_legal = ?";
		try (PreparedStatement declaracion = conexion.prepareStatement(query)) {
			declaracion.setString(1, documento);
			ResultSet resultado = declaracion.executeQuery();
			// Verificar si ya existe un cliente con el mismo documento
			if (resultado.next() && resultado.getInt(1) > 0) {
				existe = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}

	// Buscar número de la Habitacion
	public int getNumeroHabitacion(String tipoCama, String tipoVista) {
		String sql = "SELECT num_habitacion FROM habitaciones WHERE tipo_cama = ? AND tipo_vista = ? AND disponibilidad = TRUE";
		try (PreparedStatement declaracion = conexion.prepareStatement(sql)) {
			declaracion.setString(1, tipoCama);
			declaracion.setString(2, tipoVista);
			ResultSet resultado = declaracion.executeQuery();
			if (resultado.next()) {
				return resultado.getInt("num_habitacion");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // Indicar que no se encontró
	}

	// Método para verificar si una habitación está ocupada
	public boolean estaOcupada(int numHabitacion) {
		String sql = "SELECT ocupada FROM habitaciones WHERE num_habitacion = ?";
		try (PreparedStatement declaracion = conexion.prepareStatement(sql)) {
			declaracion.setInt(1, numHabitacion);
			ResultSet resultado = declaracion.executeQuery();
			if (resultado.next()) {
				return resultado.getBoolean("disponibilidad");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // Si no se encuentra, asumimos que no está ocupada
	}

	// Método para marcar la habitación como ocupada
	public void marcarHabitacionComoOcupada(int numHabitacion) {
		String sql = "UPDATE habitaciones SET disponibilidad = FALSE WHERE num_habitacion = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, numHabitacion); // Número de habitación
			stmt.executeUpdate();
			System.out.println("Habitación marcada como ocupada");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al marcar la habitación como ocupada");
		}
	}

	// Calcular el precio total de la reserva
	public double calcularPrecioTotal(int numeroHabitacion, boolean seUsaronUpgrades, int dias) {
		double precioBase = 0.0;
		double costoMejoras = 0.0;
		double costoAmenidades = 0.0;
		double precioTotal = 0.0;

		// Consultar el precio base de la habitación
		String sqlHabitacion = "SELECT precio FROM habitaciones WHERE num_habitacion = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sqlHabitacion)) {
			stmt.setInt(1, numeroHabitacion);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				precioBase = rs.getDouble("precio");
			} else {
				System.out.println("Error: No se encontró la habitación con el número: " + numeroHabitacion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al obtener el precio base de la habitación.");
			return -1; // Retorna un valor de error en caso de fallo
		}

		System.out.println("Precio base de la habitación: " + precioBase);
		System.out.println("Días seleccionados: " + dias);

		if (seUsaronUpgrades) {
			// Inicializar precios de mejoras
			Map<String, Double> preciosMejoras = new HashMap<>();
			preciosMejoras.put("Cuna", 10.0);
			preciosMejoras.put("Supletoria", 20.0);
			preciosMejoras.put("Vestimenta", 15.0);

			// Inicializar precios de amenidades
			Map<String, Double> preciosAmenidades = new HashMap<>();
			preciosAmenidades.put("Cafe", 5.0);
			preciosAmenidades.put("Caja", 7.0);
			preciosAmenidades.put("Router", 8.0);
			preciosAmenidades.put("Nevera", 12.0);

			// Calcular costo de mejoras seleccionadas
			if (Upgrades.cuna != null && Upgrades.cuna.isSelected()) {
				costoMejoras += preciosMejoras.get("Cuna");
			}
			if (Upgrades.supletoria != null && Upgrades.supletoria.isSelected()) {
				costoMejoras += preciosMejoras.get("Supletoria");
			}
			if (Upgrades.ropa != null && Upgrades.ropa.isSelected()) {
				costoMejoras += preciosMejoras.get("Vestimenta");
			}

			System.out.println("Costo de mejoras: " + costoMejoras);

			// Calcular costo de amenidades seleccionadas
			if (Upgrades.maquina != null && Upgrades.maquina.isSelected()) {
				costoAmenidades += preciosAmenidades.get("Cafe");
			}
			if (Upgrades.caja != null && Upgrades.caja.isSelected()) {
				costoAmenidades += preciosAmenidades.get("Caja");
			}
			if (Upgrades.router != null && Upgrades.router.isSelected()) {
				costoAmenidades += preciosAmenidades.get("Router");
			}
			if (Upgrades.minibar != null && Upgrades.minibar.isSelected()) {
				costoAmenidades += preciosAmenidades.get("Nevera");
			}

			System.out.println("Costo de amenidades: " + costoAmenidades);
		} else {
			System.out.println("No se seleccionaron upgrades.");
		}

		// Calcular precio total
		precioTotal = (precioBase + costoMejoras + costoAmenidades) * dias;
		return precioTotal;

	}

	// Método para verificar si un número de habitación está en la tabla reservas y
		// actualizar checkboxes
		public boolean verificarYActualizarReservas(int numHabitacion, EstiloCheckBox checkBoxMobiliario,
				EstiloCheckBox checkBoxAmenidades) {
			String sql = "SELECT * FROM reservas WHERE numero_habitacion = ?";

			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setInt(1, numHabitacion);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					System.out.println("La habitación está en reservas");

					// Actualizar checkbox Mobiliario
					if (rs.getBoolean("cuna") || rs.getBoolean("cama_supletoria") || rs.getBoolean("pijamas_albornoces")) {
						checkBoxMobiliario.setSelected(true);
						checkBoxMobiliario.setEnabled(false);
					} else {
						checkBoxMobiliario.setSelected(false);
					}

					// Actualizar checkbox Amenidades
					if (rs.getBoolean("maquina_cafe") || rs.getBoolean("router") || rs.getBoolean("minibar")
							|| rs.getBoolean("caja_fuerte")) {
						checkBoxAmenidades.setSelected(true);
						checkBoxAmenidades.setEnabled(false);
					} else {
						checkBoxAmenidades.setSelected(false);
					}

					return true; // La habitación tiene una reserva registrada
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al verificar la habitación en reservas");
			}

			System.out.println("La habitación no está en reservas");
			return false; // La habitación no tiene reserva registrada
		}

		// Método para verificar si un número de habitación está en la tabla servicios y
		// actualizar checkboxes
		public boolean verificarYActualizarServicios(int numHabitacion, EstiloCheckBox checkBoxParking,
				EstiloCheckBox checkBoxMasaje, EstiloCheckBox checkBoxSpa, EstiloCheckBox checkBoxRestaurante) {
			String sql = "SELECT * FROM servicios WHERE numero_habitacion = ?";

			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setInt(1, numHabitacion);
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					System.out.println("La habitación está en servicios");

					// Actualizar checkboxes según los datos de la habitación
					checkBoxParking.setSelected(rs.getBoolean("parking"));
					checkBoxParking.setEnabled(false);

					// Servicios de masajes
					if (rs.getBoolean("masaje_sueco") || rs.getBoolean("masaje_deportivo")
							|| rs.getBoolean("masaje_tailandes")) {
						checkBoxMasaje.setSelected(true);
						checkBoxMasaje.setEnabled(false);
					} else {
						checkBoxMasaje.setSelected(false);
					}

					// Servicios de spa
					if (rs.getBoolean("spa_medio_dia") || rs.getBoolean("spa_dia_entero")
							|| rs.getBoolean("baños_innmersion")) {
						checkBoxSpa.setSelected(true);
						checkBoxSpa.setEnabled(false);
					} else {
						checkBoxSpa.setSelected(false);
					}

					// Servicios de restaurante
					if (rs.getBoolean("mesas_estandar") || rs.getBoolean("mesas_privadas")
							|| rs.getBoolean("menus_especiales") || rs.getBoolean("eventos_y_celebraciones")
							|| rs.getBoolean("experiencia_gastronomica") || rs.getBoolean("al_aire_libre")) {
						checkBoxRestaurante.setSelected(true);
						checkBoxRestaurante.setEnabled(false);
					} else {
						checkBoxRestaurante.setSelected(false);
					}

					return true; // La habitación tiene servicios registrados
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al verificar la habitación en servicios");
			}

			System.out.println("La habitación no está en servicios");
			return false; // La habitación no tiene servicios registrados
		}

		// Metodo para obtener el cliente segun el numero de la habitacion
		public String obtenerClientePorNumeroHabitacion(int numHabitacion) {
			String dniCliente = null;

			// Consulta SQL para obtener el DNI del cliente que se corresponde con el número
			// de habitación
			String sql = "SELECT documento_cliente FROM reservas WHERE numero_habitacion = ?";

			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setInt(1, numHabitacion); // Establecer el número de habitación en la consulta
				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					// Recuperar el DNI del cliente
					dniCliente = rs.getString("documento_cliente");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al obtener el cliente para el número de habitación");
			}

			return dniCliente;
		}

		// Método para obtener el precio de la reserva
		public double obtenerPrecioDeReserva(int numHabitacion, String dniCliente) {
			System.out.println("Se obtiene los precios");
			double precioReserva = 0.0;
			String sql = "SELECT precio_total FROM reservas WHERE numero_habitacion = ? AND documento_cliente = ?";

			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setInt(1, numHabitacion); // Número de habitación
				stmt.setString(2, dniCliente); // DNI del cliente

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					precioReserva = rs.getDouble("precio_total"); // Obtener el precio de la reserva
				}
				System.out.println(rs.getDouble("precio_total"));
				System.out.println("Se ha obtenido el precio");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al obtener el precio de la reserva");
			}

			return precioReserva;
		}

		// Método para obtener el precio de los servicios
		public double obtenerPrecioDeServicios(int numHabitacion, String dniCliente) {
			double precioServicios = 0.0;
			String sql = "SELECT precio FROM servicios WHERE numero_habitacion = ? AND dni_cliente = ?";

			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setInt(1, numHabitacion); // Número de habitación
				stmt.setString(2, dniCliente); // DNI del cliente

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					// Si existe un resultado, obtener el precio de los servicios
					precioServicios = rs.getDouble("precio");
					System.out.println("Precio de los servicios: " + precioServicios);
				} else {
					// Si no hay resultados, indicar que no se encontró el precio de los servicios
					System.out.println("No se encontraron servicios para esta habitación y cliente.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al obtener el precio de los servicios");
			}

			return precioServicios;
		}

		// Metodo para eliminar reserva
		public void eliminarReservaPorNumHabitacion(int numHabitacion) {
			String sql = "DELETE FROM reservas WHERE numero_habitacion = ?";

			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setInt(1, numHabitacion); // Establece el número de habitación en el query
				int filasAfectadas = stmt.executeUpdate(); // Ejecuta la consulta

				if (filasAfectadas > 0) {
					System.out.println("Reserva eliminada correctamente");
				} else {
					System.out.println("No se encontró una reserva con el número de habitación especificado");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al eliminar la reserva");
			}
		}

		// Método para eliminar servicios
		public void eliminarServicios(int numHabitacion, String dni) {
			String sql = "DELETE FROM servicios WHERE numero_habitacion = ? AND dni_cliente = ?";

			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setInt(1, numHabitacion); // Establece el número de habitación en el query
				stmt.setString(2, dni); // Establece el DNI del cliente

				int filasAfectadas = stmt.executeUpdate(); // Ejecuta la consulta

				if (filasAfectadas > 0) {
					System.out.println("Servicios eliminados correctamente");
				} else {
					System.out.println("No se encontró ningún registro con los datos especificados");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al eliminar los servicios");
			}
		}

		// Metodo para eliminar cliente
		public void eliminarClientePorDni(String dni) {
			String sql = "DELETE FROM clientes WHERE documento_legal = ?";

			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setString(1, dni); // Establece el DNI en el query
				int filasAfectadas = stmt.executeUpdate(); // Ejecuta la consulta

				if (filasAfectadas > 0) {
					System.out.println("Cliente eliminado correctamente");
				} else {
					System.out.println("No se encontró un cliente con el DNI especificado");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al eliminar el cliente");
			}
		}

		// Método para marcar la habitación como libre
		public void marcarHabitacionComoLibre(int numHabitacion) {
			String sql = "UPDATE habitaciones SET disponibilidad = TRUE WHERE num_habitacion = ?";

			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setInt(1, numHabitacion); // Número de habitación
				stmt.executeUpdate();
				System.out.println("Habitación marcada como libre");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error al marcar la habitación como libre");
			}
		}

		// Metodo para verificar la reserva y los servicios
		public void verificarReserva(JFrame ventanaActual, JTextField txtNumeroHabitacion, JTextField txtDniCliente,
				EstiloRadioBotones radioButtonSi, EstiloCheckBox checkBoxMasajeSueco,
				EstiloCheckBox checkBoxMasajeDeportivo, EstiloCheckBox checkBoxMasajeTailandes,
				EstiloCheckBox checkBoxSpaMedio, EstiloCheckBox checkBoxSpaCompleto, EstiloCheckBox checkBoxBaños,
				EstiloCheckBox checkBoxMesasEstandar, EstiloCheckBox checkBoxMesasPrivadas,
				EstiloCheckBox checkBoxMenusEspeciales, EstiloCheckBox checkBoxEventosyCelebraciones,
				EstiloCheckBox checkBoxExperienciaGastronomica, EstiloCheckBox checkBoxAireLibre) {

			// Obtenemos el texto de los campos de número de habitación y DNI
			String numeroHabitacion = txtNumeroHabitacion.getText().trim();
			String dniCliente = txtDniCliente.getText().trim();

			// Verificar si la reserva existe en la base de datos
			String verificarEnReservas = """
					SELECT numero_habitacion, documento_cliente
					FROM reservas
					WHERE numero_habitacion = ? AND documento_cliente = ?;
					""";

			// Verificar si el cliente ya tiene servicios asociados a la reserva
			String verificarEnServicios = """
					SELECT parking, masaje_sueco, masaje_deportivo, masaje_tailandes,
					       spa_medio_dia, spa_dia_entero, baños_innmersion, mesas_estandar,
					       mesas_privadas, menus_especiales, eventos_y_celebraciones,
					       experiencia_gastronomica, al_aire_libre
					FROM servicios
					WHERE numero_habitacion = ? AND dni_cliente = ?;
					""";

			// Insertar los servicios en caso de que no existan para la reserva
			String insertarEnServicios = """
					    INSERT INTO servicios (numero_habitacion, dni_cliente, parking, masaje_sueco, masaje_deportivo, masaje_tailandes,
					    spa_medio_dia, spa_dia_entero, baños_innmersion, mesas_estandar, mesas_privadas, menus_especiales,
					    eventos_y_celebraciones, experiencia_gastronomica, al_aire_libre, precio)
					    VALUES (?, ?, false, false, false, false, false, false, false, false, false, false, false, false, false, 0);
					""";

			try (PreparedStatement verificarReservasStmt = conexion.prepareStatement(verificarEnReservas)) {
				verificarReservasStmt.setInt(1, Integer.parseInt(numeroHabitacion));
				verificarReservasStmt.setString(2, dniCliente);

				// Ejecutamos la consulta para saber si exite la reserva y obtenemos los
				// resultados
				try (ResultSet rsReservas = verificarReservasStmt.executeQuery()) {
					boolean habitacionCoincide = false;
					boolean dniCoincide = false;

					while (rsReservas.next()) {
						if (rsReservas.getInt("numero_habitacion") == Integer.parseInt(numeroHabitacion)) {
							habitacionCoincide = true;
						}
						if (rsReservas.getString("documento_cliente").equalsIgnoreCase(dniCliente)) {
							dniCoincide = true;
						}
					}

					if (!habitacionCoincide && !dniCoincide) {
						JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("!DniYHabitacion"),
								MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
						return;
					} else if (!habitacionCoincide && dniCoincide) {
						JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("!Habitacion"),
								MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
						return;
					} else if (!dniCoincide && habitacionCoincide) {
						JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("!Dni"),
								MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						// Si ambos coinciden, verificamos los servicios asociados a esta reserva
						try (PreparedStatement verificarServiciosStmt = conexion.prepareStatement(verificarEnServicios)) {
							verificarServiciosStmt.setInt(1, Integer.parseInt(numeroHabitacion));
							verificarServiciosStmt.setString(2, dniCliente);

							try (ResultSet rsServicios = verificarServiciosStmt.executeQuery()) {
								// Si ya exiten serviciox asociados a la reserva se muestran
								if (rsServicios.next()) {
									radioButtonSi.setSelected(rsServicios.getBoolean("parking"));
									checkBoxMasajeSueco.setSelected(rsServicios.getBoolean("masaje_sueco"));
									checkBoxMasajeDeportivo.setSelected(rsServicios.getBoolean("masaje_deportivo"));
									checkBoxMasajeTailandes.setSelected(rsServicios.getBoolean("masaje_tailandes"));
									checkBoxSpaMedio.setSelected(rsServicios.getBoolean("spa_medio_dia"));
									checkBoxSpaCompleto.setSelected(rsServicios.getBoolean("spa_dia_entero"));
									checkBoxBaños.setSelected(rsServicios.getBoolean("baños_innmersion"));
									checkBoxMesasEstandar.setSelected(rsServicios.getBoolean("mesas_estandar"));
									checkBoxMesasPrivadas.setSelected(rsServicios.getBoolean("mesas_privadas"));
									checkBoxMenusEspeciales.setSelected(rsServicios.getBoolean("menus_especiales"));
									checkBoxEventosyCelebraciones
											.setSelected(rsServicios.getBoolean("eventos_y_celebraciones"));
									checkBoxExperienciaGastronomica
											.setSelected(rsServicios.getBoolean("experiencia_gastronomica"));
									checkBoxAireLibre.setSelected(rsServicios.getBoolean("al_aire_libre"));

									// Desactivar los CheckBoxes que ya son true
									radioButtonSi.setEnabled(!rsServicios.getBoolean("parking"));
									checkBoxMasajeSueco.setEnabled(!rsServicios.getBoolean("masaje_sueco"));
									checkBoxMasajeDeportivo.setEnabled(!rsServicios.getBoolean("masaje_deportivo"));
									checkBoxMasajeTailandes.setEnabled(!rsServicios.getBoolean("masaje_tailandes"));
									checkBoxSpaMedio.setEnabled(!rsServicios.getBoolean("spa_medio_dia"));
									checkBoxSpaCompleto.setEnabled(!rsServicios.getBoolean("spa_dia_entero"));
									checkBoxBaños.setEnabled(!rsServicios.getBoolean("baños_innmersion"));
									checkBoxMesasEstandar.setEnabled(!rsServicios.getBoolean("mesas_estandar"));
									checkBoxMesasPrivadas.setEnabled(!rsServicios.getBoolean("mesas_privadas"));
									checkBoxMenusEspeciales.setEnabled(!rsServicios.getBoolean("menus_especiales"));
									checkBoxEventosyCelebraciones
											.setEnabled(!rsServicios.getBoolean("eventos_y_celebraciones"));
									checkBoxExperienciaGastronomica
											.setEnabled(!rsServicios.getBoolean("experiencia_gastronomica"));
									checkBoxAireLibre.setEnabled(!rsServicios.getBoolean("al_aire_libre"));

									JOptionPane.showMessageDialog(ventanaActual,
											MultiIdioma.IdiomaTexto("ServiciosActualizados"),
											MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
								} else {
									// Insertamos una nueva fila en servicios
									try (PreparedStatement insertarServiciosStmt = conexion
											.prepareStatement(insertarEnServicios)) {
										insertarServiciosStmt.setInt(1, Integer.parseInt(numeroHabitacion));
										insertarServiciosStmt.setString(2, dniCliente);
										insertarServiciosStmt.executeUpdate();

										// Marcamos todos los CheckBoxes como falsos
										radioButtonSi.setSelected(false);
										checkBoxMasajeSueco.setSelected(false);
										checkBoxMasajeDeportivo.setSelected(false);
										checkBoxMasajeTailandes.setSelected(false);
										checkBoxSpaMedio.setSelected(false);
										checkBoxSpaCompleto.setSelected(false);
										checkBoxBaños.setSelected(false);
										checkBoxMesasEstandar.setSelected(false);
										checkBoxMesasPrivadas.setSelected(false);
										checkBoxMenusEspeciales.setSelected(false);
										checkBoxEventosyCelebraciones.setSelected(false);
										checkBoxExperienciaGastronomica.setSelected(false);
										checkBoxAireLibre.setSelected(false);

										JOptionPane.showMessageDialog(ventanaActual,
												MultiIdioma.IdiomaTexto("PrimeraVezServicios"),
												MultiIdioma.IdiomaTexto("Informacion"), JOptionPane.INFORMATION_MESSAGE);
									}
								}
							}
						}
					}
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("!Numero"),
						MultiIdioma.IdiomaTexto("Error"), JOptionPane.ERROR_MESSAGE);
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(ventanaActual, MultiIdioma.IdiomaTexto("ErrorBaseDatos") + e.getMessage(),
						MultiIdioma.IdiomaTexto("Error"), JOptionPane.ERROR_MESSAGE);
			}
		}

		public void confirmarServicios(JFrame ventanaActual, JTextField txtNumeroHabitacion, JTextField txtDniCliente,
				EstiloRadioBotones radioButtonSi, EstiloCheckBox checkBoxMasajeSueco,
				EstiloCheckBox checkBoxMasajeDeportivo, EstiloCheckBox checkBoxMasajeTailandes,
				EstiloCheckBox checkBoxSpaMedio, EstiloCheckBox checkBoxSpaCompleto, EstiloCheckBox checkBoxBaños,
				EstiloCheckBox checkBoxMesasEstandar, EstiloCheckBox checkBoxMesasPrivadas,
				EstiloCheckBox checkBoxMenusEspeciales, EstiloCheckBox checkBoxEventosyCelebraciones,
				EstiloCheckBox checkBoxExperienciaGastronomica, EstiloCheckBox checkBoxAireLibre) {

			String numeroHabitacion = txtNumeroHabitacion.getText().trim();
			String dniCliente = txtDniCliente.getText().trim();

			// Verificar si el número de habitación y el DNI están vacíos
			if (numeroHabitacion.isEmpty() || dniCliente.isEmpty()) {
				JOptionPane.showMessageDialog(ventanaActual, "El número de habitación y el DNI no pueden estar vacíos.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Convertir número de habitación a entero
			int numeroHabitacionInt;
			try {
				numeroHabitacionInt = Integer.parseInt(numeroHabitacion);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(ventanaActual,
						"Por favor, ingrese un número válido para el número de habitación.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Verificar si la reserva existe en la base de datos
			String verificarEnReservas = """
					SELECT numero_habitacion, documento_cliente
					FROM reservas
					WHERE numero_habitacion = ? AND documento_cliente = ?;
					""";

			try (PreparedStatement stmt = conexion.prepareStatement(verificarEnReservas)) {
				stmt.setInt(1, numeroHabitacionInt);
				stmt.setString(2, dniCliente);

				try (ResultSet rs = stmt.executeQuery()) {
					if (!rs.next()) {
						// La reserva no existe
						JOptionPane.showMessageDialog(
								ventanaActual, "No se encontró una reserva para la habitación " + numeroHabitacion
										+ " con el DNI " + dniCliente + ".",
								"Información", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(ventanaActual, "Error al verificar la reserva: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Verificar si al menos un servicio está seleccionado
			boolean servicioSeleccionado = radioButtonSi.isSelected() || checkBoxMasajeSueco.isSelected()
					|| checkBoxMasajeDeportivo.isSelected() || checkBoxMasajeTailandes.isSelected()
					|| checkBoxSpaMedio.isSelected() || checkBoxSpaCompleto.isSelected() || checkBoxBaños.isSelected()
					|| checkBoxMesasEstandar.isSelected() || checkBoxMesasPrivadas.isSelected()
					|| checkBoxMenusEspeciales.isSelected() || checkBoxEventosyCelebraciones.isSelected()
					|| checkBoxExperienciaGastronomica.isSelected() || checkBoxAireLibre.isSelected();

			if (!servicioSeleccionado) {
				JOptionPane.showMessageDialog(ventanaActual, "Debe seleccionar al menos un servicio antes de confirmar.",
						"Advertencia", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// Preguntar por días, personas y calcular el precio
			double precioTotal = 0;
			StringBuilder resumen = new StringBuilder();

			HashMap<String, Integer> serviciosConPersonas = new HashMap<>();
			HashMap<String, Integer> serviciosConDias = new HashMap<>();
			try {
				if (radioButtonSi.isSelected() && radioButtonSi.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("Aparcamiento"));
					if (dias != -1) {
						int plazas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaParkingPlazas"),
								MultiIdioma.IdiomaTexto("Aparcamiento"));
						if (plazas != -1) {
							serviciosConDias.put("Parking", dias);
							serviciosConPersonas.put("Parking", plazas);
							precioTotal += dias * plazas * 25;
							resumen.append(MultiIdioma.IdiomaTexto("Aparcamiento") + ":" + dias
									+ MultiIdioma.IdiomaTexto("Dias") + " x " + plazas + MultiIdioma.IdiomaTexto("Plazas")
									+ " x 25 " + MultiIdioma.IdiomaTexto("Dinero") + "= " + (dias * plazas * 25)
									+ MultiIdioma.IdiomaTexto("Dinero") + "\n");
						} else {
							radioButtonSi.setSelected(false);
							return;
						}
					} else {
						radioButtonSi.setSelected(false);
						return;
					}
				}

				if (checkBoxMasajeSueco.isSelected() && checkBoxMasajeSueco.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("MasajeSueco"));
					if (dias != -1) {
						int personas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaPersonas"),
								MultiIdioma.IdiomaTexto("MasajeSueco"));
						if (personas == -1) {
							checkBoxMasajeSueco.setSelected(false);
							return; // Volver al inicio si el cuadro de diálogo fue cerrado
						} else {
							serviciosConPersonas.put("Masaje Sueco", personas);
							serviciosConDias.put("Masaje Sueco", dias);
							precioTotal += (dias * personas * 40);
							resumen.append(MultiIdioma.IdiomaTexto("MasajeSueco") + ":" + dias
									+ MultiIdioma.IdiomaTexto("Dias") + " x " + personas
									+ MultiIdioma.IdiomaTexto("Personas") + " x 25 " + MultiIdioma.IdiomaTexto("Dinero")
									+ "= " + (dias * personas * 40) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
						}
					} else {
						checkBoxMasajeSueco.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					}
				}

				if (checkBoxMasajeDeportivo.isSelected() && checkBoxMasajeDeportivo.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("MasajeDeportivo"));
					if (dias != -1) {
						int personas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaPersonas"),
								MultiIdioma.IdiomaTexto("MasajeDeportivo"));

						if (personas == -1) {
							checkBoxMasajeDeportivo.setSelected(false);
							return; // Volver al inicio si el cuadro de diálogo fue cerrado
						} else {
							serviciosConPersonas.put("Masaje Deportivo", personas);
							serviciosConDias.put("Masaje Deportivo", dias);
							precioTotal += (dias * personas * 35);
							resumen.append(MultiIdioma.IdiomaTexto("MasajeDeportivo") + ":" + dias
									+ MultiIdioma.IdiomaTexto("Dias") + " x " + personas
									+ MultiIdioma.IdiomaTexto("Personas") + " x 35 " + MultiIdioma.IdiomaTexto("Dinero")
									+ "= " + (dias * personas * 35) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
						}
					} else {
						checkBoxMasajeDeportivo.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					}
				}

				if (checkBoxMasajeTailandes.isSelected() && checkBoxMasajeTailandes.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("MasajeTailandes"));
					if (dias != -1) {
						int personas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaPersonas"),
								MultiIdioma.IdiomaTexto("MasajeTailandes"));
						if (personas == -1) {
							checkBoxMasajeTailandes.setSelected(false);
							return; // Volver al inicio si el cuadro de diálogo fue cerrado
						} else {
							serviciosConPersonas.put("Masaje Tailandés", personas);
							serviciosConDias.put("Masaje Tailandés", dias);
							precioTotal += (dias * personas * 45);
							resumen.append(MultiIdioma.IdiomaTexto("MasajeTailandes") + ":" + dias
									+ MultiIdioma.IdiomaTexto("Dias") + " x " + personas
									+ MultiIdioma.IdiomaTexto("Personas") + " x 45 " + MultiIdioma.IdiomaTexto("Dinero")
									+ "= " + (dias * personas * 45) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
						}
					} else {
						checkBoxMasajeTailandes.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					}
				}

				if (checkBoxSpaMedio.isSelected() && checkBoxSpaMedio.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("SpaMedio"));
					if (dias != -1) {
						int personas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaPersonas"),
								MultiIdioma.IdiomaTexto("SpaMedio"));
						if (personas == -1) {
							checkBoxSpaMedio.setSelected(false);
							return; // Volver al inicio si el cuadro de diálogo fue cerrado
						} else {
							serviciosConPersonas.put("Spa Medio", personas);
							serviciosConDias.put("Spa Medio", dias);
							precioTotal += (dias * personas * 35);
							resumen.append(MultiIdioma.IdiomaTexto("SpaMedio") + ":" + dias
									+ MultiIdioma.IdiomaTexto("Dias") + " x " + personas
									+ MultiIdioma.IdiomaTexto("Personas") + " x 35 " + MultiIdioma.IdiomaTexto("Dinero")
									+ "= " + (dias * personas * 35) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
						}
					} else {
						checkBoxSpaMedio.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					}
				}

				if (checkBoxSpaCompleto.isSelected() && checkBoxSpaCompleto.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("SpaCompleto"));
					if (dias != -1) {
						int personas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaPersonas"),
								MultiIdioma.IdiomaTexto("SpaCompleto"));
						if (personas == -1) {
							checkBoxSpaCompleto.setSelected(false);
							return; // Volver al inicio si el cuadro de diálogo fue cerrado
						} else {
							serviciosConPersonas.put("Spa Completo", personas);
							serviciosConDias.put("Spa Completo", dias);
							precioTotal += (dias * personas * 75);
							resumen.append(MultiIdioma.IdiomaTexto("SpaCompleto") + ":" + dias
									+ MultiIdioma.IdiomaTexto("Dias") + " x " + personas
									+ MultiIdioma.IdiomaTexto("Personas") + " x 75 " + MultiIdioma.IdiomaTexto("Dinero")
									+ "= " + (dias * personas * 75) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
						}
					} else {
						checkBoxSpaCompleto.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					}
				}

				if (checkBoxBaños.isSelected() && checkBoxBaños.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("Baños"));
					if (dias != -1) {
						int personas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaPersonas"),
								MultiIdioma.IdiomaTexto("Baños"));
						if (personas == -1) {
							checkBoxBaños.setSelected(false);
							return; // Volver al inicio si el cuadro de diálogo fue cerrado
						} else {
							serviciosConPersonas.put("Baños de inmersion", personas);
							serviciosConDias.put("Baños de inmersion", dias);
							precioTotal += (dias * personas * 25);
							resumen.append(MultiIdioma.IdiomaTexto("Baños") + ":" + dias + MultiIdioma.IdiomaTexto("Dias")
									+ " x " + personas + MultiIdioma.IdiomaTexto("Personas") + " x 25 "
									+ MultiIdioma.IdiomaTexto("Dinero") + "= " + (dias * personas * 25)
									+ MultiIdioma.IdiomaTexto("Dinero") + "\n");
						}
					} else {
						checkBoxBaños.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					}
				}

				if (checkBoxMesasPrivadas.isSelected() && checkBoxMesasPrivadas.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("MesasPrivadas"));
					serviciosConDias.put("Mesas Privadas", dias);
					if (dias == -1) {
						checkBoxMesasPrivadas.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					} else {
						precioTotal += dias * 30;
						resumen.append(MultiIdioma.IdiomaTexto("MesasPrivadas") + ":" + dias
								+ MultiIdioma.IdiomaTexto("Dias") + " x 30 " + MultiIdioma.IdiomaTexto("Dinero") + "= "
								+ (dias * 30) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
					}
				}

				if (checkBoxMesasEstandar.isSelected() && checkBoxMesasEstandar.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("MesasEstandar"));
					if (dias == -1) {
						checkBoxMesasEstandar.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					} else {
						serviciosConDias.put("Mesas Estándar", dias);
						precioTotal += dias * 10;
						resumen.append(MultiIdioma.IdiomaTexto("MesasEstandar") + ":" + dias
								+ MultiIdioma.IdiomaTexto("Dias") + " x 10 " + MultiIdioma.IdiomaTexto("Dinero") + "= "
								+ (dias * 10) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
					}
				}

				if (checkBoxMenusEspeciales.isSelected() && checkBoxMenusEspeciales.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("MenusEspeciales"));
					if (dias != -1) {
						int personas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaPersonas"),
								MultiIdioma.IdiomaTexto("MenusEspeciales"));
						if (personas == -1) {
							checkBoxMenusEspeciales.setSelected(false);
							return; // Volver al inicio si el cuadro de diálogo fue cerrado
						} else {
							serviciosConDias.put("Menus especiales", dias);
							serviciosConPersonas.put("Menus especiales", personas);
							precioTotal += (dias * personas * 30);
							resumen.append(MultiIdioma.IdiomaTexto("MenusEspeciales") + ":" + dias
									+ MultiIdioma.IdiomaTexto("Dias") + " x " + personas
									+ MultiIdioma.IdiomaTexto("Personas") + " x 30 " + MultiIdioma.IdiomaTexto("Dinero")
									+ "= " + (dias * personas * 30) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
						}
					} else {
						checkBoxMenusEspeciales.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					}

				}

				if (checkBoxExperienciaGastronomica.isSelected() && checkBoxExperienciaGastronomica.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("ExperienciaGastronomica"));
					if (dias != -1) {
						int personas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaPersonas"),
								MultiIdioma.IdiomaTexto("ExperienciaGastronomica"));
						if (dias == -1 && personas == -1) {
							checkBoxExperienciaGastronomica.setSelected(false);
							return; // Volver al inicio si el cuadro de diálogo fue cerrado
						} else {
							serviciosConPersonas.put("Experiencia Gastronomica", personas);
							serviciosConDias.put("Experiencia Gastronomica", dias);
							precioTotal += (dias * personas * 60);
							resumen.append(MultiIdioma.IdiomaTexto("ExperienciaGastronomica") + ":" + dias
									+ MultiIdioma.IdiomaTexto("Dias") + " x " + personas
									+ MultiIdioma.IdiomaTexto("Personas") + " x 60 " + MultiIdioma.IdiomaTexto("Dinero")
									+ "= " + (dias * personas * 60) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
						}
					} else {
						checkBoxExperienciaGastronomica.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					}
				}

				if (checkBoxEventosyCelebraciones.isSelected() && checkBoxEventosyCelebraciones.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("EventosyCelebraciones"));
					if (dias != -1) {
						int personas = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaPersonas"),
								MultiIdioma.IdiomaTexto("EventosyCelebraciones"));
						if (dias == -1 && personas == -1) {
							checkBoxEventosyCelebraciones.setSelected(false);
							return; // Volver al inicio si el cuadro de diálogo fue cerrado
						} else {
							serviciosConPersonas.put("Baños de inmersion", personas);
							serviciosConDias.put("Baños de inmersion", dias);
							precioTotal += (dias * personas * 130);
							resumen.append(MultiIdioma.IdiomaTexto("EventosyCelebraciones") + ":" + dias
									+ MultiIdioma.IdiomaTexto("Dias") + " x " + personas
									+ MultiIdioma.IdiomaTexto("Personas") + " x 130 " + MultiIdioma.IdiomaTexto("Dinero")
									+ "= " + (dias * personas * 130) + MultiIdioma.IdiomaTexto("Dinero") + "\n");
						}
					} else {
						checkBoxEventosyCelebraciones.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					}

				}

				if (checkBoxAireLibre.isSelected() && checkBoxAireLibre.isEnabled()) {
					int dias = solicitarNumero(ventanaActual, MultiIdioma.IdiomaTexto("ReservaDias"),
							MultiIdioma.IdiomaTexto("AireLibre"));
					if (dias == -1) {
						checkBoxAireLibre.setSelected(false);
						return; // Volver al inicio si el cuadro de diálogo fue cerrado
					} else {
						serviciosConDias.put("Aire Libre", dias);
						precioTotal += dias * 20; // Precio por día de aire libre
						resumen.append(MultiIdioma.IdiomaTexto("AireLibre") + ":" + dias + MultiIdioma.IdiomaTexto("Dias")
								+ " x 20 " + MultiIdioma.IdiomaTexto("Dinero") + "= " + (dias * 20)
								+ MultiIdioma.IdiomaTexto("Dinero") + "\n");
						resumen.append("Aire Libre: " + dias + " día(s) x $20 = $" + (dias * 20) + "\n");
					}
				}

			} catch (NumberFormatException e) {
				e.getMessage();
				return;
			}

			/// Mostrar el desglose de los precios en un JOptionPane
			String resumenFinal = resumen.toString();
			resumenFinal += "\nPrecio total: $" + precioTotal;

			int opcion = JOptionPane.showConfirmDialog(ventanaActual,
					resumenFinal + "\n\n" + MultiIdioma.IdiomaTexto("ConfirmarServicios"),
					MultiIdioma.IdiomaTexto("Confirmar"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (opcion == JOptionPane.YES_OPTION) {
				// Actualizar las selecciones iniciales en la base de datos
				String actualizarServiciosIniciales = """
						UPDATE servicios
						SET parking = ?, masaje_sueco = ?, masaje_deportivo = ?, masaje_tailandes = ?, spa_medio_dia = ?,
						spa_dia_entero = ?, baños_innmersion = ?, mesas_estandar = ?, mesas_privadas = ?,
						menus_especiales = ?, eventos_y_celebraciones = ?, experiencia_gastronomica = ?,
						al_aire_libre = ?
						WHERE numero_habitacion = ? AND dni_cliente = ?;
						""";

				try (PreparedStatement stmtInicial = conexion.prepareStatement(actualizarServiciosIniciales)) {
					stmtInicial.setBoolean(1, radioButtonSi.isSelected());
					stmtInicial.setBoolean(2, checkBoxMasajeSueco.isSelected());
					stmtInicial.setBoolean(3, checkBoxMasajeDeportivo.isSelected());
					stmtInicial.setBoolean(4, checkBoxMasajeTailandes.isSelected());
					stmtInicial.setBoolean(5, checkBoxSpaMedio.isSelected());
					stmtInicial.setBoolean(6, checkBoxSpaCompleto.isSelected());
					stmtInicial.setBoolean(7, checkBoxBaños.isSelected());
					stmtInicial.setBoolean(8, checkBoxMesasEstandar.isSelected());
					stmtInicial.setBoolean(9, checkBoxMesasPrivadas.isSelected());
					stmtInicial.setBoolean(10, checkBoxMenusEspeciales.isSelected());
					stmtInicial.setBoolean(11, checkBoxEventosyCelebraciones.isSelected());
					stmtInicial.setBoolean(12, checkBoxExperienciaGastronomica.isSelected());
					stmtInicial.setBoolean(13, checkBoxAireLibre.isSelected());
					stmtInicial.setInt(14, Integer.parseInt(numeroHabitacion));
					stmtInicial.setString(15, dniCliente);

					stmtInicial.executeUpdate();

				} catch (SQLException e) {
					System.out.println("Error al guardas las opciones");
					return;
				}

				// Actualizar el precio total en la base de datos
				String actualizarPrecio = """
						UPDATE servicios
						SET precio = precio + ?
						WHERE numero_habitacion = ? AND dni_cliente = ?;
						""";

				try (PreparedStatement stmtPrecio = conexion.prepareStatement(actualizarPrecio)) {
					stmtPrecio.setDouble(1, precioTotal);
					stmtPrecio.setInt(2, Integer.parseInt(numeroHabitacion));
					stmtPrecio.setString(3, dniCliente);

					stmtPrecio.executeUpdate();

					JOptionPane.showMessageDialog(ventanaActual, "Precio actualizado: $" + precioTotal, "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(ventanaActual, "Error al actualizar el precio: " + e.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		private int solicitarNumero(JFrame ventana, String mensaje, String titulo) {
			String input = JOptionPane.showInputDialog(ventana, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);

			if (input == null) {
				// El usuario cerró el cuadro de diálogo
				return -1;
			}
			try {
				return Integer.parseInt(input.trim());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(ventana, "Por favor, ingrese un número válido.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return solicitarNumero(ventana, mensaje, titulo); // Volver a solicitar el número
			}
		}

}
