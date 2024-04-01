package org.feiraconectada.feiraconectadaapi.controller;

import jakarta.validation.Valid;
import org.feiraconectada.feiraconectadaapi.dto.request.NicheRequest;
import org.feiraconectada.feiraconectadaapi.dto.request.ProductRequest;
import org.feiraconectada.feiraconectadaapi.dto.response.ProductResponse;
import org.feiraconectada.feiraconectadaapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/cadastre")
    public ResponseEntity<Void> cadastreAddress(@RequestBody @Valid ProductRequest productRequest){

        this.productService.saveProduct(productRequest);

       return ResponseEntity.ok().build();

    }

    @PostMapping("/niche")

    public ResponseEntity<List<ProductResponse>> findProductBySeller(@RequestBody @Valid NicheRequest nicheRequest){



        return ResponseEntity.ok(     productService.findProductForStock(nicheRequest.niche()));

    }

}
