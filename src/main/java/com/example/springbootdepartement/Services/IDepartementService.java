package com.example.springbootdepartement.Services;

import com.example.springbootdepartement.DTO.DepartementDTO;
import com.example.springbootdepartement.Models.Departement;

import java.util.List;
import java.util.Optional;

public interface IDepartementService {
    List<DepartementDTO> getAll();
    void add(DepartementDTO departement);
    void delete(DepartementDTO departement);
    DepartementDTO findDepartement(int id);
    void update(DepartementDTO departement);
    List<DepartementDTO> searchDepartement(String query);
    Optional<DepartementDTO> lePlusGrandDepartement(List<DepartementDTO> departements);
    double masseSalariale(List<DepartementDTO> departements);
}
