package br.edu.ifrn.repository;

import br.edu.ifrn.domain.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    
}
