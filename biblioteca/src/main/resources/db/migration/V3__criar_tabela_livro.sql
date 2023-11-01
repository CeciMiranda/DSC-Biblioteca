CREATE TABLE livro (
    livro_id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    isbn VARCHAR(200),
    ano_publicacao INT,
    qtd_copias_disp INT,
    autor_id INT,
    FOREIGN KEY (autor_id) REFERENCES autor(autor_id)
);
