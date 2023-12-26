package com.example.springbootdepartement.Mapper;


import com.example.springbootdepartement.DTO.EmployeDTO;
import com.example.springbootdepartement.Models.Employe;

public class EmployeMapper {

    public static Employe mapToEmploye(EmployeDTO emp_dto) {
        Employe employe = Employe.builder()
                .id_emp(emp_dto.getId_emp())
                .nom(emp_dto.getNom())
                .salaire(emp_dto.getSalaire())
                .ref_dep(DepartementMapper.mapToDepartement(emp_dto.getRef_dep()))
                .build();
        return employe;
    }

    public static EmployeDTO mapToEmployeDTO(Employe emp) {
        EmployeDTO emp_dto = EmployeDTO.builder()
                .id_emp(emp.getId_emp())
                .nom(emp.getNom())
                .salaire(emp.getSalaire())
                .ref_dep(DepartementMapper.mapToDepartementDTO(emp.getRef_dep()))
                .build();
        return emp_dto;
    }
}
