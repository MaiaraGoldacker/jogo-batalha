package com.jogo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jogo.model.FilmeRound;
import com.jogo.response.RoundResponse;

@Component
public class FilmeRoundMapper {

	public List<RoundResponse> toListResponse(List<FilmeRound> filmeRoundList) {
		 
		List<RoundResponse> listaRetorno = new ArrayList<>();
		filmeRoundList.forEach(filmeRound -> {
			listaRetorno.add(RoundResponse.builder().opcaoId(filmeRound.getId()).titulo(filmeRound.getTitulo()).build());
		});
		 
		return listaRetorno;
	}
	
	
}
