package com.example.springbootdepartement.Repositories;

import com.example.springbootdepartement.Models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends JpaRepository<Departement,Integer> {

    Optional<Departement> findById(int id);
   /*
    @Query("SELECT d from Departement d where d.id_dept=:id")
    Optional<Departement> findDepartementById(Double id);
    */
    @Query("SELECT d from Departement d where d.nom_dept LIKE CONCAT('%',:query,'%') ")
    List<Departement> searchDepartement(String query);
}
