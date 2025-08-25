package com.projeto.tcc.service;

import com.projeto.tcc.dto.LoginInformacoes;
import com.projeto.tcc.dto.entry.LoginDTO;
import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.EmployeeRepository;
import com.projeto.tcc.repository.RoleRepository;
import com.projeto.tcc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class TokenService {

    private final UserRepository repository;
    private final  BCryptPasswordEncoder passwordEncoder;
    private final  JwtEncoder encoder;
    private final  RoleRepository roleRepository;

    public LoginDTO criarToken(UserDTO userAcces){
        var user = repository.findByEmail(userAcces.email());

        if(user.isEmpty() || !user.get().verificarSenha(userAcces, passwordEncoder)){
            throw new NaoRegistradoExcpetion("Funcionário não encontrado");
        }

        var now = Instant.now();
        var expir = 300L;

        List<String> scopes = new ArrayList<>();

        if(user.get().getEmployee() != null){
            user.get().getEmployee().getRoles()
                    .stream()
                    .map(role -> role.getName().toUpperCase())
                    .forEach(scopes::add);
        }
        else{
             scopes.add(roleRepository.findById(3L).get().getName().toUpperCase());
        }

        var claims = JwtClaimsSet.builder()
                .issuer("login")
                .subject(user.get().getUsername())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expir))
                .claim("scope", scopes)
                .build();

        return new LoginDTO(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(), expir);
    }


}

