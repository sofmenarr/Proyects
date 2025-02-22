from PyQt6.QtWidgets import QFrame
from PyQt6.uic import loadUi
from check_in import VentanaCheck_In  
from listado import VentanaListado  
from main_window import VentanaPrincipal 
import logging

class VentanaSecundaria(QFrame):
    def __init__(self, id_usuario, conexion):
        super(VentanaSecundaria, self).__init__()
        loadUi("./Designs/SecondDesign.ui", self)  

        # Guardar el ID del usuario y la conexión
        self.id_usuario = id_usuario
        self.conexion = conexion

        # Inicializar componentes
        self.inicializar_ui()

    def inicializar_ui(self):
        self.obtener_nombre_apellido()  

        # Conectar los botones a sus respectivas acciones
        self.BotonFichar.clicked.connect(self.abrir_check_in)
        self.BotonImprimir.clicked.connect(self.abrir_listado)
        self.BotonVolver.clicked.connect(self.volver_main)

    def obtener_nombre_apellido(self):
        # Obtener el nombre y apellidos del trabajador que se ha logeado
        cursor = self.conexion.cursor()
        query = "SELECT nombre, apellidos FROM trabajadores WHERE idtr = ?" 
        cursor.execute(query, (self.id_usuario,))
        resultado = cursor.fetchone()

        # Imprimir en el label el nombre y apellidos del trabajador
        if resultado:
            nombre, apellido = resultado
            self.TextNombreUsuario.setText(f"<div style='text-align: center;'>{nombre} {apellido}</div>")
        else:
            self.TextNombreUsuario.setText("Usuario no encontrado")
            logging.warning("El usuario selecionado no se encuentra en la bbdd")
            

    def abrir_check_in(self):
        self.check_in_window = VentanaCheck_In(self.id_usuario, self.conexion)
        self.check_in_window.show()
        self.close()

    def abrir_listado(self):
        self.listado_window = VentanaListado(self.id_usuario ,self.conexion)
        self.listado_window.show()
        self.close()

    def volver_main(self):
        self.main_window = VentanaPrincipal()
        self.conexion.close()
        self.main_window.show()
        self.close()
