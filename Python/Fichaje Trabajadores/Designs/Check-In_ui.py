# Form implementation generated from reading ui file 'f:\Estudios\Visual Studio Code\2ºDAM\Interfaces\trabajo\Designs\Check-In.ui'
#
# Created by: PyQt6 UI code generator 6.8.0
#
# WARNING: Any manual changes made to this file will be lost when pyuic6 is
# run again.  Do not edit this file unless you know what you are doing.


from PyQt6 import QtCore, QtGui, QtWidgets


class Ui_Frame(object):
    def setupUi(self, Frame):
        Frame.setObjectName("Frame")
        Frame.resize(471, 503)
        Frame.setStyleSheet("QFrame {\n"
"background-color: rgb(18, 18, 18);\n"
"border: 2px solid rgb(239, 205, 129);\n"
"}\n"
"\n"
"QPushButton {\n"
"background-color: rgb(237, 201, 125);\n"
"}\n"
"\n"
"QPushButton:hover {\n"
"background-color: rgb(247, 222, 153);\n"
"}\n"
"\n"
"QToolButton {\n"
"background-color: rgb(237, 201, 125);\n"
"}\n"
"\n"
"QToolButton:hover {\n"
"background-color: rgb(247, 222, 153);\n"
"}")
        self.BotonFichaje = QtWidgets.QPushButton(parent=Frame)
        self.BotonFichaje.setGeometry(QtCore.QRect(160, 150, 161, 41))
        self.BotonFichaje.setStyleSheet("font: 75 12pt \"Cambria\";\n"
"\n"
"")
        self.BotonFichaje.setObjectName("BotonFichaje")
        self.FechaActual = QtWidgets.QLabel(parent=Frame)
        self.FechaActual.setGeometry(QtCore.QRect(50, 50, 161, 41))
        self.FechaActual.setStyleSheet("color: rgb(239, 205, 129);\n"
"font-size: 30px;\n"
"text-align: center;\n"
"font-weight: bold;\n"
"border: 0px solid;")
        self.FechaActual.setText("")
        self.FechaActual.setObjectName("FechaActual")
        self.HoraActual = QtWidgets.QLabel(parent=Frame)
        self.HoraActual.setGeometry(QtCore.QRect(260, 50, 161, 41))
        self.HoraActual.setStyleSheet("color: rgb(239, 205, 129);\n"
"font-size: 30px;\n"
"text-align: center;\n"
"font-weight: bold;\n"
"border: 0px solid;")
        self.HoraActual.setText("")
        self.HoraActual.setObjectName("HoraActual")
        self.BotonVolver = QtWidgets.QToolButton(parent=Frame)
        self.BotonVolver.setGeometry(QtCore.QRect(410, 450, 41, 31))
        self.BotonVolver.setStyleSheet("font-size: 30px;\n"
"")
        self.BotonVolver.setObjectName("BotonVolver")
        self.DatosCliente = QtWidgets.QLabel(parent=Frame)
        self.DatosCliente.setGeometry(QtCore.QRect(30, 330, 411, 101))
        self.DatosCliente.setStyleSheet("color: rgb(239, 205, 129);\n"
"text-align: center;\n"
"font-size: 15px;")
        self.DatosCliente.setText("")
        self.DatosCliente.setObjectName("DatosCliente")
        self.line = QtWidgets.QFrame(parent=Frame)
        self.line.setGeometry(QtCore.QRect(20, 310, 118, 3))
        self.line.setFrameShape(QtWidgets.QFrame.Shape.HLine)
        self.line.setFrameShadow(QtWidgets.QFrame.Shadow.Sunken)
        self.line.setObjectName("line")
        self.line_2 = QtWidgets.QFrame(parent=Frame)
        self.line_2.setGeometry(QtCore.QRect(130, 310, 118, 3))
        self.line_2.setFrameShape(QtWidgets.QFrame.Shape.HLine)
        self.line_2.setFrameShadow(QtWidgets.QFrame.Shadow.Sunken)
        self.line_2.setObjectName("line_2")
        self.line_3 = QtWidgets.QFrame(parent=Frame)
        self.line_3.setGeometry(QtCore.QRect(220, 310, 118, 3))
        self.line_3.setFrameShape(QtWidgets.QFrame.Shape.HLine)
        self.line_3.setFrameShadow(QtWidgets.QFrame.Shadow.Sunken)
        self.line_3.setObjectName("line_3")
        self.line_4 = QtWidgets.QFrame(parent=Frame)
        self.line_4.setGeometry(QtCore.QRect(330, 310, 118, 3))
        self.line_4.setFrameShape(QtWidgets.QFrame.Shape.HLine)
        self.line_4.setFrameShadow(QtWidgets.QFrame.Shadow.Sunken)
        self.line_4.setObjectName("line_4")
        self.BotonFichajeSalida = QtWidgets.QPushButton(parent=Frame)
        self.BotonFichajeSalida.setGeometry(QtCore.QRect(160, 220, 161, 41))
        self.BotonFichajeSalida.setStyleSheet("font: 75 12pt \"Cambria\";\n"
"\n"
"")
        self.BotonFichajeSalida.setObjectName("BotonFichajeSalida")
        self.DatosClienteEntrada = QtWidgets.QLabel(parent=Frame)
        self.DatosClienteEntrada.setGeometry(QtCore.QRect(40, 340, 391, 31))
        self.DatosClienteEntrada.setStyleSheet("color: rgb(239, 205, 129);\n"
"text-align: center;\n"
"font-size: 15px;\n"
"border: 0px solid;")
        self.DatosClienteEntrada.setText("")
        self.DatosClienteEntrada.setObjectName("DatosClienteEntrada")
        self.DatosClienteSalida = QtWidgets.QLabel(parent=Frame)
        self.DatosClienteSalida.setGeometry(QtCore.QRect(40, 390, 391, 31))
        self.DatosClienteSalida.setStyleSheet("color: rgb(239, 205, 129);\n"
"text-align: center;\n"
"font-size: 15px;\n"
"border: 0px solid;")
        self.DatosClienteSalida.setText("")
        self.DatosClienteSalida.setObjectName("DatosClienteSalida")

        self.retranslateUi(Frame)
        QtCore.QMetaObject.connectSlotsByName(Frame)

    def retranslateUi(self, Frame):
        _translate = QtCore.QCoreApplication.translate
        Frame.setWindowTitle(_translate("Frame", "Frame"))
        self.BotonFichaje.setText(_translate("Frame", "EMITIR FICHAJE"))
        self.BotonVolver.setText(_translate("Frame", "⭠"))
        self.BotonFichajeSalida.setText(_translate("Frame", "FICHAR SALIDA"))
