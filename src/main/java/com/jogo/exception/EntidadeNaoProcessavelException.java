package com.jogo.exception;

public class EntidadeNaoProcessavelException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoProcessavelException(String mensagem) {
		super(mensagem);
	}
}
