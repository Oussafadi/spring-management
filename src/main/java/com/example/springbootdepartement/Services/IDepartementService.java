package com.example.springbootdepartement.Services;

import com.example.springbootdepartement.Models.Departement;

import java.util.List;
import java.util.Optional;

public interface IDepartementService {
    List<Departement> getAll();
    void add(Departement departement);
    void delete(Departement departement);
    Departement findDepartement(Double id);
}
