package org.feiraconectada.feiraconectadaapi.service.endereco.dto;
import org.feiraconectada.feiraconectadaapi.model.endereco.EnderecoEntidade;

public record EnderecoDto(
        Long endNrId,
        String endTxNome,
        String endTxCep,
        String endTxEstado
){
        public EnderecoDto(EnderecoEntidade entidade){
                this(entidade.getEndNrId(),
                        entidade.getEndtxNome(),
                        entidade.getEndTxCep(),
                        entidade.getEndTxEstado()
                );
        }
}
