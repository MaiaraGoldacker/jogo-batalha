insert into usuario 
(id, usuario, senha) 
values (100, 'admin', '$2a$10$I/Pvt8NkOCS7JulwJ1KV3Ol/pMqn7KF8r16XmeOZ7afSSS/GxvaOG');

insert into usuario 
(id, usuario, senha) 
values (101, 'john', '$2a$10$Ro8ZdF2Fn7OiFzIZodrM1eprdcB4VnLFzyB6WqmEQACLlbWYm5zAO');

INSERT INTO partida
(data_inicio_partida, data_fim_partida, id_usuario)
VALUES(now(), null, 101);

insert into usuario 
(id, usuario, senha) 
values (102, 'katy', '$2a$10$e0nJMXbHS9Jjf7tvVDCVpeoZHjo4F/raiYVSVkkymhPFvwrA2kmzi');

INSERT INTO partida
(id, data_inicio_partida, data_fim_partida, id_usuario)
VALUES(100, now(), null, 102);

INSERT INTO round
(id, is_acertou, id_partida)
VALUES(100, null, 100);

INSERT INTO filme_round
(id, id_imdb, pontuacao, id_roud, titulo)
VALUES(59, 'tt1300854', 800, 100, 'a1');

INSERT INTO filme_round
(id, id_imdb, pontuacao, id_roud, titulo)
VALUES(60,'tt9894440', 741, 100,  'a2');