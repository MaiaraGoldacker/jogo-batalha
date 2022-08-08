package com.jogo.model;

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
@Table(name = "filme_round")
public class FilmeRound {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@Column(name = "id_imdb")
	private String idImdb;
	 
	private String titulo;
	 
	private Float pontuacao;
	 
	@ManyToOne
	@JoinColumn(name = "id_roud", referencedColumnName = "id")
	private Round round;

}
