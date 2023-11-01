package br.edu.ifrn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.domain.usuario.Usuario;
import br.edu.ifrn.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findUserById(Long userId) {
        return usuarioRepository.findById(userId).orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return null;
    }

    public Optional<Usuario> encontrarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    

    public void deletarUsuario(Long id) {
    }
}
