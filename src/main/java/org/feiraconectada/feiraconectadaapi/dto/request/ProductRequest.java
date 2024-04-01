package org.feiraconectada.feiraconectadaapi.dto.request;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.feiraconectada.feiraconectadaapi.model.StockModel;

public record ProductRequest(

        @NotBlank
        String name,

        @NotNull
        double price,

        @NotNull
        Long quantity,

        @NotNull
        Integer idStock


) {
}
