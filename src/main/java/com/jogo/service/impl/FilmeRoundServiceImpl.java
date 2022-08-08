package com.jogo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jogo.exception.EntidadeNaoEncontradaException;
import com.jogo.model.FilmeRound;
import com.jogo.model.Round;
import com.jogo.model.Usuario;
import com.jogo.repository.FilmeRoundRepository;
import com.jogo.repository.RoundRepository;
import com.jogo.response.SearchOmdbApiSimple;
import com.jogo.response.SearchOmdbApiSimple.OmdbApiSimple;
import com.jogo.response.SearchOmdbApiSimple.SearchOmdbApiFull;
import com.jogo.service.FilmeRoundService;
import com.jogo.utils.RoundUtils;

@Service
public class FilmeRoundServiceImpl implements FilmeRoundService {
	
	@Autowired
	private RoundUtils roundUtils;
	
	@Autowired
	private FilmeRoundRepository filmeRoundRepository;
	
	@Autowired
	private RoundRepository roundRepository;

	@Override
	@Transactional
	public FilmeRound pegaNovoFilmeParaRound(Round round, Usuario usuario) throws Exception {
		var searchOmdbApiFull = verificaSeFilmeJaFoiUtilizadoNaPartida(roundUtils.listaGenericaDeFilmes(), usuario);

		return createFilmeRound(searchOmdbApiFull, round);
	}
	
	private SearchOmdbApiFull verificaSeFilmeJaFoiUtilizadoNaPartida(SearchOmdbApiSimple listaDesafio, Usuario usuario) throws Exception {
		
		Integer tamanhoDaLista = listaDesafio.getSearch().size();		
		OmdbApiSimple omdbApiSimple = null;
		do {	
			omdbApiSimple =  listaDesafio.getSearch()
					.get(RoundUtils.getRandomIndiceParaLista(tamanhoDaLista));
		} while(Objects.nonNull(omdbApiSimple) && 
				filmeRoundRepository.jaUtilizouOImdbNoRoundAtual(usuario.getId(), omdbApiSimple.getImdbID()));
		
		return roundUtils.retornaFilmeEspecificoServicoExterno(omdbApiSimple.getImdbID());
	}

	private FilmeRound createFilmeRound(SearchOmdbApiFull searchOmdbApiFull, Round round) {
		
		var filmeRound = FilmeRound.builder().idImdb(searchOmdbApiFull.getImdbID())
		.pontuacao(calculaPontuacao(searchOmdbApiFull.getImdbRating(), searchOmdbApiFull.getImdbVotes()))
		.titulo(searchOmdbApiFull.getTitle())				
		.round(round)
		.build();
		
		return filmeRoundRepository.save(filmeRound);
	}
	
	private Float calculaPontuacao(BigDecimal imdbRating, String imdbVotes) {
		return imdbRating.floatValue() * getNumeroDeVotosFormatados(imdbVotes);
	}
	
	private Integer getNumeroDeVotosFormatados(String imdbVotes) {
		 return Integer.valueOf(imdbVotes.replace(",", ""));
	}
	
	@Override
	public List<FilmeRound> pegaFilmesDeRoundEmAberto(Usuario usuario) {
		
		Round round = roundRepository.findByIsAcertou(null);
		
		if (Objects.isNull(round)) return new ArrayList<>();		
		return filmeRoundRepository.findByRound(round);
	}

	@Override
	public FilmeRound buscaFilmeRoundPorId(Long idFilmeRound, Usuario usuario) {
		
		for (var filmeRound : pegaFilmesDeRoundEmAberto(usuario)) {
			if (filmeRound.getId() == idFilmeRound) {
				return filmeRound;
			}
		}

		throw new EntidadeNaoEncontradaException("Id do filme informado não foi encontrado. Verifique!");
	}

}
