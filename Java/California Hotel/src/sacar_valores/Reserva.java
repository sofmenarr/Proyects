package sacar_valores;

public class Reserva {

	private static Reserva instance;

	// Datos del cliente
	private static String nombreCliente;
	private static String apellidosCliente;
	private static String documentoIdentificacion;

	// Datos de la habitación
	private static int numHabitacion;
	private static String tipoCamaSeleccionada;
	private static String tipoVistaSeleccionada;

	private static String mejorasSeleccionadas;
	private static String amenidadesSeleccionadas;

	// Datos adicionales de la reserva
	private static boolean cuna;
	private static boolean camaSupletoria;
	private static boolean pijamasAlbornoces;
	private static boolean maquinaCafe;
	private static boolean cajaFuerte;
	private static boolean router;
	private static boolean minibar;

	private static int numeroDias;
	private static double precioTotal;

	public Reserva() {

	}

	public Reserva getInstance() {
		if (instance == null) {
			instance = new Reserva();
		}
		return instance;
	}

	public void reset() {
		nombreCliente = null;
		apellidosCliente = null;
		numeroDias = 0;
		numHabitacion = 0;
		tipoCamaSeleccionada = null;
		tipoVistaSeleccionada = null;
		mejorasSeleccionadas = null;
		amenidadesSeleccionadas = null;
		cuna = false;
		camaSupletoria = false;
		pijamasAlbornoces = false;
		maquinaCafe = false;
		cajaFuerte = false;
		router = false;
		minibar = false;
		precioTotal = 0;
	}

	// Métodos para obtener los datos del cliente
	public String getNombreCliente() {
		return nombreCliente;
	}

	public String getApellidosCliente() {
		return apellidosCliente;
	}

	public String getDocumentoIdentificacion() {
		return documentoIdentificacion;
	}

	// Métodos para obtener los datos de la habitación
	public String getTipoCamaSeleccionada() {
		return tipoCamaSeleccionada;
	}

	public String getTipoVistaSeleccionada() {
		return tipoVistaSeleccionada;
	}

	public boolean isCuna() {
		return cuna;
	}

	public void setCuna(boolean valor) {
		cuna = valor;
	}

	public boolean isCamaSupletoria() {
		return camaSupletoria;
	}

	public void setCamaSupletoria(boolean valor) {
		camaSupletoria = valor;
	}

	public boolean isPijamasAlbornoces() {
		return pijamasAlbornoces;
	}

	public void setPijamasAlbornoces(boolean valor) {
		pijamasAlbornoces = valor;
	}

	public boolean isMaquinaCafe() {
		return maquinaCafe;
	}

	public void setMaquinaCafe(boolean valor) {
		maquinaCafe = valor;
	}

	public boolean isCajaFuerte() {
		return cajaFuerte;
	}

	public void setCajaFuerte(boolean valor) {
		cajaFuerte = valor;
	}

	public boolean isRouter() {
		return router;
	}

	public void setRouter(boolean valor) {
		router = valor;
	}

	public boolean isMinibar() {
		return minibar;
	}

	public void setMinibar(boolean valor) {
		minibar = valor;
	}

	public int getNumeroDias() {
		return numeroDias;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public int getNumeroHabitacion() {
		return numHabitacion;
	}

	public void setNumeroHabitacion(int numeroHabitacion) {
		numHabitacion = numeroHabitacion;
	}

	// Método para establecer los datos del cliente
	public void setDatosCliente(String nombre, String apellidos, String documento) {
		nombreCliente = nombre;
		apellidosCliente = apellidos;
		documentoIdentificacion = documento;
	}

	public void setDiasReserva(int dias) {
		numeroDias = dias;
	}

	// Método para establecer los datos de la habitación
	public void setDatosHabitacion(String tipoCama, String tipoVista) {
		tipoCamaSeleccionada = tipoCama;
		tipoVistaSeleccionada = tipoVista;
	}

	public String getMejorasSeleccionadas() {
		return mejorasSeleccionadas;
	}

	public String getAmenidadesSeleccionadas() {
		return amenidadesSeleccionadas;
	}

	public void setMejoras(String mejoras, String amenidades) {
		mejorasSeleccionadas = mejoras;
		amenidadesSeleccionadas = amenidades;
	}

	public void setPrecioTotal(double precio) {
		precioTotal = precio;
	}

}
