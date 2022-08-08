package com.jogo.service;

import java.util.List;

import com.jogo.request.EscolhaRequest;
import com.jogo.response.RoundResponse;

public interface RoundService {
	
	List<RoundResponse> retornaRound(String usuario) throws Exception;

	String validaEscolhaRound(EscolhaRequest escolhaRequest, String usuario);
}
