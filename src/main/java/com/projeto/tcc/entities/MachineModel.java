package com.projeto.tcc.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class MachineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_model_id")
    private Long id;
    private String modelName;
    private String modelDescription;

    @OneToMany(mappedBy = "machineModel", fetch = FetchType.LAZY)
    private List<Machine> machines;
}
