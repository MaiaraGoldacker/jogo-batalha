CREATE TABLE usuario (
	id bigserial NOT NULL,
	usuario varchar not NULL,	
	senha varchar not null,
	CONSTRAINT usuario_pk PRIMARY KEY (id)
);
