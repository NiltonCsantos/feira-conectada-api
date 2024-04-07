package org.feiraconectada.feiraconectadaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.feiraconectada.feiraconectadaapi.dto.request.UserAdmin;
import org.feiraconectada.feiraconectadaapi.enuns.NicheRole;
import org.feiraconectada.feiraconectadaapi.enuns.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "seller")
public class SellerModel extends UserModel {


    @Column(unique = true)
    protected String storeNumber;

    protected NicheRole niche;

    @Column(columnDefinition = "TEXT")
    protected String image;

    @OneToMany(mappedBy = "idSellerFk", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    protected List<StockModel> stockModelList;


   public SellerModel(UserAdmin admin, String encryptedPassword){

       super(admin.fullName(), admin.email(), encryptedPassword, UserRole.ADMIN);

       this.niche=admin.niche();

       this.storeNumber=admin.storeNumber();

       this.image=admin.image();


   }



    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public UserRole getRole() {
        return super.getRole();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}
