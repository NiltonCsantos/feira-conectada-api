package org.feiraconectada.feiraconectadaapi.controller;


import org.feiraconectada.feiraconectadaapi.dto.request.UserFind;
import org.feiraconectada.feiraconectadaapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/find")
    private ResponseEntity findAllUsers(@RequestBody UserFind userFind){
        System.out.println("email");
        System.out.println(userFind.email());
        return userService.findUser(userFind.email());
    }

}
