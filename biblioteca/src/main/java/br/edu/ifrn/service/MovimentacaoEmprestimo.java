package br.edu.ifrn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.domain.livro.Livro;
import br.edu.ifrn.domain.usuario.Usuario;
import br.edu.ifrn.repository.LivroRepository;
import br.edu.ifrn.repository.UsuarioRepository;

@Service
public class MovimentacaoEmprestimo {
   
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    @Autowired
    public MovimentacaoEmprestimo(UsuarioRepository usuarioRepository, LivroRepository livroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public void realizarEmprestimo(Long idLivro, Long idUsuario) {
        Livro livro = livroRepository.findById(idLivro).orElse(null);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);

        if (livro != null && usuario != null && livro.getQtdCopiasDisp() > 0) {
            livro.setQtdCopiasDisp(livro.getQtdCopiasDisp() - 1);
            livroRepository.save(livro);

            usuario.adicionarLivroEmprestado(livro);
            usuarioRepository.save(usuario);
        } else {
           
        }
    }
}
