package org.feiraconectada.feiraconectadaapi.controller;


import org.feiraconectada.feiraconectadaapi.dto.response.AddresResponse;
import org.feiraconectada.feiraconectadaapi.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/findall")

    public ResponseEntity<List<AddresResponse>> AddressFindAll(){


        return ResponseEntity.ok(addressService.getAddresses());





    }

}
