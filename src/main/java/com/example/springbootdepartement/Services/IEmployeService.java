package com.example.springbootdepartement.Services;


import com.example.springbootdepartement.DTO.DepartementDTO;
import com.example.springbootdepartement.DTO.EmployeDTO;

import java.util.List;

public interface IEmployeService {
    List<EmployeDTO> getAll();
    void add(EmployeDTO employe);
    void delete(int id);
    void delete(EmployeDTO employe);
    EmployeDTO findEmploye(int id);
    void update(EmployeDTO employe);
    List<EmployeDTO> searchDepartement(String query);
}
