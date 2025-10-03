package com.projeto.tcc.entities;

import com.projeto.tcc.dto.entry.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Entity(name="tb_user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, unique = true)
	private String email;

	private String username;

	@ManyToMany
	@JoinTable( name = "tb_user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private Employee employee;


	public boolean verificarSenha(UserDTO userAcces, BCryptPasswordEncoder passwordEncoder){
		return passwordEncoder.matches(userAcces.password(), this.password);

	}
}
