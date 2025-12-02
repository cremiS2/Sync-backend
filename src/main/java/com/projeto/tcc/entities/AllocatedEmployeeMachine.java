package com.projeto.tcc.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_allocation_employess_machine")
@Data
@EntityListeners(AuditingEntityListener.class)
public class AllocatedEmployeeMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employees_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @CreatedDate
    @Column(name = "allocation_date")
    private LocalDate allocationDate;


    @ManyToOne
    @JoinColumn(name = "changed_employee")
    private Employee changedEmployee;
}
