CREATE TABLE partida (
	id bigserial NOT NULL,
	data_inicio_partida timestamp NOT NULL,
	data_fim_partida timestamp NULL,
	id_usuario int4 NOT NULL,
	CONSTRAINT partida_usuario_fk FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	CONSTRAINT partida_pk PRIMARY KEY (id)
);