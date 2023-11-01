CREATE TABLE emprestimo (
    emprestimo_id INT AUTO_INCREMENT PRIMARY KEY,
    data_emprestimo DATE,
    data_devolucao DATE,
    livro_id INT,
    usuario_id INT,
    FOREIGN KEY (livro_id) REFERENCES livro(livro_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id)
);