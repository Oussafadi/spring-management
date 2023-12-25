package com.example.springbootdepartement.Services;

import com.example.springbootdepartement.Models.Departement;
import com.example.springbootdepartement.Repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImp implements IDepartementService{

     private DepartementRepository repository;
     @Autowired
    public DepartementServiceImp(DepartementRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Departement> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(Departement departement) {
        repository.save(departement);
    }

    @Override
    public void delete(Departement departement) {
        repository.delete(departement);
    }

    @Override
    public Departement findDepartement(Double id) {
       Optional<Departement> d = repository.findById(id);
       return d.orElse(null);
    }
}
