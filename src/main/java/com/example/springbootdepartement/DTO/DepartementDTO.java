package com.example.springbootdepartement.DTO;

import com.example.springbootdepartement.Models.Employe;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DepartementDTO {
    private int id_dept ;
    @NotEmpty(message = "Le nom du département ne peut pas etre vide")
    private String nom_dept;

    private List<EmployeDTO> employes;
}
