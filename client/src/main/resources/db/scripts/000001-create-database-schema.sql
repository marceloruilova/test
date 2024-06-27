--liquibase formatted sql
--changeset touwolf:schema-000001

CREATE TABLE personas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(10) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(50) NOT NULL UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(15)
);

CREATE TABLE clientes (
    persona_id BIGINT PRIMARY KEY,
    cliente_id VARCHAR(50) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES personas(id)
);
