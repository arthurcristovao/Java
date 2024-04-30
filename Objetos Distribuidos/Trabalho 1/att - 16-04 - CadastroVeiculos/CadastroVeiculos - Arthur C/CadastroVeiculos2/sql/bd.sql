-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS registro_de_veiculos;

-- Utilização do banco de dados
USE registro_de_veiculos;

-- Tabela Veiculo
CREATE TABLE IF NOT EXISTS Veiculo (
    idVeiculo INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cor VARCHAR(50) NOT NULL,
    modelo VARCHAR(100) NOT NULL
);

-- Tabela Fabricante
CREATE TABLE IF NOT EXISTS Fabricante (
    idFabricante INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    paisOrigem VARCHAR(100) NOT NULL
);

-- Tabela Fabricacao (relacionamento entre Veiculo e Fabricante)
CREATE TABLE IF NOT EXISTS Fabricacao (
    idFabricacao INT AUTO_INCREMENT PRIMARY KEY,
    veiculo_id INT NOT NULL,
    fabricante_id INT NOT NULL,
    dataFabricacao DATE NOT NULL,
    paisFabricacao VARCHAR(100) NOT NULL,
    FOREIGN KEY (veiculo_id) REFERENCES Veiculo(idVeiculo),
    FOREIGN KEY (fabricante_id) REFERENCES Fabricante(idFabricante)
);
