package org.feiraconectada.feiraconectadaapi.controller;


import jakarta.validation.Valid;
import org.feiraconectada.feiraconectadaapi.dto.request.UserAddresRequest;
import org.feiraconectada.feiraconectadaapi.dto.request.IDRequest;
import org.feiraconectada.feiraconectadaapi.dto.response.AddresResponse;
import org.feiraconectada.feiraconectadaapi.dto.response.SellerResponse;
import org.feiraconectada.feiraconectadaapi.service.SellerService;
import org.feiraconectada.feiraconectadaapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    private final SellerService sellerService;


    public UserController(UserService userService, SellerService sellerService) {
        this.userService = userService;
        this.sellerService=sellerService;
    }

    @PostMapping("/addAddress")
    public ResponseEntity<Void> addAddress(@RequestBody @Valid UserAddresRequest userAddresRequest){


        userService.addAddress(userAddresRequest);

        return   ResponseEntity.ok().build();

    }
    @PostMapping("/findaddres")
    public ResponseEntity<List<AddresResponse>> findAllUsers(@RequestBody @Valid IDRequest IDRequest){
        System.out.println("CONTRROLLER"+ IDRequest.id());

        return ResponseEntity.ok(userService.findAddressOfUser(IDRequest.id()));
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<SellerResponse>> findAllSellers(){

        return ResponseEntity.ok(sellerService.findAllSeller());

    }



}
