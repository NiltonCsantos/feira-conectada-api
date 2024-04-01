package org.feiraconectada.feiraconectadaapi.repository;

import org.feiraconectada.feiraconectadaapi.model.AddressModel;
import org.feiraconectada.feiraconectadaapi.model.UserAddresModel;
import org.feiraconectada.feiraconectadaapi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAdressRepository extends JpaRepository<UserAddresModel, Integer> {

    @Query("select ua from UserAddresModel ua  where ua.idUserFk.id=:userId and ua.idAddressFk.id=:addresId ")
     Optional<UserAddresModel> findByAddresUserAddresId(
            @Param("userId") Integer userId, @Param("addresId") Integer addresId);

}
