package org.feiraconectada.feiraconectadaapi.dto.response;

import jakarta.validation.constraints.NotBlank;
import org.feiraconectada.feiraconectadaapi.model.AddressModel;


public record AddressList(

        @NotBlank
        String name,

        @NotBlank
        String cep,

        @NotBlank
        String location

){
        public AddressList(AddressModel addresModel) {
              this(addresModel.getName(), addresModel.getCep(), addresModel.getState());
        }


}
