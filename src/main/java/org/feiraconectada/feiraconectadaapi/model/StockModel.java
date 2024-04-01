package org.feiraconectada.feiraconectadaapi.model;


import jakarta.persistence.*;
import lombok.*;
import org.feiraconectada.feiraconectadaapi.dto.request.StockRequest;
import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;

import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "stock")
public class StockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    protected double amount;

    protected String description;

    protected NicheRole niche;

    @OneToMany(mappedBy = "idStockFkModel", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    protected List<ProductModel> products;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    protected SellerModel idSellerFk;

    public StockModel(StockRequest stockRequest) {
        this.amount = stockRequest.amount();
        this.description = stockRequest.description();
        this.niche=stockRequest.niche();
    }


}
