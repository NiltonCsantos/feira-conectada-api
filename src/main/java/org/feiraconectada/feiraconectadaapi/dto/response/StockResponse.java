package org.feiraconectada.feiraconectadaapi.dto.response;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.model.ProductModel;
import org.feiraconectada.feiraconectadaapi.model.SellerModel;
import org.feiraconectada.feiraconectadaapi.model.StockModel;

import java.util.List;
import java.util.stream.Collectors;

public record StockResponse(

        Integer id,


        NicheRole niche,

        List<ProductResponse> product
) {

    public StockResponse (StockModel stockModel){

        this(stockModel.getId(), stockModel.getNiche(), stockModel.getProducts().stream().map(ProductResponse::new).collect(Collectors.toList()));

    }


}
