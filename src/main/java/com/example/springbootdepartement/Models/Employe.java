package com.example.springbootdepartement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employes")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_emp ;

    private String nom ;
    private double salaire;

    @ManyToOne
    @JoinColumn(name="id_dep")
    private Departement ref_dep;
}
