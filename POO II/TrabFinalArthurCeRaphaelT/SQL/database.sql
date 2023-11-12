CREATE DATABASE IF NOT EXISTS Sistema;

USE Sistema;

CREATE TABLE IF NOT EXISTS Cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    endereco VARCHAR(100),
    telefone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Carro (
    idCarro INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    ano INT,
    disponivel BOOLEAN
);

CREATE TABLE IF NOT EXISTS Aluguel (
    idAluguel INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT,
    carro_id INT,
    dataAluguel DATE,
    dataDevolucao DATE,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(idCliente),
    FOREIGN KEY (carro_id) REFERENCES Carro(idCarro)
);

CREATE TABLE IF NOT EXISTS Avaliacao (
    idAvaliacao INT PRIMARY KEY AUTO_INCREMENT,
    aluguel_id INT,
    nota INT,
    comentario TEXT,
    FOREIGN KEY (aluguel_id) REFERENCES Aluguel(idAluguel)
);
