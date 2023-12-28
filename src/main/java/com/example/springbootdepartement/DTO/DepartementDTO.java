package com.example.springbootdepartement.DTO;

import com.example.springbootdepartement.Models.Employe;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartementDTO {
    private int id_dept ;

    @NotEmpty(message = "Le nom du département ne peut pas etre vide")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Le nom doit avoir que des caractères")
    @Size(min = 3 , message = "Le nom du département doit avoir au moins 3 caractère")
    private String nom_dept;

    private List<EmployeDTO> employes;
}
