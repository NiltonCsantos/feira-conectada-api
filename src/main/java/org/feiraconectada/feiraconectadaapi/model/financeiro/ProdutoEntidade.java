package org.feiraconectada.feiraconectadaapi.model.financeiro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(of = "proNrId")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pro_produto", schema = "financeiro")
public class ProdutoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_nr_id")
    private Long proNrId;

    @Column(name = "pro_tx_nome")
    private String proTxNome;
    @Column(name = "pro_nr_preco")
    private BigDecimal proNrPreco;
    @Column(name = "pro_nr_quantidade")
    private Long proNrQuantidade;
    @Column(name = "pro_bl_ativo")
    private Boolean proBlAtivo;
    @Column(name = "est_nr_id", nullable = false)
    private Long estNrId;
    @Column(name = "ip_nr_id",nullable = true)
    private Long ipNrId;


}
