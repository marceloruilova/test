--liquibase formatted sql
--changeset touwolf:schema-000001

CREATE TABLE persons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    ci VARCHAR(50) NOT NULL UNIQUE,
    address VARCHAR(255),
    cellphone VARCHAR(15)
);

CREATE TABLE clients (
    person_id BIGINT PRIMARY KEY,
    client_id VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    state VARCHAR(20) NOT NULL,
    FOREIGN KEY (person_id) REFERENCES persons(id)
);
