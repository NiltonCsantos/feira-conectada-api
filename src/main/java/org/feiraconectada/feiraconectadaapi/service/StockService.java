package org.feiraconectada.feiraconectadaapi.service;

import org.feiraconectada.feiraconectadaapi.dto.request.StockRequest;
import org.feiraconectada.feiraconectadaapi.dto.response.ProductResponse;
import org.feiraconectada.feiraconectadaapi.dto.response.StockProductResponse;
import org.feiraconectada.feiraconectadaapi.dto.response.StockResponse;
import org.feiraconectada.feiraconectadaapi.dto.response.UserResponse;
import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.exceptions.RegistredStockException;
import org.feiraconectada.feiraconectadaapi.model.*;
import org.feiraconectada.feiraconectadaapi.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class StockService {

    private final SellerService sellerService;

    private final StockRepository stockRepository;

    @Autowired
    public StockService(SellerService sellerService, StockRepository stockRepository){
        this.sellerService=sellerService;
        this.stockRepository=stockRepository;
    }


    public void cadastreStock( StockRequest stockRequest) throws NotFoundException{

        System.out.println(stockRequest);

        System.out.println("AUTORIDADE");

        Optional<SellerModel> sellerModelOptional= this.sellerService.findSeller(stockRequest.idSeller());

        if (sellerModelOptional.isEmpty()){
            throw  new NotFoundException("Vendedor não encontrado");
        }

        SellerModel sellerModel=sellerModelOptional.get();

        System.out.println(sellerModel.getAuthorities());

        StockModel stockModel= new StockModel(stockRequest);

        stockModel.setIdSellerFk(sellerModel);


        this.verifyStockSeller(sellerModel.getId(), stockRequest.niche());
        stockRepository.save(stockModel);


    }

    public void verifyStockSeller(Integer userId, NicheRole nicheRole) throws RegistredStockException {

        System.out.println("VERIFUCANDO");

        Optional<StockModel> stock=stockRepository.findStockByNicheIdUser(userId, nicheRole);

        if (stock.isPresent()){
            throw new RegistredStockException();
        }


    }


    public StockModel findStock(int id) throws NotFoundException {

        Optional<StockModel> optionalStockModel= this.stockRepository.findById(id);

        if (optionalStockModel.isEmpty()){
            throw new NotFoundException("Stock não encontrado");
        }

        return optionalStockModel.get();
    }

    public UserResponse  findSeller(int id) throws NotFoundException {

        Optional<StockModel> optionalUserModel= this.stockRepository.findById(id);

        if (optionalUserModel.isEmpty()){
            throw new NotFoundException("Stock não encontrado");
        }

        UserResponse userResponse= new UserResponse(optionalUserModel.get().getIdSellerFk());

        return userResponse;
    }

    public List<StockProductResponse> gelAllStockToNiche(NicheRole role){


        return stockRepository.
                findByNiche(role).
                stream().
                map(stockModel -> {
                    StockProductResponse stockProductResponse = new StockProductResponse(stockModel);
                            return stockProductResponse;
                        }
                ).collect(Collectors.toList());




    }

    public List<StockResponse> findStockForSeller(Integer idSeller){

        Optional<SellerModel> sellerModelOptional= this.sellerService.findSeller(idSeller);

        if (sellerModelOptional.isEmpty()){
            throw  new NotFoundException("Usuário não encontrado");
        }



        return  this.stockRepository.findByIdSellerFk(sellerModelOptional.get())
                .stream().map(StockResponse::new)
                .collect(Collectors.toList());


    }

    public  List<ProductResponse> findProductsForStock(int id) throws NotFoundException{

        Optional<StockModel> optionalStockModel= stockRepository.findById(id);

        if (optionalStockModel.isEmpty()){
            throw  new NotFoundException("Stock Não encontrado");
        }

        return optionalStockModel.get().getProducts().stream().map(ProductResponse::new).collect(Collectors.toList());

    }



}
