-- Insertar tipos de vehículos
INSERT INTO tipo_vehiculo (marca, modelo, precio, tipo_vehiculo, imagen) VALUES 
('Audi', 'A3', 65.00, 'COCHE', ''),
('BMW', 'Serie3', 75.00, 'COCHE', ''),
('Citroen', 'Berlingo', 55.00, 'FURGONETA', ''),
('Ducati', 'Monster821', 45.00, 'MOTO', ''),
('Fiat', 'Ducato', 60.00, 'FURGONETA', ''),
('Ford', 'Focus', 40.00, 'COCHE', ''),
('Harley-Davidson', 'Iron883', 50.00, 'MOTO', ''),
('Honda', 'CBR600RR', 40.00, 'MOTO', ''),
('Hyundai', 'i20N', 45.00, 'COCHE', ''),
('Kawasaki', 'Ninja400', 35.00, 'MOTO', ''),
('Mercedes-Benz', 'Sprinter', 70.00, 'FURGONETA', ''),
('Nissan', 'Leaf', 50.00, 'COCHE', ''),
('Opel', 'ComboLife', 52.00, 'FURGONETA', ''),
('Peugeot', '208', 38.00, 'COCHE', ''),
('Renault', 'Clio', 37.00, 'COCHE', ''),
('Seat', 'Ibiza', 39.00, 'COCHE', ''),
('Suzuki', 'GSX-R750', 42.00, 'MOTO', ''),
('Tesla', 'Model3', 85.00, 'COCHE', ''),
('Toyota', 'Corolla', 42.00, 'COCHE', ''),
('Volkswagen', 'Transporter', 65.00, 'FURGONETA', ''),
('Yamaha', 'MT-07', 33.00, 'MOTO', '');

-- Insertar vehículos
INSERT INTO vehiculo (matricula, id_tipo_vehiculo, color, kilometraje, disponibilidad, ubicacion, combustible, etiqueta, autonomia, puertas, aire_acondicionado, plazas, transmision) VALUES
('0000JJJ', 17, 'Gris', 40000, FALSE, 'PONTEVEDRA', 'DIESEL', 'C', 0, 3, TRUE, 3, 'MANUAL'),
('0120NBB', 9, 'Verde', 6000, TRUE, 'MADRID', 'GASOLINA', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('1111AAA', 8, 'Rojo', 15000, TRUE, 'ZARAGOZA', 'GASOLINA', 'C', 0, 0, FALSE, 2, 'MANUAL'),
('1212KKK', 18, 'Azul', 13000, TRUE, 'CORDOBA', 'ELECTRICO', 'CERO', 300, 5, TRUE, 5, 'AUTOMATICO'),
('1234ABC', 1, 'Blanco', 20000, TRUE, 'MADRID', 'ELECTRICO', 'CERO', 350, 5, TRUE, 5, 'AUTOMATICO'),
('2222BBB', 7, 'Negro', 25000, FALSE, 'MALAGA', 'GASOLINA', 'B', 0, 0, FALSE, 2, 'MANUAL'),
('2345PQR', 6, 'Gris', 18000, TRUE, 'ALICANTE', 'GASOLINA', 'ECO', 0, 5, TRUE, 5, 'AUTOMATICO'),
('3434LLL', 19, 'Rojo', 4500, TRUE, 'BURGOS', 'GASOLINA', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('3456JKL', 4, 'Azul', 22000, FALSE, 'SEVILLA', 'GASOLINA', 'C', 0, 0, FALSE, 2, 'MANUAL'),
('4444DDD', 11, 'Gris', 12000, TRUE, 'GRANADA', 'GASOLINA', 'ECO', 0, 5, TRUE, 5, 'MANUAL'),
('4556EEE', 12, 'Negro', 7000, TRUE, 'CANTABRIA', 'GASOLINA', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('5656MMM', 20, 'Verde', 32000, TRUE, 'ASTURIAS', 'DIESEL', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('5678DEF', 2, 'Negro', 8000, TRUE, 'BARCELONA', 'GASOLINA', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('6666FFF', 13, 'Blanco', 25000, TRUE, 'SALAMANCA', 'DIESEL', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('6778GGG', 14, 'Azul', 10000, TRUE, 'MADRID', 'ELECTRICO', 'CERO', 400, 4, TRUE, 5, 'AUTOMATICO'),
('7777GGG', 14, 'Azul', 10000, TRUE, 'MADRID', 'ELECTRICO', 'CERO', 400, 4, TRUE, 5, 'AUTOMATICO'),
('7878NNN', 21, 'Negro', 17000, TRUE, 'LEON', 'ELECTRICO', 'CERO', 300, 0, FALSE, 2, 'AUTOMATICO'),
('7890MNO', 5, 'Amarillo', 3000, TRUE, 'BALEARES', 'GASOLINA', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('8888HHH', 15, 'Blanco', 22000, TRUE, 'VALENCIA', 'GASOLINA', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('8999III', 16, 'Negro', 9000, TRUE, 'TOLEDO', 'GASOLINA', 'B', 0, 5, TRUE, 5, 'MANUAL'),
('9012GHI', 3, 'Blanco', 45000, TRUE, 'VALENCIA', 'DIESEL', 'C', 0, 3, TRUE, 3, 'MANUAL'),
('9999III', 16, 'Negro', 9000, TRUE, 'TOLEDO', 'GASOLINA', 'B', 0, 5, TRUE, 5, 'MANUAL'),
('A123BCD', 1, 'Gris', 18000, TRUE, 'MADRID', 'GASOLINA', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('B456CDE', 1, 'Blanco', 21000, FALSE, 'BARCELONA', 'GASOLINA', 'ECO', 0, 5, TRUE, 5, 'AUTOMATICO'),
('C789DEF', 5, 'Negro', 5000, TRUE, 'VALENCIA', 'GASOLINA', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('D012EFG', 5, 'Azul', 3000, TRUE, 'SEVILLA', 'GASOLINA', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('E345FGH', 14, 'Rojo', 15000, TRUE, 'MURCIA', 'ELECTRICO', 'CERO', 420, 4, TRUE, 5, 'AUTOMATICO'),
('F678GHI', 14, 'Negro', 8000, TRUE, 'MADRID', 'ELECTRICO', 'CERO', 390, 4, TRUE, 5, 'AUTOMATICO'),
('G901HIJ', 20, 'Gris', 27000, TRUE, 'ZARAGOZA', 'DIESEL', 'C', 0, 5, TRUE, 5, 'MANUAL'),
('H234IJK', 20, 'Blanco', 19000, FALSE, 'ALICANTE', 'DIESEL', 'C', 0, 5, TRUE, 5, 'MANUAL');

-- Insertar usuarios
INSERT INTO usuario (dni, nombre, apellidos, correo, contrasena, telefono, rol) VALUES
('LAbHrvBn9fDopYWhoH2DuQ==', 'Admin', 'Admin', 'admin@admin.com', '$2a$10$MLNLS.v0Lk7PXl9OpzGXPuQeEG/IwdYyao4QItwyeZ9/gzRtpeKxm', '000000000', 'ADMIN'),
('V+IGhTcfz3t9vNDXyo86vQ==', 'Juan', 'Perez', 'juan@example.com', '$2a$10$kUGD/49.zo/O9WjleYtWu.icuLg5Hia35dc1leU75Ml7nXdsuHrmu', '620212324', 'CLIENTE'),
('RwR3GARGkc3OyZatPIuJog==', 'Alicia', 'Gomez', 'alicia@example.com', '$2a$10$zy1fGbFBgr6jaBg0G.26lOWF8fhSd0gAoRvVFfIvPkhG4n/8bbotu', '629282726', 'CLIENTE');
-- Insertar reservas
INSERT INTO reserva (matricula, id_usuario, fecha_reserva, dias_reserva, precio) VALUES
('2345PQR', 1, '2025-05-29', 1, 30.00),
('2345PQR', 2, '2025-05-30', 2, 60.00),
('2345PQR', 3, '2025-06-01', 4, 120.00);

-- Insertar resenas
INSERT INTO resena (comentario, puntuacion, fecha, id_usuario, matricula, id_reserva) VALUES
('El BMW Serie 3 ofrece una conducción muy deportiva y suave a la vez. La respuesta del motor y el cambio automático fueron impecables, ideal para viajes por carretera', 5, '2025-05-30', 1, '2345PQR', 1),
('Disfruté mucho del confort interior y los asientos de piel. El sistema de navegación me guió sin fallos y el consumo se mantuvo dentro de lo esperado', 4, '2025-06-01', 2, '2345PQR', 2),
('La experiencia de lujo se nota en cada detalle: desde el sonido envolvente hasta el diseño minimalista del salpicadero. Aparcar con la cámara trasera y los sensores es pan comido', 5, '2025-06-05', 3, '2345PQR', 3);