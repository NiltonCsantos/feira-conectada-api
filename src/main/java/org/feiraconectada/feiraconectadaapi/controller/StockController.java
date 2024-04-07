package org.feiraconectada.feiraconectadaapi.controller;

import jakarta.validation.Valid;
import org.feiraconectada.feiraconectadaapi.dto.request.NicheRequest;
import org.feiraconectada.feiraconectadaapi.dto.request.StockRequest;
import org.feiraconectada.feiraconectadaapi.dto.request.IDRequest;
import org.feiraconectada.feiraconectadaapi.dto.response.ProductResponse;
import org.feiraconectada.feiraconectadaapi.dto.response.StockProductResponse;
import org.feiraconectada.feiraconectadaapi.dto.response.StockResponse;
import org.feiraconectada.feiraconectadaapi.dto.response.UserResponse;
import org.feiraconectada.feiraconectadaapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {



    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }



    @PostMapping("/cadastre")
    public ResponseEntity<Void> addStock(@RequestBody @Valid StockRequest stockRequest){

        System.out.println("Contrroller stock");


        stockService.cadastreStock(stockRequest);


      return   ResponseEntity.ok().build();

    }

    @PostMapping("/findSeller")
    public  ResponseEntity<UserResponse> findSeller(@RequestBody @Valid IDRequest IDRequest) {
        return ResponseEntity.ok(stockService.findSeller(IDRequest.id()));
    }

    @PostMapping("/niche")
    public ResponseEntity<List<StockProductResponse>> getStockNiche(@RequestBody NicheRequest role){

        System.out.println("Nicho");
        System.out.println(role.niche());


        return  ResponseEntity.ok(stockService.gelAllStockToNiche(role.niche()));


    }


    @PostMapping("/find/niche")
    public ResponseEntity<List<StockResponse>> getNicheStock(@RequestBody IDRequest idRequest){

        System.out.println("Nicho");
        System.out.println(idRequest.id());


        return  ResponseEntity.ok(stockService.findStockForSeller(idRequest.id()));


    }

    @PostMapping()
    public ResponseEntity<List<ProductResponse>> findStock(@RequestBody @Valid IDRequest idRequest){
        return ResponseEntity.ok(stockService.findProductsForStock(idRequest.id()));
    }

}
