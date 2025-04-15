package com.projeto.tcc.service;

import com.projeto.tcc.dto.UsuarioDTO;
import com.projeto.tcc.entities.Role;
import com.projeto.tcc.entities.Usuario;
import com.projeto.tcc.exceptions.EmailJaCadastradoException;
import com.projeto.tcc.exceptions.UsuarioNaoEncontradoException;
import com.projeto.tcc.repository.RoleRepository;
import com.projeto.tcc.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private Map<Long, Usuario> usuariosCache = new HashMap<>();

    public UserService(UsuarioRepository usuarioRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = encoder;
    }

    private Usuario procurarUserCache(Long id) {
        return usuariosCache.computeIfAbsent(id, chave ->
                usuarioRepository.findById(chave).orElse(null)
        );
    }

    @Transactional
    public void adicionarUser(UsuarioDTO usuario) {
        var roles = roleRepository.findByName(Role.Values.BASIC.name());
        var user = usuarioRepository.findByEmail(usuario.email());

        if(user.isEmpty()){
            var newUsuario = new Usuario();
            newUsuario.setEmail(usuario.email());
            newUsuario.setUsername(usuario.username());
            newUsuario.setRoles(Set.of(roles));
            newUsuario.setSenha(passwordEncoder.encode(usuario.senha()));
            usuarioRepository.save(newUsuario);
            usuariosCache.put(newUsuario.getId(), newUsuario);
            System.out.println("Usuario adicionado no cache com sucesso");
        }else{
            throw new EmailJaCadastradoException("E-mail já cadastrado");
        }
    }

    public UsuarioDTO buscarPorId(Long id) {
        return new UsuarioDTO(procurarUserCache(id));
    }

    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());

    }

    public UsuarioDTO atualizar(UsuarioDTO user) {
        var usuario = procurarUserCache(user.id());
        if(usuario != null){
            if(user.username() != null && !user.username().equals(usuario.getUsername())){
                usuario.setUsername(user.username());
            }
            if(user.email() != null && !user.email().equals(usuario.getEmail())){
                usuario.setEmail(user.email());
            }
            if(user.senha() != null && !usuario.verificarSenha(user, passwordEncoder)){
                usuario.setSenha(passwordEncoder.encode(user.senha()));
            }

            usuarioRepository.save(usuario);
            usuariosCache.put(usuario.getId(), usuario);
            System.out.println("Usuario atualizado com sucesso");
            return new UsuarioDTO(usuario);

        }else{
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
