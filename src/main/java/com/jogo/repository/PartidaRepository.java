package com.jogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jogo.model.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

	
	@Query(value =
			"select * from partida p, usuario u " + 
			"where  p.id_usuario = u.id " + 
			"and u.usuario = :usuario " + 
			"and p.data_fim_partida is null  ", 
		nativeQuery = true)
	Partida pegaPartidaAtualPorUsuario(String usuario);
	
	@Query(value = "select count(*) from round " + 
			"where id_partida = :partidaId  " + 
			"and is_acertou = :filtrarPontosDeAcerto ",
		nativeQuery = true)
	Integer quantidadeDePontosNaPartida(Boolean filtrarPontosDeAcerto, Long partidaId);
	
}
