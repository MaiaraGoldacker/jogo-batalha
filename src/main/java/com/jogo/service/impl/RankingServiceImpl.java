package com.jogo.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jogo.model.Usuario;
import com.jogo.repository.RoundRepository;
import com.jogo.repository.UsuarioRepository;
import com.jogo.response.UsuarioRankingResponse;
import com.jogo.service.RankingService;

@Service
public class RankingServiceImpl implements RankingService {

	@Autowired	
	private RoundRepository roundRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public List<UsuarioRankingResponse> geraHistoricoParaRanking() {
		List<UsuarioRankingResponse> listaDeUsuariosEPontos = new ArrayList<>();
		List<Usuario> usuarios = usuarioRepository.findAll();

		for (var usuario : usuarios){
			var total = pegaTotalDePontosPorUsuario(usuario.getId());
			
			if (total != 0) {								
				float pontuacaoFinal = (calculaPercentualDeAcertos(usuario.getId(), total) / 100) * total;
			
				listaDeUsuariosEPontos.add(UsuarioRankingResponse.builder()
						.scoreTotal(pontuacaoFinal)
						.usuario(usuario.getUsuario())
						.build());
			}
		}
		listaDeUsuariosEPontos.stream().sorted(Comparator.comparing(UsuarioRankingResponse::getScoreTotal).reversed());
	
		 return listaDeUsuariosEPontos;
	}
	
	private float pegaTotalDePontosPorUsuario(Long usuarioId) {
		 return Objects.isNull(roundRepository.pegaTotalDeRoundsPorUsuario(usuarioId))? 
				 0 : roundRepository.pegaTotalDeRoundsPorUsuario(usuarioId);
	}
	
	private float pegaTotalDeAcertosPorUsuario(Long usuarioId) {
		return Objects.isNull(roundRepository.pegaTotalDeRoundsAcertosPorUsuario(usuarioId))? 
				0 : roundRepository.pegaTotalDeRoundsAcertosPorUsuario(usuarioId);
	}
	
	private float calculaPercentualDeAcertos(Long usuarioId, float totalDeRounds) {
		var totalAcertos = pegaTotalDeAcertosPorUsuario(usuarioId);
		
		return (totalAcertos * 100) / totalDeRounds;		
	}
}
