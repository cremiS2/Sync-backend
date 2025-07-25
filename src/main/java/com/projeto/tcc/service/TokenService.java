//package com.projeto.tcc.service;
//
//import com.projeto.tcc.dto.cadastrar.LoginDTO;
//import com.projeto.tcc.dto.cadastrar.UsuarioDTO;
//import com.projeto.tcc.repository.RoleRepository;
//import com.projeto.tcc.repository.UsuarioRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.Instant;
//import java.util.stream.Collectors;
//
//@Service
//public class TokenService {
//
//    private UsuarioRepository repository;
//    private BCryptPasswordEncoder passwordEncoder;
//    private JwtEncoder encoder;
//    private RoleRepository roleRepository;
//
//    public TokenService(UsuarioRepository repository, BCryptPasswordEncoder passwordEncoder, JwtEncoder encoder, RoleRepository roleRepository) {
//        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
//        this.encoder = encoder;
//        this.roleRepository = roleRepository;
//    }
//
//    public LoginDTO criarToken(UsuarioDTO userAcces){
//        var user = repository.findByEmail(userAcces.email());
//
//        if(user.isEmpty() || !user.get().verificarSenha(userAcces, passwordEncoder)){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//
//        var now = Instant.now();
//        var expir = 300L;
//
//
//        var scopes = user.get().getRoles()
//                .stream()
//                .map(role -> role.getName().toUpperCase())
//                .collect(Collectors.joining(" "));
//
//        var claims = JwtClaimsSet.builder()
//                .issuer("login")
//                .subject(user.get().getUsername().toString())
//                .issuedAt(now)
//                .expiresAt(now.plusSeconds(expir))
//                .claim("scope", scopes)
//                .build();
//
//        return new LoginDTO(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(), expir);
//    }
//
//
//}
//
