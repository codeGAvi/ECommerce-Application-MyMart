package com.example.MyMart.Configuration;


import com.example.MyMart.ENUM.Roles;
import com.example.MyMart.Entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {
    String username;
    String password;
    List<GrantedAuthority>grantedAuthorities; // this holds the list of roles;

   public CustomUserDetails(Customer customer){
        this.username = customer.getUsername();
        this.password = customer.getPassword();

        List<GrantedAuthority>grantedAuthorityList = new ArrayList<>();

        for(Roles role:customer.getRoles()){
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_"+ role.name()));

        }
        this.grantedAuthorities = grantedAuthorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
