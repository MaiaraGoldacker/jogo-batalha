package com.jogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.jogo.model.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
	
	Round findByIsAcertou(Boolean isAcertou);

	@Query(value= 
			"select count(*) " + 
			"from round r, partida p " + 
			"where p.id = r.id_partida " + 
			"and p.id_usuario = :usuarioId " + 
			"group by p.id_usuario " +
			"limit 1 ", 
		nativeQuery = true)
	Integer pegaTotalDeRoundsPorUsuario(Long usuarioId);
	
	@Query(value= 
			"select count(*) " + 
			"from round r, partida p " + 
			"where p.id = r.id_partida " + 
			"and (r.is_acertou = true) " +
			"and p.id_usuario = :usuarioId " + 
			"group by p.id_usuario ", 
		nativeQuery = true)
	Integer pegaTotalDeRoundsAcertosPorUsuario(Long usuarioId);
	
	

}
