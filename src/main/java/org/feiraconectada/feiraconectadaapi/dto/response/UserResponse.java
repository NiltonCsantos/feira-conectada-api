package org.feiraconectada.feiraconectadaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.feiraconectada.feiraconectadaapi.model.UserModel;


public record UserResponse(

        Integer id,

        String fullName,

        String email



) {
    public UserResponse(UserModel userModel) {
        this(userModel.getId(), userModel.getFullName(), userModel.getEmail());
    }
}
