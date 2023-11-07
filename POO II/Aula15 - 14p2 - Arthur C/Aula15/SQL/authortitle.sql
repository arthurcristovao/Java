use aula14;

CREATE TABLE AuthorTitle (
	atAuthorsID INTEGER,
    atISBN INTEGER,
	FOREIGN KEY ( atAuthorsID ) REFERENCES authors ( authorsID ),
	FOREIGN KEY ( atISBN ) REFERENCES titles ( isbn )
);