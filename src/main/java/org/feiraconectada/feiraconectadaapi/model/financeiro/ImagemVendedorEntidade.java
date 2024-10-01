package org.feiraconectada.feiraconectadaapi.model.financeiro;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "iv_imagem_vendedor", schema = "financeiro")
@EqualsAndHashCode(of = "ivNrId")
public class ImagemVendedorEntidade{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iv_nr_id")
    private Long ivNrId;

    @Column(name = "iv_tx_imagem", columnDefinition = "Text")
    private String imgVenTxImagem;

}
