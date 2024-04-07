package org.feiraconectada.feiraconectadaapi.service;

import org.feiraconectada.feiraconectadaapi.dto.request.UserAddresRequest;
import org.feiraconectada.feiraconectadaapi.dto.response.AddresResponse;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.exceptions.RegistredUserAddressException;
import org.feiraconectada.feiraconectadaapi.model.*;
import org.feiraconectada.feiraconectadaapi.repository.SellerRepository;
import org.feiraconectada.feiraconectadaapi.repository.UserAdressRepository;
import org.feiraconectada.feiraconectadaapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserAdressRepository  userAdressRepository;

    private  final SellerRepository sellerRepository;

    private final AddressService addressService;


    @Autowired
    public UserService(UserRepository userRepository, UserAdressRepository userAdressRepository,
                       AddressService addressService, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.userAdressRepository=userAdressRepository;
        this.addressService=addressService;
        this.sellerRepository=sellerRepository;
    }

    public void addAddress(UserAddresRequest userAddresRequest) throws RegistredUserAddressException{

        System.out.println("Entrou no serviço");

        System.out.println(userAddresRequest);



           AddressModel addressModel= addressService.findAddres(userAddresRequest.idAddress());
           System.out.println(addressModel.getName());
           UserModel userModel=this.findUser(userAddresRequest.idUser());
//
           System.out.println(userModel.getFullName());

        Optional<UserAddresModel> userAddresModel =
                userAdressRepository.findByAddresUserAddresId(userAddresRequest.idUser(), userAddresRequest.idAddress());

        if (userAddresModel.isPresent()){
            throw  new RegistredUserAddressException();
        }


        UserAddresModel userAddres= new UserAddresModel(userModel, addressModel);


        userAdressRepository.save(userAddres);












    }

    //comentar
    public List<AddresResponse> findAddressOfUser(Integer id) throws NotFoundException {

        System.out.println("BUSCANDO ADDREs");
        System.out.println(id);

        //verificar



        List<AddressModel> addressModels = userRepository.findDistinctByUserId(  this.findUser(id).getId());


        return addressModels.stream().map(AddresResponse::new).collect(Collectors.toList());



    }

    public UserModel findUser(Integer id) throws NotFoundException{

        Optional<UserModel> userFind = userRepository.findById(id);

        if (userFind.isEmpty()){
            throw new NotFoundException("Usuário não encontrado");
        }

       return userFind.get();


    }




}
