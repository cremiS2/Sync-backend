package com.projeto.tcc.service;

import com.projeto.tcc.dto.LoginInformacoes;
import com.projeto.tcc.dto.entry.LoginDTO;
import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.RoleRepository;
import com.projeto.tcc.repository.UserRepository;
import com.projeto.tcc.security.CustomUserDetails;
import com.projeto.tcc.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final  JwtEncoder encoder;
    private final  RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;


    public LoginDTO criarToken(LoginInformacoes userAcces){

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userAcces.email(), userAcces.password());

        Authentication authentication = authenticationManager.authenticate(authToken);

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        user.getAuthorities().forEach(System.out::println);

        var now = Instant.now();
        var expir = 300L;

        List<String> scopes = new ArrayList<>();

        if(user.getAuthorities().isEmpty()){
             scopes.add(roleRepository.findById(3L).get().getName().toUpperCase());
        }else{
            user.getAuthorities().forEach(r -> scopes.add(r.getAuthority()));
        }

        var claims = JwtClaimsSet.builder()
                .issuer("login")
                .subject(user.getUsername())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expir))
                .claim("scope", scopes)
                .build();

        return new LoginDTO(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(), expir);
    }


}

