CREATE TABLE filme_round (
	id bigserial NOT NULL,
	id_imdb varchar not NULL,	
	pontuacao decimal not NULL,
	titulo varchar not null,
	id_roud bigint not null,	
	CONSTRAINT filme_round_round_fk FOREIGN KEY (id_roud) REFERENCES round(id),
	CONSTRAINT filme_round_pk PRIMARY KEY (id)
);