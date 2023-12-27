package com.example.springbootdepartement.Mapper;

import com.example.springbootdepartement.DTO.DepartementDTO;
import com.example.springbootdepartement.Models.Departement;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepartementMapper {
    public static Departement mapToDepartement(DepartementDTO dep_dto) {

        Departement departement = Departement.builder()
                .id_dept(dep_dto.getId_dept())
                .nom_dept(dep_dto.getNom_dept())
                .employes(Optional.ofNullable(dep_dto.getEmployes())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(employeDTO -> EmployeMapper.mapToEmploye(employeDTO))
                        .collect(Collectors.toList()))
                .build();
        return departement;
    }

    public static DepartementDTO mapToDepartementDTO(Departement dep) {
        DepartementDTO dep_dto = DepartementDTO.builder()
                .id_dept(dep.getId_dept())
                .nom_dept(dep.getNom_dept())
                .employes(Optional.ofNullable(dep.getEmployes())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(employe -> EmployeMapper.mapToEmployeDTO(employe))
                        .collect(Collectors.toList()))
                .build();
        return dep_dto;
    }
}
