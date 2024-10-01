package org.feiraconectada.feiraconectadaapi.model.financeiro;

import jakarta.persistence.*;
import lombok.*;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.FeiranteRegistroForm;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ven_vendedor", schema = "financeiro")
@EqualsAndHashCode(of = "venNrId")
public class VendedorEntidade {

    @Column(name = "ven_nr_id")
    @Id
    private Long venNrId;

    @Column(name = "ven_nr_loja", unique = true)
    protected String venTxNumeroLoja;

    @Column(name = "nic_nr_id")
    protected Long venTxNicho;

    @Column(name = "iv_nr_id")
    private Long ivNrId;

    public VendedorEntidade(FeiranteRegistroForm form, String encryptedPassword) {
        this.venTxNicho = form.nicNrId();
        this.venTxNumeroLoja = form.venTxNumeroLoja();
    }
}
