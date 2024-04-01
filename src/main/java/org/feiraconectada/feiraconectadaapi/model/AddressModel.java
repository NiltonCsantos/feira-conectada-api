package org.feiraconectada.feiraconectadaapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "address")
@NoArgsConstructor
public class AddressModel {

    @Id
    protected Integer id;

    protected String name;

    protected String cep;

    protected String state;

    @OneToMany(mappedBy = "idAddressFk", fetch = FetchType.LAZY, cascade= CascadeType.REMOVE)
    protected List<UserAddresModel> userAddresList;
    
}
