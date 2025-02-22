import sqlite3
import logging
class Conexion:
    def __init__(self, db_path):
        self.db_path = db_path
        self.connection = None
    
    def conectar(self):
        try:
            self.connection = sqlite3.connect(self.db_path)
            logging.info("Conexion establecida correctamente con la base de datos.")
        except sqlite3.Error as e:
            logging.critical(f"Error crítico al conectar a la base de datos: {e}")
            return None
        return self.connection
       
    def desconectar(self):
        if self.connection:
            self.connection.close()
            logging.info("Conexión cerrada correctamente.")
            

    
