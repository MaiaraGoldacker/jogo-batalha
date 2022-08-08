package com.jogo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogo.request.UsuarioRequest;
import com.jogo.service.UsuarioService;

import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private  UsuarioService usuarioService;

	@ApiOperation(value = "Cria um novo usuário para o jogo")
	@PostMapping
	public ResponseEntity<UsuarioRequest> salvar(@RequestBody @Valid UsuarioRequest usuario) {
		
		return new ResponseEntity<>(usuarioService.salvaUsuario(usuario), HttpStatus.CREATED);		
	}
}
