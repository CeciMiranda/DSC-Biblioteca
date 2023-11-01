package br.edu.ifrn.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifrn.domain.usuario.Usuario;
import br.edu.ifrn.repository.UsuarioRepository;
import br.edu.ifrn.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    
    
    private final UsuarioService usuarioService;
    private final UsuarioRepository repository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository repository) {
        this.usuarioService = usuarioService;
        this.repository = repository;
    }



    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(@PageableDefault(size = 30, sort = { "nome" }) Pageable paginacao) {
        var usuarios = repository.findAll(paginacao);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.encontrarPorId(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
        @Transactional
        public ResponseEntity<Object> cadastrar(@RequestBody @Valid Usuario usuario,
                UriComponentsBuilder uriBuilder) {
            Usuario usuarioLocal = repository.save(usuario);
            var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioLocal.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity<Object> excluir(@PathVariable Long id) {
            var usuario = repository.getReferenceById(id);
            repository.delete(usuario);
            return ResponseEntity.noContent().build();
        }

    @PutMapping
    @Transactional
    public ResponseEntity<Usuario> atualizar(@RequestBody @Valid Usuario usuario) {
        Usuario usuarioLocal = repository.findById(
                usuario.getId()).get();

        usuarioLocal.setNome(usuario.getNome());

        return ResponseEntity.ok(usuarioLocal);
    }
}
