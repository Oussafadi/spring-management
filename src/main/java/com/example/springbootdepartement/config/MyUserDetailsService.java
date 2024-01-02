package com.example.springbootdepartement.config;


import com.example.springbootdepartement.Models.User;
import com.example.springbootdepartement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user_entity = repository.findByUsername(username);
        user_entity.orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas"));
        String nom = user_entity.get().getNom();
        String password = user_entity.get().getPassword();
        String role = user_entity.get().getRole();
        role = "ROLE_"+ role ;
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role));

        return new MyUserDetails(username,nom,password,roles);
    }
}
