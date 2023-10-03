CREATE DATABASE sistema;

USE sistema;

CREATE TABLE ideia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    urgencia INT NOT NULL 
);
