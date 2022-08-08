package com.jogo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import com.jogo.mapper.FilmeRoundMapper;
import com.jogo.mock.MockFilmeRound;
import com.jogo.mock.MockPartida;
import com.jogo.mock.MockRound;
import com.jogo.mock.MockUsuario;
import com.jogo.repository.FilmeRoundRepository;
import com.jogo.repository.RoundRepository;
import com.jogo.request.EscolhaRequest;
import com.jogo.response.RoundResponse;
import com.jogo.service.impl.RoundServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RoundServiceTest {
	
	@InjectMocks
	private RoundServiceImpl roundServiceImpl;
	
	@Mock
	private RoundRepository roundRepository;
	
	@Mock
	private UsuarioService usuarioService;
	
	@Mock
	private FilmeRoundService filmeRoundService;
	
	@Mock
	private FilmeRoundMapper filmeRoundMapper;
	
	@Mock
	private PartidaService partidaService;
	
	@Mock
	private FilmeRoundRepository filmeRoundRepository;
	
	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(roundServiceImpl, "quantidadeDeFilmesPorRound", 2);	
	}

	@Test
	public void retornaRoundResgatadoComSucessoTest() throws Exception {
		when(usuarioService.buscaUsuario(any())).thenReturn(MockUsuario.buildUsuario());

		when(filmeRoundService.pegaFilmesDeRoundEmAberto(any())).thenReturn(MockFilmeRound.buildListaFilmeRound());
		when(filmeRoundMapper.toListResponse(any())).thenReturn(MockFilmeRound.buildListaFilmeRoundResponse());
		
		List<RoundResponse> listaResponse = roundServiceImpl.retornaRound("John");
	
		assertThat(listaResponse).isNotNull();
	}
	
	@Test
	public void retornaNovoRoundComSucessoTest() throws Exception {
		when(usuarioService.buscaUsuario(any())).thenReturn(MockUsuario.buildUsuario());
		when(partidaService.pegaPartidaAtual(any())).thenReturn(MockPartida.buildPartida());
		when(filmeRoundService.pegaFilmesDeRoundEmAberto(any())).thenReturn(new ArrayList<>());
		when(filmeRoundMapper.toListResponse(any())).thenReturn(MockFilmeRound.buildListaFilmeRoundResponse());
		
		when(filmeRoundService.pegaNovoFilmeParaRound(any(), any())).thenReturn(MockFilmeRound.buildFilmeRound());	
		when(roundRepository.save(any())).thenReturn(MockRound.buildRound());
		
		List<RoundResponse> listaResponse = roundServiceImpl.retornaRound("John");
	
		assertThat(listaResponse).isNotNull();
	}
	
	@Test
	public void validaEscolhaCorretaRoundTest() {
		when(usuarioService.buscaUsuario(any())).thenReturn(MockUsuario.buildUsuario());
		when(filmeRoundService.buscaFilmeRoundPorId(any(), any())).thenReturn(MockFilmeRound.buildFilmeRound());
		
		when(filmeRoundRepository.pegaFilmeComMaiorPontuacaoPorRound(any())).thenReturn(MockFilmeRound.buildFilmeRound());
		
		
		String retorno = roundServiceImpl.validaEscolhaRound(EscolhaRequest.builder().opcaoId(2L).build(), "John");
		
		Assertions.assertEquals(retorno, "Resposta certa!");
	}
	
	@Test
	public void validaEscolhaErradaRoundTest() {
		when(usuarioService.buscaUsuario(any())).thenReturn(MockUsuario.buildUsuario());
		when(filmeRoundService.buscaFilmeRoundPorId(any(), any())).thenReturn(MockFilmeRound.buildFilmeRound());
		
		
		var filmeRound = MockFilmeRound.buildFilmeRound();
		filmeRound.setId(8L);
		when(filmeRoundRepository.pegaFilmeComMaiorPontuacaoPorRound(any())).thenReturn(filmeRound);
		
		
		String retorno = roundServiceImpl.validaEscolhaRound(EscolhaRequest.builder().opcaoId(2L).build(), "John");
		
		Assertions.assertEquals(retorno, "Resposta errada!");
	}
}
