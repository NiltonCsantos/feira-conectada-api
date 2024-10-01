
CREATE TABLE if not exists financeiro.pro_produto (
    pro_nr_id bigserial NOT NULL,
    pro_nr_preco numeric(38, 2) NOT NULL,
    pro_nr_quantidade int8 NOT NULL,
    pro_tx_nome varchar(255) NOT NULL,
    est_nr_id int8 NOT NULL,
    ip_nr_id int8 NULL,
    CONSTRAINT pro_produto_pkey PRIMARY KEY (pro_nr_id),
    CONSTRAINT fk3d611b5p978ne8knt1y6xgpv7 FOREIGN KEY (est_nr_id) REFERENCES financeiro.est_estoque(est_nr_id),
    CONSTRAINT fkackxpeh4uyq3fsv57iac28ryp FOREIGN KEY (ip_nr_id) REFERENCES financeiro.ip_imagem_produto(ip_nr_id)
    );

CREATE TABLE if not exists financeiro.pp_pedido_produto (
    pp_nr_id bigserial NOT NULL,
    pp_dt_criado TIMESTAMP NOT NULL,
    pp_tx_status varchar(255),
    pro_nr_id int8 NOT NULL,
    usu_nr_id int8 NULL,
    CONSTRAINT nic_nicho_check CHECK (((pp_tx_status)::text = ANY ((ARRAY['CRIADO'::character varying, 'EM_PRAPARACAO'::character varying, 'CANCELAD0'::character varying, 'FINALIZADO'::character varying])::text[]))),
    CONSTRAINT pp_pp_pedido_produto_pkey PRIMARY KEY (pp_nr_id),
    CONSTRAINT fk3d611b5p978ne8knt1y6xgpv7 FOREIGN KEY (pro_nr_id) REFERENCES financeiro.pro_produto(pro_nr_id),
    CONSTRAINT fkackxpeh4uyq3fsv57iac28ryp FOREIGN KEY (usu_nr_id) REFERENCES autenticacao.usu_usuario(usu_nr_id)
    );



INSERT INTO autenticacao.per_perfil
(per_tx_nome)
VALUES('ADMINISTRADOR');
INSERT INTO autenticacao.per_perfil
(per_tx_nome)
VALUES('VENDEDOR');
INSERT INTO autenticacao.per_perfil
(per_tx_nome)
VALUES('USUARIO');


INSERT INTO financeiro.nic_nicho
(nic_tx_nome)
VALUES('HORTIFRUTI');
INSERT INTO financeiro.nic_nicho
(nic_tx_nome)
VALUES('GRÃOS');
INSERT INTO financeiro.nic_nicho
(nic_tx_nome)
VALUES('ORIGEM_ANIMAL');
INSERT INTO financeiro.nic_nicho
(nic_tx_nome)
VALUES('VEGETAIS');
