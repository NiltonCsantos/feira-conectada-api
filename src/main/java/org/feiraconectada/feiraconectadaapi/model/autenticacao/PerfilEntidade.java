package org.feiraconectada.feiraconectadaapi.model.autenticacao;

import jakarta.persistence.*;
import lombok.*;
import org.feiraconectada.feiraconectadaapi.model.autenticacao.enums.PerfilEnum;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "per_perfil", schema = "autenticacao")
@EqualsAndHashCode(of = "perNrId")
public class PerfilEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_nr_id")
    private Long perNrId;

    @Column(name = "per_tx_nome")
    @Enumerated(EnumType.STRING)
    private PerfilEnum pertxNome;

    @OneToMany(mappedBy = "perfil")
    private List<UsuarioEntidade> usuario;

}
