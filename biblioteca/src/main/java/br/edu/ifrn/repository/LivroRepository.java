package br.edu.ifrn.repository;

import br.edu.ifrn.domain.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
}
