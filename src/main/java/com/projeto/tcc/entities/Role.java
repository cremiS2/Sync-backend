package com.projeto.tcc.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

@Entity
@Table(name = "tb_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role(String name){};

    public Role() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Values{
        ADMIN(1L),
        GERENTE(2L),
        OPERADOR(3L);

        private Long value;

        Values(Long value) {
            this.value = value;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }


}
