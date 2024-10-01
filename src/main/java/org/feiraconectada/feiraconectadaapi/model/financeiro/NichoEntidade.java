package org.feiraconectada.feiraconectadaapi.model.financeiro;

import jakarta.persistence.*;
import lombok.*;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.NichoRoleEnum;

@Data
@EqualsAndHashCode(of = "nicNrId")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "nic_nicho", schema = "financeiro")
public class NichoEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nic_nr_id")
    private Long nicNrId;

    @Column(name = "nic_tx_nome")
    @Enumerated(EnumType.STRING)
    private NichoRoleEnum nicTxNome;

}
