package org.feiraconectada.feiraconectadaapi.repository;

import org.feiraconectada.feiraconectadaapi.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresRepository extends JpaRepository<AddressModel, Integer> {
}
