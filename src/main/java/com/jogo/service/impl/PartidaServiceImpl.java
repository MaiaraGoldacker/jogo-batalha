package com.jogo.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jogo.exception.EntidadeNaoEncontradaException;
import com.jogo.model.Partida;
import com.jogo.model.Usuario;
import com.jogo.repository.PartidaRepository;
import com.jogo.service.PartidaService;
import com.jogo.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PartidaServiceImpl implements PartidaService {

	@Autowired
	private PartidaRepository partidaRepository;	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Value("${jogo.config.valor-maximo-erros}")
	private Integer valorMaximoDeErros;
	
	@Override
	public Partida pegaPartidaAtual(String usuario) {		
		Partida partida =  partidaRepository.pegaPartidaAtualPorUsuario(usuario);
		
		if (Objects.isNull(partida)) {
			throw new EntidadeNaoEncontradaException("Não há partida em aberto para esse usuário. "
				+ "Gere uma nova partida através do endpoint /partidas/iniciar");
		}
		return partida;
	}

	@Override
	public void iniciaPartida(String usuario) {
		Usuario usuarioEncontrado = usuarioService.buscaUsuario(usuario);		
		Partida partidaAtual = partidaRepository.pegaPartidaAtualPorUsuario(usuarioEncontrado.getUsuario());
		
		if (Objects.isNull(partidaAtual)){
			var partidaSalva = salvaPartida(Partida.builder().dataInicioPartida(LocalDateTime.now()).usuario(usuarioEncontrado).build());			
			log.info("criou nova partida Id {} para o usuário {}", partidaSalva, usuarioEncontrado.getUsuario());
		} else {
			log.info("resgatou partida não finalizada id {} para o usuário {}", partidaAtual.getId(), usuarioEncontrado.getUsuario());
		}
		
	}
	
	private Partida salvaPartida(Partida partida) {
		return partidaRepository.save(partida);
	}
	
	private Boolean verificaSeChegouAoNumeroMaximoDeErrosNaPartida(Partida partida) {
		Integer quantidadeDeVezesPerdeu = partidaRepository.quantidadeDePontosNaPartida(false, partida.getId());		
		if (quantidadeDeVezesPerdeu >= valorMaximoDeErros) return true;			
		
		return false;
	}

	@Override
	public void encerraPartida(Partida partida) {
		if (verificaSeChegouAoNumeroMaximoDeErrosNaPartida(partida)) {		
			partida.setDataFimPartida(LocalDateTime.now());
			salvaPartida(partida);
		}
		
	}
}
