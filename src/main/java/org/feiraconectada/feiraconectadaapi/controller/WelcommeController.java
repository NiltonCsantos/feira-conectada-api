package org.feiraconectada.feiraconectadaapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcommeController {

    @GetMapping()
    public String wellcomme(){
        return "Bem vindo";
    }

}
