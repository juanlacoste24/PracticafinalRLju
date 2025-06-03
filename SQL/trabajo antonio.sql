
CREATE DATABASE IF NOT EXISTS ControlGastosCoches;
USE ControlGastosCoches;


CREATE TABLE Usuario (
    uuid CHAR(36) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE Coche (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    matricula VARCHAR(15) NOT NULL UNIQUE,
    anio INT NOT NULL
);


CREATE TABLE Usuario_Coche (
    uuid_usuario CHAR(36),
    id_coche INT,
    PRIMARY KEY (uuid_usuario, id_coche),
    FOREIGN KEY (uuid_usuario) REFERENCES Usuario(uuid) ON DELETE CASCADE,
    FOREIGN KEY (id_coche) REFERENCES Coche(id) ON DELETE CASCADE
);


CREATE TABLE Gasto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_coche INT NOT NULL,
    tipo ENUM('gasolina', 'revisión', 'ITV', 'cambio de aceite', 'otros') NOT NULL,
    kilometraje INT NOT NULL,
    fecha DATE NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    descripcion TEXT,
    FOREIGN KEY (id_coche) REFERENCES Coche(id) ON DELETE CASCADE
);

INSERT INTO Usuario (uuid, nombre) VALUES 
(UUID(), 'Juan'),
(UUID(), 'David');


INSERT INTO Coche (marca, modelo, matricula, anio) VALUES
('Toyota', 'Corolla', '1234ABC', 2015),
('Ford', 'Focus', '5678DEF', 2018);

