//package org.feiraconectada.feiraconectadaapi.controller.user;
//
//
//import jakarta.validation.Valid;
//import org.feiraconectada.feiraconectadaapi.service.usuario.form.UserAddresRequest;
//import org.feiraconectada.feiraconectadaapi.service.addres.dto.AddresResponse;
//import org.feiraconectada.feiraconectadaapi.service.seller.dto.SellerResponse;
//import org.feiraconectada.feiraconectadaapi.service.seller.SellerService;
//import org.feiraconectada.feiraconectadaapi.service.usuario.impl.UsuarioServiceImpl;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("users")
//public class UserController {
//
//    private final UsuarioServiceImpl userServiceImpl;
//
//    private final SellerService sellerService;
//
//
//    public UserController(UsuarioServiceImpl userServiceImpl, SellerService sellerService) {
//        this.userServiceImpl = userServiceImpl;
//        this.sellerService=sellerService;
//    }
//
//    @PostMapping("/addAddress")
//    public ResponseEntity<Void> addAddress(@RequestBody @Valid UserAddresRequest userAddresRequest){
//
//
//        userServiceImpl.addAddress(userAddresRequest);
//
//        return   ResponseEntity.ok().build();
//
//    }
//    @PostMapping("/findaddres")
//    public ResponseEntity<List<AddresResponse>> findAllUsers(@RequestBody @Valid IDRequest IDRequest){
//        System.out.println("CONTRROLLER"+ IDRequest.id());
//
//        return ResponseEntity.ok(userServiceImpl.findAddressOfUser(IDRequest.id()));
//    }
//
//    @GetMapping("/sellers")
//    public ResponseEntity<List<SellerResponse>> findAllSellers(){
//
//        return ResponseEntity.ok(sellerService.findAllSeller());
//
//    }
//
//
//
//}
