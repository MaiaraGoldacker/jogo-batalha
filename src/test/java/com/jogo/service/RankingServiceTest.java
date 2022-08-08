package com.jogo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.jogo.mock.MockUsuario;
import com.jogo.repository.RoundRepository;
import com.jogo.repository.UsuarioRepository;
import com.jogo.service.impl.RankingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RankingServiceTest {
	
	@InjectMocks
	private RankingServiceImpl rankingServiceImpl;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private RoundRepository roundRepository;
	
	@Test
	public void geraHistoricoParaRankingComSucesso() {
		when(usuarioRepository.findAll()).thenReturn(MockUsuario.buildListaUsuario());
		when(roundRepository.pegaTotalDeRoundsPorUsuario(any())).thenReturn(150);
		when(roundRepository.pegaTotalDeRoundsAcertosPorUsuario(any())).thenReturn(60);
		
		var listaRetorno = rankingServiceImpl.geraHistoricoParaRanking();
		
		assertThat(listaRetorno).isNotNull();
		Assertions.assertEquals(listaRetorno.size(), 3);
	}

}
