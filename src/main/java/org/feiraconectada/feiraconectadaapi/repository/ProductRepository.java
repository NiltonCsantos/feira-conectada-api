package org.feiraconectada.feiraconectadaapi.repository;

import org.feiraconectada.feiraconectadaapi.dto.response.ProductResponse;
import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.model.ProductModel;
import org.feiraconectada.feiraconectadaapi.model.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

    @Query("SELECT p FROM ProductModel p WHERE p.name = :productName and p.idStockFkModel.id = :idStock")
    public Optional<ProductModel> findByProduct(@Param("productName") String productName, @Param("idStock")
    Integer idStock);

    @Query("SELECT p" +
            " From ProductModel p " +
            "JOIN p.idStockFkModel s " +
            "WHERE s.niche=:niche"
    )
    public List<ProductModel> idStockFkModel(@Param("niche") NicheRole niche);

}
