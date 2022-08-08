package com.jogo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "partida")
public class Partida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@Column(name = "data_inicio_partida")
	private LocalDateTime dataInicioPartida;
		
	@Column(name = "data_fim_partida")
	private LocalDateTime dataFimPartida;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Usuario usuario;
}
