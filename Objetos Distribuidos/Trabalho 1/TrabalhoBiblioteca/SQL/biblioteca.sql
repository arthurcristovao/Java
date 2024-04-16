CREATE DATABASE IF NOT EXISTS BibliotecaDB;
USE BibliotecaDB;

CREATE TABLE IF NOT EXISTS Leitor (
    leitor_id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Livro (
    livro_id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS LivroAutor (
    livro_id INT,
    autor VARCHAR(100) NOT NULL,
    PRIMARY KEY (livro_id, autor),
    FOREIGN KEY (livro_id) REFERENCES Livro(livro_id)
);

CREATE TABLE IF NOT EXISTS Biblioteca (
    biblioteca_id INT AUTO_INCREMENT PRIMARY KEY,
    livro_id INT,
    leitor_id INT,
    FOREIGN KEY (livro_id) REFERENCES Livro(livro_id),
    FOREIGN KEY (leitor_id) REFERENCES Leitor(leitor_id)
);
