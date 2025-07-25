package com.projeto.tcc.entities;

import com.projeto.tcc.dto.entrada.UsuarioDTO;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Entity(name="tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false, unique = true)
	private String email;

	@ManyToMany
	@JoinTable(
			name = "tb_user_roles",
			joinColumns =  @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;

	public Usuario(Long id, String senha, String username, String email) {
		
		this.id = id;
		this.senha = senha;
		this.username = username;
		this.email = email;
	}


	public Usuario() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean verificarSenha(UsuarioDTO userAcces, BCryptPasswordEncoder passwordEncoder){
		return passwordEncoder.matches(userAcces.senha(), this.senha);

	}
}
