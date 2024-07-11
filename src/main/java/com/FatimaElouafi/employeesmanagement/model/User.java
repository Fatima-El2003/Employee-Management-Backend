package com.FatimaElouafi.employeesmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Entity;
import java.util.Collection;
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String username;

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    // Additional fields if needed

    // Implement UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the authorities/roles for this user
        return null; // Modify this to return actual roles if applicable
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
        return true; // Modify if you have an expiration logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify if you have a locking logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify if you have credential expiration logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify if you have enable/disable logic
    }

   }
