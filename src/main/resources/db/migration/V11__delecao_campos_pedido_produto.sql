alter table
    financeiro.pp_pedido_produto
    drop column pp_tx_status,
    drop column pp_dt_criado,
    drop column usu_nr_id,
    add column ped_nr_id int8 not null;

alter table
    financeiro.pp_pedido_produto add constraint ped_pedido_fkey foreign key (ped_nr_id) references financeiro.ped_pedido(ped_nr_id)