package com.jogo.mock;

import java.util.ArrayList;
import java.util.List;

import com.jogo.model.Usuario;
import com.jogo.request.UsuarioRequest;

public class MockUsuario {

	public static Usuario buildUsuario() {
		return Usuario.builder()
				.usuario("John")
				.senha("123")
				.build();
	}
	
	public static UsuarioRequest buildUsuarioRequest() {
		return UsuarioRequest.builder()
				.usuario("John")
				.senha("123")
				.build();
	}
	
	public static List<Usuario> buildListaUsuario(){
		
		List<Usuario> listaRetorno = new ArrayList<>();
		
		listaRetorno.add(Usuario.builder().id(1L).usuario("John").build());
		listaRetorno.add(Usuario.builder().id(2L).usuario("Anna").build());
		listaRetorno.add(Usuario.builder().id(3L).usuario("Gen").build());
		
		return listaRetorno;
	}
}
