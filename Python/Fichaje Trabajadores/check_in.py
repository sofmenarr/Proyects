from PyQt6.QtWidgets import QFrame
from PyQt6.uic import loadUi
from PyQt6.QtCore import QTimer, Qt, pyqtSlot, QDate, QTime
from Modelo.Fichaje import Fichaje
import logging

class VentanaCheck_In (QFrame):
    def __init__(self, id_usuario, conexion):
        super(VentanaCheck_In, self).__init__()
        loadUi("./Designs/Check-In.ui", self)  
        self.setup_timer()
         
        # Guardar el ID del usuario y la conexión
        self.id_usuario = id_usuario
        self.conexion = conexion
         
        self.fichaje = Fichaje(conexion)
         
        # Conectar los botones a sus respectivas acciones
        self.BotonFichaje.clicked.connect(self.emitir_fichaje)
        self.BotonFichajeSalida.clicked.connect(self.salir_fichaje)
        self.BotonVolver.clicked.connect(self.volver_anterior)
         
    def setup_timer(self):
        # Configurar el tiempo a la hora real y se refresque
        self.temporizador = QTimer(self)  
        self.temporizador.timeout.connect(self.update_labels)  
        self.temporizador.start(1000)  
        
    def update_labels(self):
        # Insertar el tiempo a los labels
        fecha_actual = QDate.currentDate()
        hora_actual = QTime.currentTime()
        self.FechaActual.setText(fecha_actual.toString("dd/MM/yy"))
        self.HoraActual.setText(hora_actual.toString("HH:mm:ss"))
     
    def emitir_fichaje(self):
        try:
            fecha_actual = QDate.currentDate().toString("yyyy-MM-dd")
            hora_actual = QTime.currentTime().toString("HH:mm:ss")
            estado = "In"

            cursor = self.conexion.cursor()
            
            # Comprobar que no ha fichado la entrada antes en ese día
            cursor.execute(
            "SELECT estado FROM reloj WHERE idtr = ? ORDER BY fecha DESC, hora DESC LIMIT 1",
            (self.id_usuario,))
            ultimo_fichaje = cursor.fetchone()

            if ultimo_fichaje and ultimo_fichaje[0] == "In":
                self.DatosClienteEntrada.setText("Error: Debes fichar salida antes de una nueva entrada.")
                logging.warning(f"Usuario {self.id_usuario} intentó fichar entrada sin haber fichado salida.")
                cursor.close()
                return

            # Cargar los datos del trabajador
            cursor.execute("SELECT nombre, apellidos FROM trabajadores WHERE idtr = ?", (self.id_usuario,))
            resultado = cursor.fetchone()

            # Hacer el fichaje de entrada si es correcto
            if resultado:
                nombre, apellidos = resultado
                nombre_completo = f"{nombre} {apellidos}"
                self.fichaje.insertar_fichaje(self.id_usuario, nombre_completo, fecha_actual, hora_actual, estado)
                self.DatosClienteEntrada.setText(f"{nombre_completo}, {fecha_actual}, {hora_actual}, {estado}")
                logging.info(f"Usuario {self.id_usuario} ha realizado un fichaje de entrada.")
            else:
                self.DatosClienteEntrada.setText("Error: No se encontraron datos del cliente.")
                logging.error("No se encontraron datos del cliente.")
        except Exception as e:
            self.DatosClienteEntrada.setText(f"Error al guardar fichaje")
            logging.error(f"No se ha podido guardar el fichaje: {e}")

    def salir_fichaje(self):
        try:
            fecha_actual = QDate.currentDate().toString("yyyy-MM-dd")
            hora_actual = QTime.currentTime().toString("HH:mm:ss")
            estado = "Out"

            cursor = self.conexion.cursor()

            # Comprobar que no ha fichado la salida antes en ese día
            cursor.execute(
            "SELECT estado FROM reloj WHERE idtr = ? ORDER BY fecha DESC, hora DESC LIMIT 1",
            (self.id_usuario,))
            ultimo_fichaje = cursor.fetchone()
            
            if not ultimo_fichaje or ultimo_fichaje[0] == "Out":
                self.DatosClienteSalida.setText("Error: Debes fichar entrada antes de una nueva salida.")
                logging.warning(f"Usuario {self.id_usuario} intentó fichar salida sin haber fichado entrada.")
                cursor.close()
                return
            
            # Cargar datos del cliente para el fichaje
            cursor.execute("SELECT nombre, apellidos FROM trabajadores WHERE idtr = ?", (self.id_usuario,))
            resultado = cursor.fetchone()
            cursor.close()

            # Hacer el fichaje de salida si es correcto
            if resultado:
                nombre, apellidos = resultado
                nombre_completo = f"{nombre} {apellidos}"
                self.fichaje.insertar_fichaje(self.id_usuario, nombre_completo, fecha_actual, hora_actual, estado)
                self.DatosClienteSalida.setText(f"{nombre_completo}, {fecha_actual}, {hora_actual}, {estado}")
                logging.info(f"Usuario {self.id_usuario} ha realizado un fichaje de salida.")
            else:
                self.DatosClienteSalida.setText("Error: No se encontraron datos del cliente.")
                logging.error("No se encontraron datos del cliente.")
        except Exception as e:
            self.DatosClienteSalida.setText("Error al guardar fichaje")
            logging.error(f"No se ha podido guardar el fichaje: {e}")
            
    def volver_anterior(self):
        from second_window import VentanaSecundaria
        self.second_window = VentanaSecundaria(self.id_usuario ,self.conexion)
        self.second_window.show()
        self.close()    
      