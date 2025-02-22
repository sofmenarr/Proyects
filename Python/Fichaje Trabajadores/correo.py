import smtplib, ssl
from email.message import EmailMessage
from datetime import datetime
import sqlite3
import logging

class Correo:
    def __init__(self, conexion):
        self.conexion = conexion
        self.email_address = "dam2jls@web2talent.com"
        self.email_password = "Sampedro2025"
        self.smtp_address = "smtp.servidor-correo.net"
        self.smtp_port = 587
        
    def enviar_email_aviso(self, trabajadores_faltantes, fecha):
        if not trabajadores_faltantes:
            return  # No hay avisos que enviar

        email_receiver = "receptor@receptor.com"
        textoEmail = f"Lista de trabajadores que no han fichado la salida el {fecha}:\n\n"
        for trabajador in trabajadores_faltantes:
            textoEmail += f"- {trabajador}\n"

        msg = EmailMessage()
        msg.set_content(textoEmail)
        msg["Subject"] = f"Aviso: Trabajadores sin fichaje de salida ({fecha})"
        msg["From"] = self.email_address
        msg["To"] = email_receiver

        context = ssl.create_default_context()

        try:
            with smtplib.SMTP(self.smtp_address, self.smtp_port) as server:
                server.ehlo()
                server.starttls(context=context)
                server.ehlo()
                server.login(self.email_address, self.email_password)
                server.send_message(msg)
                server.quit()
            logging.info(f"Correo enviado correctamente para el día {fecha}.")
        except Exception as e:
            logging.error(f"Error al enviar el correo: {e}")

    def verificar_fichajes(self):
        fecha_hoy = datetime.now().strftime("%Y-%m-%d")
        cursor = self.conexion.cursor()

        cursor.execute("""
            SELECT t.nombre, t.apellidos 
            FROM trabajadores t
            WHERE t.idtr IN (
                SELECT idtr FROM reloj WHERE fecha = ? AND estado = "In"
                EXCEPT
                SELECT idtr FROM reloj WHERE fecha = ? AND estado = "Out"
            )
        """, (fecha_hoy, fecha_hoy))

        trabajadores_faltantes = [f"{nombre} {apellidos}" for nombre, apellidos in cursor.fetchall()]

        self.enviar_email_aviso(trabajadores_faltantes, fecha_hoy)