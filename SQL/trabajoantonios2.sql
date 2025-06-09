DROP DATABASE IF EXISTS practicafinalrlju;
CREATE DATABASE practicafinalrlju;
USE practicafinalrlju;


CREATE TABLE usuarios (
    uuid CHAR(36) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL 
);


CREATE TABLE Coche (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    matricula VARCHAR(15) NOT NULL UNIQUE,
    anio INT NOT NULL
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


INSERT INTO Usuarios (uuid, nombre, contrasena) VALUES
(UUID(), 'Juan', '1234'),
(UUID(), 'David', 'abcd');

INSERT INTO Coche (marca, modelo, matricula, anio) VALUES
('Toyota', 'Corolla', '1234ABC', 2015),
('Ford', 'Focus', '5678DEF', 2018);
