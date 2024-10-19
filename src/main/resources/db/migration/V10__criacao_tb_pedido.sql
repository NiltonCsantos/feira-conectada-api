CREATE TABLE financeiro.ped_pedido (
                                       ped_nr_id bigserial NOT NULL,
                                       ped_dt_criado timestamp NOT NULL,
                                       ped_tx_status varchar(255) NULL,
                                       usu_nr_id int8 NOT NULL,
                                       ped_nr_valor_total numeric(5, 2) NULL,
                                       CONSTRAINT nic_nicho_check CHECK (((ped_tx_status)::text = ANY (ARRAY[('CRIADO'::character varying)::text, ('EM_PREPARACAO'::character varying)::text, ('CANCELADO'::character varying)::text, ('FINALIZADO'::character varying)::text]))),
	CONSTRAINT ped_nr_valor_total_check CHECK (((ped_nr_valor_total >= (1)::numeric) AND (ped_nr_valor_total <= (1000)::numeric))),
	CONSTRAINT ped_pedido_pkey PRIMARY KEY (ped_nr_id)
);


-- financeiro.pp_pedido_produto foreign keys

ALTER TABLE financeiro.ped_pedido ADD CONSTRAINT usu_usuario_fkey FOREIGN KEY (usu_nr_id) REFERENCES autenticacao.usu_usuario(usu_nr_id);