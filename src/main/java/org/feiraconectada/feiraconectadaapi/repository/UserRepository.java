package org.feiraconectada.feiraconectadaapi.repository;

import org.feiraconectada.feiraconectadaapi.model.AddressModel;
import org.feiraconectada.feiraconectadaapi.model.CustomUserDetails;
import org.feiraconectada.feiraconectadaapi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepository extends JpaRepository<UserModel, Integer> {

    CustomUserDetails findByEmail(String email);


    @Query("SELECT  a FROM UserModel u " +
            "JOIN u.userAddresList ua " +
            "JOIN ua.idAddressFk a " +
            "WHERE u.id = :id")
    List<AddressModel> findDistinctByUserId(@Param( "id") Integer id);

}



