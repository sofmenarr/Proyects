from PyQt6.QtWidgets import QFrame, QListWidgetItem
from PyQt6.uic import loadUi
from documento import PDF
import logging
from datetime import datetime, timedelta

class VentanaListado (QFrame):
    def __init__(self, id_usuario, conexion):
        super(VentanaListado, self).__init__()
        loadUi("./Designs/Imprimir.ui", self)
        
        # Guardar el ID del usuario y la conexión
        self.id_usuario = id_usuario
        self.conexion = conexion
        
        self.cargar_trabajadores()
        
        # Conectar los botones a sus respectivas acciones
        self.BotonImprimir.clicked.connect(self.generar_pdf)
        self.BotonVolver.clicked.connect(self.volver_anterior)
        
    def cargar_trabajadores(self):
        # Sacar la info de los trabajadores
        cursor = self.conexion.cursor()
        cursor.execute("SELECT idtr, nombre, apellidos FROM trabajadores")
        trabajadores = cursor.fetchall()
        
        # Añadir todos los trabajadores a la lista
        for trabajador in trabajadores:
            item = QListWidgetItem(f"{trabajador[0]} - {trabajador[1]} {trabajador[2]}")
            item.setData(1, trabajador[0])
            self.ListaClientes.addItem(item)

    def generar_pdf(self):
        # Hacer el pdf con el trabajador seleccionado
        trabajador_seleccionado = self.ListaClientes.currentItem()
        if not trabajador_seleccionado:
            logging.warning("Tienes que seleccionar un trabajador.")
            return

        # Sacar las fechas seleccionadas
        fecha_inicio = self.FechaInicial.date().toString("yyyy-MM-dd")
        fecha_fin = self.FechaFinal.date().toString("yyyy-MM-dd")
        id_trabajador = trabajador_seleccionado.data(1)
        
        # Sacar los fichajes del trabajador entre las dos fechas
        cursor = self.conexion.cursor()
        cursor.execute("""
            SELECT fecha, hora, estado FROM reloj 
            WHERE idtr = ? AND fecha BETWEEN ? AND ?
            ORDER BY fecha, hora
        """, (id_trabajador, fecha_inicio, fecha_fin))
        registros = cursor.fetchall()
        
        cursor.execute("SELECT nombre, apellidos, dni FROM trabajadores WHERE idtr = ?", (id_trabajador,))
        trabajador = cursor.fetchone()
        
        if not registros:
            logging.warning("No hay registros para este trabajador en el rango de fechas seleccionado.")
            return
        
        # Generar pdf
        pdf = PDF()
        pdf.alias_nb_pages()
        pdf.add_page()
        pdf.set_font('Arial', 'B', 10)
        
        # Poner la info del trabajador en las celdas
        pdf.cell(25, 10, trabajador[2], 1, 0, 'C')
        pdf.cell(20, 10, trabajador[0], 1, 0, 'C')
        pdf.cell(50, 10, trabajador[1], 1, 0, 'C')
        pdf.cell(20, 10, '85692721', 1, 0, 'C')
        pdf.cell(50, 10, 'SAMPEDRO', 1, 0, 'C')
        pdf.cell(25, 10, '03/129823319', 1, 1, 'C')
        pdf.ln(10)
        
        # Atributos para el calculo total de horas
        total_tiempo = timedelta()
        pares = []
        ultima_entrada = None
        
        # Sacar las fechas de entrada y salida con los estados
        for fecha, hora, estado in registros:
            hora_formateada = datetime.strptime(hora, "%H:%M:%S").strftime("%H:%M")  
            
            pdf.set_font('Arial', '', 8)
            pdf.cell(50, 5, fecha, 0, 0, 'C')
            pdf.cell(50, 5, hora_formateada, 0, 0, 'C')
            pdf.cell(50, 5, estado, 0, 1, 'C')
            
            if estado == "In":
                ultima_entrada = datetime.strptime(f"{fecha} {hora}", "%Y-%m-%d %H:%M:%S")
            elif estado == "Out" and ultima_entrada:
                ultima_salida = datetime.strptime(f"{fecha} {hora}", "%Y-%m-%d %H:%M:%S")
                pares.append((ultima_entrada, ultima_salida))
                ultima_entrada = None
        
        # Calcular el tiempo total trabajado
        for entrada, salida in pares:
            total_tiempo += salida - entrada
    
        tiempo_total_str = str(total_tiempo)
        
        # Colocar el tiempo total trabajado
        pdf.ln(5)
        pdf.cell(40, 5, " ", 0)
        pdf.cell(40, 5, " ", 0)
        pdf.cell(40, 5, "TOTAL DÍA ...", 0)
        pdf.cell(40, 5, fecha_fin, 0)
        pdf.cell(40, 5, tiempo_total_str, 0, 1)
        
        # Donde generar el pdf
        pdf.output(f"./Documents/reporte_{trabajador[0]}.pdf")
        logging.info("PDF generado correctamente.")
        
    def volver_anterior(self):
        from second_window import VentanaSecundaria
        self.second_window = VentanaSecundaria(self.id_usuario ,self.conexion)
        self.second_window.show()
        self.close() 
         