CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(255) NOT NULL UNIQUE,
    account_type VARCHAR(100) NOT NULL,
    initial_balance DECIMAL(10, 2) NOT NULL,
    state VARCHAR(100) NOT NULL,
    client_id VARCHAR(255) NOT NULL
);

CREATE TABLE movements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    movement_type VARCHAR(100) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    saldo DECIMAL(10, 2) NOT NULL,
    cliente_id VARCHAR(255) NOT NULL
);
