CREATE DATABASE practicafinalrlju;

USE practicafinalrlju;

CREATE TABLE usuarios (
    uuid VARCHAR(36) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL
);

CREATE TABLE Coche (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    matricula VARCHAR(10) NOT NULL UNIQUE,
    anio INT NOT NULL
);

CREATE TABLE usuarios_coches (
    usuario_uuid VARCHAR(36),
    coche_id INT,
    PRIMARY KEY (usuario_uuid, coche_id),
    FOREIGN KEY (usuario_uuid) REFERENCES usuarios(uuid),
    FOREIGN KEY (coche_id) REFERENCES coches(id)
);

CREATE TABLE gastos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    coche_id INT,
    tipo VARCHAR(50) NOT NULL,
    kilometraje INT NOT NULL,
    fecha DATE NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    descripcion TEXT,
    FOREIGN KEY (coche_id) REFERENCES coches(id)
);
select * from Coche;