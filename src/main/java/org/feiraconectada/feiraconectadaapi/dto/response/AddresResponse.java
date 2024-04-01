package org.feiraconectada.feiraconectadaapi.dto.response;

import jakarta.validation.constraints.NotBlank;
import org.feiraconectada.feiraconectadaapi.model.AddressModel;

public record AddresResponse(

        Integer id,

        String name,


        String cep,


        String state
) {

    public AddresResponse(AddressModel addressModel){

        this(addressModel.getId(), addressModel.getName(), addressModel.getCep(), addressModel.getState());

    }
}
