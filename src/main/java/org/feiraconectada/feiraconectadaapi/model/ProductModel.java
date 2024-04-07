package org.feiraconectada.feiraconectadaapi.model;


import jakarta.persistence.*;
import lombok.*;
import org.feiraconectada.feiraconectadaapi.dto.request.ProductRequest;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    protected String name;

    protected double price;

    protected Long quantity;

    @Column(columnDefinition = "TEXT")
    protected String image;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    protected StockModel idStockFkModel;

    public ProductModel(ProductRequest productRequest) {
        this.name = productRequest.name();
        this.price = productRequest.price();
        this.quantity=productRequest.quantity();
        this.image=productRequest.image();
    }
}
