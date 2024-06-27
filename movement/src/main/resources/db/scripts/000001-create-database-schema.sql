CREATE TABLE cuentas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(255) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(100) NOT NULL,
    saldo_inicial DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    cliente_id VARCHAR(255) NOT NULL
);

CREATE TABLE movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP NOT NULL,
    tipo_movimiento VARCHAR(100) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL,
    cliente_id VARCHAR(255) NOT NULL
);
