package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;
    @Column(name= "first_name", nullable = false, length = 100)
    private String firstname;
    @Column(name= "last_name", nullable = false, length = 100)
    private String lastname;
    @Column(name= "email", unique = true, nullable = false)
    private String email;
    @Column(name= "phone_number",length = 25)
    private String phoneNumber;
    @Column(name= "hire_date", nullable = false)
    private LocalDate hireDate;
    @Column(name= "position", nullable = false)
    private String position;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="department_id",  nullable = false)
    private Department department;

    public UUID getDepartment() {
        return department.getId();
    }
}
