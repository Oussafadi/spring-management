package com.example.springbootdepartement.DTO;

import com.example.springbootdepartement.Models.Departement;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeDTO {
    private int id_emp ;

    @NotEmpty(message = "Le nom de l'employé ne peut pas etre vide")
    @Size(min=1,max =30 ,message = "le nom doit avoit entre 1 et 30 caractères")
    private String nom ;

    @NotNull(message = "Veuillez spécifier un salaire pour l'employé")
    @Positive(message = "Le salaire doit étre un nombre positive")
    private double salaire;

    private DepartementDTO ref_dep;
}
