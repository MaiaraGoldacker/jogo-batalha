package com.jogo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.jogo.exception.EntidadeNaoEncontradaException;
import com.jogo.exception.EntidadeNaoProcessavelException;
import com.jogo.mock.MockUsuario;
import com.jogo.repository.UsuarioRepository;
import com.jogo.service.impl.DetalheUsuarioServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
	
	@InjectMocks
	private DetalheUsuarioServiceImpl detalheUsuarioServiceImpl;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private PasswordEncoder encoder;
	
	@Test
	void criaUsuarioComSucessoTest() {

		when(usuarioRepository.save(any())).thenReturn(MockUsuario.buildUsuario());
		when(encoder.encode(any())).thenReturn("7c86d616-76b0-498a-94b8-0108e44c7009");

		var usuarioSalvo = detalheUsuarioServiceImpl.salvaUsuario(MockUsuario.buildUsuarioRequest());

		assertThat(usuarioSalvo.getUsuario()).isNotNull();
	}
	
	@Test
	void erroAoCriarUsuarioComUsernameDuplicadoTest() {

		when(usuarioRepository.findByUsuario(any())).thenReturn(Optional.of(MockUsuario.buildUsuario()));
	
		EntidadeNaoProcessavelException exception = Assertions.assertThrows(EntidadeNaoProcessavelException.class, () -> 
		detalheUsuarioServiceImpl.salvaUsuario(MockUsuario.buildUsuarioRequest()));
		
		Assertions.assertEquals(exception.getMessage(), "Usuário já está em uso. Tente com outro usuário!");
	}

	@Test
	void erroAoBuscaUsuarioNaoEncontradoTest() {

		when(usuarioRepository.findByUsuario(any())).thenReturn(Optional.empty());
	
		EntidadeNaoEncontradaException exception = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> 
		detalheUsuarioServiceImpl.buscaUsuario("John"));
		
		Assertions.assertEquals(exception.getMessage(), "Usuário não encontrado. Verifique!");
	}

	@Test
	void buscaUsuarioComSucessoTest() {

		when(usuarioRepository.findByUsuario(any())).thenReturn(Optional.of(MockUsuario.buildUsuario()));
		
		var usuarioEncontrado = detalheUsuarioServiceImpl.buscaUsuario("John");
		
		Assertions.assertEquals(usuarioEncontrado.getUsuario(),MockUsuario.buildUsuario().getUsuario());
	}

}
