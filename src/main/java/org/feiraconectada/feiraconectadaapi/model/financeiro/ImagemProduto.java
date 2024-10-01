package org.feiraconectada.feiraconectadaapi.model.financeiro;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "ip_imagem_produto", schema = "financeiro")
@EqualsAndHashCode(of = "ipNrId")
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipNrId")
    private Long ipNrId;

    @Column(name = "ip_tx_imagem", columnDefinition = "TEXT")
    private String ipTxImagem;

}
