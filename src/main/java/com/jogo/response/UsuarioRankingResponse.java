package com.jogo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRankingResponse {

	private String usuario;
	
	private float scoreTotal;
}
