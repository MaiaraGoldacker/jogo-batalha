package com.jogo.service;

import com.jogo.model.Usuario;
import com.jogo.request.UsuarioRequest;

public interface UsuarioService {

	Usuario buscaUsuario(String usuario);
	
	UsuarioRequest salvaUsuario(UsuarioRequest usuarioRequest);
}
