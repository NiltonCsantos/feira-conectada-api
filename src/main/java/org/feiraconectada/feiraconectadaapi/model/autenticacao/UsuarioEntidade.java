package org.feiraconectada.feiraconectadaapi.model.autenticacao;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Collection;
import java.util.List;
@Data
@EqualsAndHashCode(of = "usuNrId")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usu_usuario", schema = "autenticacao")
public class UsuarioEntidade implements CustomUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_nr_id")
    private Long usuNrId;

    @Column(name = "usu_tx_nome")
    private String usuTxNome;

    @Column(name = "usu_tx_email",unique = true)
    private String usuTxEmail;

    @Column(name = "usu_bl_ativo")
    private boolean usublAtivo;

    @Column(name = "usu_tx_senha")
    private String usuTxSenha;

    @Column(name = "usu_tx_expo_token")
    private String usuTxExpoToken;

    @ManyToOne
    @JoinColumn(name = "per_nr_id", nullable = false)
    @ToString.Exclude
    private PerfilEntidade perfil;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (perfil.getPertxNome()) {
            case ADMINISTRADOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            );
            case VENDEDOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_VENDEDOR")
            );
            case USUARIO -> List.of(
                    new SimpleGrantedAuthority("ROLE_USUARIO")
            );
            default -> List.of(); // Nenhuma role padrão
        };
    }

    @Override
    public String getUsername() {
        return this.usuTxEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usublAtivo;
    }


    @Override
    public String getPassword() {
        return this.usuTxSenha;
    }

    @Override
    public Long getId() {
        return this.usuNrId;
    }

    @Override
    public String getEmail() {
        return null;
    }
}
