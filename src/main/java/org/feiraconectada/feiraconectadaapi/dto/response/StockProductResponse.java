package org.feiraconectada.feiraconectadaapi.dto.response;

import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.model.StockModel;

import java.util.List;
import java.util.stream.Collectors;

public record StockProductResponse(

        Integer id,


        NicheRole niche,

        List<ProductResponse> product
) {

    public StockProductResponse(StockModel stockModel){

        this(stockModel.getId(), stockModel.getNiche(), stockModel.getProducts().stream().map(ProductResponse::new).collect(Collectors.toList()));

    }


}
