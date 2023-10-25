CREATE DATABASE Aula12;

USE Aula12; -- Certifique-se de estar usando o banco de dados Aula12

CREATE TABLE Authors (
    authorsID INT PRIMARY KEY auto_increment,
    firstName VARCHAR(255),
    lastName VARCHAR(255)
);
