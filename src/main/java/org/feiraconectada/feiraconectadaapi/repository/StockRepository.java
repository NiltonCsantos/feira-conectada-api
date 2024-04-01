package org.feiraconectada.feiraconectadaapi.repository;

import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.model.SellerModel;
import org.feiraconectada.feiraconectadaapi.model.StockModel;
import org.feiraconectada.feiraconectadaapi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<StockModel, Integer> {

    @Query("SELECT s FROM StockModel s JOIN s.idSellerFk sl " +
            "WHERE sl.id = :sellerId AND s.niche = :niche")
    Optional<StockModel> findStockByNicheIdUser(@Param("sellerId") Integer sellerId,
                                                @Param("niche") NicheRole niche);

    List<StockModel> findByNiche(NicheRole role);

}
