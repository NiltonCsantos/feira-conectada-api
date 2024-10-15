package org.feiraconectada.feiraconectadaapi.controller;


import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.service.expo.ExpoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WelcommeController {

    private  final ExpoServiceImpl expoService;
    @GetMapping()
    public String wellcomme(){
        expoService.sendPushNotification("ExponentPushToken[deBz6dLfuEn6pSMdHJQfkV]","Essa é uma mensagem de teste", "Compra Cancelada");
        return "Olá";
    }

}
