package org.feiraconectada.feiraconectadaapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.feiraconectada.feiraconectadaapi.dto.request.UserRegister;
import org.feiraconectada.feiraconectadaapi.enuns.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserModel implements CustomUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    protected String fullName;

    @Getter
    @Column(unique = true)
    protected String email;

    protected String password;

    protected UserRole role;


    //olhar
    @OneToMany(mappedBy = "idUserFk", fetch = FetchType.LAZY, cascade= CascadeType.REMOVE)
    protected List<UserAddresModel> userAddresList;



    public UserModel(UserRegister userRegister, String password) {
        this.fullName = userRegister.fullName();
        this.email = userRegister.email();
        this.password = password;
        this.role= UserRole.USER;
    }

    public UserModel(String fullName, String email, String password, UserRole role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role == UserRole.ADMIN
                ? List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"))
                : List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.fullName;
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
        return true;
    }


}
