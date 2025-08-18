//package com.projeto.tcc.service;
//
//
//import com.projeto.tcc.dto.LoginInformacoes;
//import com.projeto.tcc.dto.entrada.FuncionarioDTO;
//import com.projeto.tcc.dto.entrada.LoginDTO;
//import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
//import com.projeto.tcc.repository.FuncionarioRepository;
//import com.projeto.tcc.repository.RoleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.stream.Collectors;
//@RequiredArgsConstructor
//@Service
//public class TokenService {
//
//    private final FuncionarioRepository repository;
//    private final  BCryptPasswordEncoder passwordEncoder;
//    private final  JwtEncoder encoder;
//    private final  RoleRepository roleRepository;
//
//    public LoginDTO criarToken(LoginInformacoes funcionarioAcces){
//        var funcionario = repository.findByMatricula(funcionarioAcces.matricula());
//
//        if(funcionario.isEmpty() || !funcionario.get().verificarSenha(funcionarioAcces, passwordEncoder)){
//            throw new NaoRegistradoExcpetion("Funcionário não encontrado");
//        }
//
//        var now = Instant.now();
//        var expir = 300L;
//
//
//        var scopes = funcionario.get().getRoles()
//                .stream()
//                .map(role -> role.getName().toUpperCase())
//                .toList();
//
//        var claims = JwtClaimsSet.builder()
//                .issuer("login")
//                .subject(funcionario.get().getNome().toString())
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
