package com.jogo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.jogo.client.OmdbApi;
import com.jogo.exception.EntidadeNaoEncontradaException;
import com.jogo.mock.MockFilmeRound;
import com.jogo.mock.MockRound;
import com.jogo.mock.MockSearchOmdbApi;
import com.jogo.mock.MockUsuario;
import com.jogo.repository.FilmeRoundRepository;
import com.jogo.repository.RoundRepository;
import com.jogo.service.impl.FilmeRoundServiceImpl;
import com.jogo.utils.RoundUtils;

@ExtendWith(MockitoExtension.class)
public class FilmeRoundServiceTest {
	
	@InjectMocks
	private FilmeRoundServiceImpl filmeRoundServiceImpl;
	
	@Mock
	private FilmeRoundRepository filmeRoundRepository;
	
	@Mock
	private OmdbApi omdbApi;
	
	@Mock
	private RoundUtils roundUtils;
	
	@Mock
	private RoundRepository roundRepository;
	
	@BeforeEach
	void setUp() {
		ReflectionTestUtils.setField(roundUtils, "externalAccessKeyOmdbApi", "8fa53dcb");	
	}

	@Test
	public void pegaNovoFilmeParaRoundComSucessoTest() throws Exception {

		when(filmeRoundRepository.jaUtilizouOImdbNoRoundAtual(any(), any())).thenReturn(false);		
		when(roundUtils.listaGenericaDeFilmes()).thenReturn(MockSearchOmdbApi.buildSearchOmdbApiSimple());
		when(roundUtils.retornaFilmeEspecificoServicoExterno(any())).thenReturn(MockSearchOmdbApi.buildSearchOmdbApiFull());
		
		when(filmeRoundRepository.save(any())).thenReturn(MockFilmeRound.buildFilmeRound());
		
		var filmeRound = filmeRoundServiceImpl.pegaNovoFilmeParaRound(MockRound.buildRound(), MockUsuario.buildUsuario());

		assertThat(filmeRound).isNotNull();
	}
	
	@Test
	public void buscaFilmeRoundPorIdComSucessoTest() {
		
		when(filmeRoundRepository.findByRound(any())).thenReturn(MockFilmeRound.buildListaFilmeRound());
		when(roundRepository.findByIsAcertou(any())).thenReturn(MockRound.buildRound());
		
		filmeRoundServiceImpl.buscaFilmeRoundPorId(2L, MockUsuario.buildUsuario());
	}
	
	@Test
	public void geraErroAoBuscarFilmeRoundPorIdTest() {
		
		when(roundRepository.findByIsAcertou(any())).thenReturn(MockRound.buildRound());
		
		EntidadeNaoEncontradaException exception = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> 
		filmeRoundServiceImpl.buscaFilmeRoundPorId(2L, MockUsuario.buildUsuario()));
		
		Assertions.assertEquals(exception.getMessage(), "Id do filme informado não foi encontrado. Verifique!");
		
		
	}
}
