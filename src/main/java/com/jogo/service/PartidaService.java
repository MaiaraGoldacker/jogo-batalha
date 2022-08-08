package com.jogo.service;

import com.jogo.model.Partida;

public interface PartidaService {

	public Partida pegaPartidaAtual(String usuario);

	public void iniciaPartida(String usuario);
	
	public void encerraPartida(Partida partida); 
}
