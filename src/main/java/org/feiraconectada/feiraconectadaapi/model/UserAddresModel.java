package org.feiraconectada.feiraconectadaapi.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_address")
@Data
@NoArgsConstructor
public class UserAddresModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    protected UserModel idUserFk;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    protected AddressModel idAddressFk;


    public UserAddresModel(UserModel userModel, AddressModel addressModel) {
        this.idUserFk=userModel;
        this.idAddressFk=addressModel;

    }


}
