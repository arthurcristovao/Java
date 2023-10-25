USE Aula12; -- Certifique-se de estar usando o banco de dados Aula12

CREATE TABLE Titles (
    ISBN INT PRIMARY KEY auto_increment,
	Title VARCHAR(255),
    EditorNumber VARCHAR(255),
    Copyright VARCHAR(255)
);
