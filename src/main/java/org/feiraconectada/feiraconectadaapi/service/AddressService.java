package org.feiraconectada.feiraconectadaapi.service;

import org.feiraconectada.feiraconectadaapi.dto.request.UserAddresRequest;
import org.feiraconectada.feiraconectadaapi.dto.response.AddresResponse;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.model.AddressModel;
import org.feiraconectada.feiraconectadaapi.repository.AddresRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddresRepository addresRepository;

    public AddressService(AddresRepository addresRepository) {
        this.addresRepository = addresRepository;
    }



    public List<AddresResponse> getAddresses(){


        return addresRepository.findAll().stream().map(AddresResponse:: new).toList();

    }

    public AddressModel findAddres(Integer id){

        Optional<AddressModel> addressFind=addresRepository.findById(id);

        if (addressFind.isEmpty()){
            throw new NotFoundException("Endereço não encontrado");
        }

        AddressModel addressModel= addressFind.get();
        return addressModel;

    }


}
