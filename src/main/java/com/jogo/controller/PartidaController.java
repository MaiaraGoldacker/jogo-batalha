package com.jogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.jogo.service.PartidaService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/partidas")
public class PartidaController {
	
	private final PartidaService partidaService;
	
	@ApiOperation(value = "Iniciar uma nova partida")
	@PostMapping("/iniciar")
	@ResponseStatus(HttpStatus.CREATED)
	public void iniciaPartida(@RequestHeader String usuario) {		
		partidaService.iniciaPartida(usuario);
	}
}
