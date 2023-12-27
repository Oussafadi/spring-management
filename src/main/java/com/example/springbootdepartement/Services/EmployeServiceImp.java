package com.example.springbootdepartement.Services;

import com.example.springbootdepartement.DTO.EmployeDTO;
import com.example.springbootdepartement.Mapper.DepartementMapper;
import com.example.springbootdepartement.Mapper.EmployeMapper;
import com.example.springbootdepartement.Models.Departement;
import com.example.springbootdepartement.Models.Employe;
import com.example.springbootdepartement.Repositories.DepartementRepository;
import com.example.springbootdepartement.Repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImp implements IEmployeService{

    private EmployeRepository repository;
    @Autowired
    public EmployeServiceImp(EmployeRepository repository){
        this.repository = repository;
    }

    @Override
    public List<EmployeDTO> getAll() {
        return repository.findAll().stream()
                .map(employe -> EmployeMapper.mapToEmployeDTO(employe))
                .collect(Collectors.toList());
    }

    @Override
    public void add(EmployeDTO employe) {
       repository.save(EmployeMapper.mapToEmploye(employe));
    }

    @Override
    public void delete(int id) {
        //repository.delete(EmployeMapper.mapToEmploye(employe));
        repository.deleteById(id);
    }

    @Override
    public void delete(EmployeDTO employe){
        repository.delete(EmployeMapper.mapToEmploye(employe));
    }

    @Override
    public EmployeDTO findEmploye(int id) {
        Optional<Employe> e = repository.findById(id);
        return e.map(EmployeMapper::mapToEmployeDTO).orElse(null);
    }

    @Override
    public void update(EmployeDTO employe) {
        repository.save(EmployeMapper.mapToEmploye(employe));
    }

    @Override
    public List<EmployeDTO> searchDepartement(String query) {
        List<Employe>employes = repository.searchEmploye(query);
        return employes.stream()
                .map(emp -> EmployeMapper.mapToEmployeDTO(emp))
                .collect(Collectors.toList());
    }
}
