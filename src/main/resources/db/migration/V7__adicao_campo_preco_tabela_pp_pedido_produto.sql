ALTER TABLE financeiro.pp_pedido_produto
    ADD COLUMN pp_nr_preco NUMERIC(5, 2) check (pp_nr_preco>=1 and pp_nr_preco<=1000 );
