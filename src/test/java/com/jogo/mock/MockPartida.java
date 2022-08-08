package com.jogo.mock;

import java.time.LocalDateTime;

import com.jogo.model.Partida;

public class MockPartida {

	public static Partida buildPartida() {
		return Partida.builder()
				.dataInicioPartida(LocalDateTime.now())
				.usuario(MockUsuario.buildUsuario())				
				.build();
	}
}
