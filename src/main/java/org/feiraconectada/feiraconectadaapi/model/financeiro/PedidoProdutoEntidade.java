package org.feiraconectada.feiraconectadaapi.model.financeiro;

import jakarta.persistence.*;
import lombok.*;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.StatusPedidoEnum;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "ppNrId")
@Entity
@Table(name = "pp_pedido_produto", schema = "financeiro")
public class PedidoProdutoEntidade {
    @Id
    @Column(name = "pp_nr_id")
    private Long ppNrId;

    @Column(name = "pp_dt_criado")
    private LocalDateTime ppDtCriado;
    @Column(name = "pp_tx_status")
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum ppTxStatus;
    @Column(name = "pro_nr_id")
    private Long proNrId;
    @Column(name = "usu_nr_id")
    private Long usuNrId;
}
