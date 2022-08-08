package com.jogo.service;

import java.util.List;

import com.jogo.model.FilmeRound;
import com.jogo.model.Round;
import com.jogo.model.Usuario;

public interface FilmeRoundService {

	FilmeRound pegaNovoFilmeParaRound(Round round, Usuario usuario);
	
	FilmeRound  buscaFilmeRoundPorId(Long idFilmeRound, Usuario usuario);
	
	public List<FilmeRound> pegaFilmesDeRoundEmAberto(Usuario usuario);
}
