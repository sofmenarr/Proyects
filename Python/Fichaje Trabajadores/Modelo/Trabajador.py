import sqlite3
from DAO.Conexion import Conexion
import logging

class Trabajador:
    def __init__(self, conexion):
        if conexion is None:
            logging.critical("La conexión a la base de datos no es válida.")
            raise ValueError("La conexión a la base de datos no es válida.")
        self.conexion = conexion
        self.cursor = self.conexion.cursor()
    
    def buscar_trabajador(self, columna, valor):
        try:
            cursor = self.conexion.cursor()
            query = f"SELECT 1 FROM trabajadores WHERE {columna} = ? LIMIT 1"
            cursor.execute(query, (valor,))
            resultado = cursor.fetchone()
            if resultado:
                logging.info(f"Usuario con ID: '{valor}' encontrado en la base de datos.")
            else:
                logging.warning(f"Intento fallido de acceso con ID: '{valor}'.")
            return resultado is not None
        except sqlite3.Error as e:
            logging.error(f"Error al buscar valor en la base de datos: {e}")
            return False  
   