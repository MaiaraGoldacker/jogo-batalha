CREATE TABLE round (
	id bigserial NOT NULL,
	is_acertou boolean,
	id_partida bigint not null,
	CONSTRAINT round_partida_fk FOREIGN KEY (id_partida) REFERENCES partida(id),
	CONSTRAINT round_pk PRIMARY KEY (id)
);