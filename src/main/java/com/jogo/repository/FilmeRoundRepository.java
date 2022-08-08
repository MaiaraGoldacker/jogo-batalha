package com.jogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jogo.model.FilmeRound;
import com.jogo.model.Round;

@Repository
public interface FilmeRoundRepository extends JpaRepository<FilmeRound, Long>{

	
	@Query(value =
			"SELECT EXISTS (" +
			"select fr.id from filme_round fr, partida p, round  r " + 
			"where p.id = r.id_partida " + 
			"and r.id = fr.id_roud " + 
			"and p.id_usuario = :idUsuario " + 
			"and fr.id_imdb = :idImdb " + 
			"and p.data_fim_partida is null)",
		nativeQuery = true)
	Boolean jaUtilizouOImdbNoRoundAtual(Long idUsuario, String idImdb);
	
	@Query(value = 
			"select * from filme_round fr " + 
			"where fr.id_roud = :roundId " + 
			"order by pontuacao desc " + 
			"limit 1",
		nativeQuery = true)
	FilmeRound pegaFilmeComMaiorPontuacaoPorRound(Long roundId);
	
	List<FilmeRound> findByRound(Round round);
}
