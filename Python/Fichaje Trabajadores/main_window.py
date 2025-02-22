from PyQt6.QtWidgets import QMainWindow, QApplication, QMessageBox
from PyQt6.uic import loadUi
from PyQt6.QtGui import QRegularExpressionValidator, QPixmap
from PyQt6.QtCore import QRegularExpression
from DAO.Conexion import Conexion
from Modelo.Trabajador import Trabajador
from correo import Correo
import re  # Para validar el formato del ID
import sys
import logging

logging.basicConfig(
    level=logging.INFO,
    format=" %(levelname)s (%(asctime)s): %(message)s",
    datefmt="%Y-%m-%d  %H:%M:%S",
    filename="./DAO/logs.txt",
    filemode="a"
)

class VentanaPrincipal(QMainWindow):
    def __init__(self):
        super(VentanaPrincipal, self).__init__()
        loadUi("./Designs/MainDesign.ui", self)   

        # Configuración inicial
        self.BotonIngresar.clicked.connect(self.validar_id) 
        self.IDLabel.setFocus()

        # Configurar limitador en la inserción del id
        self.IDLabel.setMaxLength(4)
        regex = QRegularExpression("^[a-zA-Z0-9]{1,4}$")
        validator = QRegularExpressionValidator(regex)  
        self.IDLabel.setValidator(validator)  

        # Cargar logotipo
        pixmap = QPixmap("./Designs/logotipo.png")
        self.label_icono.setPixmap(pixmap)
        self.label_icono.setScaledContents(True) 

        # Conexión a la base de datos
        self.conexion = Conexion("./DAO/interfaces.db").conectar()
        
        # Comprobar si hay que enviar un correo
        self.correo = Correo(self.conexion)
        self.iniciar_verificacion_automatica()
        
        if self.conexion is None:
            QMessageBox.critical(self, "Error", "No se pudo conectar a la base de datos.")
            logging.critical(f"Fallo al conectar con la base de datos")
            sys.exit(1)

        self.dao = Trabajador(self.conexion)

    def validar_id(self):
        # Eliminar espacios
        id_usuario = self.IDLabel.text().strip()  

        # Que no esté vacío
        if not id_usuario:
            self.MensajeError.setText("ERROR: INSERTE EL ID.")
            self.MensajeError.setStyleSheet("color: red; font-weight: bold;") 
            logging.error("Intento de ingreso sin código de usuario.")
            return

        # Máximo de 4 caracteres alfanuméricos
        if not re.match(r"^[a-zA-Z0-9]{1,4}$", id_usuario):
            self.MensajeError.setText("ERROR: ID INVÁLIDO")
            logging.error("El ID insertado no es valido")
            return

        # Comprobar si el ID existe en la base de datos
        if self.dao.buscar_trabajador("idtr", id_usuario): 
            self.MensajeError.setText("") 
            self.abrir_segunda_ventana()  
            logging.info(f"Validando ID de usuario: {id_usuario}.")
        else:
            self.MensajeError.setText("ERROR: NO EXISTE ESE ID")
            logging.error(f"El ID insertado no existe")

    def abrir_segunda_ventana(self):
        from second_window import VentanaSecundaria  
        self.second_window = VentanaSecundaria(self.IDLabel.text().strip(), self.conexion)
        self.second_window.show()
        self.close()
        
    def iniciar_verificacion_automatica(self):
        from PyQt6.QtCore import QTimer
        timer = QTimer()
        timer.timeout.connect(self.correo.verificar_fichajes)
        timer.start(60000 * 60 * 24)  # Ejecutar cada 24 horas

if __name__ == "__main__":
    app = QApplication(sys.argv)
    ventana = VentanaPrincipal()
    ventana.show()
    sys.exit(app.exec())
    