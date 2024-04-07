package org.feiraconectada.feiraconectadaapi.dto.response;

import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.model.SellerModel;
import org.feiraconectada.feiraconectadaapi.model.StockModel;

import javax.management.relation.Role;

public record SellerResponse(


        Integer id,

        String fullName,


        String StoreNumber,

        NicheRole niche,

        String image
) {

    public SellerResponse (StockModel stockModel){
        this(stockModel.getIdSellerFk().getId(),
                stockModel.getIdSellerFk().getFullName(),
                stockModel.getIdSellerFk().getStoreNumber(),
                stockModel.getIdSellerFk().getNiche(), stockModel.getIdSellerFk().getImage());
    }
    public SellerResponse (SellerModel sellerModel){
        this(sellerModel.getId(), sellerModel.getFullName(),
                sellerModel.getStoreNumber(), sellerModel.getNiche(), sellerModel.getImage());
    }

}
