package com.projeto.tcc.config;

import com.projeto.tcc.entities.Role;
import com.projeto.tcc.entities.Usuario;
import com.projeto.tcc.repository.RoleRepository;
import com.projeto.tcc.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class UserAdminConfig implements CommandLineRunner {

    private BCryptPasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UsuarioRepository usuarioRepository;

    public UserAdminConfig(BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        usuarioRepository.findByEmail("encodersAdmin@gmail.com").ifPresentOrElse(
                user -> System.out.println("Usuário admin já cadastrado"),
                () -> {
                    var admin = new Usuario();
                    admin.setEmail("encodersAdmin@gmail.com");
                    admin.setSenha(passwordEncoder.encode("12345"));
                    admin.setUsername("encoders");
                    admin.setRoles(Set.of(roleRepository.findByName(Role.Values.ADMIN.name())));
                    usuarioRepository.save(admin);
                }
        );

    }
}
