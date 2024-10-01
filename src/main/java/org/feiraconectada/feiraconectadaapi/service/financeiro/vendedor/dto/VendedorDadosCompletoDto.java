package org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto;

import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.NichoRoleEnum;

public interface VendedorDadosCompletoDto{
    Long getVenNrId();
    String getUsuTxNome();
    String getVenTxNumeroLoja();
    NichoRoleEnum venTxNicho();
    String getIvTxImagem();
}
