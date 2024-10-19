package org.feiraconectada.feiraconectadaapi.model.financeiro;

import jakarta.persistence.*;
import lombok.*;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.StatusPedidoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ped_pedido", schema = "financeiro")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "pedNrId")
public class PedidoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_nr_id")
    private Long pedNrId;

    @Column(name = "ped_dt_criado", nullable = false)
    private LocalDateTime pedDtCriado;

    @Column(name = "ped_tx_status")
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum pedTxStatus;

    @Column(name = "usu_nr_id", nullable = false)
    private Long usuNrId;

    @Column(name = "ped_nr_valor_total", precision = 5, scale = 2)
    private BigDecimal pedNrValorTotal;
}