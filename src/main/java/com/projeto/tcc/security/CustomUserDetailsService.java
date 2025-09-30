package com.projeto.tcc.security;

import com.projeto.tcc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.projeto.tcc.entities.User user = userService.findByEmail(username);
        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getEmployee().getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList())
                .build();
    }
}
