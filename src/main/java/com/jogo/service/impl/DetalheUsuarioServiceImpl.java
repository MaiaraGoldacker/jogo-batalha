package com.jogo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jogo.exception.EntidadeNaoEncontradaException;
import com.jogo.exception.EntidadeNaoProcessavelException;
import com.jogo.model.Usuario;
import com.jogo.repository.UsuarioRepository;
import com.jogo.request.UsuarioRequest;
import com.jogo.security.DetalheUsuarioData;
import com.jogo.service.UsuarioService;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService, UsuarioService {

	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }

        return new DetalheUsuarioData(usuario);
    }

	@Override
	public Usuario buscaUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario)
				.orElseThrow(() ->  new EntidadeNaoEncontradaException("Usuário não encontrado. Verifique!"));
	}

	@Override
	public UsuarioRequest salvaUsuario(UsuarioRequest usuario) {
		
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
			throw new EntidadeNaoProcessavelException("Usuário já está em uso. Tente com outro usuário!");
		}
		
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		usuarioRepository.save(Usuario.builder()
				 .usuario(usuario.getUsuario())
				 .senha(usuario.getSenha())
				 .build());
		 
		 return usuario;
	}	

}
