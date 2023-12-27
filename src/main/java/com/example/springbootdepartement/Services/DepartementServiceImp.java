package com.example.springbootdepartement.Services;

import com.example.springbootdepartement.DTO.DepartementDTO;
import com.example.springbootdepartement.Mapper.DepartementMapper;
import com.example.springbootdepartement.Models.Departement;
import com.example.springbootdepartement.Repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartementServiceImp implements IDepartementService{

     private DepartementRepository repository;
     @Autowired
    public DepartementServiceImp(DepartementRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DepartementDTO> getAll() {
         return repository.findAll().stream()
                 .map(departement -> DepartementMapper.mapToDepartementDTO(departement))
                 .collect(Collectors.toList());
    }

    @Override
    public void add(DepartementDTO departement) {
         repository.save(DepartementMapper.mapToDepartement(departement));
    }

    @Override
    public void delete(DepartementDTO departement) {
         repository.delete(DepartementMapper.mapToDepartement(departement));
    }

    @Override
    public DepartementDTO findDepartement(int id) {
       Optional<Departement> d = repository.findById(id);
       return d.map(DepartementMapper::mapToDepartementDTO).orElse(null);
    }

    @Override
    public void update(DepartementDTO departement) {
       repository.save(DepartementMapper.mapToDepartement(departement));
    }

    @Override
    public List<DepartementDTO> searchDepartement(String query) {
        List<Departement>departements = repository.searchDepartement(query);
        return departements.stream()
                .map(departement -> DepartementMapper.mapToDepartementDTO(departement))
                .collect(Collectors.toList());
    }
}
