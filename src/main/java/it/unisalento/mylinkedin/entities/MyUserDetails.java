package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(Administrator user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.active = true;
        this.authorities = Arrays.stream(Constants.ROLE_ADMIN.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public MyUserDetails(Applicant user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.active = extractActive(user.getStatus());
        this.authorities = Arrays.stream(Constants.ROLE_REGISTEREDUSER.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public MyUserDetails(Offeror user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.active = extractActive(user.getStatus());
        this.authorities = Arrays.stream(Constants.ROLE_REGISTEREDUSER.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private boolean extractActive(String registrationStatus) {
        return registrationStatus.equals(Constants.REGISTRATION_ACCEPTED);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return active;
    }
}
