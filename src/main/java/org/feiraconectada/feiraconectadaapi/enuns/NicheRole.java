package org.feiraconectada.feiraconectadaapi.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NicheRole {

    HORTIFRUTI ("HORTIFRUTI"),

    GRAINS ("GRAINS"),

    ANIMALORIGIN ("ANIMALORIGIN"),

    VEGETABLES ("VEGETABLES");


    private String niche;



}
