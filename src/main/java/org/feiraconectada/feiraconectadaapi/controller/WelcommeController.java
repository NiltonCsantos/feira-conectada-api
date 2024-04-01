package org.feiraconectada.feiraconectadaapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcommeController {


   private Model model;

    @GetMapping()
    public String wellcomme(){


       return "Olá";
    }

}
