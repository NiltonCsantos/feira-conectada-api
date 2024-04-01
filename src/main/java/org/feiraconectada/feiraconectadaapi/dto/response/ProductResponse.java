package org.feiraconectada.feiraconectadaapi.dto.response;

import org.feiraconectada.feiraconectadaapi.model.ProductModel;
import org.feiraconectada.feiraconectadaapi.model.StockModel;

public record ProductResponse (

         Integer id,

         String productName,

         double price,

         Long quantity,

         SellerResponse seller

) {

    public ProductResponse (ProductModel productModel){
        this(
                productModel.getId(), productModel.getName(),productModel.getPrice(),
                productModel.getQuantity(), new SellerResponse(productModel.getIdStockFkModel()));
    }
}
