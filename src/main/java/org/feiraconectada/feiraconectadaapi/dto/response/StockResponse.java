package org.feiraconectada.feiraconectadaapi.dto.response;

import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.model.StockModel;

public record StockResponse (
        Integer id,

        NicheRole niche
){
    public StockResponse(StockModel stockModel) {

        this(stockModel.getId(), stockModel.getNiche());
    }
}
