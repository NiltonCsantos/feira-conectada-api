package org.feiraconectada.feiraconectadaapi.service;

import org.feiraconectada.feiraconectadaapi.dto.response.SellerResponse;
import org.feiraconectada.feiraconectadaapi.model.SellerModel;
import org.feiraconectada.feiraconectadaapi.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {

    private  final SellerRepository sellerRepository;


    @Autowired
    public SellerService(SellerRepository sellerRepository){
        this.sellerRepository=sellerRepository;
    }


    public Optional<SellerModel> findSeller(int id){
        return sellerRepository.findById(id);
    }

    public List<SellerResponse> findAllSeller(){

        return sellerRepository.findAll().stream().map(SellerResponse::new).collect(Collectors.toList());

    }


}
