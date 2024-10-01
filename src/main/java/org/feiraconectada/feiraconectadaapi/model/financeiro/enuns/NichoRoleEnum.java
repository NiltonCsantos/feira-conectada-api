package org.feiraconectada.feiraconectadaapi.model.financeiro.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NichoRoleEnum {

    HORTIFRUTI("HORTIFRUTI"),
    GRÃOS ("GRÃOS"),
    ORIGEM_ANIMAL("ORIGEM_ANIMAL"),
    VEGETAIS("VEGETAIS");

    private String nicho;
}
