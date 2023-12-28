package com.example.springbootdepartement.DTO;

import com.example.springbootdepartement.Models.Departement;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Le nom doit avoir que des caractères")
    @Size(min=3,max =30 ,message = "le nom doit avoit entre 3 et 30 caractères")
    private String nom ;

    @NotNull(message = "Veuillez spécifier un salaire pour l'employé")
    @Positive(message = "Le salaire doit étre un nombre positive")
    @Digits(integer = 10, fraction = 0, message = "Le Salaire doit contenir que des nombres")
    private double salaire;

    private Departement ref_dep;
}
