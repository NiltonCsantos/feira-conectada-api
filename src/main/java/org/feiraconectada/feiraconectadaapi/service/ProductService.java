package org.feiraconectada.feiraconectadaapi.service;

import org.feiraconectada.feiraconectadaapi.dto.request.ProductRequest;
import org.feiraconectada.feiraconectadaapi.dto.response.ProductResponse;
import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.exceptions.RegistredProductException;
import org.feiraconectada.feiraconectadaapi.exceptions.RegistredStockException;
import org.feiraconectada.feiraconectadaapi.model.ProductModel;
import org.feiraconectada.feiraconectadaapi.model.StockModel;
import org.feiraconectada.feiraconectadaapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private  final  StockService stockService;

    @Autowired
    public ProductService (ProductRepository productRepository, StockService stockService){
        this.productRepository=productRepository;
        this.stockService=stockService;

    }

    public void saveProduct(ProductRequest productRequest){

        System.out.println(productRequest.name());

        verifyCadastreProduct(productRequest.name(),productRequest.idStock());

        System.out.println("Salvando produto");

        ProductModel productModel= new ProductModel(productRequest);

        productModel.setIdStockFkModel(this.stockService.findStock(productRequest.idStock()));


        this.productRepository.save(productModel);


    }

    public void verifyCadastreProduct(String name, Integer id) throws RegistredProductException {

        Optional<ProductModel> optionalProductModel=this.productRepository.findByProduct(name, id);

        if (optionalProductModel.isPresent()){
            throw  new RegistredProductException();
        }




    }

    public List<ProductResponse> findProductForStock(NicheRole role){
//
        return  productRepository.idStockFkModel(role)
                .stream().map(ProductResponse::new).collect(Collectors.toList());




    }

}
