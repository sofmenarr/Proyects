import sqlite3

class Fichaje:
    def __init__(self, conexion):
        if conexion is None:
            raise ValueError("La conexión a la base de datos no es válida.")
        self.conexion = conexion
        
    def insertar_fichaje(self, idtr, nombre, fecha, hora, estado):
        try:
            query = """
            INSERT INTO reloj (idtr, nombre, fecha, hora, estado)
            VALUES (?, ?, ?, ?, ?)
            """
            with self.conexion:
                cursor = self.conexion.cursor()
                cursor.execute(query, (idtr, nombre, fecha, hora, estado))
            print("Fichaje insertado correctamente.")
        except sqlite3.Error as e:
            print(f"Error al insertar el fichaje: {e}")