package br.edu.ifrn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;


import br.edu.ifrn.domain.livro.Livro;
import br.edu.ifrn.repository.LivroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

public class LivroController {
    
    @Autowired
    private LivroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Livro livro,
            UriComponentsBuilder uriBuilder) {
        Livro livroLocal = repository.save(livro);
        var uri = uriBuilder.path("/livro/{id}").buildAndExpand(livroLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<Livro>> listar(@PageableDefault(size = 30, sort = { "nome" }) Pageable paginacao) {
        var livro = repository.findAll(paginacao);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> detalhar(@PathVariable Long id) {
        var livro = repository.getReferenceById(id);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var livro = repository.getReferenceById(id);
        livro.setAtivo(false);
        return ResponseEntity.noContent().build();
    }
}
