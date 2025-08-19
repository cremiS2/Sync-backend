package com.projeto.tcc.entities;

import com.projeto.tcc.dto.entrada.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Entity(name="tb_usuario")
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false, unique = true)
	private String email;

	private String name;

    @OneToOne(mappedBy = "usuario")
    private Funcionario funcionario;


	public boolean verificarSenha(UsuarioDTO userAcces, BCryptPasswordEncoder passwordEncoder){
		return passwordEncoder.matches(userAcces.senha(), this.senha);

	}
}
