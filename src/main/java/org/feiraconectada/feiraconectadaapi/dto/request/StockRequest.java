package org.feiraconectada.feiraconectadaapi.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.model.ProductModel;
import org.feiraconectada.feiraconectadaapi.model.SellerModel;

import java.util.List;

public record StockRequest(


        @NotNull(message = "Valor em branco")
        double amount,

        @NotBlank(message = "Descrição em branco")
        String description,


        @Enumerated()
        NicheRole niche,

        @NotNull(message = "ID em branco")
        Integer idSeller

) {
}
