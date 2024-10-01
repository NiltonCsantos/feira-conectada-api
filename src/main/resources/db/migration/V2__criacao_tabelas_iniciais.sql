CREATE TABLE if not exists autenticacao.per_perfil (
    per_nr_id bigserial NOT NULL,
    per_tx_nome varchar(255) NOT NULL,
    CONSTRAINT per_perfil_per_tx_nome_check CHECK (((per_tx_nome)::text = ANY ((ARRAY['ADMINISTRADOR'::character varying, 'VENDEDOR'::character varying, 'USUARIO'::character varying])::text[]))),
    CONSTRAINT per_perfil_pkey PRIMARY KEY (per_nr_id)
    );



CREATE TABLE if not exists autenticacao.usu_usuario (
       usu_nr_id bigserial NOT NULL,
       usu_tx_email varchar(255) NOT NULL,
       usu_tx_nome varchar(255) NOT NULL,
       usu_bl_ativo boolean NOT NULL default false,
       usu_tx_senha varchar(255) NOT NULL,
       per_nr_id int8 NOT NULL,
       CONSTRAINT uk_r45v8xveh12lkdkmyb2lthdjb UNIQUE (usu_tx_email),
       CONSTRAINT usu_usuario_pkey PRIMARY KEY (usu_nr_id)
);


ALTER TABLE if exists autenticacao.usu_usuario ADD CONSTRAINT fk6dswajfaamir3ah3ohnxd5s7m FOREIGN KEY (per_nr_id) REFERENCES autenticacao.per_perfil(per_nr_id);


CREATE TABLE if not exists endereco.end_endereco (
     end_nr_id bigserial NOT NULL,
     end_tx_cep varchar(255) NOT NULL,
     end_tx_estado varchar(255) NOT NULL,
     end_tx_nome varchar(255) NOT NULL,
     CONSTRAINT end_endereco_pkey PRIMARY KEY (end_nr_id)
);

CREATE TABLE if not exists endereco.eu_end_usu (
   usuend_nr_id bigserial NOT NULL,
   end_nr_id int8 NOT NULL,
   usu_nr_id int8 NOT NULL,
   CONSTRAINT usuend_usuario_endereco_pkey PRIMARY KEY (usuend_nr_id),
   CONSTRAINT end_fk FOREIGN KEY (end_nr_id) REFERENCES endereco.end_endereco(end_nr_id),
   CONSTRAINT usu_fk FOREIGN KEY (usu_nr_id) REFERENCES autenticacao.usu_usuario(usu_nr_id)
);


CREATE TABLE if not exists financeiro.iv_imagem_vendedor (
     iv_nr_id bigserial NOT NULL,
     iv_tx_imagem text NOT NULL,
     CONSTRAINT img_ven_imagem_vendedor_pkey PRIMARY KEY (iv_nr_id)
);

CREATE TABLE if not exists financeiro.nic_nicho (
            nic_nr_id bigserial NOT NULL,
            nic_tx_nome varchar(255),
            CONSTRAINT nic_nicho_check CHECK (((nic_tx_nome)::text = ANY ((ARRAY['HORTIFRUTI'::character varying, 'GRÃOS'::character varying, 'ORIGEM_ANIMAL'::character varying, 'VEGETAIS'::character varying])::text[]))),
            CONSTRAINT nic_nr_id_pkey PRIMARY KEY (nic_nr_id)
    );

CREATE TABLE if not exists financeiro.ven_vendedor (
       ven_nr_id int8 NOT NULL,
       ven_nr_loja varchar(255) NOT NULL,
       nic_nr_id int8 NOT NULL,
       iv_nr_id int8 NULL,
       CONSTRAINT uk_ak3ynpxihmuvepp3qpj5d8aqs UNIQUE (ven_nr_loja),
       CONSTRAINT uk_hxwr0lrfi37cluwhj1pnugtay UNIQUE (iv_nr_id),
       CONSTRAINT ven_vendedor_pkey PRIMARY KEY (ven_nr_id),
       CONSTRAINT nic_nicho_fkey FOREIGN KEY (nic_nr_id) REFERENCES financeiro.nic_nicho(nic_nr_id),
       CONSTRAINT fk99jj17gix2by4stfbtod4o2c7 FOREIGN KEY (ven_nr_id) REFERENCES autenticacao.usu_usuario(usu_nr_id),
       CONSTRAINT fkpusdyvbvaggqxshk2sgtokard FOREIGN KEY (iv_nr_id) REFERENCES financeiro.iv_imagem_vendedor(iv_nr_id)
);



CREATE TABLE if not exists financeiro.ip_imagem_produto (
    ip_nr_id bigserial NOT NULL,
    ip_tx_imagem text NOT NULL,
    CONSTRAINT ip_imagem_produto_pkey PRIMARY KEY (ip_nr_id)
);


CREATE TABLE if not exists financeiro.est_estoque (
  est_nr_id bigserial NOT NULL,
  est_nr_valor float8 default 0.0 NULL,
  est_tx_nome varchar(255) NOT NULL,
  nic_nr_id int8 NOT  NULL,
  ven_nr_id int8 NOT NULL,
  CONSTRAINT nic_nicho_fkey FOREIGN KEY (nic_nr_id) REFERENCES financeiro.nic_nicho(nic_nr_id),
  CONSTRAINT estoque_pkey PRIMARY KEY (est_nr_id),
  CONSTRAINT fk4qe9gmfwefonvl7hvp5i94q91 FOREIGN KEY (ven_nr_id) REFERENCES financeiro.ven_vendedor(ven_nr_id)
);

