package com.jogo.service.impl;

import java.util.List;
import com.jogo.mapper.FilmeRoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import com.jogo.model.FilmeRound;
import com.jogo.model.Round;
import com.jogo.model.Usuario;
import com.jogo.repository.FilmeRoundRepository;
import com.jogo.repository.RoundRepository;
import com.jogo.request.EscolhaRequest;
import com.jogo.response.RoundResponse;
import com.jogo.service.FilmeRoundService;
import com.jogo.service.PartidaService;
import com.jogo.service.RoundService;
import com.jogo.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoundServiceImpl implements RoundService {

	@Autowired
	private FilmeRoundService filmeRoundService;
	
	@Autowired
	private FilmeRoundRepository filmeRoundRepository;
	
	@Autowired
	private FilmeRoundMapper filmeRoundMapper;
	
	@Autowired
	private PartidaService partidaService;
	
	@Autowired
	private RoundRepository roundRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Value("${jogo.config.quantidade-filmes-por-round}")
	private Integer quantidadeDeFilmesPorRound;
	
	@Override
	@Transactional
	public List<RoundResponse> retornaRound(String usuario) throws Exception {
		
		var usuarioEncontrado = usuarioService.buscaUsuario(usuario);
		
		List<FilmeRound> listaDeFilmes = filmeRoundService.pegaFilmesDeRoundEmAberto(usuarioEncontrado);
		
		if (listaDeFilmes.size() == 0) {			
			listaDeFilmes = pegaFilmesParaNovoRound(listaDeFilmes, usuarioEncontrado);
		} else {
			log.info("Round antigo resgatado");
		}
		
		return filmeRoundMapper.toListResponse(listaDeFilmes);
	}
	
	@Override
	public String validaEscolhaRound(EscolhaRequest escolhaRequest, String usuario) {
		
		var usuarioEncontrado = usuarioService.buscaUsuario(usuario);
		var filmeRound = filmeRoundService.buscaFilmeRoundPorId(escolhaRequest.getOpcaoId(), usuarioEncontrado);
		Round round = filmeRound.getRound();

		Boolean ganhouRound = verificaSeEscolheuFilmeComMaiorPontuacao(filmeRound);
		round.setIsAcertou(ganhouRound);
		salvaRound(round);
		
		if (!ganhouRound) {
			partidaService.encerraPartida(round.getPartida());
			return "Resposta errada!";
		}
	
		return "Resposta certa!";
	}
	
	private Round salvaRound(Round round) {
		return roundRepository.save(round);
	}

	private List<FilmeRound> pegaFilmesParaNovoRound(List<FilmeRound> listaDeFilmes, Usuario usuario) throws Exception {
		
		Round round = salvaRound(Round.builder().partida(partidaService.pegaPartidaAtual(usuario.getUsuario())).build());		
		
		for (int i = 0; i < quantidadeDeFilmesPorRound; i++) {
			FilmeRound filmeRound = filmeRoundService.pegaNovoFilmeParaRound(round, usuario);		
			listaDeFilmes.add(filmeRound);
		}
		
		log.info("Novo round gerado id {} com novos filmes", round.getId());
		return listaDeFilmes;
	}

	private Boolean verificaSeEscolheuFilmeComMaiorPontuacao(FilmeRound filmeRound) {

		FilmeRound filmeComMaiorPontuacaoDoRound = filmeRoundRepository.pegaFilmeComMaiorPontuacaoPorRound(filmeRound.getRound().getId());
		return filmeComMaiorPontuacaoDoRound.getId() == filmeRound.getId();
	}
	
	

}
