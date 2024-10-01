package org.feiraconectada.feiraconectadaapi.model.financeiro;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(of = "estNrId")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "est_estoque", schema = "financeiro")
public class EstoqueEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "est_nr_id")
    private Long estNrId;

    @Column(name = "est_nr_valor")
    private double estNrValor;
    @Column(name = "est_tx_nome")
    private String estTxNome;
    @Column(name = "nic_nr_id")
    private Long nicNrId;
    @Column(name = "ven_nr_id")
    private Long venNrId;

}
