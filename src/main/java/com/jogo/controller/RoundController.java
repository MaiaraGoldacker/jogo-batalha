package com.jogo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogo.request.EscolhaRequest;
import com.jogo.response.RoundResponse;
import com.jogo.service.RoundService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rounds")
public class RoundController {

private final RoundService roundService;
	
	@ApiOperation(value = "Pega os filmes para o Round atual")
	@GetMapping
	public ResponseEntity<List<RoundResponse>> pegaOpcoesFilmesRound(@RequestHeader String usuario) throws Exception{
		return ResponseEntity.ok(roundService.retornaRound(usuario));
	}
	
	@ApiOperation(value = "Valida a opção de filme escolhida pelo jogador")
	@PostMapping
	public ResponseEntity<String> validaEscolhaFilme(@RequestHeader String usuario, 
			@RequestBody @Valid EscolhaRequest escolhaRequest) {
		return ResponseEntity.ok(roundService.validaEscolhaRound(escolhaRequest, usuario));		
	}
}
