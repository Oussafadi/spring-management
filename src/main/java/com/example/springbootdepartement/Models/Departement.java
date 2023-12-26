package com.example.springbootdepartement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "departements ")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dept ;
    private String nom_dept;
    @OneToMany(mappedBy = "ref_dep",fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Employe> employes;
}
