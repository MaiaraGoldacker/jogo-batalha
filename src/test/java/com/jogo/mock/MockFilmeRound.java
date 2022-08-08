package com.jogo.mock;

import java.util.ArrayList;
import java.util.List;

import com.jogo.model.FilmeRound;
import com.jogo.response.RoundResponse;

public class MockFilmeRound {

	public static List<FilmeRound> buildListaFilmeRound() {
		
		List<FilmeRound> listaRetorno = new ArrayList<>();
		
		listaRetorno.add(FilmeRound.builder().id(1L).idImdb("aaa").titulo("teste filme 1").build());
		listaRetorno.add(FilmeRound.builder().id(2L).idImdb("bbb").titulo("teste filme 2").build());
		
		return listaRetorno;
	}
	
	public static List<RoundResponse> buildListaFilmeRoundResponse() {
		
		List<RoundResponse> listaRetorno = new ArrayList<>();
		
		listaRetorno.add(RoundResponse.builder().opcaoId(2L).titulo("teste filme 1").build());
		listaRetorno.add(RoundResponse.builder().opcaoId(3L).titulo("teste filme 2").build());
		
		return listaRetorno;
	}
	
	public static FilmeRound buildFilmeRound() {		
		return FilmeRound.builder().idImdb("aaa").titulo("teste filme 1").round(MockRound.buildRound()).build();		
	}
	
	
}
