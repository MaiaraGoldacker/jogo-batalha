package com.jogo.mock;

import com.jogo.model.Round;

public class MockRound {

	public static Round buildRound() {
		return Round.builder()
				.partida(MockPartida.buildPartida())
				.build();
	}
}
