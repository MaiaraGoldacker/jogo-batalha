package com.jogo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogo.response.UsuarioRankingResponse;
import com.jogo.service.RankingService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/ranking")
public class RankingController {
	
	private final RankingService rankingService;
	
	@ApiOperation(value = "Busca Ranking do melhor para o pior colocado")
	@GetMapping
	public ResponseEntity<List<UsuarioRankingResponse>> pegaOpcoesFilmesRound() {
		return ResponseEntity.ok(rankingService.geraHistoricoParaRanking());		
	}

}
