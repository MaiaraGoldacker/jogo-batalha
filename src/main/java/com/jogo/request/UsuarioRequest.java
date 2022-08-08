package com.jogo.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

	@NotBlank
	private String usuario;
	
	@NotBlank
	private String senha;
}
