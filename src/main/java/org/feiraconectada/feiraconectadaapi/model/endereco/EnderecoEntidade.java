package org.feiraconectada.feiraconectadaapi.model.endereco;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@EqualsAndHashCode(of = "endNrId")
@Entity
@Table(name = "end_endereco", schema = "endereco")
@NoArgsConstructor
public class EnderecoEntidade {

    @Id
    @Column(name = "end_nr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long endNrId;

    @Column(name = "end_tx_nome")
    private String endtxNome;
    @Column(name = "end_tx_cep")
    private String endTxCep;
    @Column(name = "end_tx_estado")
    private String endTxEstado;
}
