package org.feiraconectada.feiraconectadaapi.model.endereco;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eu_end_usu", schema = "endereco")
public class UsuarioEnderecoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuend_nr_id")
    private  Long usuEndNrId;

    @Column(name = "usu_nr_id", nullable = false)
    private Long usuNrId;

    @Column(name = "end_nr_id", nullable = false)
    private Long endNrId;

}
