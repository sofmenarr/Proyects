from fpdf import FPDF

class PDF(FPDF):
    def header(self):
        self.image('./Designs/logo.PNG', 15, 5, 25)
        self.ln(5)
        self.set_font('Arial', 'B', 15)
        self.cell(60)
        self.cell(100, 10, 'Control de Presencia', 1, 0, 'C')
        self.ln(10)
        
        self.set_font('Arial', 'B', 12)
        self.cell(95, 10, 'Datos del Trabajador', 1, 0, 'C')
        self.cell(95, 10, 'Datos de la Empresa', 1, 1, 'C')
        
        self.set_font('Arial', 'B', 10)
        self.cell(25, 10, 'DNI', 1, 0, 'C')
        self.cell(20, 10, 'NOMBRE', 1, 0, 'C')
        self.cell(50, 10, 'APELLIDOS', 1, 0, 'C')
        self.cell(20, 10, 'CIF', 1, 0, 'C')
        self.cell(50, 10, 'RAZÓN SOCIAL', 1, 0, 'C')
        self.cell(25, 10, 'CSS', 1, 1, 'C')
        
        
    def footer(self):
        self.set_y(-15)
        self.set_font('Arial', 'I', 8)
        self.cell(0, 10, f'Page {self.page_no()}/{{nb}}', 0, 0, 'C')


