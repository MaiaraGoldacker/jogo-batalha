package com.jogo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.jogo.exception.EntidadeNaoEncontradaException;
import com.jogo.mock.MockPartida;
import com.jogo.mock.MockUsuario;
import com.jogo.repository.PartidaRepository;
import com.jogo.service.impl.PartidaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PartidaServiceTest {

	@InjectMocks
	private PartidaServiceImpl partidaServiceImpl;
	
	@Mock
	private PartidaRepository partidaRepository;
	
	@Mock
	private UsuarioService usuarioService;

	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(partidaServiceImpl, "valorMaximoDeErros", 3);	
	}
	
	@Test
	void pegaPartidaAtualComSucessoTest() {

		when(partidaRepository.pegaPartidaAtualPorUsuario(any())).thenReturn(MockPartida.buildPartida());

		var partidaEncontrada = partidaServiceImpl.pegaPartidaAtual("John");

		assertThat(partidaEncontrada.getDataInicioPartida()).isNotNull();
		assertThat(partidaEncontrada.getDataFimPartida()).isNull();
	}
	
	@Test
	void naoEncontrouPartidaEmAbertoTest() {

		when(partidaRepository.pegaPartidaAtualPorUsuario(any())).thenReturn(null);
		
		EntidadeNaoEncontradaException exception = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> 
		partidaServiceImpl.pegaPartidaAtual("John"));
		
		Assertions.assertEquals(exception.getMessage(), "Não há partida em aberto para esse usuário. "
				+ "Gere uma nova partida através do endpoint /partidas/iniciar");
	}
	
	@Test
	void resgataPartidaJaIniciadaComSucessoTest() {
		
		when(partidaRepository.pegaPartidaAtualPorUsuario(any())).thenReturn(MockPartida.buildPartida());
		when(usuarioService.buscaUsuario(any())).thenReturn(MockUsuario.buildUsuario());
		
		partidaServiceImpl.iniciaPartida("John");
		
		verify(partidaRepository, Mockito.times(0)).save(any());		
	}
	
	@Test
	void iniciaPartidaComSucessoTest() {
		
		when(partidaRepository.pegaPartidaAtualPorUsuario(any())).thenReturn(null);
		when(usuarioService.buscaUsuario(any())).thenReturn(MockUsuario.buildUsuario());	
		
		partidaServiceImpl.iniciaPartida("John");
		
		verify(partidaRepository, Mockito.times(1)).save(any());		
	}
	
	@Test
	void encerraPartidaComSucessoTest() {
		
		when(partidaRepository.quantidadeDePontosNaPartida(any(), any())).thenReturn(3);			
		partidaServiceImpl.encerraPartida(MockPartida.buildPartida());		
		verify(partidaRepository, Mockito.times(1)).save(any());
	}
	
	@Test
	void naoEncerraPartidaPorqueNaoChegouNoTotalDeErrosMaximoTest() {
		
		when(partidaRepository.quantidadeDePontosNaPartida(any(), any())).thenReturn(1);			
		partidaServiceImpl.encerraPartida(MockPartida.buildPartida());		
		verify(partidaRepository, Mockito.times(0)).save(any());
	}

}
