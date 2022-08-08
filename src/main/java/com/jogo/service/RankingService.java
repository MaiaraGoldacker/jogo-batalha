package com.jogo.service;

import java.util.List;

import com.jogo.response.UsuarioRankingResponse;

public interface RankingService {

	public List<UsuarioRankingResponse> geraHistoricoParaRanking();
}
